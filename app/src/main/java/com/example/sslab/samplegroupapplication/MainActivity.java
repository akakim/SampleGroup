package com.example.sslab.samplegroupapplication;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.bitmap.GlidePicassoActivity;
import com.example.sslab.samplegroupapplication.common.*;
import com.example.sslab.samplegroupapplication.data.activityList;
import com.example.sslab.samplegroupapplication.imageFileView.ModeSettingActivity;
import com.example.sslab.samplegroupapplication.openfireSample.OpenfireClientActivity;
import com.example.sslab.samplegroupapplication.samples.*;
import com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx.EfficientListActivity;
import com.example.sslab.samplegroupapplication.samples.InspectorSamples.InspectorSampleListApp;
import com.example.sslab.samplegroupapplication.samples.mvp.MvpLoginActivity;
import com.example.sslab.samplegroupapplication.samples.mvp.MvpMainActivity;
import com.example.sslab.samplegroupapplication.webview.*;
import com.example.sslab.samplegroupapplication.widget.CustomListView;
import com.example.sslab.samplegroupapplication.widget.CustomTextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;

public class MainActivity extends BaseActivity {

    final String TAG = getClass().getSimpleName();
    ArrayList<activityList> items = new ArrayList<>();
    ArrayList<activityList> filteredItems = new ArrayList<>();
    ActivityViewListAdapter  adapter;
    CustomListView listView;
    EditText searchEditText;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;


    public Handler handler = new Handler();

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate getName ", GlobalApplication.getCurrentActivity().getClass().getSimpleName());
        setContentView(R.layout.activity_main);
        listView = (CustomListView) findViewById(R.id.list);



        items.add(new activityList(ThreadMessageQueueSample.class.getSimpleName(),  ThreadMessageQueueSample.class));                    // 스레드와 큐.. 즉, 동기화된 네트워크 통신을 하고싶었다.
        items.add(new activityList(Sample02Activity.class.getSimpleName(),          Sample02Activity.class));                                    // 간단한 버튼 이나, 커스터마이징 뷰목록들
        items.add(new activityList(CustomTextViewActivity.class.getSimpleName(),    CustomTextViewActivity.class));                         // 간단한 버튼 이나, 커스터마이징 뷰목록들
        items.add(new activityList(GridViewSample.class.getSimpleName(),            GridViewSample.class));                                         //  그리드 뷰 셈플
        items.add(new activityList(BitmapSamplesActivity.class.getSimpleName(),     BitmapSamplesActivity.class));
        /*
         * listview의 크기를 측정하는 로직이 들어갔다.
         *  listview가 ScrollView안에 들어가지않는다면 필요가 없다.
         */
        items.add(new activityList(ProgramaticallySettingLayoutHeightActivity.class.getSimpleName(),ProgramaticallySettingLayoutHeightActivity.class));
        /**
         * 위와 마찬가지이다.
         */

