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
import com.tlcx.library.utils.CountDownTimerUtils;
import com.tlcx.library.utils.KeyboardUtils;
import com.tlcx.library.utils.RegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册页面
 * Created by victor on 2016/9/21 21:48.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class RegisterAct extends AppBaseActivity {

    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;                 //头布局
    @BindView(R.id.tv_register)
    TextView mRegisterTv;                   //注册按钮
    @BindView(R.id.et_phone_number)
    EditText mPhoneNumberEt;               //手机号
    @BindView(R.id.et_verify_code)
    EditText mVerifyCodeEt;                //验证码
    @BindView(R.id.et_password)
    EditText mPasswordEt;                  //密码
    @BindView(R.id.tv_verify_code)
    TextView mVerifyCodeTv;                //获取验证码的按钮
    @BindView(R.id.cb_register_protocol)
    CheckBox protocolCb;                   //是否阅读协议选项
    @BindView(R.id.tv_read_protocol)
    TextView readProtocolTv;               //阅读协议按钮



    public static Intent newIntent(Context context){
       return new Intent(context,RegisterAct.class);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_register);
        ButterKnife.bind(this);

        //初始化并设置头部
        initAndSetHeaderView();
        //弹出键盘
        KeyboardUtils.showSoftInput(mPhoneNumberEt);
    }

    @Override
    protected void loadData() {
    }

    /**
     * 初始化并设置头部
     */
    private void initAndSetHeaderView(){
        mHeaderView.setBackListener(new BackListener());
        mHeaderView.setTitle(R.string.title_register);
        mHeaderView.setRightTextAndListener(R.string.title_login, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LoginAct.newIntent(RegisterAct.this));
            }
        });
    }

    /**
     * 获取手机验证码
     */
    @OnClick(R.id.tv_verify_code)
    public void getVerifyCode(){
        //校验手机号
        String mobileNumber = mPhoneNumberEt.getText().toString();
        if (TextUtils.isEmpty(mobileNumber)){
            showToast(R.string.register_tip_input_phone_number);
        }else if (!RegexUtil.isMobileNum(mobileNumber)){
            showToast(R.string.register_tip_format_wrong_phone_number);
        }else {
            //发送获取验证码请求
            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(mVerifyCodeTv,60000,1000);
            countDownTimerUtils.start();
        }

    }
    /**
     * 点击注册
     */
    @OnClick(R.id.tv_register)
    public void clickButton(){
        //隐藏软键盘
        KeyboardUtils.hideSoftInput(this);
        //校验手机号
        String mobileNumber = mPhoneNumberEt.getText().toString();
        if (TextUtils.isEmpty(mobileNumber)){
            showToast(R.string.register_tip_input_phone_number);
            return;
        }else if (!RegexUtil.isMobileNum(mobileNumber)){
            showToast(R.string.register_tip_format_wrong_phone_number);
            return;
        }
        //校验验证码
        String verifyCode = mVerifyCodeTv.getText().toString();
        if (TextUtils.isEmpty(verifyCode)){
            showToast(R.string.register_tip_input_verify_code);
            return;
        }else if (!RegexUtil.isVerifyCode(verifyCode)){
            showToast(R.string.register_tip_format_wrong_verify_code);
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
        //校验是否同意软件协议
        if (!protocolCb.isChecked()){
            showToast(R.string.register_tip_set_protocol_true);
            return;
        }
        //调取注册接口注册用户


    }

    /**
     * 阅读协议
     */
    @OnClick(R.id.tv_read_protocol)
    public void readProtocol(){
        //跳转协议页面
    }

}
