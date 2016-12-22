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
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.popup.PopupNotification;
import com.example.sslab.samplegroupapplication.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class FocusingSampleActivity extends AppCompatActivity implements View.OnClickListener,
        PopupNotification.popUpInteraction{
    final String TAG = FocusingSampleActivity.class.getSimpleName();
    ScrollView focusingSampleScrollView;
    GridLayout gridLayout;
    ListView sampleListView;


    SimpleArrayAdapter simpleArrayAdapter;
    ArrayList<EditText> validationList = new ArrayList<>();
    ArrayList<Integer> validationIDList = new ArrayList<>();
    ArrayList<String> datas = new ArrayList<>();


    LinearLayout firstLayout;
    LinearLayout secondLayout;


    ArrayList<TextView> firstLayoutChild  = new ArrayList<>();
    ArrayList<TextView> secondLayoutChild = new ArrayList<>();

    Button switchButtonFirst;
    Button switchButtonSecond;
    Button layoutsStatusButton;
    Button showAnother;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        setContentView(R.layout.activity_focusing_sample);
        focusingSampleScrollView    = (ScrollView) findViewById(R.id.activity_focusing_sample);
        focusingSampleScrollView.setSmoothScrollingEnabled(true);
        gridLayout                  = (GridLayout)findViewById(R.id.gridLayout);
        firstLayout                 = (LinearLayout)findViewById(R.id.firstLayout);
        secondLayout                 = (LinearLayout)findViewById(R.id.secondLayout);

        for(int i =0;i<firstLayout.getChildCount();i++){
            firstLayoutChild.add((TextView)firstLayout.getChildAt(i));
        }
        for(int i =0;i<secondLayout.getChildCount();i++){
            secondLayoutChild.add((TextView)secondLayout.getChildAt(i));
        }

        sampleListView              = (ListView)findViewById(R.id.someItemsListView);

        switchButtonFirst           = (Button)findViewById(R.id.firstLayoutVisibilityButton);
        switchButtonSecond          = (Button)findViewById(R.id.secondLayoutVisibilityButton);
        layoutsStatusButton         = (Button)findViewById(R.id.layoutsStatusButton);
        btnNext                     = (Button)findViewById(R.id.btnNext);


        switchButtonFirst.setOnClickListener(this);
        switchButtonSecond.setOnClickListener(this);
        layoutsStatusButton.setOnClickListener(this);
        btnNext.setOnClickListener(this);

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
            case R.id.firstLayoutVisibilityButton:
                if(firstLayout.getVisibility()==View.VISIBLE) {
                    firstLayout.setVisibility(View.GONE);
                    StringBuilder msg= new StringBuilder();


                    for(int i = 0;i<firstLayout.getChildCount();i++){
                        firstLayout.getChildAt(i).setVisibility(View.GONE);
                        if(firstLayout.getChildAt(i).getVisibility() == View.VISIBLE){
                            msg.append(i+" : VISIBLE");
                        }else {
                            msg.append(i+" : NOT VISIBLE");
                        }
                    }

                    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show();
                }else {
                    firstLayout.setVisibility(View.VISIBLE);

                    StringBuilder msg= new StringBuilder();
                    for(int i = 0;i<firstLayout.getChildCount();i++){
                        firstLayout.getChildAt(i).setVisibility(View.VISIBLE);
                        if(firstLayout.getChildAt(i).getVisibility() == View.VISIBLE){
                            msg.append(i+" : VISIBLE");
                        }else {
                            msg.append(i+" : NOT VISIBLE");
                        }
                    }
                    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.secondLayoutVisibilityButton:
                if(secondLayout.getVisibility()==View.VISIBLE) {
                    secondLayout.setVisibility(View.GONE);
                    StringBuilder msg= new StringBuilder();
                    for(int i = 0;i<secondLayout.getChildCount();i++){
                        if(secondLayout.getChildAt(i).getVisibility() == View.VISIBLE){
                            msg.append(i+" : VISIBLE");
                        }else {
                            msg.append(i+" : NOT VISIBLE");
                        }
                    }
                    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show();
                }else {
                    secondLayout.setVisibility(View.VISIBLE);
                    StringBuilder msg= new StringBuilder();
                    for(int i = 0;i<secondLayout.getChildCount();i++){
                        if(secondLayout.getChildAt(i).getVisibility() == View.VISIBLE){
                            msg.append(i+" : VISIBLE");
                        }else {
                            msg.append(i+" : NOT VISIBLE");
                        }
                    }
                    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.layoutsStatusButton:
                StringBuilder message = new StringBuilder();

                message.append(CommonUtil.checkTaggedView(focusingSampleScrollView));

//                for(int i = 0;i<focusingSampleScrollView.getChildCount();i++){
//                   View v  =focusingSampleScrollView.getChildAt(i);
//                    if(v instanceof ViewGroup) {
//
//                        if(v.getTag() != null && v.getTag().toString().length() > 0){
//                            message.append("group : " + v.getTag().toString()+" \n");
//                        }
//
//                        ViewGroup viewGroup = (ViewGroup)v;
//                        for(int k = 0;k<viewGroup.getChildCount();k++){
//                            View childView = viewGroup.getChildAt(k);
//                            if(childView.getTag()!=null && childView.getTag().toString().length()>0){
//                                message.append(childView.getTag().toString()+" ");
//                            }
//                            if(childView instanceof TextView){
//                                message.append(((TextView) childView).getText().toString()+" ");
//                            }
//                        }
//                        message.append("\n");
//
//                    }else {
//                        if(v.getTag() != null && v.getTag().toString().length() > 0){
//                            message.append(v.getTag().toString()+" ");
//                        }
//
//                    }
//                    /// / if(View instanceof focusingSampleScrollView.getChildAt(i))
//                }
                Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();

//                for(int i = 0;i<firstLayout.getChildCount();i++){
////                    firstLayout.getChildAt(i).setVisibility(View.GONE);
//                    if(firstLayout.getChildAt(i).getVisibility() == View.VISIBLE){
//                        message.append(i+" : VISIBLE");
//                    }else {
//                        message.append(i+" : NOT VISIBLE");
//                    }
//                }
//                message.append("\n");
//                message.append("secondLayout : ");
//                for(int i = 0;i<secondLayout.getChildCount();i++){
//                    if(secondLayout.getChildAt(i).getVisibility() == View.VISIBLE){
//                        message.append(i+" : VISIBLE");
//                    }else {
//                        message.append(i+" : NOT VISIBLE");
//                    }
//                }
//               Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void moveScroll(int layoutId,int rootScrollId) {

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
