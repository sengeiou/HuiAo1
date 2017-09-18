package com.myp.huiao.ui.userlogin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.base.MyApplication;
import com.myp.huiao.config.ConditionEnum;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.userforword.ForwordPassActivity;
import com.myp.huiao.ui.userregister.RegisterActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.MD5;
import com.myp.huiao.util.StringUtils;

import butterknife.Bind;


/**
 * MVPPlugin
 * �wuliang
 * <p>
 * 登陆页面
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter>
        implements LoginContract.View, View.OnClickListener {


    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.login_button)
    TextView loginButton;
    @Bind(R.id.forword_pass)
    TextView forwordPass;
    @Bind(R.id.register_person)
    TextView registerPerson;
    @Bind(R.id.weibo_login)
    ImageView weiboLogin;
    @Bind(R.id.qq_login)
    ImageView qqLogin;
    @Bind(R.id.wx_login)
    ImageView wxLogin;

    String phoneStr;
    String passwordStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListener();
    }


    @Override
    protected int getLayout() {
        return R.layout.act_login;
    }

    /**
     * 设置监听
     */
    private void setListener() {
        forwordPass.setOnClickListener(this);
        registerPerson.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forword_pass:    //忘记密码
                gotoActivity(ForwordPassActivity.class, false);
                break;
            case R.id.register_person:   //新人注册
                gotoActivity(RegisterActivity.class, false);
                break;
            case R.id.login_button:    //登陆
                phoneStr = editPhone.getText().toString().trim();
                passwordStr = editPassword.getText().toString().trim();
                if (isLogin()) {
                    mPresenter.userLogin(phoneStr, MD5.strToMd5Low32(passwordStr), null);
                }
                break;
        }
    }

    /**
     * 验证账号密码
     */
    private boolean isLogin() {
        if (StringUtils.isEmpty(phoneStr)) {
            LogUtils.showToast("请输入手机号！");
            return false;
        }
        if (StringUtils.isEmpty(passwordStr)) {
            LogUtils.showToast("请输入密码！");
            return false;
        }
        return true;
    }


    @Override
    public void getUserData(UserBO data) {
        MyApplication.isLogin = ConditionEnum.LOGIN;
        MyApplication.user = data;
        MyApplication.spUtils.put("uuid", data.getUuid());
    }
}
