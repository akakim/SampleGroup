package com.example.sslab.samplegroupapplication.samples;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.TextNumber;

import java.util.Iterator;
import java.util.List;

public class CustomTextViewActivity extends AppCompatActivity implements View.OnClickListener {
    TextNumber textNumber;
    TextView textView;

    Spinner postFixEmailSpinner;
    String [] arrayStr;

    TextView packageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_view);

        textNumber  = (TextNumber)findViewById(R.id.deprecationNumber);
        textView    = (TextView)findViewById(R.id.deprecationNumberValue);
        postFixEmailSpinner = ( Spinner )findViewById( R.id.postFixEmailSpinner);
        arrayStr = getResources().getStringArray(R.array.emailPostFix);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.simple_editable_spinner_item,arrayStr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postFixEmailSpinner.setAdapter(adapter);
        postFixEmailSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    EditText ed = (EditText) parent.getChildAt(position);

                if(ed != null){
                    if(position == arrayStr.length -1 ){
                        ed.setFocusable(true);
                    }
                }
                if( view == null){
                    Log.e("ERROR","null");

                }else {
                    if( view instanceof EditText ){
                        Log.d("editText","");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById( R.id.getPackageNameBtn ).setOnClickListener( this );
        findViewById( R.id.restartBtn ).setOnClickListener( this );

        packageTextView = ( TextView )findViewById(R.id.packageName);
        textView.setText(textNumber.getText());
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.getPackageNameBtn :
                ActivityManager manager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

                String name = getApplication().getPackageName();

                List <ActivityManager.RunningAppProcessInfo> list = manager.getRunningAppProcesses();
                Iterator<ActivityManager.RunningAppProcessInfo> iter = list.iterator();
                while (iter.hasNext()){
                    ActivityManager.RunningAppProcessInfo info = iter.next();
                    if (info.processName.equals(name)) {
                        packageTextView.setText(info.processName);
                        Log.d(this.getClass().getSimpleName(),"process Name : " + name );
                    }
                }
                break;
            case R.id.restartBtn:
                if(packageTextView.getText().toString().equals("")){
                    Toast.makeText(this," packageName is null ",Toast.LENGTH_SHORT).show();
                }else {
                    ActivityManager restartManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
//                    restartManager.killBackgroundProcesses(packageTextView.getText().toString());

                    Toast.makeText(this," package backgroundProcess is eliminated ",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private class EmailAdapter extends ArrayAdapter<String>{

        public EmailAdapter(Context context, int resource, List<String> objects) {
            super(context, -1, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }

        @Nullable
        @Override
        public Resources.Theme getDropDownViewTheme() {
            return super.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewResource(int resource) {
            super.setDropDownViewResource(resource);
        }
    }
}
