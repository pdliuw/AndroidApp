package com.air.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.air.androidapp.fragment.BlackFragment;
import com.air.base.AppCommonActivity;

/**
 * @author pd_liu 2018/9/12
 * <p>
 * SampleFragmentActivity
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class SampleFragmentActivity extends AppCommonActivity {

    @Override
    protected void onClickImpl(View view) {
        super.onClickImpl(view);

        int clickId = view.getId();

        switch (clickId) {

            case R.id.switch_fragment_btn:
                /*
                切换Fragment的显示、隐藏
                 */
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentByTag("black");
                if (fragment != null) {
                    boolean isVisible = fragment.isVisible();
                    if (isVisible) {
                        transaction.hide(fragment);
                    } else {
                        transaction.show(fragment);
                    }

                    //提交事务
                    transaction.commit();
                }
                break;

            case R.id.start_new_activity:
                startActivityTransition(new Intent(this, MainActivity.class));
                break;

            default:
        }
    }

    @Override
    protected int inflateContentViewById() {
        return R.layout.activity_sample_fragment;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        Button switchBtn = findViewById(R.id.switch_fragment_btn);
        setCommonClickListener(switchBtn);

        Button startNewActivity = findViewById(R.id.start_new_activity);
        setCommonClickListener(startNewActivity);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loggg("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        loggg("onStart");

//        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BlackFragment(), "black").addToBackStack(null).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loggg("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        loggg("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        loggg("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loggg("onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        loggg("onNewIntent");
    }

    void loggg(String message) {
        Log.e(this.getClass().getSimpleName(), "activity life cycle = " + message);
    }
}
