package com.myp.huiao.ui.main.discover.recommend;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.FeaturedBO;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.util.LogUtils;

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
        RecommendPresenter> implements RecommendContract.View {

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
    @Bind(R.id.recycle_grid)
    RecyclerView recycleGrid;
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

    RelativeLayout layouts[];


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
        layouts = new RelativeLayout[]{layout1, layout2, layout3, layout4};
        mPresenter.loadDiscoverFeatured();
    }

    /**
     * 初始化界面
     */
    private void initvion() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle.setLayoutManager(manager);
        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);
        recycleGrid.setLayoutManager(manager1);
        recycleGrid.setNestedScrollingEnabled(false);
        recycle.setNestedScrollingEnabled(false);
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
    public void getFeatured(List<FeaturedBO> featuredBOs) {
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
     * 设置界面显示变化
     */
    private void setUIData(List<FeaturedBO> featuredBOs, int size) {
        for (int i = 0; i < layouts.length; i++) {
            if (i < size) {
                layouts[i].setVisibility(View.VISIBLE);
            } else {
                layouts[i].setVisibility(View.GONE);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
