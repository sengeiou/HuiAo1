package com.myp.huiao.ui.main.discover.recommend;

import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.entity.TopicBO;
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
        void getFeatured(List<TopicBO> featuredBOs);

        /**
         * 返回话题类别列表
         */
        void getToppicList(List<TopicClaissIfyBO> topicClaissIfyBOs);

        /**
         * 返回话题列表
         */
        void getToppicUser(List<TopicBO> topicBOs);

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

        /**
         * 获取所有话题
         */
        void loadTopPicList(boolean isPage);

    }
}
