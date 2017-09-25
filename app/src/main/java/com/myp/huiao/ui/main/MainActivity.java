package com.myp.huiao.ui.main;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.myp.huiao.R;
import com.myp.huiao.base.BaseActivity;
import com.myp.huiao.ui.main.classlibraay.ClassLibraayFragment;
import com.myp.huiao.ui.main.study.StudyFragment;
import com.myp.huiao.util.AppManager;
import com.myp.huiao.util.LogUtils;
import com.xyz.tabitem.BottmTabItem;

import butterknife.Bind;


/**
 * 主页面
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.main1)
    BottmTabItem main1;
    @Bind(R.id.main2)
    BottmTabItem main2;
    @Bind(R.id.main3)
    BottmTabItem main3;
    @Bind(R.id.main4)
    BottmTabItem main4;

    StudyFragment fragment1;    //学习
    ClassLibraayFragment fragment2;   //课程库

    @Override
    protected int getLayout() {
        return R.layout.act_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment1 = new StudyFragment();
        fragment2 = new ClassLibraayFragment();

        goToFragment(fragment2);

        getPermission();
    }

    /**
     * 检查定位权限
     */
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {   //授权成功
        }
    }

    /**
     * 用户拒绝权限，重新申请
     */
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission) ||
                super.shouldShowRequestPermissionRationale(permission);
    }


    private Fragment mContent = null;

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                if (mContent != null)
                    transaction.hide(mContent).add(R.id.fragment_container, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                else
                    transaction.add(R.id.fragment_container, to).commitAllowingStateLoss();
            } else {
                if (mContent != null)
                    transaction.hide(mContent).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                else
                    transaction.show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }


    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    LogUtils.showToast("再按一次退出程序");
                    firstTime = secondTime;
                    return true;
                } else {
//                    MyApplication.SESSIONID = null;
                    AppManager.getAppManager().finishAllActivity();
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
