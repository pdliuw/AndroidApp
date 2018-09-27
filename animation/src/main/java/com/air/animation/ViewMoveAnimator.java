package com.air.animation;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;

/**
 * @author pd_liu 2018/8/29
 * <p>
 * ViewMoveAnimator
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class ViewMoveAnimator {

    public static void startMoveX(View view, float... values) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, values);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.start();

    }

    public static void startPath(View view){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
            animator.setDuration(2000);
            animator.start();
        } else {
            // Create animator without using curved path
        }
    }

    public static void startMoveXWithPath(View view, float... values) {

        PathInterpolator interpolator = createPathInterpolator();
        if (interpolator != null) {

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, values);
            animator.setInterpolator(interpolator);
            animator.setDuration(2000);
            animator.start();

        }
    }

    private static PathInterpolator createPathInterpolator() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            float left = 0f;
            float top = 0f;
            float right = 1000f;
            float bottom = 1000f;
            float startAngle = 270f;
            float sweepAngle = 180f;

            RectF rectF = new RectF(left, top, right, bottom);

            Path path = new Path();
            //Add arc
            path.arcTo(rectF, startAngle, sweepAngle,true);

            PathInterpolator pathInterpolator = new PathInterpolator(path);

            return pathInterpolator;
        } else
            return null;
    }
}
