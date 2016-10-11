package com.tlcx.kfip.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tlcx.kfip.R;
import com.tlcx.kfip.listener.OnMainMenuChangedListener;

/**
 * 主页底部View
 * Created by victor on 2016/10/1 13:32.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class MainBottomView extends LinearLayout{

    RadioGroup mRadioGroup;

    private OnMainMenuChangedListener listener;

    public MainBottomView(Context context) {
        super(context);
        initView(context);
    }

    public MainBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context){
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.widget_main_bottom_vew,null,false);
        addView(view);
        setView();
    }
    private void setView(){
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main_bottom_view);
        RadioButton childAtFirst = (RadioButton) mRadioGroup.getChildAt(0);
        childAtFirst.setChecked(true);       //默认第一个被选中
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //判断被选中的下标
                for (int j = 0; j < mRadioGroup.getChildCount(); j++) {
                    RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(j);
                    if (childAt.getId() == i && listener !=null){
                        listener.onCheckedChanged(j);
                        return;
                    }
                }
            }
        });
    }

    public void setListener(OnMainMenuChangedListener listener){
        this.listener = listener;
    }
}
