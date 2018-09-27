package com.air.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author pd_liu 2018/9/19
 * <p>
 * ViewPagerFragmentAdapter
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<? extends Fragment> mFragments;
    private List<String> mPageTitles;

    public ViewPagerFragmentAdapter(FragmentManager fm, @NonNull List<? extends Fragment> fragments) {

        this(fm, fragments, null);
    }

    public ViewPagerFragmentAdapter(FragmentManager fm, @NonNull List<? extends Fragment> fragments, @Nullable List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mPageTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mPageTitles == null || mPageTitles.isEmpty()) {
            return super.getPageTitle(position);

        } else {
            return mPageTitles.get(position);
        }
    }

}
