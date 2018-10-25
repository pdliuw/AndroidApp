package com.air.widget.time;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author pd_liu 2018/10/19
 * <p>
 * AirHourglassView
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class AirHourglassView extends View {

    private static final String TAG = "AirHourglassView";

    private Paint mHourglassArcPaint;
    private Paint mHourglassDecorPaint;

    private float mHourglassPaintWidth = 10;

    public AirHourglassView(Context context) {
        this(context, null);
    }

    public AirHourglassView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirHourglassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AirHourglassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initializePaints();
    }

    private void initializePaints() {

        mHourglassArcPaint = new Paint();
        mHourglassArcPaint.setAntiAlias(true);
        mHourglassArcPaint.setColor(Color.RED);
        mHourglassArcPaint.setStrokeWidth(mHourglassPaintWidth);
        mHourglassArcPaint.setStrokeJoin(Paint.Join.ROUND);
        mHourglassArcPaint.setStrokeCap(Paint.Cap.ROUND);
        mHourglassArcPaint.setStyle(Paint.Style.STROKE);


        mHourglassDecorPaint = new Paint();
        mHourglassDecorPaint.setAntiAlias(true);
        mHourglassDecorPaint.setColor(Color.RED);
        mHourglassDecorPaint.setStrokeWidth(mHourglassPaintWidth);
        mHourglassDecorPaint.setStrokeJoin(Paint.Join.ROUND);
        mHourglassDecorPaint.setStrokeCap(Paint.Cap.ROUND);
        mHourglassDecorPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        drawGlass(canvas);
    }

    private void drawGlass(Canvas canvas) {

        /*
        Canvas rectangle.
         */
        int drawableLeftBound = getLeft() + getPaddingLeft();
        int drawableTopBound = getTop() + getPaddingTop();
        int drawableRightBound = getRight() - getPaddingRight();
        int drawableBottomBound = getBottom() - getPaddingBottom();

        /*
        Save drawable rectangle layer.
         */
        RectF rectF = new RectF();
        rectF.left = drawableLeftBound;
        rectF.top = drawableTopBound;
        rectF.right = drawableRightBound;
        rectF.bottom = drawableBottomBound;
        canvas.saveLayerAlpha(rectF, 200);

        float drawableDiameterY = (rectF.bottom - rectF.top);
        float drawableRadiusY = drawableDiameterY / 2f;

        float drawableDiameterX = (rectF.right - rectF.left);
        float drawableRadiusX = drawableDiameterX / 2f;

        /*
        中心位置
         */
        float hourglassCenterX = rectF.left + drawableRadiusX;
        float hourglassCenterY = rectF.top + drawableRadiusY;

        /*
        Bounds
         */
        float hourglassLeftBound = rectF.left + mHourglassPaintWidth;
        float hourglassTopBound = rectF.top + mHourglassPaintWidth;
        float hourglassRightBound = rectF.right + mHourglassPaintWidth;
        float hourglassBottomBound = rectF.bottom + mHourglassPaintWidth;
        //Arc started bounds.
        float hourglassArcStartedPointLeftBound = rectF.left + drawableDiameterY / 8f;
        float hourglassArcStartedPointTopBound = rectF.top;
        float hourglassArcStartedPointRightBound = rectF.right - drawableDiameterY / 8f;
        float hourglassArcStartedPointBottomBound = rectF.bottom;

        /*
        Eight control point
         */
        PointF hourglassArcOneControlPoint = new PointF();
        hourglassArcOneControlPoint.x = hourglassLeftBound;
        hourglassArcOneControlPoint.y = drawableTopBound + drawableRadiusY / 4F;

        PointF hourglassArcTwoControlPoint = new PointF();
        hourglassArcTwoControlPoint.x = hourglassLeftBound;
        hourglassArcTwoControlPoint.y = drawableTopBound + drawableRadiusY / 2F;

        PointF hourglassArcThreeControlPoint = new PointF();
        hourglassArcThreeControlPoint.x = hourglassRightBound;
        hourglassArcThreeControlPoint.y = drawableTopBound + drawableRadiusY / 4F;

        PointF hourglassArcFourControlPoint = new PointF();
        hourglassArcFourControlPoint.x = hourglassRightBound;
        hourglassArcFourControlPoint.y = drawableTopBound + drawableRadiusY / 2F;

        PointF hourglassArcFiveControlPoint = new PointF();
        hourglassArcFiveControlPoint.x = hourglassLeftBound;
        hourglassArcFiveControlPoint.y = drawableBottomBound - drawableRadiusY / 4F;

        PointF hourglassArcSixControlPoint = new PointF();
        hourglassArcSixControlPoint.x = hourglassLeftBound;
        hourglassArcSixControlPoint.y = drawableBottomBound - drawableRadiusY / 2F;

        PointF hourglassArcSevenControlPoint = new PointF();
        hourglassArcSevenControlPoint.x = hourglassRightBound;
        hourglassArcSevenControlPoint.y = drawableBottomBound - drawableRadiusY / 4F;

        PointF hourglassArcEightControlPoint = new PointF();
        hourglassArcEightControlPoint.x = hourglassRightBound;
        hourglassArcEightControlPoint.y = drawableBottomBound - drawableRadiusY / 2F;

        Paint paint = new Paint();
        paint.setStrokeWidth(15);
        paint.setColor(Color.GREEN);
        canvas.drawPoint(hourglassArcOneControlPoint.x, hourglassArcOneControlPoint.y, paint);
        canvas.drawPoint(hourglassArcTwoControlPoint.x, hourglassArcTwoControlPoint.y, paint);
        canvas.drawPoint(hourglassArcThreeControlPoint.x, hourglassArcThreeControlPoint.y, paint);
        canvas.drawPoint(hourglassArcFourControlPoint.x, hourglassArcFourControlPoint.y, paint);
        canvas.drawPoint(hourglassArcFiveControlPoint.x, hourglassArcFiveControlPoint.y, paint);
        canvas.drawPoint(hourglassArcSixControlPoint.x, hourglassArcSixControlPoint.y, paint);
        canvas.drawPoint(hourglassArcSevenControlPoint.x, hourglassArcSevenControlPoint.y, paint);
        canvas.drawPoint(hourglassArcEightControlPoint.x, hourglassArcEightControlPoint.y, paint);

        /*
        Glass top.
         */
        //Top left
        Path glassArcPathTopLeft = new Path();
        glassArcPathTopLeft.moveTo(hourglassArcStartedPointLeftBound, hourglassArcStartedPointTopBound);
        glassArcPathTopLeft.cubicTo(hourglassArcOneControlPoint.x, hourglassArcOneControlPoint.y, hourglassArcTwoControlPoint.x, hourglassArcTwoControlPoint.y, hourglassCenterX, hourglassCenterY);

        Path glassArcPathTopCenter = new Path();
        glassArcPathTopCenter.moveTo(getRight() / 8f, getTop());
        glassArcPathTopCenter.lineTo(hourglassRightBound - getRight() / 8f, getTop());
        glassArcPathTopCenter.lineTo(hourglassCenterX, hourglassCenterY);


        //Top right.
        Path glassArcPathTopRight = new Path();
        glassArcPathTopRight.moveTo(hourglassArcStartedPointRightBound, hourglassArcStartedPointTopBound);
        glassArcPathTopRight.cubicTo(hourglassArcThreeControlPoint.x, hourglassArcThreeControlPoint.y, hourglassArcFourControlPoint.x, hourglassArcFourControlPoint.y, hourglassCenterX, hourglassCenterY);

        /*
        Glass bottom.
         */
        //Bottom left.
        Path glassArcPathBottomLeft = new Path();
        glassArcPathBottomLeft.moveTo(hourglassArcStartedPointLeftBound, hourglassArcStartedPointBottomBound);
        glassArcPathBottomLeft.cubicTo(hourglassArcFiveControlPoint.x, hourglassArcFiveControlPoint.y, hourglassArcSixControlPoint.x, hourglassArcSixControlPoint.y, hourglassCenterX, hourglassCenterY);

        //Bottom right.
        Path glassArcPathBottomRight = new Path();
        glassArcPathBottomRight.moveTo(hourglassArcStartedPointRightBound, hourglassArcStartedPointBottomBound);
        glassArcPathBottomRight.cubicTo(hourglassArcSevenControlPoint.x, hourglassArcSevenControlPoint.y, hourglassArcEightControlPoint.x, hourglassArcEightControlPoint.y, hourglassCenterX, hourglassCenterY);


        /*
        The whole glass.
         */
        Path glassPath = new Path();
        glassPath.addPath(glassArcPathTopLeft);
        glassPath.addPath(glassArcPathTopRight);
        glassPath.addPath(glassArcPathBottomLeft);
        glassPath.addPath(glassArcPathBottomRight);


        canvas.drawPath(glassPath, mHourglassArcPaint);


        //Close top bottom bounds.
        Path glassPathTopClosed = new Path();
        glassPathTopClosed.moveTo(hourglassArcStartedPointLeftBound, hourglassArcStartedPointTopBound);
        glassPathTopClosed.lineTo(hourglassArcStartedPointRightBound, hourglassArcStartedPointTopBound);

        Path glassPathBottomClosed = new Path();
        glassPathBottomClosed.moveTo(hourglassArcStartedPointLeftBound, hourglassArcStartedPointBottomBound);
        glassPathBottomClosed.lineTo(hourglassArcStartedPointRightBound, hourglassArcStartedPointBottomBound);


        Path glassDecorPath = new Path();
        glassDecorPath.addPath(glassPathTopClosed);
        glassDecorPath.addPath(glassPathBottomClosed);

        canvas.drawPath(glassDecorPath, mHourglassDecorPaint);

    }
}
