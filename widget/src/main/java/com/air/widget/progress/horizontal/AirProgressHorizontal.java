package com.air.widget.progress.horizontal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.air.widget.progress.AirProgress;

/**
 * @author pd_liu 2018/9/29
 * <p>
 * AirProgressHorizontal
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class AirProgressHorizontal extends AirProgress {

    private static final String TAG = "AirProgressHorizontal";

    /**
     * Paints
     */
    private Paint mAirProgressPaint;
    private Paint mAirTargetProgressPaint;
    private Paint mAirProgressTextPaint;

    public AirProgressHorizontal(Context context) {
        super(context);
    }

    public AirProgressHorizontal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AirProgressHorizontal(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AirProgressHorizontal(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void initialize() {

        //init paint.
        initializePaints();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = getLeft();
        int right = getRight();

        int viewWidth = getWidth();
        float viewHeight = getHeight();

        int maxPaintWidth = Math.max(getParameter().getProgressWidth(), getParameter().getTargetProgressWidth());

        //Draw target progress

        float paintHalfHeight = viewHeight / 2f;

        int targetProgressStopX = getParameter().getCurrentProgress() * viewWidth / getParameter().getProgressMax();

        //Draw line.
        canvas.drawLine(left, paintHalfHeight, right, paintHalfHeight, mAirProgressPaint);

        canvas.drawLine(left, paintHalfHeight, targetProgressStopX, paintHalfHeight, mAirTargetProgressPaint);


        String text = getParameter().getCurrentProgress() + "/" + getParameter().getProgressMax();


        float textWidth = mAirProgressTextPaint.measureText(text);
        float textHeight = mAirProgressTextPaint.getTextSize();

        float textCenterX = targetProgressStopX - textWidth;
        float textCenterY = viewHeight / 2 + textHeight / 2 + (maxPaintWidth * 1f);

        /*
        处理边缘越界问题
         */
        textCenterX = textCenterX < 0 ? 0 : textCenterX;
        textCenterY = textCenterY < 0 ? 0 : textCenterY;
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
