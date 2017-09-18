package com.myp.huiao.ui.userupdate.updateshenfen;

import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;
import com.myp.huiao.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UpdateShenfenContract {
    interface View extends BaseRequestView {


        void getChioceList(List<ChioceBO> chioceBOList);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取学历列表
         */
        void loadUserShenfen();

    }
}
