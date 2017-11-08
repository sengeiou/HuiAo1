package com.myp.huiao.ui.topicpinlun;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.TimeUtils;
import com.myp.huiao.widget.inputdialogfragment.CommentDialogFragment;
import com.myp.huiao.widget.inputdialogfragment.DialogFragmentDataCallback;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


/**
 * MVPPlugin
 * <p>
 * 话题评论页面
 */

public class TopicPinLunActivity extends MVPBaseActivity<TopicPinLunContract.View,
        TopicPinLunPresenter> implements TopicPinLunContract.View, View.OnClickListener,
        DialogFragmentDataCallback {

    @Bind(R.id.person_img)
    ShapedImageView personImg;
    @Bind(R.id.person_name)
    TextView personName;
    @Bind(R.id.pinglun_time)
    TextView pinglunTime;
    @Bind(R.id.dianzan)
    ImageView dianzan;
    @Bind(R.id.pinlun_message)
    TextView pinlunMessage;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.pinlun_layout)
    RelativeLayout pinlunLayout;

    PinLunBO pinLunBO;
    List<PinLunBO> pinLunBOs;   //二级评论列表


    @Override
    protected int getLayout() {
        return R.layout.act_topic_pinlun;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("话题评论");

        pinLunBO = (PinLunBO) getIntent().getExtras().getSerializable("pinLun");
        initUi();
        mPresenter.loadPinLunToPinLun(pinLunBO.getId(), false);
    }

    /**
     * 初始化UI界面
     */
    private void initUi() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        pinlunLayout.setOnClickListener(this);
        Glide.with(this).load(pinLunBO.getAppUser().getHeaderUrl()).error(R.drawable.defalt_person)
                .into(personImg);
        personName.setText(pinLunBO.getAppUser().getNickName());
        pinglunTime.setText(TimeUtils.getFriendlyTimeSpanByNow(pinLunBO.getCreateTime()));
        pinlunMessage.setText(pinLunBO.getContent());
        if (pinLunBO.isClick()) {
            dianzan.setImageResource(R.drawable.dianzan1xz);
        } else {
            dianzan.setImageResource(R.drawable.dianzan1);
        }
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getPinLunToPinLun(List<PinLunBO> pinLunBOs, boolean isPageAdd) {
        if (isPageAdd) {
            this.pinLunBOs.addAll(pinLunBOs);
        } else {
            this.pinLunBOs = pinLunBOs;
        }
        setAdapter();
    }

    @Override
    public void getAddPinLunSuress() {
        LogUtils.showToast("发表成功！");
        adapter = null;
        mPresenter.loadPinLunToPinLun(pinLunBO.getId(), false);
    }

    LGRecycleViewAdapter<PinLunBO> adapter;

    /**
     * 设置界面适配器
     */
    private void setAdapter() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            return;
        }
        adapter = new LGRecycleViewAdapter<PinLunBO>(pinLunBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_pinlun2_layout;
            }

            @Override
            public void convert(LGViewHolder holder, PinLunBO pinLunBO, int position) {
                holder.setImageUrl(TopicPinLunActivity.this, R.id.person_img,
                        pinLunBO.getAppUser().getHeaderUrl());
                holder.setText(R.id.person_name, pinLunBO.getAppUser().getNickName());
                holder.setText(R.id.pinglun_time, TimeUtils.getFriendlyTimeSpanByNow(pinLunBO.getCreateTime()));
                if (pinLunBO.isClick()) {
                    holder.setImageResurce(R.id.dianzan, R.drawable.dianzan1xz);
                } else {
                    holder.setImageResurce(R.id.dianzan, R.drawable.dianzan1);
                }
                holder.setText(R.id.pinlun_message, pinLunBO.getContent());
            }
        };
        recycle.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinlun_layout:
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                commentDialogFragment.show(getFragmentManager(), "CommentDialogFragment");
                break;


        }
    }

    @Override
    public String getCommentText() {
        return "";
    }

    @Override
    public void setCommentText(String commentTextTemp) {

    }

    @Override
    public void sendText(String text) {
        mPresenter.loadAddPinLun(pinLunBO.getId() + "", text);
    }
}
