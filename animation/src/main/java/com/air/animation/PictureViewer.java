package com.air.animation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Size;
import android.view.View;
import android.widget.ImageView;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * PictureViewer
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class PictureViewer {

    private int mCurrentDrawable;


    public void viewer(final ImageView prevImageView, final ImageView nextImageView, @Size(min = 2) int[] drawableIDs, Resources resources, int currentDrawable) {

        mCurrentDrawable = currentDrawable;

        prevImageView.setBackgroundColor(Color.TRANSPARENT);
        nextImageView.setBackgroundColor(Color.TRANSPARENT);

        prevImageView.animate().setDuration(1000);
        nextImageView.animate().setDuration(1000);

        final BitmapDrawable drawables[] = new BitmapDrawable[drawableIDs.length];


        for (int i = 0; i < drawableIDs.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(resources, drawableIDs[i]);

            drawables[i] = new BitmapDrawable(resources, bitmap);
        }

        prevImageView.setImageDrawable(drawables[0]);
        nextImageView.setImageDrawable(drawables[1]);

        prevImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prevImageView.animate().alpha(0f).withLayer();
                nextImageView.animate().alpha(1f).withLayer()
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                mCurrentDrawable = (mCurrentDrawable + 1) % drawables.length;

                                int nextDrawableIndex = (mCurrentDrawable + 1) % drawables.length;

                                prevImageView.setImageDrawable(drawables[mCurrentDrawable]);
                                nextImageView.setImageDrawable(drawables[nextDrawableIndex]);

                                nextImageView.setAlpha(0f);
                                prevImageView.setAlpha(1f);
                            }
                        });

            }
        });

    }
}
