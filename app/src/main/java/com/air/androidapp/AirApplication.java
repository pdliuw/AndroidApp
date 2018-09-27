package com.air.androidapp;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author pd_liu 2018/8/27
 * <p>
 * AirApplication
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class AirApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
