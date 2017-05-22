package com.zilin.zaccount.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Zning on 2016/12/7.
 */

public abstract class BaseDialog extends Dialog {

    protected LayoutInflater inflater;
    protected final float DEFAULT_WP = 0.8f;

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (inflater == null) {
            inflater = LayoutInflater.from(getContext());
        }
        initView();
    }

    protected abstract void initView();

    protected void initWindowParams(){
        initWindowParams(DEFAULT_WP, -1, -1);
    }

    protected void initWindowParams(float wp){
        initWindowParams(wp, -1, -1);
    }

    /**
     * 初始化界面
     * @param wp 屏幕宽度占比
     * @param gravity 对齐
     * @param anim 动画
     */
    private void initWindowParams(float wp, int gravity, int anim) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (lp != null) {
            DisplayMetrics d = getContext().getResources().getDisplayMetrics();
            lp.width = (int) (d.widthPixels * wp);
            dialogWindow.setAttributes(lp);
        }
        if (gravity > 0) {
            dialogWindow.setGravity(gravity);
        }
        if (anim > 0) {
            dialogWindow.setWindowAnimations(anim);
        }
    }
}
