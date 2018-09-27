package com.air.androidapp;

import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.air.animation.PictureViewer;
import com.air.animation.ViewMoveAnimator;
import com.air.base.AppCommonActivity;


public class AnimationActivity extends AppCommonActivity {


    @Override
    protected void onClickImpl(View view) {
        super.onClickImpl(view);

        int clickId = view.getId();
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        switch (clickId) {

            case R.id.start_vector_anim_btn:
                //启动矢量动画
                //                ViewMoveAnimator.startMoveX(view, 0f,-100f,100f,-50f,50f);
                //                ViewMoveAnimator.startMoveXWithPath(view, 1000f);
                //                ViewMoveAnimator.startPath(view);

                Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
                Keyframe kf1 = Keyframe.ofFloat(0.5f, 360f);
                Keyframe kf2 = Keyframe.ofFloat(1f, 0f);

                PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
                ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(view, pvhRotation);
                rotationAnim.setDuration(5000);
                rotationAnim.start();

                break;
            case R.id.start_transition_anim_btn:
                Button animTestedBtn = findViewById(R.id.transition_anim_tested_btn);
                if (animTestedBtn.getVisibility() == View.VISIBLE) {
                    animTestedBtn.setVisibility(View.GONE);
                } else if (animTestedBtn.getVisibility() == View.GONE) {
                    animTestedBtn.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.start_vector_drawable_img:



                break;
            default:

        }


    }

    @Override
    protected int inflateContentViewById() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        ViewGroup group = findViewById(R.id.group);


        Button vectorAnimBtn = findViewById(R.id.start_vector_anim_btn);
        setCommonClickListener(vectorAnimBtn);

        Button startTransitionAnimBtn = findViewById(R.id.start_transition_anim_btn);
        setCommonClickListener(startTransitionAnimBtn);

        Button animTestedBtn = findViewById(R.id.transition_anim_tested_btn);
        animTestedBtn.setVisibility(View.GONE);

        ImageView startVectorImg = findViewById(R.id.start_vector_drawable_img);
        setCommonClickListener(startVectorImg);

        AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) startVectorImg.getDrawable();
        vectorDrawable.start();


        LayoutTransition layoutTransition = new LayoutTransition();

        ObjectAnimator rotationX = ObjectAnimator.ofFloat(null, View.ROTATION_X, 180f, 0f);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(null, View.ROTATION_Y, 90f, 0f);
        rotationY.setDuration(layoutTransition.getDuration(LayoutTransition.APPEARING));

        layoutTransition.setAnimator(LayoutTransition.APPEARING, rotationY);

        group.setLayoutTransition(layoutTransition);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshData() {

    }

    private void startVectorAnimation() {

    }
}
