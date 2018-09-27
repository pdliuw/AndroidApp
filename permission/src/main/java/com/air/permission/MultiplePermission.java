package com.air.permission;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.air.permission.callback.Checkable;
import com.air.permission.multiple.AddMultipleCallback;
import com.air.permission.multiple.AddMultiplePermission;
import com.air.permission.multiple.MultipleCallback;
import com.air.permission.rationale.CustomRationaleCallback;
import com.air.permission.rationale.DefaultRationaleCallback;
import com.air.permission.rationale.RationaleDisplayable;
import com.air.permission.rationale.Reasonable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * MultiplePermission
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class MultiplePermission extends AbstractPermission implements AddMultipleCallback, AddMultiplePermission, Checkable, Reasonable, RationaleDisplayable {

    private MultipleCallback multipleCallback;

    private List<String> mAllPermissions;

    MultiplePermission(Activity context) {
        super(context);
    }

    @Override
    public void call(List<String> allPermissions, List<String> grantedPermissions, List<String> deniedPermissions) {

        mAllPermissions = new ArrayList<>();
        mAllPermissions.addAll(allPermissions);

        if (multipleCallback != null) {
            multipleCallback.callback(new MultipleHelper(this));
        }
    }

    @Override
    public Reasonable setMultipleCallback(MultipleCallback multipleCallback) {
        this.multipleCallback = multipleCallback;
        return this;
    }


    public List<String> getAllPermissions() {
        return this.mAllPermissions;
    }

    @Override
    public AddMultipleCallback addPermission(String... permissions) {
        super.addPermissions(permissions);
        return this;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public RationaleDisplayable openedRationale() {
        showRequestPermissionRationale(true);
        return this;
    }

    @Override
    public Checkable closedRationale() {
        showRequestPermissionRationale(false);
        return this;
    }

    @Override
    public Checkable defaultRationale(@NonNull DefaultRationaleCallback rationaleCallback) {
        if (rationaleCallback != null) {
            rationaleCallback.call(createDefaultRationale());
        }
        return this;
    }

    @Override
    public void customRationale(@NonNull CustomRationaleCallback rationaleCallback) {
        if (rationaleCallback != null) {
            rationaleCallback.callback(this);
        }
    }
}
