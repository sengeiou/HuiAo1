package com.myp.huiao.ui.coursermessage;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.coursermessage.message.MessageFragment;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.ScreenUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.widget.MyScrollView;
import com.myp.huiao.widget.SampleListener;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import butterknife.Bind;


/**
 * wuliang
 * <p>
 * 课程详情页
 */

public class CourserMessageActivity extends MVPBaseActivity<CourserMessageContract.View,
        CourserMessagePresenter> implements CourserMessageContract.View, View.OnClickListener {

    @Bind(R.id.video)
    StandardGSYVideoPlayer detailPlayer;
    @Bind(R.id.courser_name)
    TextView courserName;
    @Bind(R.id.ratingbar)
    RatingBar ratingbar;
    @Bind(R.id.ratingnum)
    TextView ratingnum;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.remen_bord)
    View remenBord;
    @Bind(R.id.message_layout)
    RelativeLayout messageLayout;
    @Bind(R.id.pingjia)
    TextView pingjia;
    @Bind(R.id.pingjia_bord)
    View pingjiaBord;
    @Bind(R.id.pingjia_layout)
    RelativeLayout pingjiaLayout;
    @Bind(R.id.kehou)
    TextView kehou;
    @Bind(R.id.kehou_bord)
    View kehouBord;
    @Bind(R.id.kehou_layout)
    RelativeLayout kehouLayout;
    @Bind(R.id.courser_buy)
    TextView courserBuy;
    @Bind(R.id.shoucang_img)
    ImageView shoucangImg;
    @Bind(R.id.shoucang_text)
    TextView shoucangText;
    @Bind(R.id.zixun_img)
    ImageView zixunImg;
    @Bind(R.id.zixun_text)
    TextView zixunText;
    @Bind(R.id.classify_layout_scoll)
    LinearLayout classifyLayoutScoll;
    @Bind(R.id.classify_layout)
    LinearLayout classifyLayout;
    @Bind(R.id.scoll_view)
    MyScrollView scollView;

    MessageFragment messageFragment;   //详情
    String courserId;
    CourserBO courserBO;


    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;

    @Override
    protected int getLayout() {
        return R.layout.act_courser_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("课程详情");

        courserId = getIntent().getExtras().getString("id", "");
        invition();
        setListener();
        resolveNormalVideoUI();
        mPresenter.loadCourserMessage(courserId);
    }

    /**
     * 初始化界面
     */
    private void invition() {
        ViewGroup.LayoutParams params = detailPlayer.getLayoutParams();
        params.width = ScreenUtils.getScreenWidth();
        params.height = ScreenUtils.getScreenWidth() / 16 * 9;
        detailPlayer.setLayoutParams(params);
    }

    /**
     * 初始化视频设置
     */
    private void inviVideo() {
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(courserBO.getVideo().getSnapshotUrl()).into(imageView);
        detailPlayer.setThumbImageView(imageView);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setUrl(courserBO.getVideo().getOrigUrl())
                .setCacheWithPlay(false)
                .setVideoTitle("测试视频")
                .setStandardVideoAllCallBack(new SampleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[1]);//当前全屏player
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        super.onClickStartError(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);
                        }
                    }
                }).build(detailPlayer);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(CourserMessageActivity.this, true, true);
            }
        });


    }


    /**
     * 设置监听数据
     */
    private void setListener() {
        scollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY >= classifyLayoutScoll.getY()) {
                    classifyLayout.setVisibility(View.VISIBLE);
                } else {
                    classifyLayout.setVisibility(View.GONE);
                }
            }
        });
    }


    /**
     * 为界面设置数据
     */
    private void setUIData() {
        courserName.setText(courserBO.getName());
        ratingbar.setStar(Float.parseFloat(courserBO.getScore()));
        ratingnum.setText(courserBO.getScore());
        if (StringUtils.isEmpty(courserBO.getCurrentPrice())) {
            price.setText("¥ " + courserBO.getPrice());
        } else {
            price.setText("¥ " + courserBO.getCurrentPrice());
        }
        courserBuy.setText(courserBO.getBuyCount() + "人学过");
    }


    private Fragment mContent = null;

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                if (mContent != null)
                    transaction.hide(mContent).add(R.id.fragment_container, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                else
                    transaction.add(R.id.fragment_container, to).commitAllowingStateLoss();
            } else {
                if (mContent != null)
                    transaction.hide(mContent).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                else
                    transaction.show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getCourserBo(CourserBO courserBO) {
        this.courserBO = courserBO;
        setUIData();
        inviVideo();
        messageFragment = MessageFragment.getInstance(courserBO);
        goToFragment(messageFragment);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.video_play:
//                videoImg.setVisibility(View.GONE);
//                videoPlay.setVisibility(View.GONE);
//                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils);
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }

    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer() != null) {
            return detailPlayer.getFullWindowPlayer();
        }
        return detailPlayer;
    }

}
