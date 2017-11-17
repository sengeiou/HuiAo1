package com.myp.huiao.ui.topicclassmessage;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.ScreenUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.widget.MyGridView;
import com.myp.huiao.widget.superadapter.CommonAdapter;
import com.myp.huiao.widget.superadapter.ViewHolder;

import java.util.List;

import butterknife.Bind;


/**
 * MVPPlugin
 * wuliang
 * <p>
 * 话题类别详情页
 */

public class TopicMessageActivity extends MVPBaseActivity<TopicMessageContract.View, TopicMessagePresenter>
        implements TopicMessageContract.View, View.OnClickListener {

    @Bind(R.id.add_topic)
    TextView addTopic;
    @Bind(R.id.grid_view)
    MyGridView gridView;
    @Bind(R.id.toppic_img)
    ImageView toppicImg;
    @Bind(R.id.topic_message)
    TextView topicMessage;
    @Bind(R.id.menu1_layout)
    LinearLayout menu1Layout;
    @Bind(R.id.menu2_layout)
    LinearLayout menu2Layout;
    @Bind(R.id.menu1_text)
    TextView menu1Text;
    @Bind(R.id.menu_borld)
    View menuBorld;
    @Bind(R.id.menu2_text)
    TextView menu2Text;
    @Bind(R.id.menu2_borld)
    View menu2Borld;


    TopicClaissIfyBO topBo;
    List<TopicBO> topicBOs;
    private int type = 2;

    @Override
    protected int getLayout() {
        return R.layout.act_topic_msg;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        topBo = (TopicClaissIfyBO) getIntent().getExtras().getSerializable("topic");
        setTitle(topBo.getNewName());

        invition();
        mPresenter.loadClassifyMsg(topBo.getId());
        mPresenter.loadClassifyList(type + "", topBo.getId(), false);
    }


    private void invition() {
        ViewGroup.LayoutParams params = toppicImg.getLayoutParams();
        params.height = ScreenUtils.getScreenWidth() / 16 * 9;
        toppicImg.setLayoutParams(params);
        menu1Layout.setOnClickListener(this);
        menu2Layout.setOnClickListener(this);
    }


    /**
     * 返回详情
     */
    @Override
    public void getClassifyBo(TopicClaissIfyBO claissIfyBO) {
        Glide.with(this).load(claissIfyBO.getImageUrl()).into(toppicImg);
        topicMessage.setText(claissIfyBO.getSummary());
    }

    /**
     * 返回类别详情下的话题列表
     */
    @Override
    public void getTopicList(List<TopicBO> topicBOs, boolean isPage) {
        if (isPage) {
            this.topicBOs.addAll(topicBOs);
        } else {
            this.topicBOs = topicBOs;
        }
        setAdapter();
    }

    /**
     * 设置界面适配
     */
    private void setAdapter() {
        CommonAdapter<TopicBO> adapter = new CommonAdapter<TopicBO>(this, R.layout.item_topic, topicBOs) {
            @Override
            protected void convert(ViewHolder viewHolder, TopicBO item, int position) {
                if (StringUtils.isEmpty(item.getAppUser().getHeaderUrl())) {
                    viewHolder.setImageResource(R.id.person_img01, R.drawable.defalt_person);
                } else {
                    viewHolder.setImageUrl(R.id.person_img01, item.getAppUser().getHeaderUrl());
                }
                viewHolder.setImageUrl(R.id.person_bg01, item.getImageUrl());
            }
        };
        gridView.setAdapter(adapter);
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu1_layout:
                if (type == 2) {
                    return;
                }
                menu1Text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                menu1Text.setTextColor(ContextCompat.getColor(this, R.color.D));
                menuBorld.setVisibility(View.VISIBLE);
                menu2Text.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                menu2Text.setTextColor(ContextCompat.getColor(this, R.color.G));
                menu2Borld.setVisibility(View.INVISIBLE);
                type = 2;
                mPresenter.loadClassifyList(type + "", topBo.getId(), false);
                break;
            case R.id.menu2_layout:
                if (type == 1) {
                    return;
                }
                menu2Text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                menu2Text.setTextColor(ContextCompat.getColor(this, R.color.D));
                menu2Borld.setVisibility(View.VISIBLE);
                menu1Text.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                menu1Text.setTextColor(ContextCompat.getColor(this, R.color.G));
                menuBorld.setVisibility(View.INVISIBLE);
                type = 1;
                mPresenter.loadClassifyList(null, topBo.getId(), false);
                break;
        }
    }
}