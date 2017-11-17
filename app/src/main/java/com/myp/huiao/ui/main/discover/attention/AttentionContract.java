package com.myp.huiao.ui.main.discover.attention;

import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AttentionContract {
    interface View extends BaseRequestView {

        void getTopicList(List<TopicBO> topicBOs);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取关注用户的话题
         */
        void followTopic(boolean pageAdd);

    }
}
