package com.air.permission;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.air.permission.multiple.AddMultiplePermission;
import com.air.permission.rationale.AbstractRationaleFragment;
import com.air.permission.single.AddSinglePermission;

/**
 * @author pd_liu 2018/8/20
 * <p>
 * PermissionGrant
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class PermissionGrant {

    static AbstractPermission airPermission;

    public static AddMultiplePermission buildMultiple(Activity activity) {
        MultiplePermission multiple = new MultiplePermission(activity);
        airPermission = multiple;
        return multiple;
    }

    public static AddSinglePermission buildSingle(Activity activity) {
        SinglePermission single = new SinglePermission(activity);
        airPermission = single;
        return single;
    }

    static void requestPermissions(Activity activity) {
        airPermission.requestPermissions(activity);
    }

    static AbstractRationaleFragment getPermissionRationale(){
        return airPermission.getPermissionRationale();
    }

    static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        airPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    static void release() {
        airPermission = null;
    }
}
