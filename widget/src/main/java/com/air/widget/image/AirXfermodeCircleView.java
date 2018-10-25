package com.air.widget.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author pd_liu 2018/10/23
 * <p>
 * AirXfermodeCircleView
 * </p>
 * <p>
 * Guide:
 * 1、CircleImageView.
 * <com.air.widget.image.AirXfermodeCircleView
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:paddingLeft="10dp"
 * android:paddingRight="10dp"
 * android:paddingBottom="30dp"
 * android:background="@color/colorPrimary"
 * android:src="@drawable/we_chat" />
 * </p>
 */


public class AirXfermodeCircleView extends ImageView {

    private static final String TAG = "AirXfermodeCircleView";

    private static final int MIN_SIZE_DEFAULT = 100;

    private Paint mDrawablePaint;

    public AirXfermodeCircleView(Context context) {
        this(context, null);
    }

    public AirXfermodeCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirXfermodeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AirXfermodeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


        initializePaints();
    }

    private void initializePaints() {

        mDrawablePaint = new Paint();
        mDrawablePaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                widthSize = dpToPx(MIN_SIZE_DEFAULT);
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                heightSize = dpToPx(MIN_SIZE_DEFAULT);
                break;
        }

        int minSize = Math.min(widthSize, heightSize);

        //Update measured dimension.
        setMeasuredDimension(minSize, minSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Canvas circle image.
        drawCircleImage(canvas);

    }

    /**
     * Draw circle image with paint PorterDuff.
     *
     * @param canvas
     *         canvas.
     */
    private void drawCircleImage(Canvas canvas) {

        //Save layer with parameter.
        canvas.saveLayerAlpha(getLeft(), getTop(), getRight(), getBottom(), 200);

        //Get drawable source.
        Drawable drawable = getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        //Computer circle image radius.
        float radiusX = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2f;
        float radiusY = (getHeight() - getPaddingTop() - getPaddingBottom()) / 2f;
        float radius = Math.min(radiusX, radiusY);

        //Computer
        Rect rectF = new Rect();
        rectF.left = getLeft() + getPaddingLeft();
        rectF.top = getTop() + getPaddingTop();
        rectF.right = getRight() - getPaddingRight();
        rectF.bottom = getBottom() - getPaddingBottom();

        int targetBitmapWidth = rectF.right - rectF.left;
        int targetBitmapHeight = rectF.bottom - rectF.top;

        int diameter = (int) (radius * 2 + 0.5);

        float circleX = getPaddingLeft() + radius;
        float circleY = getPaddingTop() + radius;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, targetBitmapWidth, targetBitmapHeight, false);

        /*
        Draw destination bitmap.
         */
        Bitmap bitmapDestination = Bitmap.createBitmap(targetBitmapWidth, targetBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvasDestination = new Canvas(bitmapDestination);
        //Draw circle.
        canvasDestination.drawCircle(circleX, circleY, radius, mDrawablePaint);
        //Draw destination bitmap.
        canvas.drawBitmap(bitmapDestination, 0, 0, mDrawablePaint);

        /*
        Set  paint's porterDuffMode
         */
        mDrawablePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        /*
        Draw source bitmap.
         */
        canvas.drawBitmap(scaledBitmap, rectF, rectF, mDrawablePaint);

        //Reset mode value.
        mDrawablePaint.setXfermode(null);
        //Restore.
        canvas.restore();
    }


    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5);
    }
}
