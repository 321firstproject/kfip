package com.tlcx.kfip.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tlcx.kfip.R;

/**
 * "我的"页面中item控件
 * Created by victor on 2016/10/2 14:27.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class MineTitleValuePair extends LinearLayout {

    private RelativeLayout mLayout;
    private ImageView mIconIv;
    private TextView mTitleTv;

    public MineTitleValuePair(Context context) {
        super(context);
        initView(context);
    }

    public MineTitleValuePair(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * 初始化
     */
    private void initView(Context context){
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.widget_title_value_pair_mine,null,false);
        addView(view);
        mLayout = (RelativeLayout) view.findViewById(R.id.rl_widget_title_value_pair);
        mIconIv = (ImageView) view.findViewById(R.id.iv_widget_icon);
        mTitleTv = (TextView) view.findViewById(R.id.tv_widget_title);
    }

    /**
     * 设置icon和title
     */
    public void setIconAndTitle(int imageId,int titleId){
        mIconIv.setImageResource(imageId);
        mTitleTv.setText(titleId);
    }

    /**
     * 设置点击事件
     */
    public void setClickListener(OnClickListener listener){
        if (listener != null) {
            mLayout.setOnClickListener(listener);
        }
    }
}
