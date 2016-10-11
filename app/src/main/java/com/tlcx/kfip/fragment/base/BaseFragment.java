package com.tlcx.kfip.fragment.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 碎片基类
 * Created by victor on 2016/10/1 20:30.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public abstract class BaseFragment extends Fragment {
    protected int RESULT_OK = Activity.RESULT_OK;
    
    protected void showSortToast(String content){
        if (TextUtils.isEmpty(content)){
            return;
        }
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }
    
    protected void showShortToast(int resId){
        Toast.makeText(getActivity(),resId,Toast.LENGTH_SHORT).show();
    }

    /**
     * 获得资源文件中的视图
     */
    protected abstract int getContentView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentView(),container,false);
    }
    
    protected void finish(){
        if (getActivity() != null){
            getActivity().finish();
        }
    }
    
    public final void setResult(int resultCode){
        if (getActivity()!=null){
            getActivity().setResult(resultCode);
        }
    }
    
    public final void setResult(int resultCode, Intent data){
        if (getActivity() != null){
            getActivity().setResult(resultCode,data);
        }
    }
}
