package com.myp.huiao.ui.todayarticle;

import android.content.Context;

import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;
import com.myp.huiao.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TodayArticleContract {
    interface View extends BaseRequestView {

        void getArticleList(List<ArticleBO> articleBOs, boolean isPage);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取今日新知
         */
        void loadAriticleList(boolean isPage);
    }
}
