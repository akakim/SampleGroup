package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.samples.InspectorSamples.DevFocusSampleActivity;

import junit.runner.Version;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TabOrderringSample extends AppCompatActivity implements View.OnFocusChangeListener,
EditText.OnEditorActionListener{
    Bundle saveInstance = new Bundle();
    EditText firstNumber ;
    EditText nameValue ;
    EditText secondNumber ;
    EditText thirdNumber ;
    GridLayout gridLayout;
    ListView listView;
    Button nextActivity;

    ArrayList<Item> items = new ArrayList<>();
    ArrayList<VersionDescription> textViewArrayList = new ArrayList<>();
    View lastChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_orderring_sample);
        firstNumber = ( EditText ) findViewById( R.id.firstNumber );
        nameValue = ( EditText ) findViewById( R.id.nameValue );
        secondNumber = ( EditText ) findViewById( R.id.secondNumber );
        thirdNumber = ( EditText ) findViewById( R.id.lastNumber );


        nameValue.setOnFocusChangeListener( this );
        nextActivity = ( Button ) findViewById( R.id.nextActivity);
        nextActivity.setOnClickListener( new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TabOrderringSample.this,DevFocusSampleActivity.class);
                startActivity( i );
            }
        });
        gridLayout = ( GridLayout )findViewById( R.id. gridLayout);

        int last = gridLayout.getChildCount() -1;

        for( int i =0;i<gridLayout.getChildCount();i++){
            gridLayout.getChildAt(i).setId(i);
//            if( i <=1){
//                final EditText editText = (EditText) gridLayout.getChildAt(i);
//
//                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        if (hasFocus){
//                            Log.d(getClass().getSimpleName(),"FOOOOOO");
//                        }else {
//                            if( !"".equals(editText.getText().toString())) {
//                                editText.setText(editText.getText().toString().trim());
//                            }
//                            Log.d(getClass().getSimpleName(),"NOT FOOOOOPOOOOOOOOOOO");
//
//                        }
//                    }
//                });
//            }
        }

        firstNumber.setOnFocusChangeListener( this );
        secondNumber.setOnFocusChangeListener( this );

        listView = ( ListView )findViewById( R.id.list);

//        items.add(new Item("","444"));
//        items.add(new Item("","444"));
//        items.add(new Item("","444"));
//        items.add(new Item("","444"));
//        items.add(new Item("1234","444"));
//        items.add(new Item("1234","444"));
//        items.add(new Item("1234","444"));
//        items.add(new Item("1234","444"));
//        items.add(new Item("1234","444"));
//        items.add(new Item("1234","444"));
//        items.add(new Item("1234","444"));
//        ListAdapter listAdapter = new ListAdapter(this , R.layout.edit_text , items );
        textViewArrayList.add(new VersionDescription("Android",6,"M","M"));

        textViewArrayList.add(new VersionDescription("IPhone",1,"7","7"));
        textViewArrayList.add(new VersionDescription("Android",4,"K","K"));
        textViewArrayList.add(new VersionDescription("Android",5,"L","L"));
        textViewArrayList.add(new VersionDescription("IPhone",3,"9","9"));
        textViewArrayList.add(new VersionDescription("IPhone",2,"8","8"));
        textViewArrayList.add(new VersionDescription("Android",4,"K","K"));
        textViewArrayList.add(new VersionDescription("Android",6,"M","M"));
        textViewArrayList.add(new VersionDescription("Android",7,"N","N"));
        textViewArrayList.add(new VersionDescription("Android",1,"A","A"));
        //textViewArrayList.sort();

//        for(int i =20;i>0;i--){
//            textViewArrayList.add(new VersionDescription("Android",100,"M","VersionName"));
//        }
        CustomAdapter adapter = new CustomAdapter(this,-1,textViewArrayList);
        adapter.sort();
        listView.setAdapter(adapter);
        listView.setScrollbarFadingEnabled( false );

       //adapter.sort();

