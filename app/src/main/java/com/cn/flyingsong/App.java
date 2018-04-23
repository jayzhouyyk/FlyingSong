package com.cn.flyingsong;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;

import gaei.cn.baseapp.applib.AppLib;
import gaei.cn.baseapp.net.RxHttpManager;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * desc:
 * date: 2018/4/2
 *
 * @author: YangYuKun 04118
 */

public class App extends MultiDexApplication {

    private static volatile App instance = null;

    public static App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppLib.init(this);
        initNet(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Utils.init(this);
        MultiDex.install(this);
    }


    private void initNet(Context context) {

        RxHttpManager.init(this);
        RxHttpManager
                .getInstance()
                //开启全局配置
                .config()
                //全局的BaseUrl
                .setBaseUrl(Constant.BASE_URL_QQ)
                //开启缓存策略
                .setCache()
                //全局的请求头信息
                //.setHeaders(headerMaps)
                //全局持久话cookie,保存本地每次都会携带在header中
                .setCookie(false)
                //全局ssl证书认证
                //信任所有证书,不安全有风险
                .setSslSocketFactory()
                //使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.cer"))
                //使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.bks"), "123456", getAssets().open("your.cer"))
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setLog(true, HttpLoggingInterceptor.Level.BODY);
    }
}
