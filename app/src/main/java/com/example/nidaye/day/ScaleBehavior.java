package com.example.nidaye.day;

import android.content.Context;
import android.content.IntentFilter;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nidaye on 2017/7/5.
 */

public class ScaleBehavior extends FloatingActionButton.Behavior{
    private boolean isAnimate;
    public ScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public static <V extends View> ScaleBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();
        if (!(behavior instanceof ScaleBehavior)) {
            throw new IllegalArgumentException(
                    "The view is not associated with ScaleBehavior");
        }
        return (ScaleBehavior) behavior;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;

    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, FloatingActionButton child, MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                TestActivity.recyclerView.smoothScrollToPosition(0);
        }
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        大于0是向上移动，向上移动就需要显示floatingbar,同时，我想对BottomSheetBehavior进行显示与隐藏处理
        if (((dyConsumed>0&&dyUnconsumed==0)||(dyConsumed==0&&dyUnconsumed>0))&&!isAnimate){
            if (hehe!=null){
                hehe.whatMethod(true);
            }
            AnimatorUtil.scaleShow(child,listener);
        }else if (((dyConsumed<0&&dyUnconsumed==0)||(dyConsumed==0&&dyUnconsumed<0))&&!isAnimate){
            if (hehe!=null){
                hehe.whatMethod(false);
            }
            AnimatorUtil.scaleHide(child,listener);
        }
    }
    private ViewPropertyAnimatorListener listener=new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {
            isAnimate=true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimate=false;
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimate=false;
        }
    };
    public interface noName{
        public void whatMethod(boolean isShow);
    }
    private noName hehe;
    public void setnoName(noName hehe){
        this.hehe=hehe;
    }



}
