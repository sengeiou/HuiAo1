package com.myp.huiao.ui.todayarticle;

import android.content.Context;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TodayArticlePresenter extends BasePresenterImpl<TodayArticleContract.View>
        implements TodayArticleContract.Presenter {

    int page = 1;

    @Override
    public void loadAriticleList(final boolean isPage) {
        if (isPage) {
            page++;
        } else {
            page = 1;
        }
        HttpServiceIml.todayArticle("1", page + "").subscribe(new Subscriber<List<ArticleBO>>() {
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
            public void onNext(List<ArticleBO> articleBOs) {
                if (mView != null) {
                    if (articleBOs == null || articleBOs.size() == 0) {
                        page--;
                        mView.onRequestError("已经到底了！");
                    } else {
                        mView.getArticleList(articleBOs, isPage);
                    }
                }
            }
        });
    }
}
