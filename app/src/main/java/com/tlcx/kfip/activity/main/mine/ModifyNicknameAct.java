package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改昵称页面
 * Created by victor on 2016/10/2 22:33.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class ModifyNicknameAct extends AppBaseActivity {

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;

    public static Intent newIntent(Context context){
        return new Intent(context,ModifyNicknameAct.class);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_modify_nickname);
        ButterKnife.bind(this);

        setHeaderView();
    }

    @Override
    protected void loadData() {

    }

    private void setHeaderView(){
        mHeaderView.setTitle(R.string.title_modify_nickname);
        mHeaderView.setBackListener(new BackListener());
        mHeaderView.setRightTextAndListener(R.string.common_save, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存
            }
        });
    }
}
