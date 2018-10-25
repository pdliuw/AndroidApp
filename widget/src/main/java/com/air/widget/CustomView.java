package com.air.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author pd_liu 2018/10/19
 * <p>
 * CustomView
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class CustomView extends View {

    private static PorterDuff.Mode[] sModes = PorterDuff.Mode.values();

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //    @Override
    //    protected void onDraw(Canvas canvas) {
    //        super.onDraw(canvas);
    //        //保存当前画布大小即整屏,画布不透明
    //        canvas.saveLayer(getLeft(), getTop(), getRight(), getBottom(), null);
    //
    //        Paint mPaint = new Paint();
    //        mPaint.setAntiAlias(true);
    //        mPaint.setColor(Color.BLUE);
    //
    //        //圆点位置
    //        float cx = getLeft() + 200;
    //        float cy = getTop() + 200;
    //        float radius = 200;
    //
    //        canvas.drawCircle(cx, cy, radius, mPaint);
    //
    //        //矩形的宽度、高度
    //        float rectSize = 400;
    //
    //        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    //        mPaint.setColor(Color.YELLOW);
    //
    //
    //        canvas.drawRect(cx, cy, cx + rectSize, cy + rectSize, mPaint);
    //
    //        mPaint.setXfermode(null);
    //
    //        canvas.restore();
    //    }


    // create a bitmap with a circle, used for the "dst" image
    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);


        Paint paint = new Paint();
        paint.setFilterBitmap(false);

        // draw the src/dst example into our offscreen bitmap
        int sc = canvas.saveLayerAlpha(getLeft(), getTop(), getRight(), getBottom(), 200);

        canvas.drawBitmap(makeDst(getWidth(), getHeight()), 0, 0, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));

        canvas.drawBitmap(makeSrc(getWidth(), getHeight()), 0, 0, paint);

        paint.setXfermode(null);
        canvas.restoreToCount(sc);

    }

}
