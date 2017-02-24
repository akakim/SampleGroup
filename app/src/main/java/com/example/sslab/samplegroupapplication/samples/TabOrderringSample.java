package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TabOrderringSample extends AppCompatActivity implements View.OnFocusChangeListener{
    Bundle saveInstance = new Bundle();
    EditText firstNumber ;
    EditText secondNumber ;
    EditText thirdNumber ;
    GridLayout gridLayout;
    ListView listView;

    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_orderring_sample);
        firstNumber = ( EditText ) findViewById( R.id.firstNumber );
        secondNumber = ( EditText ) findViewById( R.id.secondNumber );
        thirdNumber = ( EditText ) findViewById( R.id.lastNumber );

        gridLayout = ( GridLayout )findViewById( R.id. gridLayout);

        int last = gridLayout.getChildCount() -1;
        for (int i = 0;i<gridLayout.getChildCount();i++){
            View v = gridLayout.getChildAt(i);
            v.setId(i);

            if(i  != last )
                v.setNextFocusDownId(i+1);
            else
                ((EditText)v).setImeOptions( EditorInfo.IME_ACTION_DONE);
        }
        firstNumber.setOnFocusChangeListener( this );
        secondNumber.setOnFocusChangeListener( this );

        listView = ( ListView )findViewById( R.id.list);

        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        items.add(new Item("1234","444"));
        ListAdapter listAdapter = new ListAdapter(this , R.layout.edit_text , items );
        listView.setAdapter(listAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = new Bundle();
        onRestoreInstanceState(bundle);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bundle bundle = new Bundle();
        bundle.putString("hello","value");
        onSaveInstanceState(bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("lifeCycle","onSaveInstanceState");
        saveInstance.putString("key1","value1");
        saveInstance.putString("onSaveInstanceState","status");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("lifeCycle","onSaveInstanceState persistance ");
        saveInstance.putString("key1","value1");
        saveInstance.putString("onSaveInstanceState","status");

    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("lifeCycle","onRestore");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if ( hasFocus ) {
            switch (v.getId()) {
                case R.id.firstNumber:
//                    if(firstNumber.getText().length()== 3 ){
//                        secondNumber.requestFocus();
//                    }
                    break;
                case R.id.secondNumber:
//                    if(secondNumber.getText().length() == 4){
//                        thirdNumber.requestFocus();
//                    }
                    break;
                case R.id.lastNumber :
                    break;
            }
        }
    }

    private class ListAdapter extends ArrayAdapter{

        public ListAdapter(Context context, int resource) {
            super(context, resource);
        }

        public ListAdapter(Context context, int resource, Item[] objects) {
            super(context, resource, objects);
        }
        public ListAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if ( v == null ){
                v = LayoutInflater.from(getContext()).inflate(R.layout.edit_text,parent,false);
            }
            if( v != null){

                final Item item = (Item)getItem(position);
                EditText ed1 = (EditText ) v.findViewById(R.id.ed1);
                EditText ed2 = (EditText ) v.findViewById(R.id.ed2);
                ed1.setText(item.getStr1());
                ed2.setText(item.getStr2());

            }
            return v;
        }
    }

    public class Item {
        private String str1;
        private String str2;

        public Item(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
        }

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }
    }
}
