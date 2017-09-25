package com.myp.huiao.ui.coursermessage;

import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserMessageContract {
    interface View extends BaseRequestView {

        /**
         * 返回课程详情
         */
        void getCourserBo(CourserBO courserBO);

    }

    interface Presenter extends BasePresenter<View> {


        /**
         * 获取课程详情
         */
        void loadCourserMessage(String courserId);

    }
}
