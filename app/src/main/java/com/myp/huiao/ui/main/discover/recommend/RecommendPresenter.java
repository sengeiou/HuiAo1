package com.myp.huiao.ui.main.discover.recommend;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.FeaturedBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecommendPresenter extends BasePresenterImpl<RecommendContract.View>
        implements RecommendContract.Presenter {

    @Override
    public void loadDiscoverFeatured() {
        HttpServiceIml.discoverFeatured().subscribe(new Subscriber<List<FeaturedBO>>() {
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
            public void onNext(List<FeaturedBO> s) {
                if (mView != null) {
                    mView.getFeatured(s);
                }
            }
        });
    }

    @Override
    public void loadClassifyList() {
        HttpServiceIml.classifyList("1").subscribe(new Subscriber<String>() {
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
            public void onNext(String s) {

            }
        });
    }
}
