package com.air.permission;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * MultipleHelper
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class MultipleHelper {

    MultiplePermission mMultiple;

    public MultipleHelper(MultiplePermission multiplePermission) {
        mMultiple = multiplePermission;
    }

    public boolean allHaveGranted() {
        return PermissionHelper.allHaveGranted(mMultiple.getContext(), mMultiple.getAllPermissions());
    }
}
