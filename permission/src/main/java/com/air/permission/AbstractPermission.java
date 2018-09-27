package com.air.permission;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.air.permission.rationale.AbstractRationaleFragment;
import com.air.permission.rationale.DefaultRationale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * AbstractPermission
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public abstract class AbstractPermission implements Callback {

    private static final String TAG = "AbstractPermission";

    private List<String> mRequestPermissions;
    private List<String> mGrantedPermissions;
    private List<String> mDeniedPermissions;
    private List<String> mDeniedAppOpPermissions;
    private Activity mContext;

    private AbstractRationaleFragment mRationaleFragment;

    private int mRequestCode = 100;

    private boolean mShowRequestPermissionRationale = true;

    private Object mLock = new Object();

    private DefaultRationale mDefaultRationale;

    AbstractPermission(Activity context) {
        mRequestPermissions = new ArrayList<>();
        mGrantedPermissions = new ArrayList<>();
        mDeniedPermissions = new ArrayList<>();
        mDeniedAppOpPermissions = new ArrayList<>();
        mContext = context;
    }

    protected AbstractPermission addPermissions(String... permissions) {
        mRequestPermissions.addAll(Arrays.asList(permissions));
        return this;
    }

    protected void showRequestPermissionRationale(boolean show) {
        mShowRequestPermissionRationale = show;
    }


    protected AbstractRationaleFragment getPermissionRationale() {
        return AbstractRationaleFragment.newInstance(mDefaultRationale);
    }

    protected DefaultRationale createDefaultRationale() {
        mDefaultRationale = new DefaultRationale();
        return mDefaultRationale;
    }

    protected void start() {

        if (PermissionHelper.allHaveGranted(mContext, mRequestPermissions)) {

            mGrantedPermissions.addAll(mRequestPermissions);
            call(mRequestPermissions, mGrantedPermissions, mDeniedPermissions);

        } else {
            PermissionActivity.startActivity(mContext, mShowRequestPermissionRationale);

        }
    }

    protected void requestPermissions(Activity activity) {

        String[] permission = new String[mRequestPermissions.size()];

        for (int i = 0, size = mRequestPermissions.size(); i < size; i++) {
            permission[i] = mRequestPermissions.get(i);
        }

        Core.requestPermissions(activity, permission, mRequestCode);
    }


    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        synchronized (mLock) {

            if (mRequestCode == requestCode) {

                for (int i = 0, size = permissions.length; i < size; i++) {

                    //Current permission.
                    String permission = permissions[i];

                    //Permission status
                    int resultStatus = grantResults[i];

                    switch (resultStatus) {

                        case Core.PERMISSION_GRANTED:
                            addGrantedPermission(permission);

                            break;

                        case Core.PERMISSION_DENIED:

                            addDeniedPermission(permission);

                            break;
                        case Core.PERMISSION_DENIED_APP_OP:

                            addDeniedAppOpPermission(permission);

                            break;

                        default:
                            break;

                    }

                }

                /*
                Callback.
                 */
                call(mRequestPermissions, mGrantedPermissions, mDeniedPermissions);
            }

        }

    }

    private void addGrantedPermission(String permission) {
        mGrantedPermissions.add(permission);
    }

    private void addDeniedPermission(String permission) {
        mDeniedPermissions.add(permission);
    }

    private void addDeniedAppOpPermission(String permission) {
        mDeniedAppOpPermissions.add(permission);
    }

    public Activity getContext() {
        return mContext;
    }
}
