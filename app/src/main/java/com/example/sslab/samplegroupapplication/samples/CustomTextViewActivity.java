package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
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

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.TextNumber;

import java.util.List;

public class CustomTextViewActivity extends AppCompatActivity {
    TextNumber textNumber;
    TextView textView;

    Spinner postFixEmailSpinner;
    String [] arrayStr;
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
        textView.setText(textNumber.getText());
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
