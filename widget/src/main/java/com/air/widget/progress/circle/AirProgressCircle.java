package com.air.widget.progress.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.air.widget.progress.AirProgress;

/**
 * @author pd_liu 2018/9/29
 * <p>
 * AirProgressCircle
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class AirProgressCircle extends AirProgress {

    private static final String TAG = "AirProgressCircle";

    private static final float DEFAULT_PROGRESS_MAX_ANGLE = 360f;
    private static final float DEFAULT_PROGRESS_MIN_ANGLE = 0f;

    /**
     * Paints
     */
    private Paint mAirProgressPaint;
    private Paint mAirTargetProgressPaint;
    private Paint mAirProgressTextPaint;

    public AirProgressCircle(Context context) {
        super(context);
    }

    public AirProgressCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AirProgressCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AirProgressCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void initialize() {

        //init paint.
        initializePaints();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = getLeft();
        int top = getTop();
        int right = getRight();
        int bottom = getBottom();

        int maxPaintWidth = Math.max(getParameter().getProgressWidth(), getParameter().getTargetProgressWidth());

        //Draw target progress
        RectF rectF = new RectF();
        rectF.left = left + maxPaintWidth;
        rectF.top = top + maxPaintWidth;
        rectF.right = right - maxPaintWidth;
        rectF.bottom = bottom - maxPaintWidth;

        float startAngle = 0f;
        float anglePerUnit = (360f / getParameter().getProgressMax());
        float sweepAngle = anglePerUnit * getParameter().getCurrentProgress();
        boolean useCenter = false;


        //Draw initialize circle
        canvas.drawArc(rectF, DEFAULT_PROGRESS_MIN_ANGLE, DEFAULT_PROGRESS_MAX_ANGLE, false, mAirProgressPaint);

        canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, mAirTargetProgressPaint);

        String text = getParameter().getCurrentProgress() + "/" + getParameter().getProgressMax();

        float viewWidth = right - left;
        float viewHeight = bottom - top;
        float textWidth = mAirProgressTextPaint.measureText(text);
        float textHeight = mAirProgressTextPaint.getTextSize();

        float textCenterX = viewWidth / 2 - textWidth / 2;
        float textCenterY = viewHeight / 2 + textHeight / 2;

        //text
        canvas.drawText(text, textCenterX, textCenterY, mAirProgressTextPaint);
    }

    void initializePaints() {

        mAirProgressPaint = new Paint();
        mAirProgressPaint.setAntiAlias(true);
        mAirProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mAirProgressPaint.setStrokeJoin(Paint.Join.ROUND);
        mAirProgressPaint.setColor(getParameter().getProgressColor());
        mAirProgressPaint.setStyle(Paint.Style.STROKE);
        mAirProgressPaint.setStrokeWidth(getParameter().getProgressWidth());

        mAirTargetProgressPaint = new Paint();
        mAirTargetProgressPaint.setAntiAlias(true);
        mAirTargetProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mAirTargetProgressPaint.setStrokeJoin(Paint.Join.ROUND);
        mAirTargetProgressPaint.setStyle(Paint.Style.STROKE);
        mAirTargetProgressPaint.setColor(getParameter().getTargetProgressColor());
        mAirTargetProgressPaint.setStrokeWidth(getParameter().getTargetProgressWidth());

        mAirProgressTextPaint = new Paint();
        mAirProgressTextPaint.setAntiAlias(true);
        mAirProgressTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mAirProgressTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mAirProgressTextPaint.setStyle(Paint.Style.STROKE);
        mAirProgressTextPaint.setColor(getParameter().getTargetProgressColor());
        mAirProgressTextPaint.setTextSize(30);
        mAirProgressTextPaint.setTextAlign(Paint.Align.LEFT);
        mAirProgressTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

}
