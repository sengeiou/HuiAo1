package com.myp.huiao.ui.main.classlibraay;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.BannerBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClassLibraayPresenter extends BasePresenterImpl<ClassLibraayContract.View>
        implements ClassLibraayContract.Presenter {

    @Override
    public void getBanners(String source) {
        HttpServiceIml.bannersList(source).subscribe(new Subscriber<List<BannerBO>>() {
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
            public void onNext(List<BannerBO> s) {
                if (mView != null) {
                    mView.getBanners(s);
                }
            }
        });
    }

    @Override
    public void loadSpecialCourser() {
        HttpServiceIml.hotCourse("special").subscribe(new Subscriber<List<CourserBO>>() {
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
                    mView.getSpecialCourse(s);
                }
            }
        });
    }

    @Override
    public void loadHotCourser() {
        HttpServiceIml.hotCourse("hot").subscribe(new Subscriber<List<CourserBO>>() {
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
                    mView.getHotCourse(s);
                }
            }
        });
    }
}
