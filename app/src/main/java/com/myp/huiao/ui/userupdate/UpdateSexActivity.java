package com.myp.huiao.ui.userupdate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.base.BaseActivity;
import com.myp.huiao.ui.userupdate.updateshenfen.UpdateShenfenActivity;

import butterknife.Bind;

/**
 * Created by wuliang on 2017/9/13.
 * <p>
 * 注册页面 选择性别页
 */

public class UpdateSexActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.nv_check)
    ImageView nvCheck;
    @Bind(R.id.nv)
    RelativeLayout nv;
    @Bind(R.id.nan_check)
    ImageView nanCheck;
    @Bind(R.id.nan)
    RelativeLayout nan;
    @Bind(R.id.next_button)
    TextView nextButton;

    String check;

    @Override
    protected int getLayout() {
        return R.layout.act_user_sex;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("选择性别");

        nv.setOnClickListener(this);
        nan.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        nextButton.setTextColor(ContextCompat.getColor(this, R.color.F));
        nextButton.setEnabled(true);
        switch (v.getId()) {
            case R.id.nan:    //男
                nanCheck.setVisibility(View.VISIBLE);
                nvCheck.setVisibility(View.GONE);
                check = "1";
                break;
            case R.id.nv:    //女
                nvCheck.setVisibility(View.VISIBLE);
                nanCheck.setVisibility(View.GONE);
                check = "2";
                break;
            case R.id.next_button:   //下一步
                Bundle bundle = new Bundle();
                bundle.putString("sex", check);
                gotoActivity(UpdateShenfenActivity.class, bundle, false);
                break;
        }
    }
}
