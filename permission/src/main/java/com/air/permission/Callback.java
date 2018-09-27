package com.air.permission;

import java.util.List;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * Callback
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
interface Callback {

    void call(List<String> allPermissions, List<String> grantedPermissions, List<String> deniedPermissions);
}
