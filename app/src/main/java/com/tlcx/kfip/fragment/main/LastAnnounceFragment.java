package com.tlcx.kfip.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tlcx.kfip.R;
import com.tlcx.kfip.fragment.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 最新揭晓碎片
 * Created by victor on 2016/10/1 20:46.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class LastAnnounceFragment extends BaseFragment {

    private Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.frag_last_announce;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
