package com.myp.huiao.ui.coursersort;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.CourserClassifyBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.courserlist.CourserListActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


/**
 * wuliang
 * <p>
 * 课程分类
 */

public class CourserSortActivity extends MVPBaseActivity<CourserSortContract.View,
        CourserSortPresenter> implements CourserSortContract.View {

    @Bind(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected int getLayout() {
        return R.layout.act_courser_sort;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("课程分类");

        initvion();
        mPresenter.loadCourserClassify("classify");
    }

    /**
     * 初始化布局
     */
    private void initvion() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(manager);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    /**
     * 返回的课程分类
     */
    @Override
    public void getCourserClassify(final List<CourserClassifyBO> courserClassifyBOs) {
        LGRecycleViewAdapter<CourserClassifyBO> adapter = new LGRecycleViewAdapter<CourserClassifyBO>(courserClassifyBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_courser_sort;
            }

            @Override
            public void convert(LGViewHolder holder, CourserClassifyBO courserClassifyBO, int position) {
                holder.setText(R.id.courser_title, courserClassifyBO.getClassifyName());
                ShapedImageView image = (ShapedImageView) holder.getView(R.id.courser_img);
                Glide.with(CourserSortActivity.this).load(courserClassifyBO.getImageUrl())
                        .crossFade()
                        .animate(R.anim.glide_img_anim).into(image);
            }
        };
        adapter.setOnItemClickListener(R.id.item_courser, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", courserClassifyBOs.get(position).getId());
                gotoActivity(CourserListActivity.class, bundle, false);
            }
        });
        recycle.setAdapter(adapter);
    }
}
