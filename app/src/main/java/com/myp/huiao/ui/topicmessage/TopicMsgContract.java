package com.myp.huiao.ui.topicmessage;

import android.view.View;

import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TopicMsgContract {
    interface View extends BaseRequestView {


        /**
         * 返回话题详情
         */
        void getTopicMessage(TopicBO topicBO);

        /**
         * 返回话题一级评论
         */
        void getTopicMsgPinLun(List<PinLunBO> pinLunBOs, boolean isPageAdd);

        /**
         * 发布评论成功
         */
        void addPinLunSurcess();

        /**
         * 评论点赞成功
         */
        void addDianZanSurcess(android.view.View view, String status);

        /**
         * 话题点赞成功
         */
        void addTopicDianZan(String status);

        /**
         * 关注用户
         */
        void followUser(String status);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取话题详情
         */
        void loadTopicMessage(String topid);

        /**
         * 获取话题一级评论
         */
        void loadTopicMessagePinLun(int id, boolean isPageAdd);

        /**
         * 发布话题评论
         */
        void loadAddTopicPinLun(String topicId, String content);

        /**
         * 为评论点赞
         */
        void loadPinLunZan(android.view.View view, String discussId, String status);

        /**
         * 为话题点赞
         */
        void loadTopicDianZan(String id, String status);

        /**
         * 关注用户
         */
        void loadFollowUser(String userId, String status);


    }
}
