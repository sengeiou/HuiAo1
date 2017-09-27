package com.myp.huiao.ui.coursermessage.evaluate;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.EvaluateBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EvaluatePresenter extends BasePresenterImpl<EvaluateContract.View>
        implements EvaluateContract.Presenter {

    @Override
    public void loadCourserPingjia(String courseId) {
        HttpServiceIml.courserPingjia(courseId).subscribe(new Subscriber<List<EvaluateBO>>() {
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
            public void onNext(List<EvaluateBO> s) {
                if (mView != null) {
                    mView.getEvaluateList(s);
                }
            }
        });
    }
}
