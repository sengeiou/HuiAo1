package com.myp.huiao.ui.userlogin;


import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 * �wuliang
 * <p>
 * 数据请求处理类 (业务处理)
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View>
        implements LoginContract.Presenter {


    @Override
    public void userLogin(String phone, String passWord, String uuid) {
        HttpServiceIml.userLogin(phone, passWord, uuid).subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(UserBO s) {
                if (mView != null) {
                    mView.getUserData(s);
                }
            }
        });
    }
}
