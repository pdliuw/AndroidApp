package com.air.camera;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * @author pd_liu 2018/8/20
 * <p>
 * TakePhoto
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class TakePhoto {

    public static void showCamera(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivity(intent);
    }

    public static void showGallery(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        context.startActivity(intent);
    }

    public static void showVideo(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        context.startActivity(intent);
    }
}