//        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemSelected",parent.getChildCount() +"");

                for(int i = 0;i<parent.getChildCount();i++){
                    Log.d("onItemSelected",parent.getChildAt(i).getId()+"");
                }
            }
        });

        for( int i =0;i<gridLayout.getChildCount() ;i++){

            if ( gridLayout.getChildAt( i ) instanceof EditText ) {
                EditText child = ( EditText )gridLayout.getChildAt(i);


                if (i != last) {
                    child.setNextFocusDownId(i + 1);
                } else {
                    child.setImeOptions( EditorInfo.IME_ACTION_NEXT );
                    child.setOnEditorActionListener( this );
                    listView.setSelection( 0 );
                }
            }
        }

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

        switch ( v.getId() ){
            case R.id.nameValue:

                if(hasFocus){
                    Log.d("nameValue","has Focuse .... " ) ;
                }else {
                    nameValue.setText( nameValue.getText().toString().trim());
                    Log.d("nameValue","has not Focuse .... " ) ;
                }
                break;
        }
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

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_LONG).show();
                break;
            case EditorInfo.IME_ACTION_NEXT:
                Toast.makeText(getApplicationContext(), "다음", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
                return false;
        }

        return false;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            int ed1Idx = 1000 * position;
            int ed2Idx = ed1Idx | position;
            if ( v == null ){
                v = LayoutInflater.from(getContext()).inflate(R.layout.edit_text,parent,false);



            }
            final Item item = (Item)getItem(position);
            final EditText ed1 = (EditText ) v.findViewById(R.id.ed1);
            final EditText ed2 = (EditText ) v.findViewById(R.id.ed2);
            ed1.setNextFocusRightId(ed2.getId());
            ed2.setNextFocusForwardId(ed1.getId());

            ed1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            ed2.setImeOptions(EditorInfo.IME_ACTION_NEXT);

                ed1.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        switch (actionId) {
                            case EditorInfo.IME_ACTION_SEARCH:
                                Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_LONG).show();
                                break;
                            case EditorInfo.IME_ACTION_NEXT:
                                Toast.makeText(getApplicationContext(), "다음", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
                                ed2.requestFocus();
                                return false;
                        }
                        return true;
                    }
                });
                ed2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        return false;
                    }
                });
                ed1.setText(item.getStr1());



            return v;
        }



        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }
    }

    private class CustomAdapter extends ArrayAdapter{

        public CustomAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        public CustomAdapter(Context context, int resource, Object[] objects) {
            super(context, resource, objects);
        }


        public CustomAdapter(Context context, int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout,parent,false);
            }

            TextView textView = ( TextView )view.findViewById( R.id.textview1 );
            final String item = getItem(position).toString();
            if( item != null )
                textView.setText(item);

            return view;
        }

        /**
         * 단순히 선형적으로 sort는 ok이다. 다만 여러 조건이 붙으면 사용하면 안됨!
         */
        public void sort(){


            super.sort(new Comparator<VersionDescription>() {
                @Override
                public int compare(VersionDescription o1, VersionDescription o2) {
                    return o1.compare(o1,o2);
                }
            });
        }

        public boolean selfSortingCheck(){

            boolean isSorted = false;
            for( int i =0;i<getCount()-1;i++){
                final VersionDescription iItem = (VersionDescription)getItem( i );
                for (int j = i+1;j<getCount();j++){
                    final VersionDescription jItem = (VersionDescription)getItem( j );
                    if( i != j ){
                        isSorted  = (iItem.compare(iItem,jItem) < 0 );
                    }

                }
            }

            return isSorted;
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

    public static final String K = "Kitkal";
    public static final String L = "Lollipop";
    public static final String M = "MarshMallow";

    private class VersionDescription implements Comparator<VersionDescription>{
        private String prefix;
        private int code;
        private String codeName;
        private String versionName;


        public VersionDescription(String prefix, int code, String codeName, String versionName) {
            this.prefix = prefix;
            this.code = code;
            this.codeName = codeName;
            this.versionName = versionName;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getCodeName() {
            return codeName;
        }

        public void setCodeName(String codeName) {
            this.codeName = codeName;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public int compare(VersionDescription o1, VersionDescription o2) {

            byte[] byteO1 = o1.getPrefix().getBytes();
            byte[] byteO2 = o2.getPrefix().getBytes();
            StringBuilder timing = new StringBuilder();

            boolean isSmall= false;

            if( byteO1[0] < byteO2[0] ){
                Log.d("compare","timing getPrefix o1 : " + o1.getPrefix() + " o2 : "+ o2.getPrefix());
                return -1;
            }

            if( o1.getCode() < o2.getCode() ) {
                Log.d("compare","timing getCode"+ o1.getCode() + " o2 : "+ o2.getCode());
                return -1;
            }

            if(o1.getCodeName().compareTo(o2.getCodeName()) >= 0 ) {
                Log.d("compare","timing getCodeName"+ o1.getCodeName() + " o2 : "+ o2.getCodeName());
                return -1;
            }

            if(o1.getVersionName().compareTo(o2.getVersionName()) >= 0) {
                Log.d("compare","timing getVersionName"+ o1.getVersionName() + " o2 : "+ o2.getVersionName() );
                return -1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return "VersionDescription{" +
                    "prefix='" + prefix + '\'' +
                    ", code=" + code +
                    ", codeName='" + codeName + '\'' +
                    ", versionName='" + versionName + '\'' +
                    '}';
        }
    }

}
