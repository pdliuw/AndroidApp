package com.air.permission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.air.permission.rationale.AbstractRationaleFragment;

/**
 * PermissionActivity
 * <p>
 * The core page of permission component.
 * </p>
 * <p>
 * Start the activity{@link #startActivity(Context, boolean)}
 * </p>
 */

class PermissionActivity extends AppCompatActivity {

    private static final String TAG = "PermissionActivity";

    public static String INTENT_EXTRA_TYPE_SHOW = "is_show_dialog";
    private static final boolean DEFAULT_SHOW_DIALOG = false;
    private boolean isShowDialog;

    /**
     * @param context
     *         previous page context.
     * @param showDialog
     *         if true ,it will show dialog when checking permission.
     */
    public static void startActivity(Context context, boolean showDialog) {
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(INTENT_EXTRA_TYPE_SHOW, showDialog);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        Intent intent = getIntent();
        isShowDialog = intent.getBooleanExtra(INTENT_EXTRA_TYPE_SHOW, DEFAULT_SHOW_DIALOG);

        /*
        执行对话框提示用户
         */
        if (isShowDialog) {

            AbstractRationaleFragment defaultRationale = PermissionGrant.getPermissionRationale();

            if (defaultRationale != null) {

                final AbstractRationaleFragment rationaleFragment = defaultRationale;

                rationaleFragment.setConfirmListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rationaleFragment.dismiss();
                        requestPermission();
                    }
                });
                rationaleFragment.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rationaleFragment.dismiss();

                        onBackPressed();
                    }
                });
                rationaleFragment.show(getSupportFragmentManager(), "rationale");
            }

        } else {
            requestPermission();
        }

    }

    private void requestPermission() {

        //Request permission.
        PermissionGrant.requestPermissions(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Handler response.
        PermissionGrant.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Close this page.
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Release.
        PermissionGrant.release();
    }
}
