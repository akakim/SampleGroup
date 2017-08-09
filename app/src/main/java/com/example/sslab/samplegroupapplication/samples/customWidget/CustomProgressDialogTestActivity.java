package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.CustomProgressDialog;
import com.example.sslab.samplegroupapplication.widget.DilatingProgressDialog;

public class CustomProgressDialogTestActivity extends AppCompatActivity implements View.OnClickListener{

    private CustomProgressDialog dialog;
    private DilatingProgressDialog dilatingProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_dialog_test);
        dialog = new CustomProgressDialog(this);
        dilatingProgressDialog = new DilatingProgressDialog(this,-1);
        dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.WHITE));           // 다이얼로그의 배경을 의미한다.
        dilatingProgressDialog.getWindow().setBackgroundDrawable(  new ColorDrawable( Color.TRANSPARENT) );

        findViewById( R.id.customProgressDialogBtn ).setOnClickListener( this );
        findViewById( R.id.showDilatingDialogBtn ).setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.customProgressDialogBtn :
                dialog.show();
                break;
            case R.id.showDilatingDialogBtn:
                dilatingProgressDialog.show();
                break;
        }
    }
}
