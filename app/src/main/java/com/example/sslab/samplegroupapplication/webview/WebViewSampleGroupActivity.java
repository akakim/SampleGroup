package com.example.sslab.samplegroupapplication.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.ArrayList;

public class WebViewSampleGroupActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<activityList> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = ( ListView )findViewById( R.id.listView);

        items.add(new activityList (javaScriptSampleActivity.class.getSimpleName(), WebViewVideoSample.class));
//        items.add( new activityList(WebViewVideoSample.class.getSimpleName(), WebViewVideoSample.class)); 에러있음 수정해야됨 .
        


        ActivityViewListAdapter adapter = new ActivityViewListAdapter(this,android.R.layout.simple_list_item_1,items );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent( WebViewSampleGroupActivity.this , items.get(position).getaClass() );
                i.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP );
                startActivity( i );
            }
        });
    }
}
