package com.air.ocr;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;

import java.io.File;

/**
 * @author pd_liu 2018/8/22
 * <p>
 * OcrHelper
 * </p>
 * <p>
 * Guide:
 * 1、...
 * 2、{@link #initAccessTokenByKey(Context, String, String, InitializeResultCallback)} or {@link #initAccessTokenByLicense(Context, String)}
 * 3、{@link #startIdCard(Context, String, boolean)} or {@link #startIdCardNativeScan(Context, String, boolean)} and {@link #initNativeCamera(Context)}
 * 4、{@link #receiveIDCardResult(Context, boolean, String)}
 * 5、{@link #release(Context)}
 * </p>
 */
public class OcrHelper {


    public static void initAccessTokenByLicense(Context context, String licenseFile) {

        OCR.getInstance(context)
                .initAccessToken(new OnResultListener<AccessToken>() {
                    @Override
                    public void onResult(AccessToken accessToken) {
                        /*
                         Success.
                         */
                        String token = accessToken.getAccessToken();
                    }

                    @Override
                    public void onError(OCRError ocrError) {
                        /*
                        Error
                         */
                        String errorInfo = ocrError.getMessage();

                    }
                }, licenseFile, context.getApplicationContext());

    }

    public static void initAccessTokenByKey(Context context, String appKey, String secretKey, final InitializeResultCallback callback) {

        OCR.getInstance(context)
                .initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
                    @Override
                    public void onResult(AccessToken accessToken) {
                        /*
                        Successful
                         */
                        String token = accessToken.getAccessToken();
                        if (callback != null) {
                            callback.success();
                        }
                    }

                    @Override
                    public void onError(OCRError ocrError) {
                        /*
                        Error
                         */
                        String info = ocrError.getMessage();
                        ocrError.printStackTrace();
                        if (callback != null) {
                            callback.error();
                        }
                    }
                }, context.getApplicationContext(), appKey, secretKey);

    }

    /**
     * Release OCR release.
     *
     * @param context
     *         context.
     */
    public static void release(Context context) {
        OCR.getInstance(context).release();

        releaseNativeCamera();
    }


    public static void initNativeCamera(final Context context) {

        CameraNativeHelper.init(context, OCR.getInstance(context).getLicense(), new CameraNativeHelper.CameraNativeInitCallback() {
            @Override
            public void onError(int errorCode, Throwable e) {
                String msg;
                switch (errorCode) {

                    case CameraView.NATIVE_SOLOAD_FAIL:
                        msg = "加载so失败，请确保apk中存在ui部分的so";
                        break;
                    case CameraView.NATIVE_AUTH_FAIL:
                        msg = "授权本地质量控制token获取失败";
                        break;
                    case CameraView.NATIVE_INIT_FAIL:
                        msg = "本地质量控制";
                        break;
                    default:
                        msg = String.valueOf(errorCode);
                }

                Toast.makeText(context, "本地质量控制初始化错误，错误原因：" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void receiveIDCardResult(@NonNull final Context context, boolean idCardFront, String filePath, @Nullable final ReceiveIDCardCallback callback) {
        String idCardSide;

        if (idCardFront) {
            idCardSide = IDCardParams.ID_CARD_SIDE_FRONT;
        } else {
            idCardSide = IDCardParams.ID_CARD_SIDE_BACK;
        }


        IDCardParams params = new IDCardParams();

        //设置图像文件
        params.setImageFile(new File(filePath));
        //设置身份正反面
        params.setIdCardSide(idCardSide);
        //设置方向检测
        params.setDetectDirection(true);
        //设置图像参数压缩质量
        params.setImageQuality(20);

        OCR.getInstance(context)
                .recognizeIDCard(params, new OnResultListener<IDCardResult>() {
                    @Override
                    public void onResult(IDCardResult idCardResult) {
                        if (idCardResult != null) {
                            Toast.makeText(context, "" + idCardResult.toString(), Toast.LENGTH_SHORT).show();
                            if (callback != null) {
                                callback.success(new ReceiveIDCardResult.Success(idCardResult));
                            }
                        }
                    }

                    @Override
                    public void onError(OCRError ocrError) {
                        Toast.makeText(context, "" + ocrError.getMessage(), Toast.LENGTH_SHORT).show();
                        if (callback != null) {
                            callback.error(new ReceiveIDCardResult.Error(ocrError));
                        }
                    }
                });
    }

    public static void receiveIDCardResult(final Context context, boolean idCardFront, String filePath) {

        receiveIDCardResult(context, idCardFront, filePath, null);
    }

    /**
     * 银行卡识别
     * @param context
     * @param filePath
     * @param listener
     */
    public static void receiveBankCardResult(Context context, String filePath, RecognizeService.ServiceListener listener) {

        RecognizeService.recBankCard(context, filePath, listener);
    }

    /**
     * 驾驶证识别
     */
    public static void receiveDrivingLicenseResult(Context context, String filePath, RecognizeService.ServiceListener listener) {

        RecognizeService.recDrivingLicense(context, filePath, listener);
    }

    /**
     * 行驶证识别
     */
    public static void receiveVehicleLicenseResult(Context context, String filePath, RecognizeService.ServiceListener listener) {
        RecognizeService.recVehicleLicense(context, filePath, listener);
    }

    /**
     * 名片识别
     *
     * @param context
     * @param filePath
     * @param listener
     */
    public static void receiveBusinessCardResult(Context context, String filePath, RecognizeService.ServiceListener listener) {
        RecognizeService.recBusinessCard(context, filePath, listener);
    }

    /**
     * 护照识别
     *
     * @param context
     * @param filePath
     * @param listener
     */
    public static void receivePassportResult(Context context, String filePath, RecognizeService.ServiceListener listener) {
        RecognizeService.recPassport(context, filePath, listener);
    }


    /**
     * 通用文字、网络图片、行驶证、驾驶证、车牌、营业执照、通用票据、二维码、数字、名片、增值税发票、彩票、手写、自定义模板识别
     *
     * @param context
     * @param filePath
     *
     * @return
     */
    public static Intent startGeneralCard(Context context, String filePath) {

        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);

        return intent;
    }

    /**
     * 护照识别
     *
     * @param context
     * @param filePath
     *
     * @return
     */
    public static Intent startPassportCard(Context context, String filePath) {
        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_PASSPORT);

        return intent;
    }

    /**
     * 银行卡识别
     *
     * @param context
     * @param filePath
     *
     * @return
     */
    public static Intent startBankCard(Context context, String filePath) {

        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);

        return intent;
    }

    /**
     * 身份证识别
     *
     * @param context
     *         context
     * @param filePath
     *         文件地址
     * @param isFront
     *         if true 身份证正面拍照
     *         if false 身份证反面拍照
     */
    public static void startIdCard(Context context, String filePath, boolean isFront) {

        if (isFront) {
            startIdCardFront(context, filePath);
        } else {
            startIdCardBack(context, filePath);
        }

    }

    /**
     * 身份证识别（本地扫描识别）
     *
     * @param context
     *         context
     * @param filePath
     *         filePath
     * @param isFront
     *         if true, 身份证正面
     *         if false,身份证反面
     *
     * @see #initNativeCamera(Context) befor call.
     * @see #release(Context)   after call.
     */
    public static Intent startIdCardNativeScan(Context context, String filePath, boolean isFront) {

        if (isFront) {

            return startIdCardNativeScanFront(context, filePath);
        } else {
            return startIdCardNativeScanBack(context, filePath);
        }
    }

    /**
     * 身份证正面识别
     *
     * @param context
     * @param filePath
     *
     * @return
     */
    private static Intent startIdCardFront(Context context, String filePath) {

        Intent intent = new Intent(context, CameraActivity.class);
        //设置临时输出文件路径
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        //设置正面拍照
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);

        return intent;
    }

    /**
     * 身份证反面识别
     *
     * @param context
     * @param filePath
     *
     * @return
     */
    private static Intent startIdCardBack(Context context, String filePath) {

        Intent intent = new Intent(context, CameraActivity.class);
        //设置临时输出文件路径
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        //设置反面拍照
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);

        return intent;
    }

    /**
     * 身份证正面识别（本地扫描识别）
     *
     * @param context
     * @param filePath
     *
     * @return
     */
    private static Intent startIdCardNativeScanFront(Context context, String filePath) {

        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        //开启本地识别
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
        //设置本地识别
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
        //设置正面
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);

        return intent;
    }

    /**
     * 身份证反面识别（本地扫描识别）
     *
     * @param context
     *         context
     * @param filePath
     *         filePath
     *
     * @return intent
     */
    private static Intent startIdCardNativeScanBack(Context context, String filePath) {

        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, filePath);
        //开启本地识别
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
        //设置本地识别
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
        //设置反面
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);

        return intent;
    }

    /**
     * Release native camera instance.
     */
    private static void releaseNativeCamera() {
        CameraNativeHelper.release();
    }

}
