package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.BaseFragmentActivity;
import com.example.sslab.samplegroupapplication.R;

import org.json.JSONObject;

public class NetWorkDemoActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_demo);
    }

    @Override
    public void responseData(JSONObject jsonObject) {

    }
}
