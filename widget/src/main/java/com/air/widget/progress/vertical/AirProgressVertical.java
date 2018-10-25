package com.air.widget.progress.vertical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.air.widget.progress.AirProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pd_liu 2018/10/15
 * <p>
 * AirProgressVertical
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class AirProgressVertical extends AirProgress {

    /**
     * Paints
     */
    private Paint mAirProgressPaint;
    private Paint mAirTargetProgressPaint;
    private Paint mAirProgressTextPaint;
    private List<String> mDrawTexts = new ArrayList<>(10);

    public AirProgressVertical(Context context) {
        super(context);
    }

    public AirProgressVertical(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AirProgressVertical(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AirProgressVertical(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void initialize() {
        //init paint.
        initializePaints();
        mDrawTexts = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int top = getTop();
        int bottom = getBottom();

        int viewWidth = getWidth();
        int viewHeight = getHeight();

        int maxPaintWidth = Math.max(getParameter().getProgressWidth(), getParameter().getTargetProgressWidth());

        //Draw target progress

        float paintHalfWidth = viewWidth / 2f;

        int targetProgressStopY = getParameter().getCurrentProgress() * viewHeight / getParameter().getProgressMax();

        //Draw line.
        canvas.drawLine(paintHalfWidth, top, paintHalfWidth, bottom, mAirProgressPaint);

        canvas.drawLine(paintHalfWidth, top, paintHalfWidth, targetProgressStopY, mAirTargetProgressPaint);

        mDrawTexts.clear();
        mDrawTexts.add(String.valueOf(getParameter().getCurrentProgress()));
        mDrawTexts.add(String.valueOf("——"));
        mDrawTexts.add(String.valueOf(getParameter().getProgressMax()));


        float textHeight = mAirProgressTextPaint.getTextSize();

        float textCenterX = paintHalfWidth + (maxPaintWidth * 1f);
        float textCenterY = targetProgressStopY - (textHeight * mDrawTexts.size());

        /*
        处理边缘越界问题
         */
        //Max
        textCenterX = Math.max(textCenterX, 0);
        //Min
        textCenterX = Math.min(textCenterX, getRight());
        //Max
        textCenterY = Math.max(textCenterY, 0);

        //Draw text.
        for (int i = 0, size = mDrawTexts.size(); i < size; i++) {
            String drawedText = mDrawTexts.get(i);
            canvas.drawText(drawedText, textCenterX, (textCenterY + (textHeight * (i + 1))), mAirProgressTextPaint);
        }
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
