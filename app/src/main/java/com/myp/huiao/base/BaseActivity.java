package com.myp.huiao.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.myp.huiao.R;
import com.myp.huiao.util.AppManager;
import com.myp.huiao.util.BarUtils;
import com.myp.huiao.util.LogUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * 作者 by wuliang 时间 16/10/31.
 * <p>
 * 所有activity的基类，此处建立了一个activity的栈，用于管理activity
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        ImmersionBar.with(this).statusBarDarkFont(true).init();   //解决虚拟按键与状态栏沉浸冲突
        LogUtils.init(this);
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        ButterKnife.unbind(this);
        AppManager.getAppManager().removeActivity(this);
    }

    /**
     * 常用的跳转方法
     */
    public void gotoActivity(Class<?> cls, boolean isFinish) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    public void gotoActivity(Class<?> cls, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }


    /**
     * 设置返回键
     */
    protected void goBack() {
        LinearLayout back = (LinearLayout) findViewById(R.id.back_to);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 修改标题栏标题
     */
    protected void setTitle(String title) {
        TextView text = (TextView) findViewById(R.id.title_text);
        text.setText(title);
    }


    protected abstract int getLayout();
}
