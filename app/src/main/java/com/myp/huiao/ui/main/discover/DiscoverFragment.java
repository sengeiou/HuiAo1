package com.myp.huiao.ui.main.discover;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
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

public class DiscoverFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

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
        setListener();
        fragment1 = new RecommendFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
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
    }


    @Override
    public void onRefresh() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
