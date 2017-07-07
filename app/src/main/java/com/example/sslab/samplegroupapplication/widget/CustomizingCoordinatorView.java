package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by SSLAB on 2017-07-06.
 */

public class CustomizingCoordinatorView extends CoordinatorLayout {
    public CustomizingCoordinatorView(Context context) {
        super(context);
    }

    public CustomizingCoordinatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizingCoordinatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
        super.setOnHierarchyChangeListener(onHierarchyChangeListener);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void setStatusBarBackground(@Nullable Drawable bg) {
        super.setStatusBarBackground(bg);
    }

    @Nullable
    @Override
    public Drawable getStatusBarBackground() {
        return super.getStatusBarBackground();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public void setStatusBarBackgroundResource(@DrawableRes int resId) {
        super.setStatusBarBackgroundResource(resId);
    }

    @Override
    public void setStatusBarBackgroundColor(@ColorInt int color) {
        super.setStatusBarBackgroundColor(color);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }

    @Override
    public void onMeasureChild(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        super.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLayoutChild(View child, int layoutDirection) {
        super.onLayoutChild(child, layoutDirection);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }

    @Override
    public void setFitsSystemWindows(boolean fitSystemWindows) {
        super.setFitsSystemWindows(fitSystemWindows);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    public void dispatchDependentViewsChanged(View view) {
        super.dispatchDependentViewsChanged(view);
    }

    @NonNull
    @Override
    public List<View> getDependencies(@NonNull View child) {
        return super.getDependencies(child);
    }

    @NonNull
    @Override
    public List<View> getDependents(@NonNull View child) {
        return super.getDependents(child);
    }

    @Override
    public boolean isPointInChildBounds(View child, int x, int y) {
        return super.isPointInChildBounds(child, x, y);
    }

    @Override
    public boolean doViewsOverlap(View first, View second) {
        return super.doViewsOverlap(first, second);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    /**
     *  There’s one catch- the CoordinatorLayout will let our Behavior know
     *  when a scroll is started from any of its descendants,
     *  but to receive any future scroll events we need to let the CoordinatorLayout
     *  know we care:
     *  CoordinatorLayout이 잡는 행위는 하나밖에 없다.
     *  그러나 어떤 미래의 이벤트가 우리가 필요한 것을 정의해줘야한다.
     *
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        return ( nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL ) != 0;
//        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
    }

    /**
     * 이건 AppbarLayout때문에 존재한다.왜냐하면 scroll event의 부분을 차지 할 수 있기에
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     * 이건 AppbarLayout때문에 존재한다. 왜냐하면 scroll event의 부분을 차지 할 수 있기에
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public int getNestedScrollAxes() {
        return super.getNestedScrollAxes();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        return super.requestChildRectangleOnScreen(child, rectangle, immediate);
    }
}
