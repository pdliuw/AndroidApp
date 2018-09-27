package com.air.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author pd_liu 2018/8/13
 * <p>
 * AppAbstractFragment
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public abstract class AppCommonFragment extends AppAbstractFragment {

    protected String TAG_LOG = getClass().getSimpleName();

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
    /**
     * Click listener.
     */
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickImpl(v);
        }
    };
    /**
     * 是否已经创建过视图
     */
    private boolean mHasCreatedView;
    /**
     * 是否是首次对用户可见
     */
    private boolean mIsFirstVisible;

    /**
     * 设置点击事件
     *
     * @param view
     *         v
     *
     * @see #onClickImpl(View)
     */
    protected void setCommonClickListener(View view) {
        if (view == null) {
            return;
        }
        if (!view.isClickable()) {
            if (view.getBackground() == null) {
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = view.getContext().getTheme();
                int top = view.getPaddingTop();
                int bottom = view.getPaddingBottom();
                int left = view.getPaddingLeft();
                int right = view.getPaddingRight();
                if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                    view.setBackgroundResource(typedValue.resourceId);
                }
                view.setPadding(left, top, right, bottom);
            }
        }
        view.setOnClickListener(mOnClickListener);
    }

    protected void setClickListener(View view) {
        view.setOnClickListener(mOnClickListener);
    }

    /**
     * @param view
     *         v
     *
     * @see #setCommonClickListener(View)
     * 接收点击事件
     */
    protected void onClickImpl(View view) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = LayoutInflater.from(getContext()).inflate(inflateContentViewById(), container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //已经创建完视图
        mHasCreatedView = true;
        //是否是第一次可见
        mIsFirstVisible = true;

        //初始化外部传递的操作
        initialize(savedInstanceState);
        //初始化此页面的视图
        initView(view, savedInstanceState);
        //延迟初始化视图
        if (mHasCreatedView && getUserVisibleHint() && mIsFirstVisible) {
            onFragmentVisibleToUser(true);
            onFragmentFirstVisibleToUser();
            mIsFirstVisible = false;
        }
        //初始化此页面的数据
        initData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //延迟初始化视图
        if (mHasCreatedView && getUserVisibleHint() && mIsFirstVisible) {
            onFragmentVisibleToUser(isVisibleToUser);
            onFragmentFirstVisibleToUser();
            mIsFirstVisible = false;
        }

        //visible to user.
        if (mHasCreatedView && !mIsFirstVisible) {

            onFragmentVisibleToUser(isVisibleToUser);
        }
    }

    /**
     * Inflate the fragment content view.
     *
     * @return the activity content resourceId.
     */
    protected abstract int inflateContentViewById();

    /**
     * 初始化创建对象的初始操作
     *
     * @param savedInstanceState
     *         bundle.
     */
    protected abstract void initialize(@Nullable Bundle savedInstanceState);

    /**
     * 初始化视图
     *
     * @param view
     *         rootView
     * @param savedInstanceState
     *         bundle.
     */
    protected abstract void initView(@NonNull View view, @Nullable Bundle savedInstanceState);

    /**
     * Callback that Fragment first visible to user.
     */
    protected void onFragmentFirstVisibleToUser() {
        //初始化此页面用于刷新的数据
        refreshData();
    }

    /**
     * Callback that Fragment visible to user.
     *
     * @param isVisibleToUser
     *         whether visible to user.
     */
    protected void onFragmentVisibleToUser(boolean isVisibleToUser) {
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 刷新当前页面时所需要加载的数据
     * 当前页面第一次加载时需要的数据
     */
    protected abstract void refreshData();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void startActivity(Intent intent) {
        startActivityTransition(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
    }

    protected void startActivityTransition(Intent intent) {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

}
