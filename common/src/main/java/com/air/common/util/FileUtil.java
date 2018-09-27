package com.air.common.util;

import android.content.Context;

import java.io.File;

/**
 * @author pd_liu 2018/8/22
 * <p>
 * FileUtil
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public final class FileUtil {

    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }
}
