package com.air.common.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author pd_liu 2018年4月3日
 * <p>
 * ScreenUtil
 * </p>
 */
public final class ScreenUtil {

    private ScreenUtil() {
        throw new UnsupportedOperationException("非法的创建对象");
    }

    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point out = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(out);
        } else {
            int width = display.getWidth();
            int height = display.getHeight();
            out.set(width, height);
        }
        return out;
    }

    public static int[] getScreenDispaly(Context context) {
        Point point = getScreenSize(context);

        int[] size = new int[]{point.x, point.y};
        return size;
    }

    /**
     * 把dp转换成px
     *
     * @param context
     *         context.
     * @param dp
     *         dp.
     *
     * @return px.
     */
    public static int dp2px(Context context, int dp) {
        // px = dp * 密度比 0.75 1 1.5 2
        //dpi = 像素密度
        //density = dpi / 160;
        //px = dp * density;
        // 获取当前手机的密度比
        float density = context.getResources().getDisplayMetrics().density;
        // 四舍五入
        return (int) (dp * density + 0.5f);
    }

    /**
     * 把px转换成dp.
     *
     * @param context
     *         context.
     * @param px
     *         px.
     *
     * @return dp.
     */
    public static int px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;

        return (int) (px / density + 0.5f);
    }

    /**
     * 把sp转换成px
     *
     * @param context
     *         context.
     * @param sp
     *         sp.
     *
     * @return px.
     */
    public static int sp2px(Context context, int sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity + 0.5f);
    }

    /**
     * 把px转换成sp
     *
     * @param context
     *         context.
     * @param px
     *         px.
     *
     * @return sp.
     */
    public static int px2sp(Context context, int px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }
}
