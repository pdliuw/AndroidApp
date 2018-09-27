package com.air.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * @author pd_liu 2018/9/19
 * <p>
 * CircularRevealAnimator
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class CircularRevealAnimator {

    private static final int delayTimeMS = 1000;

    public static Animator createRevealWithDelay(View view, int centerX, int centerY, float startRadius, float endRadius) {

        Animator delayAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, startRadius);
        delayAnimator.setDuration(delayTimeMS);
        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(delayAnimator, revealAnimator);
        return set;
    }
}
