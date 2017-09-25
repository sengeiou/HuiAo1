package com.myp.huiao.ui.userforword;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.RegexUtils;
import com.myp.huiao.util.StringUtils;

import butterknife.Bind;


/**
 * 忘记密码第一页
 */

public class ForwordPassActivity extends MVPBaseActivity<ForwordPassContract.View, ForwordPassPresenter>
        implements ForwordPassContract.View, View.OnClickListener {

    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.yanzheng_button)
    TextView yanzhengButton;
    @Bind(R.id.edit_yanzheng)
    EditText editYanzheng;
    @Bind(R.id.next_button)
    TextView nextButton;
    @Bind(R.id.edit_accent)
    EditText editAccent;

    String accountStr;
    String phoneStr;
    String yanzhengmaStr;

    @Override
    protected int getLayout() {
        return R.layout.act_forword01;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("忘记密码");

        nextButton.setOnClickListener(this);
        yanzhengButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        accountStr = editAccent.getText().toString().trim();
        phoneStr = editPhone.getText().toString().trim();
        yanzhengmaStr = editYanzheng.getText().toString().trim();
        switch (v.getId()) {
            case R.id.next_button:   //下一步
                if (StringUtils.isEmpty(accountStr)) {
                    LogUtils.showToast("请输入账号！");
                    return;
                } else if (StringUtils.isEmpty(phoneStr)) {
                    LogUtils.showToast("请输入手机号！");
                    return;
                } else if (!RegexUtils.isMobileExact(phoneStr)) {
                    LogUtils.showToast("请输入正确手机号！");
                    return;
                } else if (StringUtils.isEmpty(yanzhengmaStr)) {
                    LogUtils.showToast("请输入验证码！");
                    return;
                }
                mPresenter.loadAuthentication(accountStr, phoneStr, yanzhengmaStr);
                showProgress("加载中...");
                break;
            case R.id.yanzheng_button:   //获取验证码验证按钮
                if (StringUtils.isEmpty(phoneStr)) {
                    LogUtils.showToast("请输入手机号！");
                    return;
                } else if (!RegexUtils.isMobileExact(phoneStr)) {
                    LogUtils.showToast("请输入正确手机号！");
                    return;
                }
                mPresenter.loadVersionCode(phoneStr, "validate");
                showProgress("加载中...");
                break;
        }
    }

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
    public void versionSuress() {
        stopProgress();
        yanzhengButton.setEnabled(false);
        timer.start();
    }

    @Override
    public void getUserBo(UserBO userBO) {
        stopProgress();
        gotoActivity(ForwordPass2Act.class, true);
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
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
