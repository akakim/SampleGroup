package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sslab.samplegroupapplication.R;

public class CoordinatorExamples extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_examples);




        findViewById(R.id.main_coordinator_textview).setOnClickListener(this);
        findViewById(R.id.main_materialup_textview).setOnClickListener(this);
        findViewById(R.id.main_ioexample_textview).setOnClickListener(this);
        findViewById(R.id.main_space_textview).setOnClickListener(this);
        findViewById(R.id.main_swipebehavior_textview).setOnClickListener(this);
        findViewById(R.id.main_doublenestedscrollview_textview).setOnClickListener(this);


    }

    public static void start (Context c ){
        c.startActivity( new Intent( c, CoordinatorExamples.class ));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_coordinator_textview:
                SimpleCoordinatorActivity.start(this);
                break;

            case R.id.main_ioexample_textview:
                IOActivityExample.start(this);
                break;

            case R.id.main_space_textview:
                FlexibleSpaceExampleActivity.start(this);
                break;

            case R.id.main_materialup_textview:
                MaterialUpConceptActivity.start(this);
                break;

            case R.id.main_swipebehavior_textview:
                SwipeBehaviorExampleActivity.start(this);
                break;
            case R.id.main_doublenestedscrollview_textview:
                DoubleNestedScrollViewSampleActivity.start(this);
                break;
        }
    }
}
