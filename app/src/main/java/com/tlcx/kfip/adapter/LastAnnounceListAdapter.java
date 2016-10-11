package com.tlcx.kfip.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tlcx.kfip.R;
import com.tlcx.kfip.entity.LastAnnounceEntity;

/**
 * Created by victor on 2016/10/6 21:38.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class LastAnnounceListAdapter extends BaseAdapter<LastAnnounceEntity> {

    public LastAnnounceListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemResource(int viewType) {
        return R.layout.adapter_last_announce;
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder viewHolder) {
        TextView title = viewHolder.getView(R.id.tv_last_announce_title);
        LastAnnounceEntity item = getItem(position);
        title.setText(item.getName());
        return convertView;
    }
}
