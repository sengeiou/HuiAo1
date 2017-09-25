package com.myp.huiao.ui.main.classlibraay;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.donkingliang.banner.CustomBanner;
import com.myp.huiao.R;
import com.myp.huiao.entity.BannerBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.ui.coursermessage.CourserMessageActivity;
import com.myp.huiao.ui.coursersort.CourserSortActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.ScreenUtils;
import com.myp.huiao.util.SizeUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.widget.superadapter.CommonAdapter;
import com.myp.huiao.widget.superadapter.ViewHolder;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 课程库fragment
 */

public class ClassLibraayFragment extends MVPBaseFragment<ClassLibraayContract.View,
        ClassLibraayPresenter> implements ClassLibraayContract.View,
        SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, AdapterView.OnItemClickListener {


    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.title)
    CollapsingToolbarLayout title;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.scoll_view)
    NestedScrollView scollView;
    /***
     * 头部布局
     */
    CustomBanner banner;
    /**
     * 底部布局
     */
    ViewPager mViewPager;
    TextView itemName;
    TextView allCourser;

    List<CourserBO> hotCoursers;    //课程排行
    List<CourserBO> courserBOs;     //专栏课程

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_class_library, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHealderLayout();
        setFoolorLayout();
        invitionSwipeRefresh(swipe);
        swipe.setOnRefreshListener(this);
        list.setOnItemClickListener(this);
        setSwipe();
        mPresenter.getBanners("index");
        mPresenter.loadSpecialCourser();
        mPresenter.loadHotCourser();
        swipe.setRefreshing(true);
    }


    /**
     * 设置头部布局
     */
    private void setHealderLayout() {
        View healder = LayoutInflater.from(getActivity()).inflate(R.layout.fra_mian_healder_layout, null);
        banner = (CustomBanner) healder.findViewById(R.id.banner);
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.width = ScreenUtils.getScreenWidth();
        params.height = ScreenUtils.getScreenWidth() / 16 * 9;
        banner.setLayoutParams(params);
        list.addHeaderView(healder);
    }


    /**
     * 设置底部布局
     */
    private void setFoolorLayout() {
        View fooler = LayoutInflater.from(getActivity()).inflate(R.layout.fra_class_buttom, null);
        mViewPager = (ViewPager) fooler.findViewById(R.id.id_viewpager);
        itemName = (TextView) fooler.findViewById(R.id.item_name);
        allCourser = (TextView) fooler.findViewById(R.id.all_courser);
        mViewPager.setPageMargin(SizeUtils.dp2px(14));//设置page间间距，自行根据需求设置
        mViewPager.setOffscreenPageLimit(3);//>=3
        //setPageTransformer 决定动画效果
        mViewPager.setPageTransformer(true, new ScaleInTransformer());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (hotCoursers != null) itemName.setText(hotCoursers.get(position).getName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        allCourser.setOnClickListener(this);
        list.addFooterView(fooler);
    }


    /**
     * 设置下拉刷新与滑动控件的事件冲突
     */
    private void setSwipe() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipe.setEnabled(true);
                } else {
                    swipe.setEnabled(false);
                }
            }
        });
        scollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    appBar.setExpanded(true, true);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRequestError(String msg) {
        swipe.setRefreshing(false);
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
        swipe.setRefreshing(false);
    }

    /**
     * 返回的专栏课程
     */
    @Override
    public void getSpecialCourse(List<CourserBO> courserBOs) {
        swipe.setRefreshing(false);
        this.courserBOs = courserBOs;
        CommonAdapter<CourserBO> adapter = new CommonAdapter<CourserBO>(getActivity(), R.layout.item_class, courserBOs) {
            @Override
            protected void convert(ViewHolder viewHolder, CourserBO item, int position) {
                viewHolder.setImageUrl(R.id.class_img, item.getShowImageUrl());
                viewHolder.setText(R.id.class_title, item.getName());
                viewHolder.setText(R.id.class_message, item.getDetail());
                viewHolder.setText(R.id.class_xueguo, item.getBuyCount() + "人学过");
                TextView oldPrice = viewHolder.getView(R.id.class_old_price);
                oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                if (StringUtils.isEmpty(item.getCurrentPrice())) {
                    oldPrice.setVisibility(View.GONE);
                    viewHolder.setText(R.id.class_price, "¥ " + item.getPrice());
                } else {
                    oldPrice.setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.class_price, "¥ " + item.getCurrentPrice());
                    oldPrice.setText("¥ " + item.getPrice());
                }
            }
        };
        list.setAdapter(adapter);
    }

    /**
     * 返回的热门课程
     */
    @Override
    public void getHotCourse(List<CourserBO> courserBOs) {
        swipe.setRefreshing(false);
        hotCoursers = courserBOs;
        itemName.setText(hotCoursers.get(0).getName());
        HotCourceAdapter adapter = new HotCourceAdapter(courserBOs, getActivity());
        mViewPager.setAdapter(adapter);
    }

    /**
     * 返回的轮播图
     */
    @Override
    public void getBanners(List<BannerBO> bannerBOs) {
        banner.setPages(new CustomBanner.ViewCreator<BannerBO>() {
            @Override
            public View createView(Context context, int i) {
                ImageView imageView = new ImageView(context);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int i, BannerBO lunBoBO) {
                Glide.with(context).load(lunBoBO.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .error(R.drawable.zhanwei2)
                        .into((ImageView) view);
            }
        }, bannerBOs);
        banner.startTurning(4000);
        //设置轮播图的滚动速度
        banner.setScrollDuration(300);
        //设置轮播图的点击事件
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<BannerBO>() {
            @Override
            public void onPageClick(int position, BannerBO str) {
            }
        });
    }

    @Override
    public void onRefresh() {
        mPresenter.getBanners("index");
        mPresenter.loadSpecialCourser();
        mPresenter.loadHotCourser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_courser:
                gotoActivity(CourserSortActivity.class, false);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0 || position > courserBOs.size()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", courserBOs.get(position - 1).getId());
        gotoActivity(CourserMessageActivity.class, bundle, false);
    }
}
