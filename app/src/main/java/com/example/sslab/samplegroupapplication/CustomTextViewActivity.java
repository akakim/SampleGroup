package com.example.sslab.samplegroupapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
