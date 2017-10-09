package com.myp.huiao.ui.teachermsg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.TeachersBo;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.coursermessage.CourserMessageActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.widget.bigimage.ImagPagerUtil;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


/**
 * wuliang
 * <p>
 * 课程讲师详情
 */

public class TeacherMsgActivity extends MVPBaseActivity<TeacherMsgContract.View,
        TeacherMsgPresenter> implements TeacherMsgContract.View {

    @Bind(R.id.teather_img)
    ShapedImageView teatherImg;
    @Bind(R.id.teather_name)
    TextView teatherName;
    @Bind(R.id.teather_ratingbar)
    RatingBar teatherRatingbar;
    @Bind(R.id.teacher_message)
    TextView teacherMessage;
    @Bind(R.id.thacher_imglist)
    RecyclerView thacherImglist;
    @Bind(R.id.thacher_courser)
    RecyclerView thacherCourser;
    @Bind(R.id.teacher_post)
    TextView teacherPost;
    @Bind(R.id.scoll_view)
    ScrollView scollView;

    private String teacherId;
    private TeachersBo teachersBo;
    private List<String> images;    //教师相册

    @Override
    protected int getLayout() {
        return R.layout.act_teacher_msg;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("课程讲师");

        teacherId = getIntent().getExtras().getString("id", "");
        invition();
        mPresenter.loadTeacherMsg(teacherId);
        mPresenter.loadTeacherCourser(teacherId);
    }


    /***
     * 初始化界面
     */
    private void invition() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        thacherImglist.setLayoutManager(manager);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        thacherCourser.setLayoutManager(manager1);
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getTeacherBean(TeachersBo teachersBo) {
        this.teachersBo = teachersBo;
        scollView.setVisibility(View.VISIBLE);
        setUIData();
        setTeacherImgAdapter();
    }


    /**
     * 设置界面显示
     */
    private void setUIData() {
        Glide.with(this).load(teachersBo.getHeaderUrl()).into(teatherImg);
        teatherName.setText(teachersBo.getTeacherName());
        teatherRatingbar.setStar(Float.parseFloat(teachersBo.getLevel()));
        teacherPost.setText(teachersBo.getProfessional());
        teacherMessage.setText(teachersBo.getSummary());
        images = new ArrayList<>();
        for (TeachersBo.UploadAlbumsBo uploadAlbumsBo : teachersBo.getUploadAlbums()) {
            images.add(uploadAlbumsBo.getUrl());
        }
    }

    /**
     * 设置教师相册
     */
    private void setTeacherImgAdapter() {
        LGRecycleViewAdapter<TeachersBo.UploadAlbumsBo> adapter =
                new LGRecycleViewAdapter<TeachersBo.UploadAlbumsBo>(teachersBo.getUploadAlbums()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.item_teacher_img;
                    }

                    @Override
                    public void convert(LGViewHolder holder, TeachersBo.UploadAlbumsBo uploadAlbumsBo, int position) {
                        holder.setImageUrl(TeacherMsgActivity.this, R.id.teather_img, uploadAlbumsBo.getUrl());
                    }
                };
        adapter.setOnItemClickListener(R.id.teather_img, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                setBigImageShow(images, teachersBo.getTeacherName(), position);
            }
        });
        thacherImglist.setAdapter(adapter);
    }

    /**
     * 设置大图显示
     *
     * @param images   图片列表
     * @param position 当前查看第几张
     */
    private void setBigImageShow(List<String> images, String content, int position) {
        ImagPagerUtil imagPagerUtil = new ImagPagerUtil(this, images);
        imagPagerUtil.setContentText(content);
        imagPagerUtil.show();
        imagPagerUtil.mViewPager.setCurrentItem(position);
    }


    /**
     * 设置相关课程
     */
    @Override
    public void getTeacherCourser(final List<CourserBO> courserBOs) {
        LGRecycleViewAdapter<CourserBO> adapter = new LGRecycleViewAdapter<CourserBO>(courserBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_teacher_courser;
            }

            @Override
            public void convert(LGViewHolder holder, CourserBO courserBO, int position) {
                holder.setText(R.id.courser_name, courserBO.getName());
                holder.setImageUrl(TeacherMsgActivity.this, R.id.courser_img, courserBO.getImageUrl());
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
        thacherCourser.setAdapter(adapter);
    }
}
