package com.air.permission.multiple;

/**
 * @author pd_liu 2018/9/18
 * <p>
 * AddMultiplePermission
 * </p>
 * <p>
 * Guide:
 * 1、...
 * Multiple permission added.
 * </p>
 */
public interface AddMultiplePermission {
    /**
     * Add permission.
     * @param permissions multiple permissions.
     * @return callback.
     */
    AddMultipleCallback addPermission(String... permissions);
}
