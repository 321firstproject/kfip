package com.tlcx.kfip.activity.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.tlcx.kfip.R;
import com.tlcx.kfip.engine.SystemBarTintManager;
import com.tlcx.library.utils.ScreenUtils;
import com.tlcx.library.activity.BaseActivity;

/**
 * 基于基类的有沉浸效果的页面的基类
 * Created by victor on 2016/9/20 23:18.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public abstract class AppBaseActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在4.4版本以上设置沉浸效果
        initWindow();
    }
    /**
     * 该方法可以给状态栏设置颜色，但窗口的高度包含了状态栏
     * 所以要给窗口里第一个view加上一个paddingTop,值为状态栏的高度
     */
    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.standard_bg_status);
            setFirstViewPaddingTop();
        }
    }
    /**
     * 给窗口里第一个view设置paddingTop
     */
    private void setFirstViewPaddingTop(){
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14){
            parentView.setPadding(0, ScreenUtils.getStatusBarHeight(this.getBaseContext()),0,0);
        }
    }

}
