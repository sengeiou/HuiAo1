package com.myp.huiao.ui.topicclassmessage;

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

public class TopicMessagePresenter extends BasePresenterImpl<TopicMessageContract.View>
        implements TopicMessageContract.Presenter {

    @Override
    public void loadClassifyMsg(String id) {
        HttpServiceIml.classifyMessage(id).subscribe(new Subscriber<TopicClaissIfyBO>() {
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
            public void onNext(TopicClaissIfyBO s) {
                if (mView != null) {
                    mView.getClassifyBo(s);
                }
            }
        });
    }

    private int page = 1;

    @Override
    public void loadClassifyList(String stuts,String id, final boolean isPageAdd) {
        if (isPageAdd) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.classifyTopicList(stuts,id, page + "").subscribe(new Subscriber<List<TopicBO>>() {
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
}
