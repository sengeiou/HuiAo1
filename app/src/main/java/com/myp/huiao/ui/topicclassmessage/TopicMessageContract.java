package com.myp.huiao.ui.topicclassmessage;

import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TopicMessageContract {
    interface View extends BaseRequestView {


        /**
         * 返回类别详情
         */
        void getClassifyBo(TopicClaissIfyBO claissIfyBO);

        /**
         * 返回类别详情下的话题列表
         */
        void getTopicList(List<TopicBO> topicBOs, boolean ispage);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取类别详情
         */
        void loadClassifyMsg(String id);

        /**
         * 获取类别详情下的话题列表
         */
        void loadClassifyList(String stuts, String id, boolean isPageAdd);
    }
}
