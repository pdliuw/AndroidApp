package com.air.permission.multiple;

import com.air.permission.rationale.Reasonable;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * Single
 * </p>
 * <p>
 * Guide:
 * 1、...
 * Multiple added interface.
 * </p>
 */
public interface AddMultipleCallback {
    /**
     * Set back the receiving result
     * @param multipleCallback call
     * @return Rationale.
     */
    Reasonable setMultipleCallback(MultipleCallback multipleCallback);
}
