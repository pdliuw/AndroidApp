package com.air.androidapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.air.androidapp.R;

/**
 * @author pd_liu 2018/9/12
 * <p>
 * BlackFragment
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class BlackFragment extends Fragment {

    String TAG = "BlackFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logg("onAttach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logg("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logg("onCreateView");
        return inflater.inflate(R.layout.fragment_black, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logg("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        logg("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        logg("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        logg("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logg("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logg("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logg("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logg("onDetach");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        logg("onHiddenChanged:"+hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        logg("setUserVisibleHint:"+ isVisibleToUser);
    }

    void logg(String message) {
        Log.e(TAG, "| fragment life cycle =" + message);
    }
}
