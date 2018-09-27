package com.air.androidapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.air.base.AppCommonActivity;
import com.air.graphics.AirPalette;

import java.util.HashMap;

public class GraphicsActivity extends AppCommonActivity {


    @Override
    protected void onClickImpl(View view) {
        super.onClickImpl(view);

        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(10) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return 1;
            }

            @Override
            protected Bitmap create(String key) {
                HashMap<String, Bitmap> map = new HashMap<>();
                return map.get(key);
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };


        Bitmap bitmap = lruCache.get("key");
        if (bitmap != null) {
            //LruCache数据集中已存储

        }


        int clickId = view.getId();

        switch (clickId) {

            case R.id.compress_btn:
                compress();
                break;

            case R.id.start_openGL_btn:

                openGL();
                break;
            default:
        }
    }

    private void compress() {

        EditText editText = findViewById(R.id.inSampleSize_et);

        String inputString = editText.getText().toString().trim();

        int inSampleSize = Integer.valueOf(inputString);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        /*
        Scaled
         */
        //        options.inScaled = true;
        //        options.inDensity = 2;
        //        options.inTargetDensity = 3;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round, options);


        ImageView afterCompressImg = findViewById(R.id.after_compress_img);
        afterCompressImg.setImageBitmap(bitmap);

    }

    @Override
    protected int inflateContentViewById() {
        return R.layout.activity_graphics;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        Button compressBtn = findViewById(R.id.compress_btn);
        setCommonClickListener(compressBtn);

        Button openGlBtn = findViewById(R.id.start_openGL_btn);
        setCommonClickListener(openGlBtn);


        generatePalette();
    }

    private void generatePalette() {

        ImageView imageView = findViewById(R.id.palette_origin_img);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.palette02, options);

        imageView.setImageBitmap(bitmap);

        Palette palette = AirPalette.createPalette(bitmap);

        /*
        Vibrant
         */

        Palette.Swatch swatch = palette.getVibrantSwatch();

        if (swatch != null) {
            int vibrantColor = palette.getVibrantColor(Color.BLACK);
            int darkMutedColor = palette.getDarkMutedColor(Color.BLACK);
            int dominantColor = palette.getDominantColor(Color.BLACK);
            int mutedColor = palette.getMutedColor(Color.BLACK);

            TextView vibrantTv = findViewById(R.id.palette_vibrate_place_tv);
            vibrantTv.setBackgroundColor(vibrantColor);
            vibrantTv.setText("VibrantColor");

            TextView vibrantTitleColorTv = findViewById(R.id.palette_vibrate_place_title_color_tv);
            vibrantTitleColorTv.setBackgroundColor(darkMutedColor);
            vibrantTitleColorTv.setText("darkMutedColor");

            TextView vibrantBodyColorTv = findViewById(R.id.palette_vibrate_place_body_color_tv);
            vibrantBodyColorTv.setBackgroundColor(dominantColor);
            vibrantBodyColorTv.setText("dominantColor");


            TextView argbColorTv = findViewById(R.id.palette_vibrate_place_argb_color_tv);
            argbColorTv.setBackgroundColor(mutedColor);
            argbColorTv.setText("mutedColor");
        } else {
            TextView vibrantTv = findViewById(R.id.palette_vibrate_place_tv);
            vibrantTv.setText("Swatch对象为null");
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshData() {

    }

    private void openGL() {
        startActivityTransition(new Intent(this, OpenGraphicsLibraryActivity.class));
    }
}
