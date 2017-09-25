package com.myp.huiao.ui.courserselect;

import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;
import com.myp.huiao.ui.courserlist.CourserListContract;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserSelectContract {
    interface View extends BaseRequestView {


        /**
         * 返回课程列表
         */
        void getCourserList(List<CourserBO> courserBOs, boolean isPageAdd);

    }

    interface Presenter extends BasePresenter<CourserSelectContract.View> {

        /**
         * 获取课程列表
         */
        void loadCourserList(String price, String buyCount, String time,
                             String facilityValue, String courseTypeId,
                             String courseName, boolean isPageAdd);

    }
}
