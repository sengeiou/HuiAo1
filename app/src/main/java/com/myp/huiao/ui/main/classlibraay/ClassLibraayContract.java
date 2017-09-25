package com.myp.huiao.ui.main.classlibraay;

import com.myp.huiao.entity.BannerBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClassLibraayContract {

    interface View extends BaseRequestView {

        /**
         * 返回的专栏课程
         */
        void getSpecialCourse(List<CourserBO> courserBOs);


        /**
         * 返回的热门课程
         */
        void getHotCourse(List<CourserBO> courserBOs);


        /**
         * 返回轮播图
         */
        void getBanners(List<BannerBO> bannerBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取轮播图
         */
        void getBanners(String source);


        /**
         * 获取专栏课程
         */
        void loadSpecialCourser();

        /**
         * 获取热门课程
         */
        void loadHotCourser();

    }
}
