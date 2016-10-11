package com.tlcx.library.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.tlcx.library.R;

/**
 * 倒计时工具
 * Created by victor on 2016/9/29 15:42.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class CountDownTimerUtils extends CountDownTimer{

    private TextView mTextView;

    /**
     * @param textView
     * @param millisInFuture  总时间
     * @param countDownInterval 间隔时间
     */
    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }


    @Override
    public void onTick(long millisInFuture) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText("还剩"+(millisInFuture / 1000)+"秒");  //设置倒计时时间
        mTextView.setBackgroundColor(Color.GRAY); //设置按钮为灰色，这时是不能点击的
    }

    @Override
    public void onFinish() {
        mTextView.setText(R.string.count_down_obtain_verify_again);
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.color.count_down_default_bg);  //还原背景色
    }
}
