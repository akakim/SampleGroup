package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

/**
 * 이것을 응용하면 genie에서 만들었던 UI를 만들지도 모른다.
 * swipe -> item 을 지울지 말지를 결정
 * -> 결정에 따른 UI update
 *
 */
public class SwipeBehaviorExampleActivity extends AppCompatActivity implements View.OnClickListener,SwipeDismissBehavior.OnDismissListener{


    LinearLayout confirmGroup;
    Button confirmButton;
    Button cancelButton;
//    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_behavior_example);

//        confirmGroup = ( LinearLayout )findViewById( R.id.confirmGroup);
//        confirmButton = ( Button ) findViewById( R.id.confirm);
//        cancelButton = (Button )findViewById( R.id.cancel);
//        confirmButton.setOnClickListener(this);
//        cancelButton.setOnClickListener( this );
//        confirmGroup.setVisibility(View.GONE);
        final CardView cardView = (CardView) findViewById(R.id.swype_card);
        final SwipeDismissBehavior swipe = new SwipeDismissBehavior();

        // Intercepter를 잘 정의해야 에러 로그가 나지 않을 거같다.
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);

        swipe.setListener( this );



        CoordinatorLayout.LayoutParams coordinatorParams = (CoordinatorLayout.LayoutParams) cardView.getLayoutParams();
        coordinatorParams.setBehavior(swipe);


//        swipe.onInterceptTouchEvent(getParent(),cardView,c,)
    }

    public static void start (Context c ){
        c.startActivity( new Intent( c, SwipeBehaviorExampleActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.confirm:
//                Toast.makeText(this,"confirm Clicked",Toast.LENGTH_SHORT).show();
//                cardView.setVisibility(View.GONE );
//                break;
//            case R.id.cancel:
//                Toast.makeText(this,"cancel Clicked",Toast.LENGTH_SHORT).show();
//                cardView.setVisibility(View.VISIBLE);
//                break;
        }
    }

    /**
     *  TODO: geni와 같은 swipe 해서 아이템을 삭제 할지 말지를 결정할 수 있는 UI를 그리는 것이 목표이다.
     *
     * @param view
     */
    @Override
    public void onDismiss(View view) {
        Log.d("OnDismissListener","onDissmiss View ");
        Toast.makeText(SwipeBehaviorExampleActivity.this,
                "Card swiped !!", Toast.LENGTH_SHORT).show();
        Log.d("View get Id",view.getId()+"");


        if (view instanceof CardView){
            Log.d("View","is CardView");
            TextView tv = (TextView) view.findViewById( R.id.itemTextView);
            switch (tv.getVisibility()){
                case View.VISIBLE:
                    Log.d("tv","is VISIBLE");
                    break;
                case View.INVISIBLE:
                    Log.d("tv","is INVISIBLE");
                    break;
                case View.GONE:
                    Log.d("tv","is GONE");
                    break;
            }

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((CardView) view).getLayoutParams();
            params.setMargins(0,0,0,0);
            tv.setAlpha(1);
        }else {
            Log.d("View","is not CardView");
        }


        switch (view.getVisibility()){
            case View.VISIBLE:
                Log.d("View","is VISIBLE");
                 break;
            case View.INVISIBLE:
                Log.d("View","is INVISIBLE");
                break;
            case View.GONE:
                Log.d("View","is GONE");
                break;
        }


    }

    @Override
    public void onDragStateChanged(int state) {

    }
}