        items.add(new activityList(ScrollViewInsideListViewAcitivity.class.getSimpleName(), ScrollViewInsideListViewAcitivity.class));
        items.add(new activityList(ExpandableListViewSampleActivity.class.getSimpleName(),  ExpandableListViewSampleActivity.class));     // expandable list view 의 샘플 .
        items.add(new activityList(SwipeRefreshBottomLayoutActivity.class.getSimpleName(),  SwipeRefreshBottomLayoutActivity.class));     // swipe 이벤트가 발생하면
        items.add(new activityList(FocusingSampleActivity.class.getSimpleName(),    FocusingSampleActivity.class));                         // requestLayout을 응용한 focusing 기능, 또한 , VISIBILITY를
        items.add(new activityList(FocusingLinearActivity.class.getSimpleName(),    FocusingLinearActivity.class));                         // LinearLayout일지라도 requestLayout이될 것인가.
        items.add(new activityList(URLConnectionSampleActivity.class.getSimpleName(),URLConnectionSampleActivity.class));               // 새로운 네트워크모듈을 생성
        items.add(new activityList(DialogSamplesActivity.class.getSimpleName(),     DialogSamplesActivity.class));                           // 새로운 네트워크모듈을 생성
        items.add(new activityList(AnimationActivity.class.getSimpleName(),         AnimationActivity.class));                                   // 새로운 네트워크모듈을 생성
        items.add(new activityList(DilatingDotActivity.class.getSimpleName(),       DilatingDotActivity.class));                               // 새로운 네트워크모듈을 생성
        items.add(new activityList(SNSSampleActivity.class.getSimpleName(),         SNSSampleActivity.class));                              // SNS 샘플들 .
        items.add(new activityList(SVGSampleActivity.class.getSimpleName(),         SVGSampleActivity.class));
        items.add(new activityList(WebViewActivity.class.getSimpleName() ,          WebViewActivity.class ));
        items.add(new activityList(WebViewSampleGroupActivity.class.getSimpleName() , WebViewSampleGroupActivity.class ));
        items.add(new activityList(ModeSettingActivity.class.getSimpleName() ,      ModeSettingActivity.class ));
        items.add(new activityList(TabOrderringSample.class.getSimpleName() ,       TabOrderringSample.class ));
        items.add(new activityList(InspectorSampleListApp.class.getSimpleName() ,   InspectorSampleListApp.class ));
        items.add(new activityList(LambdaExpressionActivity.class.getSimpleName() , LambdaExpressionActivity.class ));
        items.add(new activityList(EfficientListActivity.class.getSimpleName() ,    EfficientListActivity.class ));
        items.add(new activityList(OpenfireClientActivity.class.getSimpleName() ,   OpenfireClientActivity.class ));
        items.add(new activityList(CustomWidgetListActivity.class.getSimpleName() , CustomWidgetListActivity.class ));
        items.add(new activityList(MvpMainActivity.class.getSimpleName() ,          MvpMainActivity.class ));
        items.add(new activityList(MvpLoginActivity.class.getSimpleName() ,         MvpLoginActivity.class ));
        items.add(new activityList(GlidePicassoActivity.class.getSimpleName() ,         GlidePicassoActivity.class ));
        items.add(new activityList(DiffUtilActivity.class.getSimpleName() ,         DiffUtilActivity.class ));

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                filteredItems.addAll(items);
//                adapter.notifyDataSetChanged();
//            }
//        },5000);

//        items.add(new activityList(MyCloudProviderActivity.class.getSimpleName(),MyCloudProviderActivity.class));                       // https://developer.android.com/samples/StorageProvider


        filteredItems.addAll(items);
        items.clear();

//        items.add(new activityList(BitmapSamplesActivity.class.getSimpleName(),BitmapSamplesActivity.class));
        adapter = new ActivityViewListAdapter(this, -1, filteredItems);


        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activityList item = (activityList) adapterView.getItemAtPosition( i );
                Intent intent = new Intent(getApplicationContext(),item.getaClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
//                overridePendingTransition(R.anim.anim_slide_in_bottom,R.anim.anim_slide_out_top);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch ( msg.what){
                    case 0:
                        Toast.makeText(getApplicationContext(),"FCM TOKEN Created",Toast.LENGTH_SHORT ).show();
                        break;
                    case 1:
                        showToast("something here");
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        handler = null;
        super.onDestroy();

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

    private class ListViewAdapter extends ArrayAdapter<activityList>  {




        public ListViewAdapter(Context context, int resource, List<activityList> objects) {
            super(context, -1, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            Log.d(getClass().getSimpleName(),"getView() start ");
            if(v == null){
                v= LayoutInflater.from(getContext()).inflate(R.layout.mylayout,null);
            }


            final activityList item = getItem(position);
            CustomTextView textView = (CustomTextView)v.findViewById(R.id.customTextView);
            textView.setText(item.getActivityName());
            return v;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            super.registerDataSetObserver(observer);
        }



        @Override
        public void setNotifyOnChange(boolean notifyOnChange) {
            super.setNotifyOnChange(notifyOnChange);
        }


        @Override
        public void notifyDataSetChanged() {
            Log.d(getClass().getSimpleName(),"notifyDataSetChanged()");
            super.notifyDataSetChanged();
        }

        //
//        @NonNull
//        @Override
//        public Filter getFilter() {
//            Filter filter = new Filter() {
//
//
//                @Override
//                protected FilterResults performFiltering(CharSequence charSequence) {
//                    FilterResults results = new FilterResults();
//                    ArrayList<activityList> FilteredArrayNames = new ArrayList<activityList>();
//
//
//                    charSequence = charSequence.toString().toLowerCase();
//
//                    for (int i = 0; i < items.size(); i++) {
//                        activityList item = items.get(i);
//                        if (item.getActivityName().toLowerCase().startsWith(charSequence.toString()))  {
//                            FilteredArrayNames.add(item);
//                        }
//                    }
//
//                    results.count = FilteredArrayNames.size();
//                    results.values = FilteredArrayNames;
//                    Log.d("VALUES", results.values.toString());
//
//
//                    return results;
//                }
//
//                @Override
//                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                    filteredItems = (ArrayList<activityList>) filterResults.values;
//
//                    listView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            listView.invalidate();
//                            adapter.notifyDataSetChanged();
//                        }
//                    });
//                }
//            };
//            return filter;
//        }

    }
}
