package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;

/**
 * 그러니까... 만약에 이 레이아웃 안의 EditText가 처음 고정되어있는체 있다면
 * NestedScrollview의 자식이 되어있지 않았다면, EditText는 자연스럽게 늘어나지 않았을 것이다.!
 * L 버전이후로 잘 적용이 된다고 한다. 이전버전에서 사용한다면 테스트를 먼저 해봐야한다. ..
 *
 */
public class DoubleNestedScrollViewSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_nested_scroll_view_sample);
    }

    public static void start(Context c ){

        c.startActivity( new Intent( c, DoubleNestedScrollViewSampleActivity.class));
    }
}
