package com.air.ocr;

/**
 * @author pd_liu 2018/8/23
 * <p>
 * ReceiveIDCardCallback
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public interface ReceiveIDCardCallback {

    void success(ReceiveIDCardResult.Success result);

    void error(ReceiveIDCardResult.Error error);
}
