package com.air.media;

/**
 * @author pd_liu 2018/9/10
 * <p>
 * MediaRecorderHelper
 * </p>
 * <p>
 * Guide:
 * 1、...
 * 2、You must include this permission{<uses-permission android:name="android.permission.RECORD_AUDIO"></uses-permission>}
 * 3、This include "dangerous" permission.
 *
 * </p>
 */
public class MediaRecorderHelper {

    private static MediaRecorderHelper INSTANCE;

    private MediaRecorderHelper() {
    }

    public static MediaRecorderHelper getInstance() {

        if (INSTANCE == null) {
            synchronized (INSTANCE) {
                INSTANCE = new MediaRecorderHelper();
            }
        }

        return INSTANCE;
    }
}
