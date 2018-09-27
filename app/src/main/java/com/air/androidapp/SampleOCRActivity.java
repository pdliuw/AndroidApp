package com.air.androidapp;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.air.base.AppCommonActivity;
import com.air.common.util.FileUtil;
import com.air.ocr.InitializeResultCallback;
import com.air.ocr.OcrHelper;
import com.air.ocr.RecognizeService;
import com.air.permission.rationale.DefaultRationale;
import com.air.permission.rationale.DefaultRationaleCallback;
import com.air.permission.multiple.MultipleCallback;
import com.air.permission.MultipleHelper;
import com.air.permission.PermissionGrant;

public class SampleOCRActivity extends AppCommonActivity {

    final String cameraPermission = Manifest.permission.CAMERA;
    final String readPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    final String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    final int REQUEST_CODE_GENERAL_BASIC = 63;
    final int REQUEST_CODE_ID_CARD = REQUEST_CODE_GENERAL_BASIC + 1;

    @Override
    protected void onClickImpl(View view) {
        super.onClickImpl(view);

        int clickID = view.getId();

        switch (clickID) {

            case R.id.start_ocr_btn:
                PermissionGrant.buildMultiple(this)
                        .addPermission(cameraPermission, readPermission, writePermission)
                        .setMultipleCallback(new MultipleCallback() {
                            @Override
                            public void callback(MultipleHelper multiple) {
                                if (multiple.allHaveGranted()) {
                                    openOCR();
                                } else {
                                    Toast.makeText(SampleOCRActivity.this, "您拒绝了相关权限，导致无法使用此功能", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).openedRationale()
                        .defaultRationale(new DefaultRationaleCallback() {
                            @Override
                            public void call(DefaultRationale rationale) {

                            }
                        }).start();
                break;

            case R.id.start_ocr_general_btn:

                PermissionGrant.buildMultiple(this)
                        .addPermission(cameraPermission, readPermission, writePermission)
                        .setMultipleCallback(new MultipleCallback() {
                            @Override
                            public void callback(MultipleHelper multiple) {
                                if (multiple.allHaveGranted()) {
                                    openOCRGeneral();
                                } else {
                                    Toast.makeText(SampleOCRActivity.this, "您拒绝了相关权限，导致无法使用此功能", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).openedRationale().defaultRationale(new DefaultRationaleCallback() {
                    @Override
                    public void call(DefaultRationale rationale) {

                    }
                }).start();

                break;
            default:
        }
    }

    @Override
    protected int inflateContentViewById() {
        return R.layout.sample_ocr_activity;
    }

    @Override
    protected void initialize() {
        showSlideActivityTransition();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        Button startOCRbutton = findViewById(R.id.start_ocr_btn);
        setCommonClickListener(startOCRbutton);

        Button startOCRGeneralBtn = findViewById(R.id.start_ocr_general_btn);
        setCommonClickListener(startOCRGeneralBtn);
    }

    @Override
    protected void initData() {
        OcrHelper.initAccessTokenByKey(this, "3NifZpQfqB3TmAT0pDrqmGcu", "3h1su9SPzSvoKkdQ1EFpe3pNDcKeMX25", new InitializeResultCallback() {
            @Override
            public void success() {
                new Handler(Looper.getMainLooper())
                        .post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SampleOCRActivity.this, "success", Toast.LENGTH_SHORT).show();
                                //初始化本地检测
                                OcrHelper.initNativeCamera(SampleOCRActivity.this);
                            }
                        });


            }

            @Override
            public void error() {
                new Handler(Looper.getMainLooper())
                        .post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SampleOCRActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

    @Override
    protected void refreshData() {

    }

    private void openOCR() {
        String filePath = FileUtil.getSaveFile(getApplication()).getAbsolutePath();
        Intent idCardIntent = OcrHelper.startIdCardNativeScan(this, filePath, true);
        startActivityForResult(idCardIntent, REQUEST_CODE_ID_CARD);
    }

    private void openOCRGeneral() {
        String filePath = FileUtil.getSaveFile(getApplication()).getAbsolutePath();
        Intent generalIntent = OcrHelper.startGeneralCard(this, filePath);
        startActivityForResult(generalIntent, REQUEST_CODE_GENERAL_BASIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        String filePath = FileUtil.getSaveFile(getApplication()).getAbsolutePath();

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case REQUEST_CODE_ID_CARD:
                    OcrHelper.receiveIDCardResult(this, true, filePath);
                    break;
                case REQUEST_CODE_GENERAL_BASIC:
                    OcrHelper.receiveBankCardResult(this, filePath, new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            Toast.makeText(SampleOCRActivity.this, "" + result.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                default:
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        OcrHelper.release(this);
    }
}
