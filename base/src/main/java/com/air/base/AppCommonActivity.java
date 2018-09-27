package com.air.base;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author pd_liu 2018/8/9
 * <p>
 * AppCommonActivity
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public abstract class AppCommonActivity extends AppAbstractActivity {

    /**
     * Animation default duration.
     */
    private static final int ANIMATION_DURATION_DEFAULT = 500;

    /**
     * Status bar size.
     */
    private View mStatusBarView;
    private Rect mRectScreenSize;
    private Rect mRectDecorView;

    /**
     * Tag
     */
    protected String TAG_LOG = this.getClass().getSimpleName();
    /**
     * 广播接收者接受广播信息
     */
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
    /**
     * Click listener 的统一管理
     */
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickImpl(v);
        }
    };
    private View.OnClickListener mOnNavClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isOpenNavClickImp()) {

                onNavClickImp(v);
            }
        }
    };

    protected boolean isOpenNavClickImp() {
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    /**
     * 支持启动页面带动画
     * {@link #showDefaultActivityTransition()}
     *
     * @param intent
     *         意图
     */
    public void startActivityTransition(Intent intent) {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

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
                if (theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, typedValue, true)) {
                    view.setBackgroundResource(typedValue.resourceId);
                }
                view.setPadding(left, top, right, bottom);
            }
        }
        view.setOnClickListener(mOnClickListener);

    }

    protected void setStateListAnimator(View animView, @AnimRes int id){
        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(this, id);
        animView.setStateListAnimator(stateListAnimator);
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

    /**
     * Toolbar back click.
     *
     * @param view
     */
    protected void onNavClickImp(View view) {
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
        每次Activity一创建就会执行，一般只执行一次；
         */
        /*
        invalidateOptionsMenu()刷新menu里的选项里内容，它会调用onCreateOptionsMenu(Menu menu)方法
         */
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*
        每次menu被打开时，该方法就会执行一次；
         */
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        每次menu菜单项被点击时，该方法就会执行一次；
         */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        /*
        创建控件绑定的上下文菜单menu，根据方法里的View参数识别是哪个控件绑定
         */
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        /*
        点击控件绑定的上下菜单menu的内容项
         */
        return super.onContextItemSelected(item);
    }

    protected boolean showAppActionBar() {
        return true;
    }

    /**
     * Inflate the activity content view.
     *
     * @return the activity content resourceId.
     */
    protected abstract int inflateContentViewById();

    /**
     * Inflate the activity content view.
     *
     * @return the activity content view object.
     */
    protected View inflateContentViewByView() {
        return null;
    }

    /**
     * 初始化默认值
     */
    protected abstract void initialize();

    /**
     * 初始化视图
     *
     * @param savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        setContentView
         */
        if (inflateContentViewByView() == null) {
            setContentView(inflateContentViewById());
        } else {
            setContentView(inflateContentViewByView());
        }


        //StatusBar
        mStatusBarView = findViewById(R.id.app_common_status_bar);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.app_common_tool_bar);
        if (toolbar == null) {
            /*
            输出：请在xml中补齐actionBar
             */
        } else {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            //不显示默认的Title
            actionBar.setDisplayShowTitleEnabled(true);
            //不显示默认的Home（an activity icon or logo.）
            actionBar.setDisplayShowHomeEnabled(false);
            //是否显示左上角的返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(mOnNavClickListener);

            //Title
            TextView titleTv = findViewById(R.id.app_common_title_tv);
            titleTv.setVisibility(View.GONE);
            setCommonClickListener(titleTv);
            if (titleTv == null) {
                /*
                输出：Title is null.
                 */
            } else {
                titleTv.setText(getTitle());
            }


            if (showAppActionBar()) {

            } else {
                toolbar.setVisibility(View.GONE);
                mStatusBarView.setVisibility(View.GONE);
            }
        }

        if (mStatusBarView == null) {
            /*
            输出：StatusBar为空
             */
        } else {
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        }

        showFadeActivityTransition();


        initialize();
        initView(savedInstanceState);
        initData();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        /*
        初始化状态栏尺寸
         */
        if (hasFocus) {
            initializeStatusBarSize();
        }
    }

    @Override
    public void onBackPressed() {

        finishRelease();
    }

    /**
     * 关闭并释放
     */
    protected void finishRelease() {
        if (isFinishing()) {
            //如果正在finishing current activity，那么返回
            return;
        }
        supportFinishAfterTransition();
    }


    /**
     * Add current activity with transition animation.
     */
    protected void showDefaultActivityTransition() {
        showSlideActivityTransition();
    }

    /**
     * Add current activity with transition animation.
     */
    protected void showSlideActivityTransition() {
        //Animation.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide(Gravity.RIGHT).setDuration(ANIMATION_DURATION_DEFAULT));
        }
    }

    /**
     * Add current activity with transition animation.
     */
    protected void showBoundActivityTransition() {
        //Animation.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new ChangeBounds().setDuration(ANIMATION_DURATION_DEFAULT));
        }
    }

    /**
     * Add current activity with transition animation.
     */
    protected void showFadeActivityTransition() {
        //Animation.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade().setDuration(ANIMATION_DURATION_DEFAULT));
        }
    }

    /**
     * Add current activity with transition animation.
     */
    protected void showExplodeActivityTransition() {
        //Animation.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode().setDuration(ANIMATION_DURATION_DEFAULT));
        }
    }

    private void initializeStatusBarSize() {


        boolean isContinue = (mRectScreenSize == null || mRectDecorView == null) && (mStatusBarView != null);
        if (!isContinue) {
            return;
        }
        mRectScreenSize = new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(mRectScreenSize);

        mRectDecorView = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(mRectDecorView);

        int statusHeight = mRectScreenSize.height() - mRectDecorView.height();


        ViewGroup.LayoutParams statusParams = mStatusBarView.getLayoutParams();

        statusParams.height = statusHeight;
        mStatusBarView.setLayoutParams(statusParams);
    }
}
