package com.myp.huiao.ui.userregister;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.userupdate.UpdateSexActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.MD5;
import com.myp.huiao.util.RegexUtils;
import com.myp.huiao.util.StringUtils;

import butterknife.Bind;


/***
 * 注册页面填写账号密码页
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View, View.OnClickListener {

    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.yanzheng_button)
    TextView yanzhengButton;
    @Bind(R.id.edit_yanzheng)
    EditText editYanzheng;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.next_button)
    TextView nextButton;

    String phoneStr;
    String yanzhengmaStr;
    String passwordStr;


    @Override
    protected int getLayout() {
        return R.layout.act_register_phone;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitle("注册信息");
        nextButton.setOnClickListener(this);
        yanzhengButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        phoneStr = editPhone.getText().toString().trim();
        yanzhengmaStr = editYanzheng.getText().toString().trim();
        passwordStr = editPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.next_button:    //下一步
                if (isRegister()) {
                    showProgress("加载中...");
                    mPresenter.registerUser(phoneStr, MD5.strToMd5Low32(passwordStr), yanzhengmaStr);
                }
//                gotoActivity(UpdateSexActivity.class, true);
                break;
            case R.id.yanzheng_button:   //获取验证码
                if (StringUtils.isEmpty(phoneStr)) {
                    LogUtils.showToast("请输入手机号！");
                    return;
                }
                if (!RegexUtils.isMobileExact(phoneStr)) {
                    LogUtils.showToast("请输入正确手机号！");
                    return;
                }
                mPresenter.getVersion(phoneStr, "register");
                showProgress("加载中...");
                break;
        }
    }


    /**
     * 判断是否可以注册
     */
    private boolean isRegister() {
        if (StringUtils.isEmpty(phoneStr)) {
            LogUtils.showToast("请输入手机号！");
            return false;
        }
        if (!RegexUtils.isMobileExact(phoneStr)) {
            LogUtils.showToast("请输入正确手机号！");
            return false;
        }
        if (StringUtils.isEmpty(yanzhengmaStr)) {
            LogUtils.showToast("请输入验证码！");
            return false;
        }
        if (StringUtils.isEmpty(passwordStr)) {
            LogUtils.showToast("请输入密码！");
            return false;
        }
        return true;
    }


    /**
     * 获取验证码按钮倒计时
     */
    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //每隔countDownInterval秒会回调一次onTick()方法
            yanzhengButton.setText(millisUntilFinished / 1000 + " s");
        }

        @Override
        public void onFinish() {
            yanzhengButton.setText("重新获取");
            yanzhengButton.setEnabled(true);
        }
    };


    @Override
    public void onRequestError(String msg) {
        stopProgress();
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void getData() {
        gotoActivity(UpdateSexActivity.class, true);
    }

    @Override
    public void getVersionSuress() {
        stopProgress();
        yanzhengButton.setEnabled(false);
        timer.start();
    }
}
