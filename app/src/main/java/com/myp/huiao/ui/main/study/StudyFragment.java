package com.myp.huiao.ui.main.study;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.MyCourserBO;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.ui.WebViewActvity;
import com.myp.huiao.ui.coursermessage.CourserMessageActivity;
import com.myp.huiao.ui.todayarticle.TodayArticleActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的学习Fragment
 */

public class StudyFragment extends MVPBaseFragment<StudyContract.View, StudyPresenter>
        implements StudyContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.my_courser_list)
    RecyclerView myCourserList;
    @Bind(R.id.xinzhi_list)
    RecyclerView xinzhiList;
    @Bind(R.id.scoll_view)
    NestedScrollView scollView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.courser_title)
    TextView courserTitle;
    @Bind(R.id.all_courser)
    TextView allCourser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_home, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        invition();
        invitionSwipeRefresh(swipe);
        swipe.setRefreshing(true);
        swipe.setOnRefreshListener(this);
        setListener();
        mPresenter.loadTodayArticle("1");
    }

    /**
     * 初始化页面布局
     */
    private void invition() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        myCourserList.setLayoutManager(manager);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        xinzhiList.setLayoutManager(manager1);
        myCourserList.setNestedScrollingEnabled(false);
        xinzhiList.setNestedScrollingEnabled(false);
    }


    /**
     * 设置监听,去除滑动卡顿
     */
    private void setListener() {
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
        allCourser.setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadMyCourserList("1");
    }

    /**
     * 返回我的课程
     */
    @Override
    public void getMyCourserList(List<MyCourserBO> courserBOs) {
        if (courserBOs == null || courserBOs.size() == 0) {   //如果没有我的课程，则显示推荐课程
            mPresenter.loadHotCourser();
            return;
        }
        swipe.setRefreshing(false);
        courserTitle.setText("我的课程");
        LGRecycleViewAdapter<MyCourserBO> adapter = new LGRecycleViewAdapter<MyCourserBO>(courserBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_my_courser;
            }

            @Override
            public void convert(LGViewHolder holder, MyCourserBO courserBO, int position) {
                holder.setText(R.id.courser_name, courserBO.getCourse().getName());
                holder.setImageUrl(getActivity(), R.id.courser_img, courserBO.getCourse().getImageUrl());
                holder.setText(R.id.courser_seek, "已学" + courserBO.getSectionNum()
                        + "/" + courserBO.getSectionTotalNum() + "课");
            }
        };
        myCourserList.setAdapter(adapter);
    }

    /**
     * 返回今日新知
     */
    @Override
    public void getArticle(final List<ArticleBO> articleBOs) {
        LGRecycleViewAdapter<ArticleBO> adapter = new LGRecycleViewAdapter<ArticleBO>(articleBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_article_layout;
            }

            @Override
            public void convert(LGViewHolder holder, ArticleBO articleBO, int position) {
                holder.setImageUrl(getActivity(), R.id.article_img, articleBO.getImageUrl());
                holder.setText(R.id.article_message, articleBO.getTitle());
                holder.setText(R.id.article_type, articleBO.getArticleTagName());
                holder.setText(R.id.article_xueguo, articleBO.getPageView() + "人已读");
            }
        };
        adapter.setOnItemClickListener(R.id.item_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", articleBOs.get(position).getArticleUrl());
                gotoActivity(WebViewActvity.class, bundle, false);
            }
        });
        xinzhiList.setAdapter(adapter);
    }

    /**
     * （如果没有购买课程）返回课程排行
     */
    @Override
    public void getHotCourse(final List<CourserBO> courserBOs) {
        swipe.setRefreshing(false);
        courserTitle.setText("推荐课程");
        LGRecycleViewAdapter<CourserBO> adapter = new LGRecycleViewAdapter<CourserBO>(courserBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_my_courser;
            }

            @Override
            public void convert(LGViewHolder holder, CourserBO courserBO, int position) {
                holder.setText(R.id.courser_name, courserBO.getName());
                holder.setImageUrl(getActivity(), R.id.courser_img, courserBO.getImageUrl());
                holder.getView(R.id.courser_seek).setVisibility(View.GONE);
            }
        };
        adapter.setOnItemClickListener(R.id.item_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", courserBOs.get(position).getId());
                gotoActivity(CourserMessageActivity.class, bundle, false);
            }
        });
        myCourserList.setAdapter(adapter);
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

    @Override
    public void onRefresh() {
        mPresenter.loadMyCourserList("1");
        mPresenter.loadTodayArticle("1");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_courser:    //查看全部新知
                gotoActivity(TodayArticleActivity.class, false);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
