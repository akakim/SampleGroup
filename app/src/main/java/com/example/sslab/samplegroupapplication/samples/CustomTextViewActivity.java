package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.TextNumber;

public class CustomTextViewActivity extends AppCompatActivity {
    TextNumber textNumber;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_view);

        textNumber  = (TextNumber)findViewById(R.id.deprecationNumber);
        textView    = (TextView)findViewById(R.id.deprecationNumberValue);

        textView.setText(textNumber.getText());
    }
}
