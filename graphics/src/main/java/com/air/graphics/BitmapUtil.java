package com.air.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;

/**
 * @author pd_liu 2018/9/4
 * <p>
 * BitmapUtil
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class BitmapUtil {

    public static void readBitmap(@IdRes int resourceId) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(Resources.getSystem(), resourceId, options);

        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;

        String imageType = options.outMimeType;

    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqwidth, int reqHeight) {
        //Raw height and width of image.
        int height = options.outHeight;
        int width = options.outWidth;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqwidth) {

            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqwidth) {

                inSampleSize = inSampleSize * 2;
            }

        }

        return inSampleSize;
    }
}
