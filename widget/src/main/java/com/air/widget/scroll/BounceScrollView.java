package com.air.widget.scroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author pd_liu 2018/10/8
 * <p>
 * BounceScrollView
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class BounceScrollView extends NestedScrollView {

    private static final String KEY_TAG = "BounceScrollView";

    public BounceScrollView(@NonNull Context context) {
        this(context, null);
    }

    public BounceScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);


        //View scrolling.
        if (l > oldl) {
            //向右
        } else {
            //向左
        }

        if (t > oldt) {
            //向下
        } else {
            //向上
        }

    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


}
