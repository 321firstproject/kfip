package com.tlcx.kfip;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tlcx.kfip.utils.Constants;

import okhttp3.OkHttpClient;

/**
 * Created by victor on 2016/9/22 17:48.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class BaseApplication extends Application {

    private static final String TAG = "KFIP_LOG";

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化日志框架
        Logger.init(TAG);

        //初始化（安装）内存溢出检测框架
        LeakCanary.install(this);

        //初始化Android调试
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        new OkHttpClient.Builder() .
                addNetworkInterceptor(new StethoInterceptor()) .build();

        //初始化图片加载框架
        Fresco.initialize(this);

        //初始化数据
        Constants.initValue(this);
    }
}
