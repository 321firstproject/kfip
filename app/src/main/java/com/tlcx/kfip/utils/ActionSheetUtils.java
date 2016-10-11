package com.tlcx.kfip.utils;

import android.app.Activity;

import com.tlcx.kfip.R;
import com.tlcx.kfip.ui.ActionSheet;

/**
 * ActionSheet工具类
 * Created by victor on 2016/10/9 17:26.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class ActionSheetUtils {

    /**
     * 设置头像的选择菜单
     */
    public static void menuOfSetAvatar(Activity activity, final OnClickItemListener listener){
        if (activity == null || listener == null)
            return;
        //获得菜单选项字符串数据
        String[] stringArray = activity.getResources().getStringArray(R.array.common_menu_set_avatar);
        ActionSheet actionSheet = new ActionSheet(activity);
        actionSheet.builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true);
        //添加item
        for (int i = 0; i < stringArray.length; i++) {
            actionSheet.addSheetItem(stringArray[i], new ActionSheet.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    listener.onClick(which);
                }
            });
        }
        actionSheet.show();
    }
    public interface OnClickItemListener{
        void onClick(int index);
    }
}
