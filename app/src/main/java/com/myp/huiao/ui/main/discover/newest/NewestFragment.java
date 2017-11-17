package com.myp.huiao.ui.main.discover.newest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.ui.topicclassmessage.TopicMessageActivity;
import com.myp.huiao.ui.topicmessage.TopicMsgActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.SizeUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.util.TimeUtils;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NewestFragment extends MVPBaseFragment<NewestContract.View, NewestPresenter>
        implements NewestContract.View {


    @Bind(R.id.recycle)
    RecyclerView recycle;


    List<TopicBO> topicBOs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_attention, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        recycle.setNestedScrollingEnabled(false);
        mPresenter.loadTopicList(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getTopicList(List<TopicBO> topicBOs, boolean isPageAdd) {
        if (isPageAdd) {
            this.topicBOs.addAll(topicBOs);
        } else {
            this.topicBOs = topicBOs;
        }
        setAdapter();
    }


    /**
     * 设置界面适配器
     */
    private void setAdapter() {
        LGRecycleViewAdapter<TopicBO> adapter = new LGRecycleViewAdapter<TopicBO>(topicBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_follow_topic;
            }

            @Override
            public void convert(LGViewHolder holder, TopicBO topicBO, int position) {
                TextView addGuanzhu = (TextView) holder.getView(R.id.add_guanzhu);
                addGuanzhu.setTag(topicBO);
                if (topicBO.isAttention()) {
                    addGuanzhu.setBackgroundResource(R.drawable.button_red);
                    addGuanzhu.setTextColor(ContextCompat.getColor(getActivity(), R.color.C));
                    addGuanzhu.setText("已关注");
                } else {
                    addGuanzhu.setBackgroundResource(R.drawable.button_red_borld);
                    addGuanzhu.setTextColor(ContextCompat.getColor(getActivity(), R.color.K));
                    addGuanzhu.setText("+ 关注");
                }
                holder.setText(R.id.person_name, topicBO.getAppUser().getNickName());
                holder.setText(R.id.topic_message, topicBO.getContent());
                holder.setImageUrl(getActivity(), R.id.person_img, topicBO.getAppUser().getHeaderUrl());
                holder.setImageUrl(getActivity(), R.id.toppic_img, topicBO.getImageUrl());
                if (!StringUtils.isEmpty(topicBO.getCreateTime()))
                    holder.setText(R.id.topic_time, TimeUtils.getFriendlyTimeSpanByNow(topicBO.getCreateTime()));
                if (topicBO.isClick()) {
                    holder.setImageResurce(R.id.dianzan, R.drawable.dianzan1xz);
                } else {
                    holder.setImageResurce(R.id.dianzan, R.drawable.dianzan1);
                }
                holder.setText(R.id.dianzan_num, String.valueOf(topicBO.getLikeNum()));
                holder.setText(R.id.huifu_num, String.valueOf(topicBO.getDiscussNum()));
                LinearLayout topicTypeLayout = (LinearLayout) holder.getView(R.id.topic_type_layout);
                for (int i = 0; i < topicBO.getTopicCategories().size(); i++) {
                    TextView textView = getTextView();
                    textView.setText(topicBO.getTopicCategories().get(i).getNewName());
                    textView.setTag(topicBO.getTopicCategories().get(i));
                    textView.setOnClickListener(listener);
                    topicTypeLayout.addView(textView);
                }
            }
        };
        adapter.setOnItemClickListener(R.id.item_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", topicBOs.get(position).getId());
                gotoActivity(TopicMsgActivity.class, bundle, false);
            }
        });
        adapter.setOnItemClickListener(R.id.add_guanzhu, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                if (topicBOs.get(position).isAttention()) {
                    mPresenter.loadFollowUser(view, topicBOs.get(position).getAppUser().getId() + "", "0");
                } else {
                    mPresenter.loadFollowUser(view, topicBOs.get(position).getAppUser().getId() + "", "1");
                }
            }
        });
        recycle.setAdapter(adapter);
    }

    /**
     * 创建一个标签text
     */
    private TextView getTextView() {
        TextView textView = new TextView(getActivity());
        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.H));
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
     * 关注用户
     */
    @Override
    public void followUser(String status, View view) {
        TextView addGuanzhu = (TextView) view;
        TopicBO topicBO = (TopicBO) view.getTag();
        if ("0".equals(status)) {
            addGuanzhu.setBackgroundResource(R.drawable.button_red_borld);
            addGuanzhu.setTextColor(ContextCompat.getColor(getActivity(), R.color.K));
            addGuanzhu.setText("+ 关注");
            topicBO.setAttention(false);
        } else {
            addGuanzhu.setBackgroundResource(R.drawable.button_red);
            addGuanzhu.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            addGuanzhu.setText("已关注");
            topicBO.setAttention(true);
        }
    }
}