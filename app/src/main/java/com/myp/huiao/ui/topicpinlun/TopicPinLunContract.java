package com.myp.huiao.ui.topicpinlun;

import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TopicPinLunContract {
    interface View extends BaseRequestView {

        /**
         * 返回二级评论列表
         */
        void getPinLunToPinLun(List<PinLunBO> pinLunBOs, boolean isPageAdd);

        /**
         * 发布二级评论成功
         */
        void getAddPinLunSuress();

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取二级评论
         */
        void loadPinLunToPinLun(int id, boolean isPageAdd);

        /**
         * 发布二级评论
         */
        void loadAddPinLun(String id, String content);
    }
}
