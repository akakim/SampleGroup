package com.example.sslab.samplegroupapplication.openfireSample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.openfireSample.entity.Account;
import com.example.sslab.samplegroupapplication.openfireSample.entity.AuthenticationToken;
import com.example.sslab.samplegroupapplication.openfireSample.entity.RestApiClient;
import com.example.sslab.samplegroupapplication.openfireSample.entity.UserInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class OpenfireClientActivity extends AppCompatActivity implements View.OnClickListener, Listener,ErrorListener{


    Button sendButton;
    TextView resultTextView;
    ListView propertyList;
    ArrayAdapter arrayAdapter;
    RestApiClient client;
    Account account;

    ArrayList<String> items = new ArrayList<>();
    AuthenticationToken token;
    UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openfire_client);
        sendButton = ( Button ) findViewById( R.id.sendButton );
        sendButton.setText("get Properties");
        resultTextView = ( TextView )findViewById( R.id.resultTextView );
        sendButton.setOnClickListener( this );

        propertyList = ( ListView )findViewById( R.id.propertyList );
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        propertyList.setAdapter( arrayAdapter );


        token = new AuthenticationToken ("admin","root");
        account = new Account(token,"192.168.0.101","9090");

        client = new RestApiClient( this );

        client.account(account);

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId () ){
            case R.id.sendButton :
                client.getProperties( this , this );

                 break;

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
//        Toast.makeText(this,"error : "+ error.toString(),Toast.LENGTH_SHORT).show();
        NetworkResponse errorResponse =
                new NetworkResponse(
                        error.networkResponse.statusCode,
                        error.networkResponse.data,
                        error.networkResponse.headers,
                        error.networkResponse.notModified,
                        error.networkResponse.networkTimeMs
                );

        items.clear();


        items.add("statusCode : " + error.networkResponse.statusCode );
        items.add("rawDataSize : " + error.networkResponse.data.length );

        Set<String> set = error.networkResponse.headers.keySet();
        Iterator<String> iter = set.iterator();
        while(iter.hasNext()){
            String key = iter.next();
            items.add( "key : "+ key +" value : "  + error.networkResponse.headers.get(key) );
            Log.d("getKeys ",key );
        }

        arrayAdapter.notifyDataSetChanged();

//        if( error.networkResponse != null)
//            resultTextView.setText("response Not Null error : " + error.networkResponse);
//        else
//            resultTextView.setText("error : " + error.getMessage() );
    }

    @Override
    public void onResponse(Object response) {
//        Toast.makeText(this,"response : "+ response.toString(),Toast.LENGTH_SHORT).show();

        if(response instanceof UserInfo ){
            UserInfo userInfo = new UserInfo( (UserInfo) response );
        }
//        resultTextView.setText( "response : "+ response.toString() );

//        NetworkResponse errorResponse = (NetworkResponse )response ;
//
//        items.clear();
//
//
//        items.add("statusCode : " + errorResponse.statusCode );
//        items.add("rawDataSize : " + errorResponse.data.length );
//
//        Set<String> set = errorResponse.headers.keySet();
//        Iterator<String> iter = set.iterator();
//        while(iter.hasNext()){
//            String key = iter.next();
//            items.add( "key : "+ key +" value : "  + errorResponse.headers.get(key) );
//            Log.d("getKeys ",key );
//        }
//
//        arrayAdapter.notifyDataSetChanged();
    }
}
