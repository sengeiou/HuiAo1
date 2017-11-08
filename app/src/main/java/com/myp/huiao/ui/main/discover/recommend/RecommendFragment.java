package com.myp.huiao.ui.main.discover.recommend;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.ui.topicclassmessage.TopicMessageActivity;
import com.myp.huiao.ui.topicmessage.TopicMsgActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.widget.MyGridView;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;
import com.myp.huiao.widget.superadapter.CommonAdapter;
import com.myp.huiao.widget.superadapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * MVPPlugin
 * <p>
 * 热门推荐页面
 */

public class RecommendFragment extends MVPBaseFragment<RecommendContract.View,
        RecommendPresenter> implements RecommendContract.View, View.OnClickListener {

    @Bind(R.id.person_bg01)
    ShapedImageView personBg01;
    @Bind(R.id.person_img01)
    CircleImageView personImg01;
    @Bind(R.id.person_bg02)
    ShapedImageView personBg02;
    @Bind(R.id.person_img02)
    CircleImageView personImg02;
    @Bind(R.id.person_bg03)
    ShapedImageView personBg03;
    @Bind(R.id.person_img03)
    CircleImageView personImg03;
    @Bind(R.id.person_bg04)
    ShapedImageView personBg04;
    @Bind(R.id.person_img04)
    CircleImageView personImg04;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.layout1)
    RelativeLayout layout1;
    @Bind(R.id.layout2)
    RelativeLayout layout2;
    @Bind(R.id.layout3)
    RelativeLayout layout3;
    @Bind(R.id.layout4)
    RelativeLayout layout4;
    @Bind(R.id.layout_line2)
    LinearLayout layoutLine2;
    @Bind(R.id.grid_view)
    MyGridView gridView;


    RelativeLayout layouts[];
    List<TopicBO> topicBOs;    //精选推荐的四个

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_discover_tuijian, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initvion();
        setListener();
        layouts = new RelativeLayout[]{layout1, layout2, layout3, layout4};
        mPresenter.loadDiscoverFeatured();
        mPresenter.loadClassifyList();
        mPresenter.loadTopPicList(false);
    }

    /**
     * 初始化界面
     */
    private void initvion() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle.setLayoutManager(manager);
    }


    /**
     * 设置监听
     */
    private void setListener() {
        personBg01.setOnClickListener(this);
        personBg02.setOnClickListener(this);
        personBg03.setOnClickListener(this);
        personBg04.setOnClickListener(this);
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    /**
     * 返回精选推荐
     */
    @Override
    public void getFeatured(List<TopicBO> featuredBOs) {
        switch (featuredBOs.size()) {
            case 1:
                layoutLine2.setVisibility(View.GONE);
                setUIData(featuredBOs, 1);
                break;
            case 2:
                layoutLine2.setVisibility(View.GONE);
                setUIData(featuredBOs, 2);
                break;
            case 3:
                layoutLine2.setVisibility(View.VISIBLE);
                setUIData(featuredBOs, 3);
                break;
            case 4:
                layoutLine2.setVisibility(View.VISIBLE);
                setUIData(featuredBOs, 4);
                break;
        }
    }


    /**
     * 设置精选推荐界面显示变化
     */
    private void setUIData(List<TopicBO> featuredBOs, int size) {
        topicBOs = featuredBOs;
        for (int i = 0; i < layouts.length; i++) {
            if (i < size) {
                layouts[i].setVisibility(View.VISIBLE);
            } else {
                layouts[i].setVisibility(View.INVISIBLE);
            }
        }
        for (int i = 0; i < featuredBOs.size(); i++) {
            switch (i) {
                case 0:
                    Glide.with(getActivity()).load(featuredBOs.get(i).getImageUrl()).into(personBg01);
                    Glide.with(getActivity()).load(featuredBOs.get(i).getAppUser().getHeaderUrl()).into(personImg01);
                    break;
                case 1:
                    Glide.with(getActivity()).load(featuredBOs.get(i).getImageUrl()).into(personBg02);
                    Glide.with(getActivity()).load(featuredBOs.get(i).getAppUser().getHeaderUrl()).into(personImg02);
                    break;
                case 2:
                    Glide.with(getActivity()).load(featuredBOs.get(i).getImageUrl()).into(personBg03);
                    Glide.with(getActivity()).load(featuredBOs.get(i).getAppUser().getHeaderUrl()).into(personImg03);
                    break;
                case 3:
                    Glide.with(getActivity()).load(featuredBOs.get(i).getImageUrl()).into(personBg04);
                    Glide.with(getActivity()).load(featuredBOs.get(i).getAppUser().getHeaderUrl()).into(personImg04);
                    break;
            }
        }
    }


    /***
     * 返回横滑的话题类别列表数据
     */
    @Override
    public void getToppicList(final List<TopicClaissIfyBO> topicClaissIfyBOs) {
        LGRecycleViewAdapter<TopicClaissIfyBO> adapter = new LGRecycleViewAdapter<TopicClaissIfyBO>(topicClaissIfyBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_recoment_classify;
            }

            @Override
            public void convert(LGViewHolder holder, TopicClaissIfyBO topicBO, int position) {
                holder.setImageUrl(getActivity(), R.id.classify_img, topicBO.getIconUrl());
                holder.setText(R.id.classify_text, topicBO.getNewName());
            }
        };
        adapter.setOnItemClickListener(R.id.item_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("topic", topicClaissIfyBOs.get(position));
                gotoActivity(TopicMessageActivity.class, bundle, false);
            }
        });
        recycle.setAdapter(adapter);
    }

    /**
     * 返回话题列表数据
     */
    @Override
    public void getToppicUser(List<TopicBO> topicBOs) {
        CommonAdapter<TopicBO> adapter = new CommonAdapter<TopicBO>(getActivity(),
                R.layout.item_recoment_grid, topicBOs) {
            @Override
            protected void convert(ViewHolder viewHolder, TopicBO item, int position) {
                viewHolder.setImageUrl(R.id.toppic_img, item.getImageUrl());
            }
        };
        gridView.setAdapter(adapter);
        gridView.setFocusable(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        int i = 0;
        switch (v.getId()) {
            case R.id.person_bg01:
                i = 0;
                break;
            case R.id.person_bg02:
                i = 1;
                break;
            case R.id.person_bg03:
                i = 2;
                break;
            case R.id.person_bg04:
                i = 3;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id", topicBOs.get(i).getId());
        gotoActivity(TopicMsgActivity.class, bundle, false);
    }
}
