package com.myp.huiao.ui.main.discover.attention;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AttentionPresenter extends BasePresenterImpl<AttentionContract.View>
        implements AttentionContract.Presenter {

    int page = 1;

    @Override
    public void followTopic(boolean pageAdd) {
        if (pageAdd) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.followTopic(page + "").subscribe(new Subscriber<List<TopicBO>>() {
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
                    mView.getTopicList(s);
                }
            }
        });
    }
}
