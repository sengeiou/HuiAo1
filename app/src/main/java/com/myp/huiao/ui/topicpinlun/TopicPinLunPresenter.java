package com.myp.huiao.ui.topicpinlun;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TopicPinLunPresenter extends BasePresenterImpl<TopicPinLunContract.View>
        implements TopicPinLunContract.Presenter {

    private int page = 1;

    @Override
    public void loadPinLunToPinLun(int id, final boolean isPageAdd) {
        if (isPageAdd) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.topicPinLunToPinLun(id, page + "").subscribe(new Subscriber<List<PinLunBO>>() {
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
            public void onNext(List<PinLunBO> s) {
                if (mView != null) {
                    mView.getPinLunToPinLun(s, isPageAdd);
                }
            }
        });
    }

    @Override
    public void loadAddPinLun(String id, String content) {
        HttpServiceIml.topicPinLunToPinLun(id, content).subscribe(new Subscriber<String>() {
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
                    mView.getAddPinLunSuress();
                }
            }
        });
    }
}
