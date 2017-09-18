package com.myp.huiao.ui.userforword;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.mvp.MVPBaseActivity;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:   //下一步
                gotoActivity(ForwordPass2Act.class, false);
                break;
        }
    }
}
