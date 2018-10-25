package com.air.widget.scroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author pd_liu 2018/10/8
 * <p>
 * ScrollLinearLayout
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class ScrollLinearLayout extends LinearLayout {

    private static final String TAG = "ScrollLinearLayout";

    private static final int DEFAULT_MOVE_DURATION = 0;

    private Scroller mScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    private float mPreviewX;
    private float mPreviewY;

    public ScrollLinearLayout(Context context) {
        this(context, null);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        boolean isTouchEvent = true;

        float currentX = 0F;
        float currentY = 0F;

        int moveX;
        int moveY;

        switch (action) {

            case MotionEvent.ACTION_DOWN:

                mPreviewX = event.getX();
                mPreviewY = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:

                currentX = event.getX();
                currentY = event.getY();


                moveX = (int) (mPreviewX - currentX + 0.5f);
                moveY = (int) (mPreviewY - currentY + 0.5f);


                if ((Math.abs(moveX) > mTouchSlop || Math.abs(moveY) > mTouchSlop)) {
                    //move
                    scrollBy(moveX, moveY);
                    mPreviewX = currentX;
                    mPreviewY = currentY;
                }

                break;

            case MotionEvent.ACTION_UP:

                currentX = event.getX();
                currentY = event.getY();

                moveX = (int) (mPreviewX - currentX + 0.5f);
                moveY = (int) (mPreviewY - currentY + 0.5f);

                mScroller.startScroll(getScrollX(), getScrollY(), moveX, moveY);
                invalidate();
                break;
            default:

        }

        return isTouchEvent;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {

            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            postInvalidate();
        }

    }

}
