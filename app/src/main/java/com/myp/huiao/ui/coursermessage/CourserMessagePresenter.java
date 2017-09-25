package com.myp.huiao.ui.coursermessage;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserMessagePresenter extends BasePresenterImpl<CourserMessageContract.View>
        implements CourserMessageContract.Presenter {

    @Override
    public void loadCourserMessage(String courserId) {
        HttpServiceIml.courserMessage(courserId).subscribe(new Subscriber<CourserBO>() {
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
            public void onNext(CourserBO s) {
                if (mView != null) {
                    mView.getCourserBo(s);
                }
            }
        });
    }
}
