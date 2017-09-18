package com.myp.huiao.ui.userupdate.updateshenfen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.base.MyApplication;
import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.userupdate.UpdateBartherDayActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.widget.superadapter.CommonAdapter;
import com.myp.huiao.widget.superadapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * MVPPlugin
 * 修改用户身份界面
 */

public class UpdateShenfenActivity extends MVPBaseActivity<UpdateShenfenContract.View,
        UpdateShenfenPresenter> implements UpdateShenfenContract.View,
        AdapterView.OnItemClickListener, View.OnClickListener {

    @Bind(R.id.next_button)
    TextView nextButton;
    @Bind(R.id.recycle)
    GridView recycle;

    List<ChioceBO> chioceBOs;
    ChioceBO chioceBO;    //当前选中的学历

    @Override
    protected int getLayout() {
        return R.layout.act_user_shenfen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("选择身份");

        mPresenter.loadUserShenfen();
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
    public void getChioceList(List<ChioceBO> chioceBOList) {
        chioceBOs = chioceBOList;
        setChioceAdapter();
    }

    CommonAdapter<ChioceBO> adapter;

    /**
     * 设置身份适配器
     */
    private void setChioceAdapter() {
        adapter = new CommonAdapter<ChioceBO>(this, R.layout.item_user_update, chioceBOs) {
            @Override
            protected void convert(ViewHolder viewHolder, ChioceBO item, int position) {
                CircleImageView imageView = viewHolder.getView(R.id.image);
                Glide.with(UpdateShenfenActivity.this).load(item.getImageUrl()).into(imageView);
                viewHolder.setText(R.id.text, item.getName());
                if (checked == position) {
                    viewHolder.getView(R.id.check_brold).setVisibility(View.VISIBLE);
                } else {
                    viewHolder.getView(R.id.check_brold).setVisibility(View.GONE);
                }
            }
        };
        recycle.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setChecks(position);
        adapter.notifyDataSetChanged();
        nextButton.setEnabled(true);
        nextButton.setTextColor(getResources().getColor(R.color.F));
        chioceBO = chioceBOs.get(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                Bundle bundle = getIntent().getExtras();
                bundle.putString("educationId", chioceBO.getId());
                gotoActivity(UpdateBartherDayActivity.class, bundle, false);
                break;
        }
    }
}
