package com.tlcx.kfip.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tlcx.kfip.R;

/**
 * 自定义头部
 * Created by victor on 2016/9/21 22:04.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class HeaderView extends LinearLayout {

    private ImageView leftIv;               //左侧图标
    private ImageView rightIv;              //右侧图标
    private TextView titleTv;               //标题
    private TextView rightTv;               //右侧按钮
    private View convertView;

    public HeaderView(Context context) {
        super(context);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.widget_header_view,null,false);
        addView(convertView);
        initView();

    }

    /**
     * 初始化view
     */
    private void initView(){
        leftIv = (ImageView) findViewById(R.id.iv_header_view_left);
        titleTv = (TextView) findViewById(R.id.tv_header_view_title);
        rightTv = (TextView) findViewById(R.id.tv_header_view_right);
        rightIv = (ImageView) findViewById(R.id.iv_header_view_right);
    }

    /**
     * 设置左侧点击事件
     */
    public void setBackListener(OnClickListener listener){
        leftIv.setImageResource(R.mipmap.icon_arrow_back);
        if (listener != null) {
            leftIv.setOnClickListener(listener);
        }
    }

    /**
     * 设置标题
     */
    public void setTitle(int charId){
        titleTv.setText(charId);
    }

    /**
     * 设置右侧按钮文本及点击事件
     */
    public void setRightTextAndListener(int charId,OnClickListener listener){
        rightTv.setText(charId);
        if (listener != null){
            rightTv.setOnClickListener(listener);
        }
    }

    /**
     * 设置左侧图标、及点击事件
     */
    public void setLeftImageAndListener(int imageId,OnClickListener listener){
        leftIv.setImageResource(imageId);
        if (listener != null) {
            leftIv.setOnClickListener(listener);
        }
    }

    /**
     * 设置右侧图标、及点击事件
     */
    public void setRightImageAndListener(int imageId,OnClickListener listener){
        rightIv.setImageResource(imageId);
        if (listener != null) {
            rightIv.setOnClickListener(listener);
        }
    }
}
