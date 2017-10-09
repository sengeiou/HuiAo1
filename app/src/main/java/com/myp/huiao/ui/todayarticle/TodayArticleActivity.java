package com.myp.huiao.ui.todayarticle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myp.huiao.R;
import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.WebViewActvity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * MVPPlugin
 * 全部今日新知界面
 */

public class TodayArticleActivity extends MVPBaseActivity<TodayArticleContract.View,
        TodayArticlePresenter> implements TodayArticleContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;

    List<ArticleBO> articleBOs;

    @Override
    protected int getLayout() {
        return R.layout.recycle_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("今日新知");

        invitionSwipeRefresh(swipe);
        swipe.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        articleBOs = new ArrayList<>();
        mPresenter.loadAriticleList(false);
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogUtils.D("------->isSlideToBottom:" + isSlideToBottom(recyclerView));
                if (isSlideToBottom(recyclerView)) {
                    mPresenter.loadAriticleList(true);
                }
            }
        });
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }


    @Override
    public void getArticleList(List<ArticleBO> articleBOs, boolean isPage) {
        if (isPage) {
            this.articleBOs.addAll(articleBOs);
        } else {
            this.articleBOs = articleBOs;
        }
        setArticleBOsAdapter();
    }

    LGRecycleViewAdapter<ArticleBO> adapter;

    /**
     * 设置监听
     */
    private void setArticleBOsAdapter() {
        swipe.setRefreshing(false);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            return;
        }
        adapter = new LGRecycleViewAdapter<ArticleBO>(articleBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_article_layout;
            }

            @Override
            public void convert(LGViewHolder holder, ArticleBO articleBO, int position) {
                holder.setImageUrl(TodayArticleActivity.this, R.id.article_img, articleBO.getImageUrl());
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
        recycle.setAdapter(adapter);
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
        mPresenter.loadAriticleList(false);
    }
}
