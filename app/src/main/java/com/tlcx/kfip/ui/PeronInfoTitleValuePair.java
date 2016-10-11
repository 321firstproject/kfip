package com.tlcx.kfip.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tlcx.kfip.R;

/**
 * 个人信息页面中Item控件
 * Created by victor on 2016/10/2 21:46.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class PeronInfoTitleValuePair extends LinearLayout {


    private TextView titleTv;              //左侧title文本
    private TextView valueTv;              //右侧对应值
    private RelativeLayout mLayout;        //item布局

    public PeronInfoTitleValuePair(Context context) {
        super(context);
        initView(context);
    }

    public PeronInfoTitleValuePair(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    private void initView(Context context){
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.widget_title_value_pair_person_info,null,false);
        addView(view);

        titleTv = (TextView) findViewById(R.id.tv_person_info_title);
        valueTv = (TextView) findViewById(R.id.iv_person_info_value);
        mLayout = (RelativeLayout) findViewById(R.id.rl_person_info_item);
    }

    /**
     * 设置左侧文本
     */
    public void setTitle(int titleId){
        titleTv.setText(titleId);
    }

    /**
     * 设置右侧文本
     */
    public void setValue(String value){
        if (!TextUtils.isEmpty(value)){
            valueTv.setText(value);
        }
    }

    /**
     * 给整个view设置点击监听
     */
    public void setClickListener(OnClickListener listener){
        if (listener != null) {
            mLayout.setOnClickListener(listener);
        }
    }
}
