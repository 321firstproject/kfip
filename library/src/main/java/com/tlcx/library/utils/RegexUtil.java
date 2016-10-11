package com.tlcx.library.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 正则判断工具
 * Created by victor on 2016/9/29 16:53.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class RegexUtil {
    public static final String MOBILE_REGEX = "^1((3[0-9]|4[57]|5[0-35-9]|7[0678]|8[0-9])\\d{8}$)"; //手机号
    public static final String VERIFY_CODE_REGEX = "^\\d{6}$";                                       //验证码
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9_]{6,13}$";                              //用户密码校验

    public static final Pattern MOBILE_PATTERN = Pattern.compile(MOBILE_REGEX);
    public static final Pattern VERIFY_CODE_PATTERN = Pattern.compile(VERIFY_CODE_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    /**
     * 判断是否是手机号
     * @param mobile
     */
    public static boolean isMobileNum(String mobile){
        if (TextUtils.isEmpty(mobile)){
            return false;
        }
        return MOBILE_PATTERN.matcher(mobile).matches();
    }
    /**
     * 判断是否为验证码
     * @param verifyCode
     */
    public static boolean isVerifyCode(String verifyCode){
        if (TextUtils.isEmpty(verifyCode)){
            return false;
        }
        return VERIFY_CODE_PATTERN.matcher(verifyCode).matches();
    }
    /**
     * 判断密码
     * @param pwd
     */
    public static boolean isPassword(String pwd) {
        if (TextUtils.isEmpty(pwd)){
            return false;
        }
        return PASSWORD_PATTERN.matcher(pwd).matches();
    }
}
