package com.myp.huiao.ui.coursersort;

import com.myp.huiao.entity.CourserClassifyBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserSortContract {
    interface View extends BaseRequestView {

        /**
         * 返回课程分类列表
         */
        void getCourserClassify(List<CourserClassifyBO> courserClassifyBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取课程分类
         */
        void loadCourserClassify(String cls);
    }
}
