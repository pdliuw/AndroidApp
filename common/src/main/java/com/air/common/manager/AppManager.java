package com.air.common.manager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedList;

/**
 * @author pd_liu on 2017/10/31.
 *         <p>
 *         AppManager.
 *         对Activity进行全局管理.{@link ActivityLifecycleManager}.
 *         销毁TopActivity {@link #popActivity(Activity)}.
 *         创建TopActivity {@link #pushActivity(Activity)}.
 *         退出应用{@link #exitApp(Activity)}.
 *         </p>
 */

public final class AppManager {

    private static final String KEY_TAG = "AppManager";

    private final LinkedList<Activity> mStack;

    private AppManager() {
        mStack = new LinkedList<>();
    }

    /**
     * Get app manager instance.
     *
     * @return instance.
     */
    public static AppManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * create instance.
     */
    private static class Holder {
        private static final AppManager INSTANCE = new AppManager();
    }

    /**
     * Add activity to the stack.
     * 将Activity推送到Stack中保存.
     *
     * @param activity activity.
     * @see #popActivity(Activity) .
     */
    public void pushActivity(@NonNull Activity activity) {

        if (activity != null) {
            mStack.add(activity);
        }

        for (int i = 0; i < mStack.size(); i++) {
            String name = mStack.get(i).getClass().getSimpleName();

        }
    }

    /**
     * Pop activity and finish this activity.
     * 将Activity弹出Stack，并且销毁所弹出Activity对象.
     *
     * @param activity activity.
     * @see #pushActivity(Activity) .
     */
    public void popActivity(@NonNull Activity activity) {

        if (activity != null) {
            mStack.remove(activity);
        }

        for (int i = 0; i < mStack.size(); i++) {
            String name = mStack.get(i).getClass().getSimpleName();

        }
    }

    /**
     * 退出App
     *
     * @param activity activity.
     */
    public void exitApp(@Nullable Activity activity) {


        for (int i = 0; i < mStack.size(); i++) {
            mStack.get(i).finish();

        }
    }

    /**
     * 除了某一个Activity，其余的都退出.
     *
     * @param elseActivity {@link Activity}
     */
    public void finishAllElse(Activity elseActivity) {

        for (int i = 0; i < mStack.size(); i++) {
            Activity element = mStack.get(i);
            if (element == elseActivity) {
                //结束本次循环，开始下次循环操作
                continue;
            }
            element.finish();
        }

    }

}
