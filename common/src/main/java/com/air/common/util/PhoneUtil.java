package com.air.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @author pd_liu 2018/7/31
 * <p>
 * PhoneUtil
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public final class PhoneUtil {

    private PhoneUtil() {
        throw new UnsupportedOperationException("非法的创建对象!");
    }

    /**
     * Get phone device id.
     *
     * @param context
     *         context.
     *
     * @return device id.
     */
    public static String getDeviceId(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String IMEI = telephonyManager.getDeviceId();
        return IMEI;
    }
}
