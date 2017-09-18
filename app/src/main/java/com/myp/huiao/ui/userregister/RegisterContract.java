package com.myp.huiao.ui.userregister;

import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;
import com.myp.huiao.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseRequestView {

        void getData();

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取验证码
         */
        void getVersion(String phone, String versionType);

        /**
         * 注册
         */
        void registerUser(String phone, String password, String version);

    }
}
