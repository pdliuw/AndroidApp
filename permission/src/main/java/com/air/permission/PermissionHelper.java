package com.air.permission;

import android.app.Activity;
import android.content.Context;

import com.air.permission.Core;

import java.util.Collection;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * PermissionHelper
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
class PermissionHelper {

    static boolean allHaveGranted(Context context, Collection<String> permissions) {

        for (String permission :
                permissions) {

            if (Core.checkSelfPermission(context, permission) != Core.PERMISSION_GRANTED) {
                return false;
            }

        }
        return true;
    }

    static boolean allShouldShowRequest(Activity activity, Collection<String> permissions) {

        for (String permission :
                permissions) {

            if (!Core.shouldShowRequestPermissionRationale(activity, permission)) {
                return false;
            }
        }
        return true;
    }
}
