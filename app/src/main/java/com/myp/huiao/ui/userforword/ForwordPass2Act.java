package com.myp.huiao.ui.userforword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.api.HttpServiceIml;
import com.myp.huiao.base.BaseActivity;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.MD5;
import com.myp.huiao.util.StringUtils;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by wuliang on 2017/9/13.
 * <p>
 * 忘记密码第二个页面
 */

public class ForwordPass2Act extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.edit_yanzheng)
    EditText editYanzheng;
    @Bind(R.id.next_button)
    TextView nextButton;

    String passwordStr;
    String password2;

    @Override
    protected int getLayout() {
        return R.layout.act_forword02;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("设置密码");

        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        passwordStr = editPhone.getText().toString().trim();
        password2 = editYanzheng.getText().toString().trim();
        switch (v.getId()) {
            case R.id.next_button:
                if (StringUtils.isEmpty(passwordStr)) {
                    LogUtils.showToast("请填写新密码！");
                    return;
                } else if (StringUtils.isEmpty(password2)) {
                    LogUtils.showToast("请确认密码！");
                    return;
                } else if (!passwordStr.equals(password2)) {
                    LogUtils.showToast("两次密码填写不一致！");
                    return;
                }
                loadPassword();
                break;
        }
    }

    /**
     * 提交新密码
     */
    private void loadPassword() {
        HttpServiceIml.userPassWord(MD5.strToMd5Low32(passwordStr), null).subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(UserBO userBO) {
                LogUtils.showToast("修改成功！");
                finish();
            }
        });
    }
}
