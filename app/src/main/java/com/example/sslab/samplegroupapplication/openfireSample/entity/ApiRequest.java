package com.example.sslab.samplegroupapplication.openfireSample.entity;

import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.example.sslab.samplegroupapplication.openfireSample.wrapper.AuthenticationMode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by SSLAB on 2017-05-24.
 */

public class ApiRequest<T> extends JsonRequest<T> {
    private Class<T> clazz;
    private AuthenticationToken token;
    private int method;


    public ApiRequest(int method, String url, String jsonRequest, Response.Listener<T> listener,
                      Response.ErrorListener errorListener, Class<T> clazz, AuthenticationToken token) {
        super(method, url.replace(" ", "%20"), jsonRequest, listener, errorListener);
        this.clazz = clazz;
        this.token = token;
        this.method = method;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        if (token.getAuthMode() == AuthenticationMode.SHARED_SECRET_KEY) {
            headers.put("Authorization", token.getSharedSecretKey());
        } else if (token.getAuthMode() == AuthenticationMode.BASIC_AUTH) {
            String base64 = Base64.encodeToString((token.getUsername() + ":" + token.getPassword()).getBytes(), Base64.DEFAULT);
            headers.put("Authorization", "Basic " + base64);
        }
        headers.put("Accept", "application/json");
        return headers;

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString;
            if( method == Method.GET ){

                Set<String> keys = response.headers.keySet();
                Iterator<String> iter = keys.iterator();

                while(iter.hasNext() != false){
                    String next = iter.next();
                    Log.d(getClass().getSimpleName(),"key : "+ next + " value : " + response.headers.get(next));
                }
                jsonString = new String(response.data, HttpHeaderParser.parseCharset( response.headers, PROTOCOL_CHARSET ));
                Log.d(getClass().getSimpleName(),"GET Json " + jsonString);
            } else {
                JSONObject responseJson = new JSONObject();
                responseJson.put("code", response.statusCode);
                responseJson.put("message", response.headers.toString());
                jsonString = responseJson.toString();
                Log.d(getClass().getSimpleName(),"jsonString" + jsonString);
            }

            // XML상의 자료를 어떻게 설정할건지에 대한 설정
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
//            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 결과 값을 리턴한다.
            // 메퍼를 이용해 값을 읽거나 cacheheader로 뭔갈 한다.
            // cache는 이 값을 갱신해야할지 아니면 그냥 줄지를 결정한다.
            // 구체적인 분류과정은 나중에.///

            return Response.success(mapper.readValue(jsonString, clazz), HttpHeaderParser.parseCacheHeaders(response));
//            return Response.success((T) response, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
        catch (JsonParseException e) {
            return Response.error(new ParseError(e));
        }
        catch (JsonMappingException e) {
            return Response.error(new ParseError(e));
        }
        catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }


    // 유저 세션 시스템 property

}
