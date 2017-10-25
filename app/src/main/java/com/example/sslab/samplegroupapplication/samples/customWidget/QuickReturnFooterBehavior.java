package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by SSLAB on 2017-07-06.
 */

public class QuickReturnFooterBehavior extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int mDySinceDirectionChange;
    private boolean mIsShowing;
    private boolean mIsHiding;

    private final String TAG = getClass().getSimpleName();
    public QuickReturnFooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        if( dy > 0 && mDySinceDirectionChange < 0 || dy < 0 && mDySinceDirectionChange > 0 ){
            child.animate().cancel();
            mDySinceDirectionChange = 0;
        }

        mDySinceDirectionChange += dy;

        if (mDySinceDirectionChange > child.getHeight()
                && child.getVisibility() == View.VISIBLE
                && !mIsHiding) {
            hide(child);
        } else if (mDySinceDirectionChange < 0
                && child.getVisibility() == View.GONE
                && !mIsShowing) {
            show(child);
        }

    }

    /**
     * Hide the quick return view.
     *
     * Animates hiding the view, with the view sliding down and out of the screen.
     * After the view has disappeared, its visibility will change to GONE.
     *
     * @param view The quick return view
     */

    private void hide( final View view){
        mIsHiding = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(view.getHeight())
                .setInterpolator(INTERPOLATOR)
                .setDuration( 200 );

        animator.setListener(
                new Animator.AnimatorListener(){
                  @Override
                  public void onAnimationStart(Animator animation) {
                        Log.d(TAG,"hide onAnimationStart");
                  }

                  @Override
                  public void onAnimationEnd(Animator animation) {
                    // Prevent drawing the View after it is gone
                      mIsHiding = false;
                      view.setVisibility(View.GONE);
                      Log.d(TAG,"hide onAnimationEnd");
                  }

                  @Override
                  public void onAnimationCancel(Animator animation) {
                        // Canceling a hid should show the view
                      mIsHiding = false;
                      if(mIsShowing){
                          show(view);
                      }

                      Log.d(TAG,"hide onAnimationCancel");
                  }

                  @Override
                  public void onAnimationRepeat(Animator animation) {
                      Log.d(TAG,"hide onAnimationRepeat");
                  }
              }
        );

        animator.start();
    }
    /**
     * Show the quick return view.
     *
     * Animates showing the view, with the view sliding up from the bottom of the screen.
     * After the view has reappeared, its visibility will change to VISIBLE.
     *
     * @param view The quick return view
     */
    private void show(final View view) {
        mIsShowing = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // Canceling a show should hide the view
                mIsShowing = false;
                if (!mIsHiding) {
                    hide(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        animator.start();
    }
}