package com.myp.huiao.ui.userupdate.updateshenfen;

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

public class UpdateShenfenPresenter extends BasePresenterImpl<UpdateShenfenContract.View>
        implements UpdateShenfenContract.Presenter {

    @Override
    public void loadUserShenfen() {
        HttpServiceIml.userLoadChoice("education").subscribe(new Subscriber<List<ChioceBO>>() {
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
            public void onNext(List<ChioceBO> s) {
                if (mView != null) {
                    mView.getChioceList(s);
                }
            }
        });
    }

}
