package com.myp.huiao.ui.courserlist;


import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.courserselect.CourserSelectActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.widget.AppBarStateChangeListener;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;


/**
 * 课程列表
 */

public class CourserListActivity extends MVPBaseActivity<CourserListContract.View,
        CourserListPresenter> implements CourserListContract.View, View.OnClickListener {


    @Bind(R.id.edit_message)
    TextView editMessage;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.scoll_view)
    NestedScrollView scollView;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.yinying)
    ImageView yinying;
    @Bind(R.id.remen_img)
    ImageView remenImg;
    @Bind(R.id.remen_layout)
    LinearLayout remenLayout;
    @Bind(R.id.nandu_img)
    ImageView nanduImg;
    @Bind(R.id.nandu_layout)
    LinearLayout nanduLayout;
    @Bind(R.id.jiage_img)
    ImageView jiageImg;
    @Bind(R.id.jiage_layout)
    LinearLayout jiageLayout;
    @Bind(R.id.remen)
    TextView remen;
    @Bind(R.id.nandu)
    TextView nandu;
    @Bind(R.id.jiage)
    TextView jiage;
    @Bind(R.id.remen_bord)
    View remenBord;
    @Bind(R.id.nandu_bord)
    View nanduBord;
    @Bind(R.id.jiage_bord)
    View jiageBord;


    String courserId;    //课程分类Id
    List<CourserBO> courserBOs;
    LGRecycleViewAdapter<CourserBO> adapter;

    int check = 1;    //当前选中项，默认第一项
    boolean isUp = true;   //默认从高到低

    TextView text[];
    ImageView images[];
    View bord[];

    private String remenSort = "desc";   //默认热门降序
    private String nanduSort = null;   //默认难度降序
    private String jiageSort = null;   //默认价格降序

    @Override
    protected int getLayout() {
        return R.layout.act_courser_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("课程列表");

        courserId = getIntent().getExtras().getString("id", "");
        invition();
        setAppBarListener();
        mPresenter.loadCourserList(jiageSort, remenSort, null, nanduSort, courserId, null, false);
    }

    /**
     * 初始化界面
     */
    private void invition() {
        text = new TextView[]{remen, nandu, jiage};
        images = new ImageView[]{remenImg, nanduImg, jiageImg};
        bord = new View[]{remenBord, nanduBord, jiageBord};
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        recycle.setNestedScrollingEnabled(false);
        remenLayout.setOnClickListener(this);
        nanduLayout.setOnClickListener(this);
        jiageLayout.setOnClickListener(this);
        editMessage.setOnClickListener(this);
    }


    /**
     * 处理AppBarLayout下滑不流畅问题
     */
    private void setAppBarListener() {
        scollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    appBar.setExpanded(true, true);
                }
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    mPresenter.loadCourserList(jiageSort, remenSort, null, nanduSort, courserId, null, true);
                }
            }
        });
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {    //折叠之后的阴影效果
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    yinying.setVisibility(View.GONE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    yinying.setVisibility(View.VISIBLE);
                } else {
                    //中间状态
                    yinying.setVisibility(View.GONE);
                }
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
    public void getCourserList(List<CourserBO> courserBOs, boolean isPageAdd) {
        if (isPageAdd) {
            this.courserBOs.addAll(courserBOs);
        } else {
            this.courserBOs = courserBOs;
        }
        setCourserAdapter();
    }


    /**
     * 设置课程列表适配器
     */
    private void setCourserAdapter() {
        adapter = new LGRecycleViewAdapter<CourserBO>(courserBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_class;
            }

            @Override
            public void convert(LGViewHolder viewHolder, CourserBO item, int position) {
                viewHolder.setImageUrl(CourserListActivity.this, R.id.class_img, item.getShowImageUrl());
                viewHolder.setText(R.id.class_title, item.getName());
                viewHolder.setText(R.id.class_message, item.getDetail());
                viewHolder.setText(R.id.class_xueguo, item.getBuyCount() + "人学过");
                TextView oldPrice = (TextView) viewHolder.getView(R.id.class_old_price);
                oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                if (StringUtils.isEmpty(item.getCurrentPrice())) {
                    oldPrice.setVisibility(View.GONE);
                    viewHolder.setText(R.id.class_price, "¥ " + item.getPrice());
                } else {
                    oldPrice.setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.class_price, "¥ " + item.getCurrentPrice());
                    oldPrice.setText(String.valueOf("¥ " + item.getPrice()));
                }
            }
        };
        recycle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remen_layout:   //热门
                if (check != 1) {
                    isUp = false;
                }
                nanduSort = null;
                jiageSort = null;
                check = 1;
                if (isUp) {
                    isUp = false;
                    remenSort = "asc";
                } else {
                    isUp = true;
                    remenSort = "desc";
                }
                setSortView();
                break;
            case R.id.nandu_layout:   //难度
                if (check != 2) {
                    isUp = false;
                }
                remenSort = null;
                jiageSort = null;
                check = 2;
                if (isUp) {
                    isUp = false;
                    nanduSort = "asc";
                } else {
                    isUp = true;
                    nanduSort = "desc";
                }
                setSortView();
                break;
            case R.id.jiage_layout:   //价格
                if (check != 3) {
                    isUp = false;
                }
                nanduSort = null;
                remenSort = null;
                check = 3;
                if (isUp) {
                    isUp = false;
                    jiageSort = "asc";
                } else {
                    isUp = true;
                    jiageSort = "desc";
                }
                setSortView();
                break;
            case R.id.edit_message:   //搜索
                gotoActivity(CourserSelectActivity.class, false);
                break;
        }
    }


    /**
     * 处理排序图标界面的变化
     */
    private void setSortView() {
        for (int i = 0; i < text.length; i++) {
            if (check - 1 == i) {
                text[i].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                text[i].setTextColor(ContextCompat.getColor(this, R.color.D));
                bord[i].setVisibility(View.VISIBLE);
                if (isUp) {
                    images[i].setImageResource(R.drawable.paixu_xia);
                } else {
                    images[i].setImageResource(R.drawable.paixu_shang);
                }
            } else {
                text[i].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                text[i].setTextColor(ContextCompat.getColor(this, R.color.G));
                images[i].setImageResource(R.drawable.paixu_none);
                bord[i].setVisibility(View.GONE);
            }
        }
        mPresenter.loadCourserList(jiageSort, remenSort, null, nanduSort, courserId, null, false);
    }
}
