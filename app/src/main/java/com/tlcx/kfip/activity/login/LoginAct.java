package com.tlcx.kfip.activity.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;
import com.tlcx.library.utils.KeyboardUtils;
import com.tlcx.library.utils.RegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginAct extends AppBaseActivity {

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;
    @BindView(R.id.et_phone_number)
    EditText mPhoneNumberEt;               //手机号
    @BindView(R.id.et_password)
    EditText mPasswordEt;                  //密码
    @BindView(R.id.cb_remember_pwd)
    CheckBox rememberPwdCb;               //记住密码对话框
    @BindView(R.id.tv_forget_pwd)
    TextView forgetPwdTv;                 //忘记密码
    @BindView(R.id.tv_login)
    TextView loginTv;                     //登录

    public static Intent newIntent(Context context){
        return new Intent(context,LoginAct.class);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);

        //设置头部
        setHeaderView();
        //弹出键盘
        KeyboardUtils.openKeyboard(this,mPhoneNumberEt);
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
                KeyboardUtils.closeKeyboard(LoginAct.this,mPhoneNumberEt);
                finish();
            }
        });
        mHeaderView.setTitle(R.string.title_login);
    }

    /**
     * 登录
     */
    @OnClick(R.id.tv_login)
    public void login(){
        //隐藏键盘
        KeyboardUtils.closeKeyboard(this,mPhoneNumberEt);
        //校验手机号
        String mobileNumber = mPhoneNumberEt.getText().toString();
        if (TextUtils.isEmpty(mobileNumber)){
            showToast(R.string.register_tip_input_phone_number);
            return;
        }else if (!RegexUtil.isMobileNum(mobileNumber)){
            showToast(R.string.register_tip_format_wrong_phone_number);
            return;
        }
        //校验密码
        String password = mPasswordEt.getText().toString();
        if (TextUtils.isEmpty(password)){
            showToast(R.string.register_tip_input_pwd);
            return;
        }else if (RegexUtil.isPassword(password)){
            showToast(R.string.register_tip_format_wrong_pwd);
            return;
        }
        //校验是否记住密码
        if (rememberPwdCb.isChecked()){
            //将密码保存在手机本地
        }
        //调用登录接口

    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.tv_forget_pwd)
    public void retrievePwd(){
        //跳转到找回密码页
    }
}
