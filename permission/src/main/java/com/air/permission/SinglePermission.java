package com.air.permission;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.air.permission.callback.Checkable;
import com.air.permission.rationale.CustomRationaleCallback;
import com.air.permission.rationale.DefaultRationaleCallback;
import com.air.permission.rationale.RationaleDisplayable;
import com.air.permission.rationale.Reasonable;
import com.air.permission.single.AddSingleCallback;
import com.air.permission.single.AddSinglePermission;
import com.air.permission.single.SingleCallback;

import java.util.List;

/**
 * @author pd_liu 2018/8/21
 * <p>
 * SinglePermission
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class SinglePermission extends AbstractPermission implements AddSingleCallback, AddSinglePermission, Checkable, Reasonable, RationaleDisplayable {

    private SingleCallback singleCallback;

    SinglePermission(Activity context) {
        super(context);
    }

    @Override
    public void call(List<String> allPermissions, List<String> grantedPermissions, List<String> deniedPermissions) {
        if (singleCallback != null) {
            if (!grantedPermissions.isEmpty()) {
                singleCallback.grant();
            } else {
                singleCallback.deny();
            }
        }
    }

    @Override
    public AddSingleCallback addPermission(String permission) {
        super.addPermissions(permission);
        return this;
    }


    @Override
    public Reasonable setSingleCallback(SingleCallback singleCallback) {
        this.singleCallback = singleCallback;
        return this;
    }

    @Override
    public void start() {
        super.start();
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
}
