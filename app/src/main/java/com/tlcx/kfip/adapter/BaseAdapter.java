package com.tlcx.kfip.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseAdapter
 * Created by victor on 2016/10/6 21:07.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public  abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected List<T> mData;
    protected Context mContext;

    public BaseAdapter(Context context){
        this(context,null);
    }
    public BaseAdapter(Context context,List<T> data){
        mData = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            int viewType = getItemViewType(position);
            convertView = View.inflate(mContext,getItemResource(viewType),null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return getItemView(position,convertView,holder);
    }
    public class ViewHolder{
        private SparseArray<View> views = new SparseArray<>();
        private View convertView;

        public ViewHolder(View convertView){
            this.convertView = convertView;
        }
        public <T extends View> T getView(int resId){
            View view = views.get(resId);
            if (view == null) {
                view = convertView.findViewById(resId);
                views.put(resId,view);
            }
            return (T) view;
        }
    }

    /**
     *需要子类实现，返回item的布局id
     */
    public abstract int getItemResource(int viewType);

    /**
     *需要子类实现，替换getView（）方法
     */
    public abstract View getItemView(int position ,View convertView,ViewHolder viewHolder);

    /**
     * 清空数据源
     */
    public void clearData(){
        mData.clear();
    }

    /**
     * 添加数据
     */
    public void addAllData(List<T> list){
        if (list != null && list.size() >0){
            mData.addAll(list);
        }
    }

    /**
     * 添加数据并刷新
     */
    public void addAllAndNotify(List<T> list){
        addAllData(list);
        notifyDataSetChanged();
    }
}
