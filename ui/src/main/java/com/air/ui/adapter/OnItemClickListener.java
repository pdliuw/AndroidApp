package com.air.ui.adapter;

import android.view.View;

/**
 * @author pd_liu on 2018/4/28.
 *         <p>
 *         OnItemClickListener
 *         </p>
 *         <p>
 *         Item click listener
 *         {@link RecyclerAdapter}
 *         </p>
 */

public interface OnItemClickListener {
    /**
     * 点击
     *
     * @param view     click view.
     * @param position click position.
     */
    void onItemClick(View view, int position);

}
