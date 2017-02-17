package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.graphics.Color;
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

    /** The animation has been reset or hasn't started yet. */
    public static final int STATE_NOT_STARTED = 0;
    /** The SVG is being traced */
    public static final int STATE_TRACE_STARTED = 1;
    /** The SVG has been traced and is now being filled */
    public static final int STATE_FILL_STARTED = 2;
    /** The animation has finished */
    public static final int STATE_FINISHED = 3;

    private static final String TAG = "AnimatedSvgView";

    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    private static float constrain(float min, float max, float v) {
        return Math.max(min, Math.min(max, v));
    }

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

    private int mState = STATE_NOT_STARTED;
    private OnStateChangeListener mOnStateChangeListener;

    public AnimatedSVGView(Context context) {
        super(context);
        init(context, null);
    }

    public AnimatedSVGView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedSVGView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mFillPaint = new Paint();
        mFillPaint.setAntiAlias( true );
        mFillPaint.setStyle(Paint.Style.FILL);

        mTraceColors = new int[1];
        mTraceColors[0] = Color.BLACK;
        mTraceResidueColors = new int[1];
        mTraceResidueColors[0] = 0x32000000;


    }



    /**
     * Callback for listening to animation state changes
     */
    public interface OnStateChangeListener {

        /**
         * Called when the animation state changes.
         *
         * @param state
         *     The state of the animation.
         *     Either {{@link #STATE_NOT_STARTED},
         *     {@link #STATE_TRACE_STARTED}},
         *     {@link #STATE_FILL_STARTED} or
         *     {@link #STATE_FINISHED}
         */
        void onStateChange(int state);
    }


    static final class GlyphData {

        Path path;
        Paint paint;
        float length;
    }
}
