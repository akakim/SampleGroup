package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

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

public class EfficientListActivity extends AppCompatActivity {

    ArrayList<activityList> items = new ArrayList<>();

    ListView efficientListView;
    ActivityViewListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efficient_list);

        efficientListView = ( ListView ) findViewById( R.id.EfficientListView );

        adapter = new ActivityViewListAdapter(this, -1, items);
        efficientListView.setAdapter(adapter);
        efficientListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent (EfficientListActivity.this, items.get(position).getaClass());
                startActivity( i );
            }
        });
        items.add(new activityList(LooperActivity.class.getSimpleName(),LooperActivity.class));                         // 작업자 스레드를 발생시켜서
        items.add(new activityList(HandlerExampleActivity.class.getSimpleName(),HandlerExampleActivity.class));
        items.add(new activityList(HandlerCallbackActivity.class.getSimpleName(),HandlerCallbackActivity.class));
        items.add(new activityList(MessengerOnewayActivity.class.getSimpleName(),MessengerOnewayActivity.class));       // 서비스를 이용한 주건희 받건희
        items.add(new activityList(ThreadRetainActivity.class.getSimpleName(),ThreadRetainActivity.class));         // 스레드의상태를 유지해주는 예제
        items.add(new activityList(ThreadRetainWithFragmentActivity.class.getSimpleName(),ThreadRetainWithFragmentActivity.class));         // 스레드의상태를 유지해주는 예제
        items.add(new activityList(SharedPreferencesActivity.class.getSimpleName(),SharedPreferencesActivity.class));         // 스레드의상태를 유지해주는 예제
        items.add(new activityList(ChainedNetworkActivity.class.getSimpleName(),ChainedNetworkActivity.class));         // 네트워크 시나리오 셈플

        adapter.notifyDataSetChanged();
    }
}
