package com.myp.huiao.ui.coursersort;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.CourserClassifyBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserSortPresenter extends BasePresenterImpl<CourserSortContract.View>
        implements CourserSortContract.Presenter {

    @Override
    public void loadCourserClassify(String cls) {
        HttpServiceIml.cousersClassify(cls).subscribe(new Subscriber<List<CourserClassifyBO>>() {
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
            public void onNext(List<CourserClassifyBO> s) {
                if (mView != null) {
                    mView.getCourserClassify(s);
                }
            }
        });
    }
}
