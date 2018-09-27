package com.air.permission;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

/**
 * @author pd_liu 2018/8/16
 * <p>
 * Core
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
class Core {

    static final int PERMISSION_GRANTED = PermissionChecker.PERMISSION_GRANTED;
    static final int PERMISSION_DENIED = PermissionChecker.PERMISSION_DENIED;
    static final int PERMISSION_DENIED_APP_OP = PermissionChecker.PERMISSION_DENIED_APP_OP;

    static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
        return PermissionChecker.checkSelfPermission(context, permission);
    }

    static void requestPermissions(@Nullable Activity activity, @NonNull String[] permissions, int requestCode) {
        if (activity == null) {
            return;
        }

        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    static boolean shouldShowRequestPermissionRationale(@Nullable Activity activity, @NonNull String permission) {
        if (activity == null) {
            return false;
        }

        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }
}
