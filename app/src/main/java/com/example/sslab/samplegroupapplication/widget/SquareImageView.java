package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 어떤 이미지에서 너비와 높이 중 작은 값을 선택해서
 * 정사각형을 만드는 ImageView
 */
public class SquareImageView extends ImageView {
    public SquareImageView( Context context ) {
        super( context );
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle ) {
        super( context, attrs, defStyle );
        // TODO Auto-generated constructor stub
    }

    public SquareImageView( Context context, AttributeSet attrs ) {
        super( context, attrs );
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {

        int w = MeasureSpec.getSize( widthMeasureSpec );
        int h = MeasureSpec.getSize( heightMeasureSpec );

        w = Math.min( w, h );
        h = w;

        setMeasuredDimension( w, h );
    }


}
