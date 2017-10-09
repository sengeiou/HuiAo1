package com.myp.huiao.ui.main.study;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.MyCourserBO;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class StudyPresenter extends BasePresenterImpl<StudyContract.View>
        implements StudyContract.Presenter {

    @Override
    public void loadMyCourserList(String courseStatus) {
        HttpServiceIml.courserMyList(courseStatus, "1").subscribe(new Subscriber<List<MyCourserBO>>() {
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
            public void onNext(List<MyCourserBO> courserBOs) {
                if (mView != null) {
                    mView.getMyCourserList(courserBOs);
                }
            }
        });
    }

    @Override
    public void loadTodayArticle(String articleType) {
        HttpServiceIml.todayArticle(articleType, "1").subscribe(new Subscriber<List<ArticleBO>>() {
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
            public void onNext(List<ArticleBO> s) {
                if (mView != null) {
                    mView.getArticle(s);
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
