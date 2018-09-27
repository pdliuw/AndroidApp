package com.air.permission.rationale;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @author pd_liu 2018/9/27
 * <p>
 * DefaultRationale
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class DefaultRationale implements Serializable {

    private static final long serialVersionUID = -6701418573492570810L;

    private String title;
    private String content;
    private String confirm;
    private String cancel;

    public DefaultRationale setTitle(String title) {
        this.title = title;

        return this;
    }

    public DefaultRationale setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTitle() {
        if (TextUtils.isEmpty(title)) {
            return "权限提示";
        }
        return this.title;
    }

    public String getContent() {
        if (TextUtils.isEmpty(content)) {
            return "权限提示";
        }
        return this.content;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getConfirm() {
        if (TextUtils.isEmpty(confirm)) {
            return "确认";
        }
        return this.confirm;
    }

    public String getCancel() {
        if (TextUtils.isEmpty(cancel)) {
            return "取消";
        }
        return this.cancel;
    }
}
