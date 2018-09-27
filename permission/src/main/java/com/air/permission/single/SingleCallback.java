package com.air.permission.single;

/**
 * @author pd_liu 2018/8/16
 * <p>
 * Callback
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public interface SingleCallback {
    /**
     * Permission have granted.
     */
    void grant();

    /**
     * Permission have denied.
     */
    void deny();
}
