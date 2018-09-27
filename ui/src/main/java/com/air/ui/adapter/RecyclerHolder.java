package com.air.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @author pd_liu on 2018/4/28.
 *         <p>
 *         RecyclerHolder
 *         </p>
 *         <p>
 *         {@link RecyclerAdapter}
 *         </p>
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener mOnItemClickListener;
    private View.OnClickListener mOutOnClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setTag(getCurrentClickPosition());
            if (mOutOnClickListener != null) {
                mOutOnClickListener.onClick(v);
            }
        }
    };

    public RecyclerHolder(View itemView) {
        super(itemView);
    }

    public RecyclerHolder(View itemView, OnItemClickListener onItemClickListener, int[] clickIds, View.OnClickListener onClickListener) {
        //this.
        this(itemView);

        //click .
        mOnItemClickListener = onItemClickListener;
        mOutOnClickListener = onClickListener;
        /*
        Item click
         */
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, getCurrentClickPosition());
                }
            }
        });

        /*
        click
         */
        if (clickIds != null) {

            for (int i = 0; i < clickIds.length; i++) {
                getViewById(clickIds[i]).setOnClickListener(mOnClickListener);
            }
        }
    }

    public void text(int textId, String text) {
        TextView textView = (TextView) getViewById(textId);
        if (textView.getVisibility() == View.INVISIBLE || textView.getVisibility() == View.GONE) {
            textView.setVisibility(View.VISIBLE);
        }
        textView.setText(text);

    }

    public void textWithColor(int textId, String text, int color) {
        TextView textView = (TextView) getViewById(textId);
        textView.setTextColor(color);
        textView.setText(text);
    }

    public void textWithVisbility(int textId, boolean isVisible) {
        TextView textView = (TextView) getViewById(textId);
        textView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public <T extends View> T getViewById(int viewId) {
        return itemView.findViewById(viewId);
    }


    public int getCurrentClickPosition() {
        return getAdapterPosition();
    }
}
