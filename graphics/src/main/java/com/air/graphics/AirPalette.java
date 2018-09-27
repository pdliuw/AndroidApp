package com.air.graphics;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

/**
 * @author pd_liu 2018/9/6
 * <p>
 * AirPalette
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class AirPalette {

    private AirPalette() {
    }

    public static Palette createPalette(@NonNull Bitmap bitmap) {
        Palette palette = Palette.from(bitmap).generate();
        return palette;
    }

    public static void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {

                //Use generated instance.

            }
        });

    }
}
