package com.myp.huiao.ui.main.study;

import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.MyCourserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class StudyContract {
    interface View extends BaseRequestView {


        void getMyCourserList(List<MyCourserBO> courserBOs);

        void getArticle(List<ArticleBO> articleBOs);

        /**
         * 返回的热门课程
         */
        void getHotCourse(List<CourserBO> courserBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取我的课程列表
         */
        void loadMyCourserList(String courseStatus);

        /**
         * 获取今日新知
         */
        void loadTodayArticle(String articleType);

        /**
         * 获取热门课程
         */
        void loadHotCourser();

    }
}
