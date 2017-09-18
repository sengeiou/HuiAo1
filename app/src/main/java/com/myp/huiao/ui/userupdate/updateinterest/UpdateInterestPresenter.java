package com.myp.huiao.ui.userupdate.updateinterest;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UpdateInterestPresenter extends BasePresenterImpl<UpdateInterestContract.View>
        implements UpdateInterestContract.Presenter {

    @Override
    public void loadUserInterest() {
        HttpServiceIml.userSetInterest("interest").subscribe(new Subscriber<List<ChioceBO>>() {
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
            public void onNext(List<ChioceBO> userBO) {
                if (mView != null) {
                    mView.getListChoices(userBO);
                }
            }
        });
    }

    @Override
    public void setInterest(String gender, String educationId, String birthday, String interestIds) {
        HttpServiceIml.userInterest(gender, educationId, birthday, interestIds).subscribe(new Subscriber<UserBO>() {
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
