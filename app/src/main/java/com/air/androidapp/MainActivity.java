package com.air.androidapp;


import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.air.animation.CircularRevealAnimator;
import com.air.base.AppCommonActivity;
import com.air.camera.TakePhoto;
import com.air.permission.rationale.DefaultRationale;
import com.air.permission.rationale.DefaultRationaleCallback;
import com.air.permission.multiple.MultipleCallback;
import com.air.permission.MultipleHelper;
import com.air.permission.PermissionGrant;
import com.air.permission.single.SingleCallback;

public class MainActivity extends AppCommonActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    String cameraPermission = Manifest.permission.CAMERA;
    String galleryPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;


    @Override
    protected void onClickImpl(View view) {
        super.onClickImpl(view);

        int clickId = view.getId();

        switch (clickId) {

            case R.id.start_camera_btn:
                /*
                单个权限处理
                 */
                PermissionGrant.buildSingle(this)//The activity object.
                        .addPermission(cameraPermission)//The request permission
                        .setSingleCallback(new SingleCallback() {
                            @Override
                            public void grant() {
                                /*
                                Granted
                                Do something
                                openCamera
                                 */
                                openCamera();
                            }

                            @Override
                            public void deny() {
                                /*
                                Denied
                                Do something
                                Toast tips user.
                                 */
                                Toast.makeText(MainActivity.this, "deny", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .closedRationale()//关闭提示用户的解释框
                        .start();//Start
                break;

            case R.id.start_galley_btn:
                /*
                多个权限处理
                 */
                PermissionGrant.buildMultiple(this)
                        .addPermission(galleryPermission, cameraPermission, writePermission)
                        .setMultipleCallback(new MultipleCallback() {
                            @Override
                            public void callback(MultipleHelper multiple) {
                                if (multiple.allHaveGranted()) {
                                    openGallery();
                                } else {
                                    Toast.makeText(MainActivity.this, "您关闭了某些权限，导致无法使用此功能", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .openedRationale()
                        .defaultRationale(new DefaultRationaleCallback() {
                            @Override
                            public void call(DefaultRationale rationale) {
                                rationale.setTitle("Title");
                                rationale.setContent("需要相册的相关信息");
                            }
                        })
                        .start();

                break;

            case R.id.picture_viewer_btn:
                startActivity(new Intent(this, AnimationActivity.class));
                break;

            case R.id.ocr_btn:
                //启动OCR
                Intent ocrIntent = new Intent(this, SampleOCRActivity.class);
                startActivityTransition(ocrIntent);
                break;

            case R.id.compress_graphics_btn:
                startActivityTransition(new Intent(this, GraphicsActivity.class));
                break;
            case R.id.test_fragment_btn:
                startActivityTransition(new Intent(this, SampleFragmentActivity.class));
                break;

            case R.id.start_new_activity:
                startActivityTransition(new Intent(this, MainActivity.class));
                break;

            case R.id.start_reveal_btn:
                ImageView circularRevealImg = findViewById(R.id.circular_reveal_img);
                int width = circularRevealImg.getWidth();
                int height = circularRevealImg.getHeight();

                int startRadius = width / 8;
                int endRadius = width / 2;

                Animator animator = CircularRevealAnimator.createRevealWithDelay(circularRevealImg, width / 2, height / 2, startRadius, endRadius);

                animator.start();
                break;
            default:
        }
    }

    @Override
    protected int inflateContentViewById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialize() {
        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();

                float xDpi = displayMetrics.xdpi;
                float yDpi = displayMetrics.ydpi;
                int widthPixel = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;
                float density = displayMetrics.density;
                int densityDpi = displayMetrics.densityDpi;
                float scaledDensity = displayMetrics.scaledDensity;
                //        new ArrayList().iterator()
                loggg("xDpi" + xDpi);
                loggg("yDpi" + yDpi);
                loggg("widthPixel" + widthPixel);
                loggg("heightPixels" + heightPixels);
                loggg("density" + density);
                loggg("densityDpi" + densityDpi);
                loggg("scaledDensity" + scaledDensity);


            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        Button startCameraBtn = findViewById(R.id.start_camera_btn);
        setCommonClickListener(startCameraBtn);

        Button startGalleryBtn = findViewById(R.id.start_galley_btn);
        setCommonClickListener(startGalleryBtn);

        Button pictureViewBtn = findViewById(R.id.picture_viewer_btn);
        setCommonClickListener(pictureViewBtn);

        Button ocrBtn = findViewById(R.id.ocr_btn);
        setCommonClickListener(ocrBtn);

        Button compressGraphics = findViewById(R.id.compress_graphics_btn);
        setCommonClickListener(compressGraphics);

        Button testFragment = findViewById(R.id.test_fragment_btn);
        setCommonClickListener(testFragment);

        Button startNewActivityBtn = findViewById(R.id.start_new_activity);
        setCommonClickListener(startNewActivityBtn);

        Button startRevealBtn = findViewById(R.id.start_reveal_btn);
        setCommonClickListener(startRevealBtn);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loggg("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        loggg("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loggg("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        loggg("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        loggg("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loggg("onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        loggg("onNewIntent");
    }

    void openCamera() {
        TakePhoto.showCamera(this);
    }

    void openGallery() {
        TakePhoto.showGallery(this);
    }


    void loggg(String message) {
        Log.e("MainActivity", "" + message);
    }
}
