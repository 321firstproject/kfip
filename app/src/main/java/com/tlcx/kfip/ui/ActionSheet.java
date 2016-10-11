package com.tlcx.kfip.ui;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tlcx.kfip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自屏幕底部弹出的弹出框
 * Created by victor on 2016/10/9 16:48.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class ActionSheet {
    private List<SheetItem> sheetItemList;   //存放item的集合

    private Dialog mDialog;
    private Context mContext;
    private Display mDisplay;
    private LayoutInflater mInflater;

    private TextView mCancelTv;           //取消
    private LinearLayout mContentLayout;  //容器

    public ActionSheet(Context context){
        mContext = context;
        //使用windowManager创建一个窗口实例display
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public ActionSheet builder(){

        View view = mInflater.inflate(R.layout.widget_action_sheet,null);

        //设置view的最小宽度
        view.setMinimumWidth(mDisplay.getWidth());

        mCancelTv= (TextView) view.findViewById(R.id.tv_cancel);
        mContentLayout = (LinearLayout) view.findViewById(R.id.ly_content);

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog = new Dialog(mContext,R.style.NewActionSheetStyle);
        mDialog.setContentView(view);

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.START|Gravity.BOTTOM);

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    /**
     * Sets whether this dialog is cancelable with the BACK key.
     * @param cancel
     * @return
     */
    public ActionSheet setCancelable(boolean cancel){
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置点击弹窗外部区域弹窗是否消失
     * @param cancel
     * @return
     */
    public ActionSheet setCanceledOnTouchOutside(boolean cancel){
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 添加item
     */
    public ActionSheet addSheetItem(String strItem,OnSheetItemClickListener listener){
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, listener));
        return this;
    }

    private void setSheetItems(){
        if (sheetItemList == null || sheetItemList.size()<=0){
            return;
        }
        int size = sheetItemList.size();

        if (size>=7){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mContentLayout.getLayoutParams();
            params.height = mDisplay.getHeight()/2;
            mContentLayout.setLayoutParams(params);
        }

        for (int i = 0; i < size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i);

            String strItem = sheetItem.content;
            final OnSheetItemClickListener listener = sheetItem.listener;

            TextView textView = new TextView(mContext);
            textView.setText(strItem);
            textView.setTextColor(mContext.getResources().getColor(R.color.action_sheet_text_color));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            textView.setBackgroundResource(R.drawable.shape_item_action_sheet);
            textView.setGravity(Gravity.CENTER);

            //设置textview高度
            float density = mContext.getResources().getDisplayMetrics().density;
            int height = (int)(47*density+0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    height));
            //设置点击监听
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onClick(index);
                    }
                    mDialog.dismiss();
                }
            });

            mContentLayout.addView(textView);
        }
    }

    /**
     * 显示对话框
     */
    public void show(){
        setSheetItems();
        mDialog.show();
    }
    /**
     * sheetItem 的点击回调
     */
    public interface OnSheetItemClickListener{
        void onClick(int which);
    }

    public class SheetItem{
        String content; //内容
        OnSheetItemClickListener listener; //点击监听
        public SheetItem(String content,OnSheetItemClickListener listener){
            this.content = content;
            this.listener = listener;
        }
    }
}
