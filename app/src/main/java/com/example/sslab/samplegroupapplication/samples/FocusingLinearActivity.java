package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.popup.PopupNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 반드시 ScrollView로 넣어줘야한다.
 * */
public class FocusingLinearActivity extends AppCompatActivity implements View.OnClickListener,
        PopupNotification.popUpInteraction {
    final String TAG = FocusingSampleActivity.class.getSimpleName();
    ScrollView focusingSampleScrollView;
    GridLayout gridLayout;
    ListView sampleListView;


    FocusingLinearActivity.SimpleArrayAdapter simpleArrayAdapter;
    ArrayList<EditText> validationList = new ArrayList<>();
    ArrayList<Integer> validationIDList = new ArrayList<>();
    ArrayList<String> datas = new ArrayList<>();


    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focusing_linear);

        focusingSampleScrollView    = (ScrollView) findViewById(R.id.focusing_linear);

//        focusingSampleScrollView.setSmoothScrollingEnabled(true);
        gridLayout                  = (GridLayout)findViewById(R.id.gridLayout);
        sampleListView              = (ListView)findViewById(R.id.someItemsListView);
        btnNext                     = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        datas.add("현금");
        datas.add("anjwl ");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");
        datas.add("현금");


        simpleArrayAdapter = new FocusingLinearActivity.SimpleArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,datas);

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

        recursivelyIntializeValidationGroup(gridLayout);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }

    /**
     *
     * @param rootLayout
     * @return
     */

    // 어떻게 만들면 좀 더 유연하게 만들어질까 . TAG를 붙여야하는건가 ?
    public void recursivelyIntializeValidationGroup(ViewGroup rootLayout){
        if(rootLayout == null){
            return;
        }else {
            int count = rootLayout.getChildCount();
            for(int i =0;i<count;i++){

                if(rootLayout.getChildAt(i) instanceof ViewGroup){
                    ViewGroup childGroup = (ViewGroup) rootLayout.getChildAt(i);
                    ArrayList<EditText> items = getChildeId(childGroup);

                    if(items != null){
                        if(items.size() != 0)
                            validationList.addAll(items);
                    }
                }

                if(rootLayout.getChildAt(i) instanceof EditText){
                    validationList.add( ( EditText ) rootLayout.getChildAt(i) );
                }
            }

            for(EditText editText : validationList) {
                validationIDList.add(editText.getId());
            }
        }
    }



    public ArrayList<EditText> getChildeId(ViewGroup root){
        int id = -1;
        ArrayList<EditText> items = new ArrayList<>();

        if (root.getChildCount() == 0){
            return null;
        }

        for( int i =0 ;i<root.getChildCount();i++){


            if(root.getChildAt(i) instanceof EditText){
                items.add( ( EditText )root.getChildAt(i) );
            }else if(root.getChildAt(i) instanceof ViewGroup){
                ArrayList getItems = getChildeId( (ViewGroup) root.getChildAt(i) );

                if(getItems != null){
                    if(getItems.size() != 0){
                        items.addAll(getItems);
                    }
                }
            }

        }

        return items;
    }

    public void CheckValidation(){
        for(EditText editText : validationList ){
            if(editText.getText().toString().equals("")){
                PopupNotification notification = new PopupNotification(this);
                notification.setPopupInteraction(this);
                notification.setValidateItems( validationIDList );
                notification.show(editText.getId());
                return;
            }
        }
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnNext:
                CheckValidation();
                break;
        }
    }

    @Override
    public void moveScroll(int layoutId,int rootLayout) {

        final EditText highlightedView = (EditText)findViewById(layoutId);

        if(highlightedView.isFocused()){
            focusingSampleScrollView.scrollTo( focusingSampleScrollView.getScrollY() , (int)highlightedView.getY() );
        }
        else {
            highlightedView.requestFocus();
        }

        highlightedView.postDelayed(new Runnable(){

            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(highlightedView, 0);
            }
        },500);

//        int scrollView  = focusingSampleScrollView.getScrollY();
//        int highlistViewY = highlightedView.get;
//
//
    }



    private class SimpleArrayAdapter extends ArrayAdapter<String> {

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
