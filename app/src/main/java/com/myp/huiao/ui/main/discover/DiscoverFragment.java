package com.myp.huiao.ui.main.discover;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.base.BaseFragment;
import com.myp.huiao.ui.FragmentPaerAdapter;
import com.myp.huiao.ui.main.discover.attention.AttentionFragment;
import com.myp.huiao.ui.main.discover.newest.NewestFragment;
import com.myp.huiao.ui.main.discover.recommend.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * <p>
 * 发现Fragment
 */

public class DiscoverFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.tuijian)
    TextView tuijian;
    @Bind(R.id.remen_bord_scoll)
    View remenBordScoll;
    @Bind(R.id.tuijian_layout)
    RelativeLayout tuijianLayout;
    @Bind(R.id.guanzhu)
    TextView guanzhu;
    @Bind(R.id.guanzhu_bord_scoll)
    View guanzhuBordScoll;
    @Bind(R.id.guanzhu_layout)
    RelativeLayout guanzhuLayout;
    @Bind(R.id.zuixin)
    TextView zuixin;
    @Bind(R.id.zuixin_bord_scoll)
    View zuixinBordScoll;
    @Bind(R.id.zuixin_layout)
    RelativeLayout zuixinLayout;
    @Bind(R.id.scoll_view)
    NestedScrollView scollView;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    RecommendFragment fragment1;
    AttentionFragment fragment2;
    NewestFragment fragment3;


    TextView[] texts;
    View[] points;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_discover, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        invitionSwipeRefresh(swipe);
//        swipe.setOnRefreshListener(this);
        invition();
        setListener();

    }


    /**
     * 初始化布局
     */
    private void invition() {
        texts = new TextView[]{tuijian, guanzhu, zuixin};
        points = new View[]{remenBordScoll, guanzhuBordScoll, zuixinBordScoll};
        fragment1 = new RecommendFragment();
        fragment2 = new AttentionFragment();
        fragment3 = new NewestFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        FragmentPaerAdapter adapter = new FragmentPaerAdapter(getActivity().getSupportFragmentManager(),
                fragments);
        viewPager.setAdapter(adapter);
    }


    /**
     * 设置监听,去除滑动卡顿
     */
    private void setListener() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
//                    swipe.setEnabled(true);
                    line.setVisibility(View.GONE);
                } else {
//                    swipe.setEnabled(false);
                    line.setVisibility(View.VISIBLE);
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
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                refreshUI(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tuijianLayout.setOnClickListener(this);
        guanzhuLayout.setOnClickListener(this);
        zuixinLayout.setOnClickListener(this);
    }


    @Override
    public void onRefresh() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tuijian_layout:
                refreshUI(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.guanzhu_layout:
                refreshUI(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.zuixin_layout:
                refreshUI(2);
                viewPager.setCurrentItem(2);
                break;
        }
    }


    /**
     * 根据点击更新UI
     */
    private void refreshUI(int index) {
        for (int i = 0; i < texts.length; i++) {
            if (index == i) {
                texts[i].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                texts[i].setTextColor(ContextCompat.getColor(getActivity(), R.color.D));
                points[i].setVisibility(View.VISIBLE);
            } else {
                texts[i].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                texts[i].setTextColor(ContextCompat.getColor(getActivity(), R.color.G));
                points[i].setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
