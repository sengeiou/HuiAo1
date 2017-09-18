package com.myp.huiao.api;

import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.util.rx.RxResultHelper;

import java.util.List;

import rx.Observable;

/**
 * Created by wuliang on 2017/4/19.
 * <p>
 * 所有网络请求方法
 */

public class HttpServiceIml {

    private static HttpService service;

    /**
     * 获取代理对象
     */
    private static HttpService getService() {
        if (service == null)
            service = ApiManager.getInstance().configRetrofit(HttpService.class, HttpService.URL);
        return service;
    }


    /**
     * 获取验证码
     */
    public static Observable<Object> phoneVerification(String phone, String versionType) {
        return getService().phoneVerification(phone, versionType).compose(RxResultHelper.httpRusult());
    }

    /**
     * 用户注册
     */
    public static Observable<Object> userRegister(String phone, String password, String version) {
        return getService().userRegister(phone, password, version).compose(RxResultHelper.httpRusult());
    }


    /**
     * 用户登录
     */
    public static Observable<UserBO> userLogin(String phone, String password, String uuid) {
        return getService().userLogin(phone, password, uuid).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * 获取学历列表
     */
    public static Observable<List<ChioceBO>> userLoadChoice(String education) {
        return getService().userLoadChoice(education).compose(RxResultHelper.<List<ChioceBO>>httpRusult());
    }

    /**
     * 获取兴趣列表
     */
    public static Observable<List<ChioceBO>> userSetInterest(String interest) {
        return getService().userSetInterest(interest).compose(RxResultHelper.<List<ChioceBO>>httpRusult());
    }

    /**
     * 修改用户资料
     */
    public static Observable<UserBO> userInterest(String gender, String educationId, String birthday, String interestIds) {
        return getService().userInterest(gender, educationId, birthday, interestIds).compose(RxResultHelper.<UserBO>httpRusult());
    }

}
