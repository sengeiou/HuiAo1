package com.myp.huiao.ui.courserlist;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserListPresenter extends BasePresenterImpl<CourserListContract.View>
        implements CourserListContract.Presenter {

    int page = 1;

    @Override
    public void loadCourserList(String price, String buyCount, String time, String facilityValue,
                                String courseTypeId, String courseName, final boolean isPageAdd) {
        if (isPageAdd) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.courserList(price, buyCount, time, facilityValue, courseTypeId, courseName, page + "")
                .subscribe(new Subscriber<List<CourserBO>>() {
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
                    public void onNext(List<CourserBO> s) {
                        if (mView != null) {
                            if (s == null || s.size() == 0) {
                                mView.onRequestError("已经到底了！");
                                page--;
                            } else {
                                mView.getCourserList(s, isPageAdd);
                            }
                        }
                    }
                });
    }
}
