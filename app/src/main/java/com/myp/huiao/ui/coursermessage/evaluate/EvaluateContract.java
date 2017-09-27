package com.myp.huiao.ui.coursermessage.evaluate;

import android.content.Context;

import com.myp.huiao.entity.EvaluateBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;
import com.myp.huiao.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EvaluateContract {
    interface View extends BaseRequestView {

        /**
         * 返回评论列表
         */
        void getEvaluateList(List<EvaluateBO> evaluateBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取课程评价列表
         */
        void loadCourserPingjia(String courseId);
    }
}
