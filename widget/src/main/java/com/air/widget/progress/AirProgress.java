package com.air.widget.progress;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import com.air.widget.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author pd_liu 2018/9/28
 * <p>
 * AirProgress
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public abstract class AirProgress extends View implements AirProgressRule {

    private static final String TAG = "AirProgress";

    private ValueAnimator mAnimator;


    private Parameter mParameter = new Parameter();

    private int mAirProgressMax;
    /**
     * Origin\Current\Target 's progress values.
     */
    private int mAirOriginProgress = DEFAULT_PROGRESS_MIN;
    private int mAirCurrentProgress = mAirOriginProgress;
    private int mAirTargetProgress = DEFAULT_PROGRESS_MAX;

    /**
     * Color
     */
    private int mAirProgressColor;
    private int mAirTargetProgressColor;
    private int mAirBackgroundColor;


    /**
     * Animator enable.
     */
    private boolean mAirPreview;

    /**
     * Size.
     */
    private int mAirProgressWidth;
    private int mAirTargetProgressWidth;

    /**
     * Duration
     */
    private int mAirProgressDuration;
    /**
     * Default values.
     */
    private static final int DEFAULT_PROGRESS_MAX = 100;
    private static final int DEFAULT_PROGRESS_MIN = 0;
    private static final int DEFAULT_PROGRESS_COLOR = Color.BLUE;
    private static final int DEFAULT_PROGRESS_WIDTH = 3;
    private static final int DEFAULT_PROGRESS_DURATION = 2000;
    private static final int DEFAULT_ZERO = 0;


    private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {

            int updatedCurrentProgress = (int) animation.getAnimatedValue();

            mParameter.setCurrentProgress(updatedCurrentProgress);

            //Redraw.
            postInvalidate();
        }
    };


    public AirProgress(Context context) {
        this(context, null);
    }

    public AirProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AirProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AirProgress);

        //Max
        mAirProgressMax = typedArray.getInteger(R.styleable.AirProgress_air_max, DEFAULT_PROGRESS_MAX);

        //Progress
        mAirTargetProgress = typedArray.getInt(R.styleable.AirProgress_air_progress, DEFAULT_PROGRESS_MIN);

        //Color
        mAirProgressColor = typedArray.getColor(R.styleable.AirProgress_air_color, DEFAULT_PROGRESS_COLOR);
        mAirTargetProgressColor = typedArray.getColor(R.styleable.AirProgress_air_progress_color, DEFAULT_PROGRESS_COLOR);

        //Size Px
        int progressWidth = typedArray.getDimensionPixelSize(R.styleable.AirProgress_air_progress_width, DEFAULT_PROGRESS_WIDTH);

        mAirProgressWidth = mAirTargetProgressWidth = progressWidth;

        //Duration
        mAirProgressDuration = typedArray.getInteger(R.styleable.AirProgress_air_progress_duration, DEFAULT_PROGRESS_DURATION);

        mAirBackgroundColor = typedArray.getColor(R.styleable.AirProgress_air_background_color, Color.WHITE);

        mAirPreview = typedArray.getBoolean(R.styleable.AirProgress_air_preview, true);
        //Recycler.
        typedArray.recycle();

        openPreview(mAirPreview);

        updateParameter();


        initialize();
    }

    protected abstract void initialize();

    private void openPreview(boolean opened) {
        if (opened) {
            //预览（将动画时间设置为0）
            mAirProgressDuration = DEFAULT_ZERO;
        }
    }

    void updateParameter() {

        mParameter.setProgressWidth(mAirProgressWidth);
        mParameter.setTargetProgressWidth(mAirTargetProgressWidth);
        mParameter.setProgressMax(mAirProgressMax);
        mParameter.setCurrentProgress(mAirCurrentProgress);
        mParameter.setProgressColor(mAirProgressColor);
        mParameter.setTargetProgressColor(mAirTargetProgressColor);
        mParameter.setTargetProgress(mAirTargetProgress);
        mParameter.setDuration(mAirProgressDuration);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                widthSize = getMinimumWidth();
                break;
        }

        switch (heightMode) {

            case MeasureSpec.AT_MOST:
                heightSize = getMinimumHeight();
                break;
        }

        setMeasuredDimension(widthSize, heightSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mAirBackgroundColor);

    }


    public void setProgress(@IntRange(from = 0) int progress) {
        //Update progress value
        mParameter.setTargetProgress(progress);

        //
        if (isAttachedToWindow()) {
            startAnimation();
        }
    }

    public void addListener(@Nullable Animator.AnimatorListener animatorListener) {
        mParameter.addListener(animatorListener);
    }

    public void setInterpolator(@Nullable TimeInterpolator interpolator) {
        mParameter.setInterpolator(interpolator);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        stopAnimation();
    }

    protected void startAnimation() {

        if (mAnimator != null && mAnimator.isStarted()) {
            stopAnimation();
        }

        mAnimator = ValueAnimator.ofInt(mParameter.getCurrentProgress(), mParameter.getTargetProgress());
        mAnimator.setDuration(mParameter.getDuration());
        mAnimator.setInterpolator(mParameter.getInterpolator());
        mAnimator.addListener(mParameter.getListener());
        mAnimator.addUpdateListener(updateListener);
        //Start animator.
        mAnimator.start();
    }

    protected void stopAnimation() {
        if (mAnimator == null) {
            return;
        }

        //Release animator object.
        mAnimator.cancel();
    }

    protected Parameter getParameter() {
        return mParameter;
    }

    public static class Parameter {
        private int progressWidth;
        private int targetProgressWidth;
        private int progressMax;
        private int currentProgress;
        private int progressColor;
        private int targetProgressColor;
        private int targetProgress;
        private int originProgress;
        private int duration;

        private TimeInterpolator interpolator = new LinearInterpolator();

        private Animator.AnimatorListener listener = new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        public int getProgressWidth() {
            return this.progressWidth;
        }

        public void setProgressWidth(int progressWidth) {
            this.progressWidth = progressWidth;
        }

        public int getTargetProgressWidth() {
            return this.targetProgressWidth;
        }

        public void setTargetProgressWidth(int targetProgressWidth) {
            this.targetProgressWidth = targetProgressWidth;
        }

        public int getProgressMax() {
            return this.progressMax;
        }

        public void setProgressMax(int progressMax) {
            if (progressMax <= 0) {
                return;
            }
            this.progressMax = progressMax;
        }

        public int getCurrentProgress() {
            return this.currentProgress;
        }

        public void setCurrentProgress(int currentProgress) {
            this.currentProgress = currentProgress;
        }

        public int getProgressColor() {
            return this.progressColor;
        }

        public void setProgressColor(int progressColor) {
            this.progressColor = progressColor;
        }

        public int getTargetProgressColor() {
            return this.targetProgressColor;
        }

        public void setTargetProgressColor(int targetProgressColor) {
            this.targetProgressColor = targetProgressColor;
        }

        public int getTargetProgress() {
            return this.targetProgress;
        }

        public void setTargetProgress(int targetProgress) {
            if (targetProgress <= 0) {
                return;
            }

            if (targetProgress > getProgressMax()) {
                return;
            }

            this.targetProgress = targetProgress;
        }

        public int getDuration() {
            return this.duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getOriginProgress() {
            return this.originProgress;
        }

        public void setOriginProgress(int originProgress) {
            this.originProgress = originProgress;
        }

        public void addListener(@Nullable Animator.AnimatorListener listener) {
            this.listener = listener;
        }

        @Nullable
        public AnimatorListener getListener() {
            return this.listener;
        }

        public void removeListener() {
            this.listener = null;
        }

        public void setInterpolator(@Nullable TimeInterpolator interpolator) {
            this.interpolator = interpolator;
        }

        @Nullable
        public TimeInterpolator getInterpolator() {
            return this.interpolator;
        }
    }
}
