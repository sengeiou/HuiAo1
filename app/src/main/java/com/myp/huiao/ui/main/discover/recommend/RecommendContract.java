package com.myp.huiao.ui.main.discover.recommend;

import com.myp.huiao.entity.FeaturedBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecommendContract {
    interface View extends BaseRequestView {

        /**
         * 返回精选推荐
         */
        void getFeatured(List<FeaturedBO> featuredBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取精选推荐
         */
        void loadDiscoverFeatured();

        /**
         * 获取分类列表
         */
        void loadClassifyList();

    }
}
