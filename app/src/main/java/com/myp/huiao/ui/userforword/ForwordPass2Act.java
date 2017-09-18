package com.myp.huiao.ui.userforword;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myp.huiao.R;
import com.myp.huiao.base.BaseActivity;

/**
 * Created by wuliang on 2017/9/13.
 * <p>
 * 忘记密码第二个页面
 */

public class ForwordPass2Act extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.act_forword02;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("设置密码");
    }
}
