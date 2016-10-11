package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 充值页面
 * Created by victor on 2016/10/10 18:46.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class RechargeAct extends AppBaseActivity {

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;

    public static Intent newIntent(Context context){
        return new Intent(context,RechargeAct.class);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_recharge);
        ButterKnife.bind(this);

        //设置头部
        setHeaderView();
    }

    @Override
    protected void loadData() {

    }

    /**
     * 设置头部
     */
    private void setHeaderView(){
        mHeaderView.setBackListener(new BackListener());
        mHeaderView.setTitle(R.string.title_recharge);
    }
}
