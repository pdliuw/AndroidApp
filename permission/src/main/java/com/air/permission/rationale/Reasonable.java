package com.air.permission.rationale;

import com.air.permission.callback.Checkable;

/**
 * @author pd_liu 2018/9/18
 * <p>
 * Reasonable
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public interface Reasonable {
    /**
     * Open permission's rationale to visible the front of user.
     *
     * @return displayable.
     */
    RationaleDisplayable openedRationale();

    /**
     * Don't {@link #openedRationale()}
     *
     * @return the object of start request permission.
     */
    Checkable closedRationale();
}
