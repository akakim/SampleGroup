package com.example.sslab.samplegroupapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.data.activityList;
import com.example.sslab.samplegroupapplication.imageFileView.ModeSettingActivity;
import com.example.sslab.samplegroupapplication.samples.*;
import com.example.sslab.samplegroupapplication.util.UncaughtExceptionHandlerApplication;
import com.example.sslab.samplegroupapplication.webview.*;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    final String TAG = getClass().getSimpleName();
    ArrayList<activityList> items = new ArrayList<>();
    ArrayList<activityList> filteredItems = new ArrayList<>();
    ListViewAdapter adapter;
    ListView listView;
    EditText searchEditText;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        // searchSample 실패 .. ㅠ
//        searchEditText = (EditText) findViewById(R.id.searchEditText);
//        searchEditText.setFocusable(false);
//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                MainActivity.this.adapter.getFilter().filter(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication(mUncaughtExceptionHandler,this));


        items.add(new activityList(ThreadMessageQueueSample.class.getSimpleName(), ThreadMessageQueueSample.class));                    // 스레드와 큐.. 즉, 동기화된 네트워크 통신을 하고싶었다.
        items.add(new activityList(Sample02Activity.class.getSimpleName(), Sample02Activity.class));                                    // 간단한 버튼 이나, 커스터마이징 뷰목록들
        items.add(new activityList(CustomTextViewActivity.class.getSimpleName(),CustomTextViewActivity.class));                         // 간단한 버튼 이나, 커스터마이징 뷰목록들
        items.add(new activityList(GridViewSample.class.getSimpleName(),GridViewSample.class));                                         //  그리드 뷰 셈플
        items.add(new activityList(BitmapSamplesActivity.class.getSimpleName(),BitmapSamplesActivity.class));
        /*
         * listview의 크기를 측정하는 로직이 들어갔다.
         *  listview가 ScrollView안에 들어가지않는다면 필요가 없다.
         */
        items.add(new activityList(ProgramaticallySettingLayoutHeightActivity.class.getSimpleName(),ProgramaticallySettingLayoutHeightActivity.class));
        /**
         * 위와 마찬가지이다.
         */
        items.add(new activityList(ScrollViewInsideListViewAcitivity.class.getSimpleName(),ScrollViewInsideListViewAcitivity.class));
        items.add(new activityList(ExpandableListViewSampleActivity.class.getSimpleName(),ExpandableListViewSampleActivity.class));     // expandable list view 의 샘플 .
        items.add(new activityList(SwipeRefreshBottomLayoutActivity.class.getSimpleName(),SwipeRefreshBottomLayoutActivity.class));     // swipe 이벤트가 발생하면
        items.add(new activityList(FocusingSampleActivity.class.getSimpleName(),FocusingSampleActivity.class));                         // requestLayout을 응용한 focusing 기능, 또한 , VISIBILITY를
        items.add(new activityList(FocusingLinearActivity.class.getSimpleName(),FocusingLinearActivity.class));                         // LinearLayout일지라도 requestLayout이될 것인가.
        items.add(new activityList(URLConnectionSampleActivity.class.getSimpleName(),URLConnectionSampleActivity.class));               // 새로운 네트워크모듈을 생성
        items.add(new activityList(DialogSamplesActivity.class.getSimpleName(),DialogSamplesActivity.class));                           // 새로운 네트워크모듈을 생성
        items.add(new activityList(AnimationActivity.class.getSimpleName(),AnimationActivity.class));                                   // 새로운 네트워크모듈을 생성
        items.add(new activityList(DilatingDotActivity.class.getSimpleName(),DilatingDotActivity.class));                               // 새로운 네트워크모듈을 생성
        items.add(new activityList(SNSSampleActivity.class.getSimpleName(),SNSSampleActivity.class));
        items.add(new activityList(SVGSampleActivity.class.getSimpleName(),SVGSampleActivity.class));
        items.add(new activityList(WebViewActivity.class.getSimpleName() , WebViewActivity.class ));
        items.add(new activityList(WebViewSampleGroupActivity.class.getSimpleName() , WebViewSampleGroupActivity.class ));
        items.add(new activityList(ModeSettingActivity.class.getSimpleName() , ModeSettingActivity.class ));
//        items.add(new activityList(MyCloudProviderActivity.class.getSimpleName(),MyCloudProviderActivity.class));                       // https://developer.android.com/samples/StorageProvider


        filteredItems.addAll(items);
//        items.add(new activityList(BitmapSamplesActivity.class.getSimpleName(),BitmapSamplesActivity.class));
        adapter = new ListViewAdapter(this, android.R.layout.simple_list_item_1, filteredItems);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activityList item = (activityList) adapterView.getItemAtPosition( i );
                Intent intent = new Intent(getApplicationContext(),item.getaClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG,"keyCode :"+keyCode +"KeyEvent" + event.getAction());

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            Log.d(TAG,keyCode + "KEY Menu Pressed");
        }else if (keyCode == KeyEvent.KEYCODE_HOME ){
            Log.d(TAG,keyCode + "KEY Home Pressed");

        }else if(keyCode == KeyEvent.KEYCODE_BACK ){
            Log.d(TAG,keyCode + "KEY Back Pressed");
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void responseData(JSONObject jsonObject) {

    }

    private class ListViewAdapter extends ArrayAdapter<activityList> implements Filterable{

        public ListViewAdapter(Context context, int resource, List<activityList> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = super.getView(position, convertView, parent);
            final activityList item = getItem(position);
            TextView textView = (TextView)v.findViewById(android.R.id.text1);
            textView.setText(item.getActivityName());
            return v;
        }

        @NonNull
        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {


                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults results = new FilterResults();
                    ArrayList<activityList> FilteredArrayNames = new ArrayList<activityList>();


                    charSequence = charSequence.toString().toLowerCase();

                    for (int i = 0; i < items.size(); i++) {
                        activityList item = items.get(i);
                        if (item.getActivityName().toLowerCase().startsWith(charSequence.toString()))  {
                            FilteredArrayNames.add(item);
                        }
                    }

                    results.count = FilteredArrayNames.size();
                    results.values = FilteredArrayNames;
                    Log.d("VALUES", results.values.toString());


                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredItems = (ArrayList<activityList>) filterResults.values;

                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            listView.invalidate();
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            };
            return filter;
        }

    }
}
