package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.popup.PopupNotification;

import java.util.ArrayList;
import java.util.List;

public class FocusingSampleActivity extends AppCompatActivity implements View.OnClickListener {


    ListView sampleListView;
    SimpleArrayAdapter simpleArrayAdapter;
    ArrayList<String> datas = new ArrayList<>();
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focusing_sample);

        sampleListView = (ListView)findViewById(R.id.someItemsListView);
        btnNext = (Button)findViewById(R.id.btnNext);


        datas.add("현금");
        datas.add("anjwl ");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");


        simpleArrayAdapter = new SimpleArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,datas);

        sampleListView.setAdapter(simpleArrayAdapter);
        sampleListView.post(new Runnable() {
            @Override
            public void run() {
                ListAdapter listAdapter = sampleListView.getAdapter();
                if(listAdapter == null)
                    return;
                int totalHeight = 0;
                int desiredWidth = View.MeasureSpec.makeMeasureSpec(sampleListView.getWidth(),View.MeasureSpec.AT_MOST); // 먼저 width를 구한다.
                for(int i = 0;i < listAdapter.getCount(); i++){
                    View listItem = listAdapter.getView(i,null,sampleListView);
                    listItem.measure(desiredWidth,View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) sampleListView.getLayoutParams();
                params.height = totalHeight + (sampleListView.getDividerHeight() * (listAdapter.getCount() -1 ) + sampleListView.getPaddingBottom() + sampleListView.getPaddingTop());
                sampleListView.setLayoutParams(params);
                sampleListView.requestLayout();
            }
        });


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnNext:
                PopupNotification notification = new PopupNotification(this);

                break;
        }
    }

    private class SimpleArrayAdapter extends ArrayAdapter<String>{

        public SimpleArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public SimpleArrayAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }
}
