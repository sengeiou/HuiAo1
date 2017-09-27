package com.myp.huiao.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.myp.huiao.config.ConditionEnum;
import com.myp.huiao.config.LocalConfiguration;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.util.SPUtils;
import com.myp.huiao.util.Utils;
import com.myp.huiao.wxapi.WXUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * 作者 by wuliang 时间 16/10/26.
 * <p>
 * 程序的application
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    public static ConditionEnum isLogin = ConditionEnum.NOLOGIN;   //默认未登陆
    public static SPUtils spUtils;   //缓存类型
    public static UserBO user;    //程序使用用户

    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
//        CustomActivityOnCrash.setErrorActivityClass(CustomErrorActivity.class);
        /***初始化工具类*/
        Utils.init(this);
        /**初始化SharedPreferences缓存*/
        spUtils = new SPUtils(LocalConfiguration.SP_NAME);
        /***注册分享*/
        ShareSDK.initSDK(this);
        /***注册极光推送*/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        /***注册微信登陆，支付服务*/
        WXUtils.registerWX(this);
        initImageLoader();
    }


    /**
     * 初始化ImageLoader，这个适用在大图片加载中
     */
    public void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
