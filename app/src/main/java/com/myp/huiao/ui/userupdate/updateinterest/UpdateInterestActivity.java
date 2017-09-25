package com.myp.huiao.ui.userupdate.updateinterest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.base.MyApplication;
import com.myp.huiao.config.ConditionEnum;
import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.main.MainActivity;
import com.myp.huiao.util.LogUtils;

import java.util.List;
import java.util.Map;

import butterknife.Bind;


/**
 * 选择兴趣页面
 */

public class UpdateInterestActivity extends MVPBaseActivity<UpdateInterestContract.View,
        UpdateInterestPresenter> implements UpdateInterestContract.View, View.OnClickListener,
        AdapterView.OnItemClickListener {

    @Bind(R.id.next_button)
    TextView nextButton;
    @Bind(R.id.recycle)
    GridView recycle;

    List<ChioceBO> chioceBOs;
    Map<String, ChioceBO> selecChoice;    //选中集合

    InterestAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.act_user_interest;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("选择兴趣");

        mPresenter.loadUserInterest();
        recycle.setOnItemClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getListChoices(List<ChioceBO> chioceBOs) {
        this.chioceBOs = chioceBOs;
        adapter = new InterestAdapter(this, chioceBOs);
        recycle.setAdapter(adapter);
    }

    @Override
    public void getUserBo(UserBO userBO) {
        MyApplication.user = userBO;
        MyApplication.isLogin = ConditionEnum.LOGIN;
        MyApplication.spUtils.put("uuid", userBO.getUuid());
        gotoActivity(MainActivity.class, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                Bundle bundle = getIntent().getExtras();
                String sex = bundle.getString("sex", "");
                String educationId = bundle.getString("educationId", "");
                String birhday = bundle.getString("birhday", "");
                mPresenter.setInterest(sex, educationId, birhday, chioce2String());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selecChoice = adapter.setSelectCho(chioceBOs.get(position));
        if (selecChoice.size() == 0) {
            nextButton.setEnabled(false);
            nextButton.setTextColor(getResources().getColor(R.color.E));
        } else {
            nextButton.setEnabled(true);
            nextButton.setTextColor(getResources().getColor(R.color.F));
        }
    }

    /**
     * 拼接选中的Id返回给服务器
     */
    private String chioce2String() {
        StringBuilder ids = new StringBuilder();
        for (String id : selecChoice.keySet()) {
            ids.append(id + ",");
        }
        return ids.substring(0, ids.length() - 1);
    }
}
