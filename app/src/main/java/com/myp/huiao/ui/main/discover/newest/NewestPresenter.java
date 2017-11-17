package com.myp.huiao.ui.main.discover.newest;

import android.view.View;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NewestPresenter extends BasePresenterImpl<NewestContract.View> implements
        NewestContract.Presenter {

    int page = 1;

    @Override
    public void loadTopicList(final boolean isPageAdd) {
        if (isPageAdd) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.topicList(page + "").subscribe(new Subscriber<List<TopicBO>>() {
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
            public void onNext(List<TopicBO> topicBOs) {
                if (mView != null) {
                    mView.getTopicList(topicBOs, isPageAdd);
                }
            }
        });
    }

    @Override
    public void loadFollowUser(final View view, String userId, final String status) {
        HttpServiceIml.followUser(userId, status).subscribe(new Subscriber<String>() {
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
                if (mView != null) {
                    mView.followUser(status, view);
                }
            }
        });
    }
}
