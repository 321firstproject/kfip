package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 夺宝记录页面
 * Created by victor on 2016/10/2 17:13.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class IPRecordsAct extends AppBaseActivity{

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;

    public static Intent newIntent(Context context){
        return new Intent(context,IPRecordsAct.class);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_ip_records);
        ButterKnife.bind(this);

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
        mHeaderView.setTitle(R.string.title_ip_records);
    }
}
