package com.myp.huiao.ui.teachermsg;

import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.TeachersBo;
import com.myp.huiao.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TeacherMsgPresenter extends BasePresenterImpl<TeacherMsgContract.View>
        implements TeacherMsgContract.Presenter {

    @Override
    public void loadTeacherMsg(String teacherId) {
        HttpServiceIml.teacherMessage(teacherId).subscribe(new Subscriber<TeachersBo>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(TeachersBo s) {
                if (mView != null) {
                    mView.getTeacherBean(s);
                }
            }
        });
    }

    @Override
    public void loadTeacherCourser(String teacherId) {
        HttpServiceIml.teacherCoursers(teacherId).subscribe(new Subscriber<List<CourserBO>>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(List<CourserBO> s) {
                if (mView != null) {
                    mView.getTeacherCourser(s);
                }
            }
        });
    }
}
