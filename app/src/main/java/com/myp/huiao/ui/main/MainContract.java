package com.myp.huiao.ui.main;


import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

/**
 * MVPPlugin
 * �wuliang
 * <p>
 * 主页的业务接口
 */

public class MainContract {
    interface View extends BaseRequestView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
