package com.myp.huiao.ui.userupdate.updateinterest;

import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BasePresenter;
import com.myp.huiao.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UpdateInterestContract {
    interface View extends BaseRequestView {

        void getListChoices(List<ChioceBO> chioceBOs);

        void getUserBo(UserBO userBO);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取兴趣列表
         */
        void loadUserInterest();

        /**
         * 修改用户信息
         */
        void setInterest(String gender, String educationId, String birthday, String interestIds);

    }
}
