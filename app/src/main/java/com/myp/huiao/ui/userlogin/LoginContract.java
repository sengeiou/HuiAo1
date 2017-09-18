package com.myp.huiao.ui.userlogin;


import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

/**
 * MVPPlugin
 * Wuliang
 * <p>
 * 登陆页面的业务控制
 */

public class LoginContract {

    interface View extends BaseRequestView {

        void getUserData(UserBO data);

    }

    interface Presenter extends BasePresenter<View> {

        void userLogin(String phone, String passWord, String uuid);

    }
}
