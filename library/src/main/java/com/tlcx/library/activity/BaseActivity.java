package com.tlcx.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.tlcx.library.utils.KeyboardUtils;

/**
 * Activity基类
 * Created by victor on 2016/9/19 19:10.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initViews();
        loadData();
    }

    /**
     * 初始化变量（包括Intent带来的变量以及activity内的变量）
     */
    protected abstract void initVariables();

    /**
     * 加载layout布局文件，初始化控件，为控件挂载事件
     */
    protected abstract void initViews();

    /**
     * 调用手机API获取数据
     */
    protected abstract void loadData();

    /**
     * 返回Listener
     */
    public class BackListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            KeyboardUtils.hideSoftInput(BaseActivity.this);
            finish();
        }
    }

    /**
     * 显示吐丝信息
     * @param msg
     */
    public void showToast(String msg){
        if (!TextUtils.isEmpty(msg)){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示吐丝信息
     * @param msgId
     */
    public void showToast(int msgId){
        showToast(getString(msgId));
    }
}

