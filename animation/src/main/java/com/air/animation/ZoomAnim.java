package com.air.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author air on 2018/5/7.
 * <p>
 * ZoomAnim
 * </p>
 * <p>
 * {@link #setZoomListener(ZoomListener)}
 * {@link #zoomImage()}
 * {@link ZoomListener}
 * </p>
 */

public class ZoomAnim {

    private static final String TAG_LOG = "ZoomAnim";
    /**
     * 默认动画时间(Unit:ms)
     */
    private static final int DURATION = 1000;
    private final int mAnimationDurationTime;
    private final View mStartView;
    private final View mEndView;
    private AnimatorSet mCurrentAnimator;

    private ZoomListener mZoomListener;

    public ZoomAnim(View startView, View endView) {
        this(startView, endView, DURATION);
    }

    public ZoomAnim(View startView, View endView, int animationDuration) {
        mAnimationDurationTime = animationDuration;
        mStartView = startView;
        mEndView = endView;
    }

    public ZoomAnim setZoomListener(ZoomListener zoomListener) {
        mZoomListener = zoomListener;
        return this;
    }

    public boolean zoomImage() {
        if (mStartView == null) {
            if (mZoomListener != null) {
                mZoomListener.zoomEnd();
            }
            return false;
        }
        if (mEndView == null) {
            if (mZoomListener != null) {
                mZoomListener.zoomEnd();
            }
            return false;
        }
        if (mCurrentAnimator != null) {
            //动画还未结束，无法开始新动画操作.
            return false;
        }

        //开始动画任务
        mStartView.post(new AnimationRunnable());

        return true;
    }

    /**
     * 动画监听
     */
    public interface ZoomListener {
        /**
         * 动画开始
         */
        void zoomStart();

        /**
         * 动画结束
         */
        void zoomEnd();
    }

    /**
     * Animation Runnable.
     */
    class AnimationRunnable implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            /*
            获取，开始、结束的View位置
             */
            int[] startViewLocation = new int[2];
            mStartView.getLocationOnScreen(startViewLocation);

            int[] endViewLocation = new int[2];
            mEndView.getLocationOnScreen(endViewLocation);

            float startViewX = startViewLocation[0];
            float startViewY = startViewLocation[1];

            float endViewX = endViewLocation[0];
            float endViewY = endViewLocation[1];

            float translationX = 0f;
            float translationY = 0f;

            /*
            Translation x.
             */
            if (startViewX < endViewX) {
                translationX = endViewX - startViewX;
            } else {
                translationX = endViewX - startViewX;
            }

            /*
            Translation y.
             */
            if (startViewY < endViewY) {
                translationY = endViewY - startViewY;
            } else {
                translationY = endViewY - startViewY;
            }


            float scaleX = 1f * mEndView.getWidth() / mStartView.getWidth();

            float scaleY = 1f * mEndView.getHeight() / mStartView.getHeight();

            /*
            Animator.
             */
            mCurrentAnimator = new AnimatorSet();

            ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(mStartView, View.TRANSLATION_X, translationX);
            ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(mStartView, View.TRANSLATION_Y, translationY);
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mStartView, View.SCALE_X, scaleX);
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mStartView, View.SCALE_Y, scaleY);


            mStartView.setPivotX(0f);
            mStartView.setPivotY(0f);

            mCurrentAnimator
                    .play(translationXAnimator)
                    .with(translationYAnimator)
                    .with(scaleXAnimator)
                    .with(scaleYAnimator);

            mCurrentAnimator.setDuration(mAnimationDurationTime);
            mCurrentAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
                /**
                 * {@inheritDoc}
                 *
                 * @param animation
                 */
                @Override
                public void onAnimationCancel(Animator animation) {
                    super.onAnimationCancel(animation);
                }

                /**
                 * {@inheritDoc}
                 *
                 * @param animation
                 */
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    //动画结束
                    mCurrentAnimator = null;

                    //结束标志
                    if (mZoomListener != null) {
                        mZoomListener.zoomEnd();
                    }
                }

                /**
                 * {@inheritDoc}
                 *
                 * @param animation
                 */
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    if (mZoomListener != null) {
                        mZoomListener.zoomStart();
                    }
                }
            });

            //开启动画
            mCurrentAnimator.start();

        }
    }
}
