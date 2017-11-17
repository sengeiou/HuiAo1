package com.myp.huiao.ui.main.discover.newest;

import android.view.View;

import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NewestContract {
    interface View extends BaseRequestView {

        void getTopicList(List<TopicBO> topicBOs, boolean isPageAdd);

        /**
         * 关注用户
         */
        void followUser(String status, android.view.View view);

    }

    interface Presenter extends BasePresenter<View> {

        void loadTopicList(boolean isPageAdd);

        /**
         * 关注用户
         */
        void loadFollowUser(android.view.View view, String userId, String status);

    }
}
