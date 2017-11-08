package com.myp.huiao.ui.main.discover.recommend;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.entity.TopicBO;
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
        HttpServiceIml.discoverFeatured().subscribe(new Subscriber<List<TopicBO>>() {
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
            public void onNext(List<TopicBO> s) {
                if (mView != null) {
                    mView.getFeatured(s);
                }
            }
        });
    }

    @Override
    public void loadClassifyList() {
        HttpServiceIml.classifyList("1").subscribe(new Subscriber<List<TopicClaissIfyBO>>() {
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
            public void onNext(List<TopicClaissIfyBO> s) {
                if (mView != null) {
                    mView.getToppicList(s);
                }
            }
        });
    }

    int page = 1;

    @Override
    public void loadTopPicList(boolean isPage) {
        if (isPage) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.HotTopicList("3", page + "").subscribe(new Subscriber<List<TopicBO>>() {
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
            public void onNext(List<TopicBO> s) {
                if (mView != null) {
                    mView.getToppicUser(s);
                }
            }
        });
    }
}
