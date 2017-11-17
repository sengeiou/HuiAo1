package com.myp.huiao.ui.topicmessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.topicclassmessage.TopicMessageActivity;
import com.myp.huiao.ui.topicpinlun.TopicPinLunActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.SizeUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.util.TimeUtils;
import com.myp.huiao.widget.ScrollViewExt;
import com.myp.huiao.widget.inputdialogfragment.CommentDialogFragment;
import com.myp.huiao.widget.inputdialogfragment.DialogFragmentDataCallback;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


/**
 * MVPPlugin
 * 话题详情页面
 */

public class TopicMsgActivity extends MVPBaseActivity<TopicMsgContract.View,
        TopicMsgPresenter> implements TopicMsgContract.View, View.OnClickListener,
        DialogFragmentDataCallback {

    @Bind(R.id.person_img)
    ShapedImageView personImg;
    @Bind(R.id.person_name)
    TextView personName;
    @Bind(R.id.add_guanzhu)
    TextView addGuanzhu;
    @Bind(R.id.toppic_img)
    ShapedImageView toppicImg;
    @Bind(R.id.topic_message)
    TextView topicMessage;
    @Bind(R.id.topic_time)
    TextView topicTime;
    @Bind(R.id.dianzan)
    ImageView dianzan;
    @Bind(R.id.dianzan_num)
    TextView dianzanNum;
    @Bind(R.id.huifu)
    ImageView huifu;
    @Bind(R.id.huifu_num)
    TextView huifuNum;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.pinlun_layout)
    LinearLayout pinlunLayout;
    @Bind(R.id.topic_type_layout)
    LinearLayout topicTypeLayout;
    @Bind(R.id.pinlun_text)
    TextView pinlunText;
    @Bind(R.id.scoll_view)
    ScrollViewExt scollView;
    @Bind(R.id.dianzan_layout)
    LinearLayout dianzanLayout;
    @Bind(R.id.buttom_layout)
    LinearLayout buttomLayout;
    @Bind(R.id.pinlun_num_layout)
    LinearLayout pinlunNumLayout;

    List<PinLunBO> pinLunBOs;
    TopicBO topicBO;
    int id = 0;


    @Override
    protected int getLayout() {
        return R.layout.act_topic_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("话题详情");

        invition();
        setListener();
        id = getIntent().getExtras().getInt("id", 0);
        mPresenter.loadTopicMessage(id + "");
        mPresenter.loadTopicMessagePinLun(id, false);
        showProgress("加载中...");
    }


    /**
     * 初始化界面
     */
    private void invition() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        recycle.setNestedScrollingEnabled(false);
        pinlunLayout.setOnClickListener(this);
        dianzanLayout.setOnClickListener(this);
        addGuanzhu.setOnClickListener(this);
        pinlunNumLayout.setOnClickListener(this);
    }


    /**
     * 设置加载更多
     */
    private void setListener() {
        scollView.setScrollViewListener(new ScrollViewExt.IScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                mPresenter.loadTopicMessagePinLun(id, true);
            }

            @Override
            public void onScrolledToTop() {
            }
        });
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
    }

    @Override
    public void getTopicMessage(TopicBO topicBO) {
        this.topicBO = topicBO;
        Glide.with(this).load(topicBO.getAppUser().getHeaderUrl()).into(personImg);
        personName.setText(topicBO.getAppUser().getNickName());
        if (topicBO.isAttention()) {
            addGuanzhu.setBackgroundResource(R.drawable.button_red);
            addGuanzhu.setTextColor(ContextCompat.getColor(this, R.color.C));
            addGuanzhu.setText("已关注");
        } else {
            addGuanzhu.setBackgroundResource(R.drawable.button_red_borld);
            addGuanzhu.setTextColor(ContextCompat.getColor(this, R.color.K));
            addGuanzhu.setText("+ 关注");
        }
        if (topicBO.isClick()) {
            dianzan.setImageResource(R.drawable.dianzan1xz);
        } else {
            dianzan.setImageResource(R.drawable.dianzan1);
        }
        Glide.with(this).load(topicBO.getImageUrl()).into(toppicImg);
        topicMessage.setText(topicBO.getContent());
        topicTime.setText(TimeUtils.getFriendlyTimeSpanByNow(topicBO.getCreateTime()));
        dianzanNum.setText(String.valueOf(topicBO.getLikeNum()));
        huifuNum.setText(String.valueOf(topicBO.getDiscussNum()));
        for (int i = 0; i < topicBO.getTopicCategories().size(); i++) {
            TextView textView = getTextView();
            textView.setText(topicBO.getTopicCategories().get(i).getNewName());
            textView.setTag(topicBO.getTopicCategories().get(i));
            textView.setOnClickListener(listener);
            topicTypeLayout.addView(textView);
        }
    }

    /**
     * 创建一个标签text
     */
    private TextView getTextView() {
        TextView textView = new TextView(this);
        textView.setTextColor(ContextCompat.getColor(this, R.color.H));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, SizeUtils.dp2px(10), 0);
        textView.setLayoutParams(params);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        return textView;
    }

    /**
     * 点击标签的事件
     */
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TopicClaissIfyBO id = (TopicClaissIfyBO) v.getTag();
            Bundle bundle = new Bundle();
            bundle.putSerializable("topic", id);
            gotoActivity(TopicMessageActivity.class, bundle, false);
        }
    };


    /**
     * 返回一级评论,设置界面
     */
    @Override
    public void getTopicMsgPinLun(List<PinLunBO> pinLunBOs, boolean isPageAdd) {
        stopProgress();
        if (isPageAdd) {
            this.pinLunBOs.addAll(pinLunBOs);
        } else {
            this.pinLunBOs = pinLunBOs;
        }
        setPinLunAdapter();
    }

    /**
     * 发表评论成功
     */
    @Override
    public void addPinLunSurcess() {
        LogUtils.showToast("发表成功！");
        adapter = null;
        mPresenter.loadTopicMessage(id + "");
        mPresenter.loadTopicMessagePinLun(id, false);
    }

    /**
     * 为评论点赞
     */
    @Override
    public void addDianZanSurcess(View view, String status) {
        ImageView imageView = (ImageView) view;
        PinLunBO pinLunBO = (PinLunBO) imageView.getTag();
        if ("0".equals(status)) {    //取消关注
            imageView.setImageResource(R.drawable.dianzan1);
            pinLunBO.setClick(false);
        } else {
            imageView.setImageResource(R.drawable.dianzan1xz);
            pinLunBO.setClick(true);
        }
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.love_anim);
        imageView.startAnimation(animation);
    }

    /**
     * 为话题点赞
     */
    @Override
    public void addTopicDianZan(String status) {
        mPresenter.loadTopicMessage(topicBO.getId() + "");
    }


    /**
     * 关注用户
     */
    @Override
    public void followUser(String status) {
        if ("0".equals(status)) {
            addGuanzhu.setBackgroundResource(R.drawable.button_red_borld);
            addGuanzhu.setTextColor(ContextCompat.getColor(this, R.color.K));
            addGuanzhu.setText("+ 关注");
            topicBO.setAttention(false);
        } else {
            addGuanzhu.setBackgroundResource(R.drawable.button_red);
            addGuanzhu.setTextColor(ContextCompat.getColor(this, R.color.white));
            addGuanzhu.setText("已关注");
            topicBO.setAttention(true);
        }
    }


    LGRecycleViewAdapter<PinLunBO> adapter;

    /**
     * 设置评论显示适配器
     */
    private void setPinLunAdapter() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            return;
        }
        adapter = new LGRecycleViewAdapter<PinLunBO>(pinLunBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_pinlun_layout;
            }

            @Override
            public void convert(LGViewHolder holder, PinLunBO pinLunBO, int position) {
                holder.setImageUrl(TopicMsgActivity.this, R.id.person_img, pinLunBO.getAppUser().getHeaderUrl());
                holder.setText(R.id.person_name, pinLunBO.getAppUser().getNickName());
                holder.setText(R.id.pinglun_time, TimeUtils.getFriendlyTimeSpanByNow(pinLunBO.getCreateTime()));
                if (pinLunBO.isClick()) {
                    holder.setImageResurce(R.id.dianzan, R.drawable.dianzan1xz);
                } else {
                    holder.setImageResurce(R.id.dianzan, R.drawable.dianzan1);
                }
                holder.setText(R.id.pinlun_message, pinLunBO.getContent());
                if (pinLunBO.getChildren() == null || pinLunBO.getChildren().size() == 0) {
                    holder.getView(R.id.pinlun_layout).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.pinlun_layout).setVisibility(View.VISIBLE);
                    if (pinLunBO.getChildren().size() == 1) {
                        holder.getView(R.id.message02).setVisibility(View.GONE);
                        TextView message01 = (TextView) holder.getView(R.id.message01);
                        message01.setText(Html.fromHtml("<font color='#6487c2'>" + pinLunBO.getChildren().get(0).getOwner()
                                + "：</font>" + pinLunBO.getChildren().get(0).getContent()));
                        holder.getView(R.id.all_pinlun_num).setVisibility(View.GONE);
                    } else if (pinLunBO.getChildren().size() >= 2) {
                        holder.getView(R.id.message02).setVisibility(View.VISIBLE);
                        TextView message01 = (TextView) holder.getView(R.id.message01);
                        TextView message02 = (TextView) holder.getView(R.id.message02);
                        PinLunBO.ChildrenBo childrenBo01 = pinLunBO.getChildren().get(0);
                        PinLunBO.ChildrenBo childrenBo02 = pinLunBO.getChildren().get(1);
                        if (StringUtils.isEmpty(childrenBo01.getToWho())) {
                            message01.setText(Html.fromHtml("<font color='#6487c2'>" + childrenBo01.getOwner()
                                    + "：</font>" + childrenBo01.getContent()));
                        } else {
                            message01.setText(Html.fromHtml("<font color='#6487c2'>" + childrenBo01.getOwner()
                                    + "：</font>回复" + "<font color='#6487c2'>" + childrenBo01.getToWho()
                                    + "：</font>" + childrenBo01.getContent()));
                        }
                        if (StringUtils.isEmpty(childrenBo02.getToWho())) {
                            message02.setText(Html.fromHtml("<font color='#6487c2'>" + childrenBo02.getOwner()
                                    + "：</font>" + childrenBo02.getContent()));
                        } else {
                            message02.setText(Html.fromHtml("<font color='#6487c2'>" + childrenBo02.getOwner()
                                    + "：</font>回复" + "<font color='#6487c2'>" + childrenBo02.getToWho()
                                    + "：</font>" + childrenBo02.getContent()));
                        }
                        if (pinLunBO.getChiNum() > 2) {
                            holder.getView(R.id.all_pinlun_num).setVisibility(View.VISIBLE);
                            holder.setText(R.id.all_pinlun_num, "共" + pinLunBO.getChiNum() + "条回复 >");
                        } else {
                            holder.getView(R.id.all_pinlun_num).setVisibility(View.GONE);
                        }
                    }
                }
            }
        };
        adapter.setOnItemClickListener(R.id.dianzan, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                view.setTag(pinLunBOs.get(position));
                if (pinLunBOs.get(position).isClick()) {
                    mPresenter.loadPinLunZan(view, pinLunBOs.get(position).getId() + "", "0");
                } else {
                    mPresenter.loadPinLunZan(view, pinLunBOs.get(position).getId() + "", "1");
                }
            }
        });
        adapter.setOnItemClickListener(R.id.item_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pinLun", pinLunBOs.get(position));
                gotoActivity(TopicPinLunActivity.class, bundle, false);
            }
        });
        recycle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinlun_layout:
            case R.id.pinlun_num_layout:
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                commentDialogFragment.show(getFragmentManager(), "CommentDialogFragment");
                break;
            case R.id.dianzan_layout:   //点击点赞
                if (topicBO.isClick()) {
                    mPresenter.loadTopicDianZan(topicBO.getId() + "", "0");
                } else {
                    mPresenter.loadTopicDianZan(topicBO.getId() + "", "1");
                }
                break;
            case R.id.add_guanzhu:    //关注用户
                if (topicBO.isAttention()) {
                    mPresenter.loadFollowUser(topicBO.getAppUser().getId() + "", "0");
                } else {
                    mPresenter.loadFollowUser(topicBO.getAppUser().getId() + "", "1");
                }
                break;
        }
    }

    @Override
    public String getCommentText() {
        return pinlunText.getText().toString();
    }

    @Override
    public void setCommentText(String commentTextTemp) {
        pinlunText.setText(commentTextTemp);
        buttomLayout.clearFocus();
    }

    @Override
    public void sendText(String text) {
        mPresenter.loadAddTopicPinLun(topicBO.getId() + "", text);
    }
}
