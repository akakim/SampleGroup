package com.example.sslab.samplegroupapplication.openfireSample.entity;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by SSLAB on 2017-05-24.
 */

public class RestApiClient {

    private AuthenticationToken token;
    private String baseUrl;
    Context context;
    private Gson gson;
    private ObjectMapper mapper;
    private OnSuccess onSuccess;
    private OnFailure onFailure;


    private ObjectMapper getConfiguredMapper() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public RestApiClient(Context context, Account account) {
        this.context = context;
        this.token = account.getAuthenticationToken();
        this.baseUrl = "http://" + account.getHost() + ":" + account.getPort();
        this.gson = new Gson();
        this.mapper = getConfiguredMapper();
    }

    public RestApiClient(Context context) {
        this.context = context;
    }

    public RestApiClient account(Account account) {
        this.token = account.getAuthenticationToken();
        this.baseUrl = "http://" + account.getHost() + ":" + account.getPort() ;
        this.gson = new Gson();
        this.mapper = getConfiguredMapper();
        return this;
    }

    public RestApiClient success(OnSuccess onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    public RestApiClient failure(OnFailure onFailure) {
        this.onFailure = onFailure;
        return this;
    }


    public void getProperties(Response.Listener<SystemProperties> listener, Response.ErrorListener error) {
        Request<SystemProperties> request = new ApiRequest<>(Request.Method.GET, baseUrl +"/plugins/restapi/v1/" + "system/properties/",
                null, listener, error, SystemProperties.class, token);
        try {
            Set<String> keys = request.getHeaders().keySet();
            Iterator<String> iter = keys.iterator();

            while (iter.hasNext() != false) {
                String next = iter.next();
                Log.d(getClass().getSimpleName(), "key : " + next + " value : " + request.getHeaders().get(next));
            }
            Volley.newRequestQueue(context).add(request);
        }catch (AuthFailureError e){
            e.printStackTrace();
        }
    }

    // response는 interface에서 알아서 받아준다. 굳이 신경을 안써도 되긴하네. Volley에서 뭐가 되었든 response를 주게 되니까..

    public void getUserInfo(Response.Listener<UserInfo> listener, Response.ErrorListener errorListener){
        Request<UserInfo> request = new ApiRequest<>(Request.Method.GET,baseUrl+"/AndroidPushTestServer/getTestUserInfo", null , listener, errorListener, UserInfo.class, token );
        Volley.newRequestQueue(context).add(request);
    }
}
