package com.air.widget.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.air.widget.R;

/**
 * @author pd_liu 2018/10/17
 * <p>
 * CircleImageView
 * </p>
 * <p>
 * Guide:
 * 1、Supported circle image.
 * </p>
 */
public class AirCircleImageView extends ImageView {

    private Paint mDrawablePaint;

    /**
     * Bound value.
     */
    private Paint mBoundPaint;
    private int mBoundColor;
    private int mBoundWidth;
    private int mBoundProgressMax;
    private int mBoundProgress;

    private static final int ZERO_DEFAULT = 0;
    private static final int BOUND_WIDTH_DEFAULT = ZERO_DEFAULT;
    private static final int BOUND_COLOR_DEFAULT = Color.RED;
    private static final int BOUND_PROGRESS_MAX_DEFAULT = 100;
    private static final int BOUND_PROGRESS_DEFAULT = 100;
    private static final float SWEEP_ANGLE_MAX_DEFAULT = 360F;

    public AirCircleImageView(Context context) {
        this(context, null);
    }

    public AirCircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AirCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AirCircleImageView);

        mBoundWidth = typedArray.getDimensionPixelSize(R.styleable.AirCircleImageView_air_bound_width, BOUND_WIDTH_DEFAULT);
        mBoundColor = typedArray.getColor(R.styleable.AirCircleImageView_air_bound_color, BOUND_COLOR_DEFAULT);
        mBoundProgressMax = typedArray.getInteger(R.styleable.AirCircleImageView_air_bound_progress_max, BOUND_PROGRESS_MAX_DEFAULT);
        mBoundProgress = typedArray.getInteger(R.styleable.AirCircleImageView_air_bound_progress, BOUND_PROGRESS_DEFAULT);

        typedArray.recycle();

        initializePaints();

    }

    private void initializePaints() {

        mDrawablePaint = new Paint();

        /*
        Bound
         */
        mBoundPaint = new Paint();
        mBoundPaint.setAntiAlias(true);
        mBoundPaint.setStrokeWidth(mBoundWidth);
        mBoundPaint.setColor(mBoundColor);
        mBoundPaint.setStyle(Paint.Style.STROKE);
        mBoundPaint.setStrokeCap(Paint.Cap.ROUND);
        mBoundPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        //Canvas circle image.
        canvas.drawColor(Color.WHITE);
        drawCircleImage(canvas);

        //Draw
        canvas.save();
        drawCircleBounds(canvas);
    }

    private void drawCircleBounds(Canvas canvas) {
        if (mBoundWidth == BOUND_WIDTH_DEFAULT) {
            return;
        }

        float radius = (getWidth() - getPaddingLeft() - getPaddingRight() - (mBoundWidth * 2)) / 2f;
        float circleX = getPaddingLeft() + mBoundWidth + radius;


        RectF rectF = new RectF();
        rectF.left = circleX - radius;
        rectF.top = circleX - radius;
        rectF.right = circleX + radius;
        rectF.bottom = circleX + radius;

        float perProgressAngle = SWEEP_ANGLE_MAX_DEFAULT / mBoundProgressMax;

        float startAngle = 0f;
        float sweepAngle = perProgressAngle * mBoundProgress;
        boolean useCenter = false;

        canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, mBoundPaint);
    }

    private void drawCircleImage(Canvas canvas) {

        Drawable drawable = getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();


        float radiusX = (getWidth() - getPaddingLeft() - getPaddingRight() - (mBoundWidth * 2)) / 2f;
        float radiusY = (getHeight() - getPaddingTop() - getPaddingBottom() - (mBoundWidth * 2)) / 2f;
        float radius = Math.min(radiusX, radiusY);

        int diameter = (int) (radius * 2 + 0.5);

        float circleX = getPaddingLeft() + mBoundWidth + radius;
        float circleY = getPaddingTop() + mBoundWidth + radius;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);

        Shader shader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mDrawablePaint.setShader(shader);

        canvas.drawCircle(circleX, circleY, radius, mDrawablePaint);

    }
}
