package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.HeaderLayout;

/**
 * Created by SSLAB on 2017-07-07.
 */

public class HeaderScrollBehavior extends ViewOffsetBehavior<HeaderLayout>{
    private static final String TAG = HeaderScrollBehavior.class.getSimpleName();

    private int mTouchSlop;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;

    private boolean mIsScrolling;

    private View mTabLayout;

    private int mMinOffset;
    private int mMaxOffset;

    private View mTargetView;

    private int mSkippedOffset;

    private ViewFlinger mViewFlinger;

    public HeaderScrollBehavior() {

    }

    public HeaderScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        final ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }



    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, HeaderLayout child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, HeaderLayout child, int parentWidthMeasureSpec,
                                  int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec,
                heightUsed);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, HeaderLayout child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, HeaderLayout child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, HeaderLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG,"onStartNestedScroll");

        if (mViewFlinger != null) {
            mViewFlinger.cancel();
        }

        // 수직으로 스크롤 하는경우
        if ((nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0) {
            mTargetView = target;

            mIsScrolling = false;
            mSkippedOffset = 0;

            mTabLayout = child.findViewById(R.id.tab_layout);

            mMinOffset = -(child.getHeight() - mTabLayout.getHeight());
            mMaxOffset = 0;

            // 트루를 반환하면 뭔가 변화가 있다는 것인가?
            return true;
        }

        return false;
    }


    /**
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, HeaderLayout child, View target, int dx, int dy, int[] consumed) {

        Log.d(TAG,"onNestedPreScroll");
        /**
         * 스크롤중이 아닐땐  생략학 변화값을 조정하고
         *
         * 스킵량이
         */
        if (!mIsScrolling) {
            Log.d(TAG,"onNestedPreScroll not Scrolled");
            mSkippedOffset += dy;

            if (Math.abs(mSkippedOffset) >= mTouchSlop) {
                mIsScrolling = true;
                target.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        /**
         * 스크롤링일째
         */
        if (mIsScrolling && dy != 0) {
            Log.d(TAG,"onNestedPreScroll isScrolled Vertical ");
            int min = -child.getScrollRange();  // HeaderLayout에 정의된 스크롤을 할 만큼의 범위 지정

            int max = 0;

            int currentOffset = getTopAndBottomOffset();
            int newOffset = Math.min(Math.max(min, currentOffset - dy), max);

            consumed[1] = newOffset - currentOffset;                //??

            setTopAndBottomOffset(newOffset);                       //??
        }

    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, HeaderLayout child, View target,
                                    float velocityX, float velocityY) {
        Log.d(TAG,"onNestedPreFling");
        // fling을 위한 객체생성
        if (mViewFlinger != null) {
            mViewFlinger.cancel();
        } else {
            mViewFlinger = new ViewFlinger(coordinatorLayout);
        }

        final int targetOffsetRange;        // 범위를 알아낸다.
        final int targetOffset;             // 얼만큼 내릴것인가.
        if (target instanceof ScrollingView) {
            Log.d(TAG,"onNestedPreFling target is Scrolling View ");
            // 스크롤 뷰의 총크기
            targetOffsetRange = ((ScrollingView) target).computeVerticalScrollRange() + target.getPaddingTop()
                    + target.getPaddingBottom();
            targetOffset = ((ScrollingView) target).computeVerticalScrollOffset();
        } else {
            Log.d(TAG,"onNestedPreFling target is not Scrolling View ");
            targetOffsetRange = Math.max(0, target.getHeight() - coordinatorLayout.getHeight());
            targetOffset = target.getScrollY();
        }

        mViewFlinger.fling((int) velocityY, targetOffset, targetOffsetRange);


        return true;
    }
    private class ViewFlinger implements Runnable {

        private final ScrollerCompat mScroller;             //애니메이션이나 기본적인 좌표를 계산한 걸 가지고 있는애
        private final CoordinatorLayout mCoordinatorLayout;
        private int mLastFlingY;


        public ViewFlinger(CoordinatorLayout coordinatorLayout) {
            mScroller = ScrollerCompat.create(coordinatorLayout.getContext());
            mCoordinatorLayout = coordinatorLayout;
        }

        public void fling(int velocity, int targetOffset, int targetOffsetRange) {
            Log.d(getClass().getSimpleName(),"Fling Start");
            if (Math.abs(velocity) < mMinFlingVelocity) {
                return;
            }

            velocity = Math.max(-mMaxFlingVelocity, Math.min(velocity, mMaxFlingVelocity));

            mScroller.fling(0, 0, 0, velocity, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            mCoordinatorLayout.postOnAnimation(this);

            mLastFlingY = 0;
        }

        public void cancel() {
            Log.d(getClass().getSimpleName(),"Fling cancel...");
            mScroller.abortAnimation();
        }

        @Override
        public void run() {

            Log.d(getClass().getSimpleName(),"Fling run in Thread");
            if (mScroller.computeScrollOffset()) {
                int dy = mScroller.getCurrY() - mLastFlingY;

                final int selfOffset = getTopAndBottomOffset();
                final int newSelfOffset = Math.max(mMinOffset, Math.min(mMaxOffset, selfOffset - dy));
                final int skipped = newSelfOffset - selfOffset + dy;

                final boolean selfFinished = !setTopAndBottomOffset(newSelfOffset);

                final int targetOffset;
                final boolean targetFinished;
                if (mTargetView instanceof ScrollingView) {
                    Log.d(getClass().getSimpleName(),"mTargetView is ScrollingView instance");
                    targetOffset = ((ScrollingView) mTargetView).computeVerticalScrollOffset();
                    mTargetView.scrollBy(0, skipped);
                    targetFinished = (targetOffset == ((ScrollingView) mTargetView).computeVerticalScrollOffset());
                } else {
                    Log.d(getClass().getSimpleName(),"mTargetView is not ScrollingView instance");
                    targetOffset = mTargetView.getScrollY();
                    mTargetView.scrollBy(0, skipped);
                    targetFinished = (targetOffset == mTargetView.getScrollY());
                }

                final boolean scrollerFinished = mScroller.isFinished();

                if (scrollerFinished || (selfFinished && targetFinished)) {
                    return;
                }


                // 부모에게 애니메이션을 하도록 요청한다.
                //
                mCoordinatorLayout.postOnAnimation(this);

                mLastFlingY = mScroller.getCurrY();
            }
        }
    }
}
