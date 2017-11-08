package com.myp.huiao.ui.topicmessage;

import android.view.View;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TopicMsgPresenter extends BasePresenterImpl<TopicMsgContract.View>
        implements TopicMsgContract.Presenter {

    @Override
    public void loadTopicMessage(String topid) {
        HttpServiceIml.topicMessage(topid).subscribe(new Subscriber<TopicBO>() {
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
            public void onNext(TopicBO s) {
                if (mView != null) {
                    mView.getTopicMessage(s);
                }
            }
        });
    }

    int page = 1;

    @Override
    public void loadTopicMessagePinLun(int id, final boolean isPageAdd) {
        if (isPageAdd) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.topicMessagePinLun(id, page + "").subscribe(new Subscriber<List<PinLunBO>>() {
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
                    if (s == null || s.size() == 0) {
                        page--;
                    } else {
                        mView.getTopicMsgPinLun(s, isPageAdd);
                    }
                }
            }
        });
    }

    @Override
    public void loadAddTopicPinLun(String topicId, String content) {
        HttpServiceIml.topicAddPinLun(topicId, content).subscribe(new Subscriber<String>() {
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
                    mView.addPinLunSurcess();
                }
            }
        });
    }

    @Override
    public void loadPinLunZan(final View view, String discussId, final String status) {
        HttpServiceIml.PinLunDianZan(discussId, status).subscribe(new Subscriber<String>() {
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
                    mView.addDianZanSurcess(view, status);
                }
            }
        });
    }

    @Override
    public void loadTopicDianZan(String id, final String status) {
        HttpServiceIml.topicDianZan(id, status).subscribe(new Subscriber<String>() {
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
                    mView.addTopicDianZan(status);
                }
            }
        });
    }

    @Override
    public void loadFollowUser(String userId, final String status) {
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
                    mView.followUser(status);
                }
            }
        });
    }
}
