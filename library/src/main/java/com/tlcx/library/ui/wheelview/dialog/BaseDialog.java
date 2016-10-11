package com.tlcx.library.ui.wheelview.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 对话框基类
 * Created by victor on 2016/10/9 10:27.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public abstract class BaseDialog {

    protected Context context;
    protected Dialog dialog;

    public boolean isShow() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public void show() {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    public void dismiss() {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }
}
