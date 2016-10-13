package com.tlcx.kfip.adapter;

import android.content.Context;
import android.view.View;

import com.tlcx.kfip.R;
import com.tlcx.kfip.entity.KfipListEntity;

/**
 * 功夫夺宝列表适配器
 * Created by victor on 2016/10/12 15:20.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class KfipListAdapter extends BaseAdapter<KfipListEntity> {
    public KfipListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemResource(int viewType) {
        return R.layout.adapter_kfip;
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder viewHolder) {
        KfipListEntity item = getItem(position);

        return convertView;
    }
}
