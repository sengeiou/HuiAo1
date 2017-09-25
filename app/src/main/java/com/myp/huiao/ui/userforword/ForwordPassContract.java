package com.myp.huiao.ui.userforword;

import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;
import com.myp.huiao.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ForwordPassContract {
    interface View extends BaseRequestView {


        /**
         * 获取验证码成功
         */
        void versionSuress();

        /**
         * 验证身份成功
         */
        void getUserBo(UserBO userBO);

    }

    interface  Presenter extends BasePresenter<View> {

        /**
         * 获取验证码
         */
        void loadVersionCode(String phone,String type);

        /**
         * 验证身份
         */
        void loadAuthentication(String account,String phone,String version);
    }
}
