package com.myp.huiao.ui.userregister;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements
        RegisterContract.Presenter {

    @Override
    public void getVersion(String phone, String versionType) {
        HttpServiceIml.phoneVerification(phone, versionType).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(Object s) {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }
        });
    }

    @Override
    public void registerUser(String phone, String password, String version) {
        HttpServiceIml.userRegister(phone, password, version).subscribe(new Subscriber<Object>() {
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
            public void onNext(Object s) {
                if (mView != null) {
                    mView.getData();
                }
            }
        });
    }
}
