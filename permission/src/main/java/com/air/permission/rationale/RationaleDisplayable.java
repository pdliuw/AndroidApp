package com.air.permission.rationale;

import android.support.annotation.NonNull;

import com.air.permission.callback.Checkable;

/**
 * @author pd_liu 2018/9/27
 * <p>
 * RationaleDisplayable
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public interface RationaleDisplayable {

    Checkable defaultRationale(@NonNull DefaultRationaleCallback rationaleCallback);

    void customRationale(@NonNull CustomRationaleCallback rationaleCallback);
}
