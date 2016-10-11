package com.tlcx.kfip.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tlcx.kfip.R;
import com.tlcx.kfip.fragment.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 功夫夺宝碎片
 * Created by victor on 2016/10/1 20:44.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class KFIPFragment extends BaseFragment{




    private Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.frag_kfip;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

    }

    /**
     * 跳转搜索页
     */
    @OnClick(R.id.iv_frag_kfip_search)
    public void skipToSearch(){

    }

    /**
     * 跳转消息页
     */
    @OnClick(R.id.iv_frag_kfip_message)
    public void skipToMessage(){

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
