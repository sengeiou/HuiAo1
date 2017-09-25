package com.myp.huiao.ui.userforword;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForwordPassPresenter extends BasePresenterImpl<ForwordPassContract.View> implements
        ForwordPassContract.Presenter {

    @Override
    public void loadVersionCode(String phone, String type) {
        HttpServiceIml.phoneVerification(phone, type).subscribe(new Subscriber<Object>() {

            @Override
            public void onNext(Object o) {
                if (mView != null) {
                    mView.versionSuress();
                }
            }

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
        });
    }

    @Override
    public void loadAuthentication(String account, String phone, String version) {
        HttpServiceIml.userAuthentication(account, phone, version).subscribe(new Subscriber<UserBO>() {
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
            public void onNext(UserBO userBO) {
                if (mView != null) {
                    mView.getUserBo(userBO);
                }
            }
        });
    }
}
