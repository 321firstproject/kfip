package com.tlcx.kfip.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tlcx.kfip.R;

/**
 * 对话框工具类
 * Created by victor on 2016/10/9 16:10.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class AlertDialogUtils {

    /**
     * 选择性别的对话框
     */
    public static AlertDialog selectSexDialog(Context context, final OnClickItemListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.widget_dialog_select_sex,null,false);
        TextView textView1 = (TextView) view.findViewById(R.id.tv_dialog_male);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_dialog_female);
        builder.setView(view);
        final AlertDialog mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);
        if (listener != null) {
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    listener.onClick(0);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    listener.onClick(1);
                }
            });
        }
        mDialog.show();
        return mDialog;
    }
    public interface OnClickItemListener{
        void onClick(int index);
    }
}
