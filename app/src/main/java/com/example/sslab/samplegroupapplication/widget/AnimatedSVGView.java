package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by SSLAB on 2017-02-03.
 */

public class AnimatedSVGView extends View {
    public static final int STATE_NOT_STARTED = 0;
    public static final int STATE_TRACE_STARTED = 0;
    public static final int STATE_FILL_STARTED = 0;
    public static final int STATE_FINISHED = 0;

    private final String TAG = getClass().getSimpleName();
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    private int mTraceTime = 2000;
    private int mTraceTimePerGlyph = 1000;
    private int mFillStart = 1200;
    private int mFillTime = 1000;
    private int[] mTraceResidueColors;
    private int[] mTraceColors;
    private float mViewportWidth;
    private float mViewportHeight;
    private PointF mViewport = new PointF(mViewportWidth, mViewportHeight);
    private float aspectRatioWidth = 1;
    private float aspectRatioHeight = 1;

    private Paint mFillPaint;
    private int[] mFillColors;
    private GlyphData[] mGlyphData;
    private String[] mGlyphStrings;
    private float mMarkerLength;
    private int mWidth;
    private int mHeight;
    private long mStartTime;

    private static float constrain( float min, float max, float v){
        return Math.max(min,Math.min( max, v ) );
    }

    public AnimatedSVGView( Context context ){
        super( context );
        init( context, null );
    }

    private void init(Context context, AttributeSet attrs ){
        mFillPaint = new Paint();
        mFillPaint.setAntiAlias( true );
        mFillPaint.setStyle( Paint.Style.FILL );


    }

    static final class GlyphData {

        Path path;
        Paint paint;
        float length;
    }

}
