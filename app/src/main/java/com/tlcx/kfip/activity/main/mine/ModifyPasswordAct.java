package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;
import com.tlcx.library.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 * Created by victor on 2016/10/3 9:58.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class ModifyPasswordAct extends AppBaseActivity {

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;
    @BindView(R.id.et_input_old_pwd)
    EditText oldPwdEt;                        //旧密码
    @BindView(R.id.et_input_new_pwd)
    EditText newPwdEt;                        //新密码
    @BindView(R.id.et_confirm_pwd)
    EditText confirmEt;                       //确认密码
    @BindView(R.id.tv_confirm_pwd)
    TextView confirmModifyTv;                 //确认修改按钮

    public static Intent newIntent(Context context){
        return new Intent(context,ModifyPasswordAct.class);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_modify_password);
        ButterKnife.bind(this);

        //设置头部
        setHeaderView();

        //引出软键盘
        KeyboardUtils.openKeyboard(this,oldPwdEt);
    }

    @Override
    protected void loadData() {

    }

    /**
     * 设置头部
     */
    private void setHeaderView(){
        mHeaderView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏软键盘
                KeyboardUtils.closeKeyboard(ModifyPasswordAct.this,oldPwdEt);
                finish();
            }
        });
        mHeaderView.setTitle(R.string.title_modify_pwd);
    }
    @OnClick(R.id.tv_confirm_pwd)
    public void modifyPwd(){
        //隐藏软键盘
        KeyboardUtils.closeKeyboard(this,oldPwdEt);
    }
}
