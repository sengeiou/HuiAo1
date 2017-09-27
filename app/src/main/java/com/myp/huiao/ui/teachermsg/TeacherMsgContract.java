package com.myp.huiao.ui.teachermsg;

import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.TeachersBo;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TeacherMsgContract {
    interface View extends BaseRequestView {

        void getTeacherBean(TeachersBo teachersBo);

        void getTeacherCourser(List<CourserBO> courserBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取教师详情
         */
        void loadTeacherMsg(String teacherId);

        /**
         * 获取教师相关课程
         */
        void loadTeacherCourser(String teacherId);
    }
}
