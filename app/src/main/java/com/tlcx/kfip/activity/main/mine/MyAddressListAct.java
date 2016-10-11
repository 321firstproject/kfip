package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的收货地址页面
 * Created by victor on 2016/10/2 17:31.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class MyAddressListAct extends AppBaseActivity {

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;

    public static Intent newIntent(Context context){
        return new Intent(context,MyAddressListAct.class);
    }

    @Override
    protected void initVariables() {
        setContentView(R.layout.act_my_address_list);
        ButterKnife.bind(this);

        setHeaderView();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }

    private void setHeaderView(){
        mHeaderView.setBackListener(new BackListener());
        mHeaderView.setTitle(R.string.title_my_address);
    }
}
