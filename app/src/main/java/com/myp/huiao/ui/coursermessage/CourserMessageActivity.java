package com.myp.huiao.ui.coursermessage;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.ui.coursermessage.afterclass.AfterClassFragment;
import com.myp.huiao.ui.coursermessage.evaluate.EvaluateFragment;
import com.myp.huiao.ui.coursermessage.message.MessageFragment;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.ScreenUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.widget.MyScrollView;
import com.myp.huiao.widget.ShareDialog;
import com.myp.huiao.widget.video.SampleListener;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
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
    @Bind(R.id.zixun_img)
    ImageView zixunImg;
    @Bind(R.id.classify_layout_scoll)
    LinearLayout classifyLayoutScoll;
    @Bind(R.id.classify_layout)
    LinearLayout classifyLayout;
    @Bind(R.id.scoll_view)
    MyScrollView scollView;
    @Bind(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @Bind(R.id.message_scoll)
    TextView messageScoll;
    @Bind(R.id.remen_bord_scoll)
    View remenBordScoll;
    @Bind(R.id.message_layout_scoll)
    RelativeLayout messageLayoutScoll;
    @Bind(R.id.pingjia_scoll)
    TextView pingjiaScoll;
    @Bind(R.id.pingjia_bord_scoll)
    View pingjiaBordScoll;
    @Bind(R.id.pingjia_layout_scoll)
    RelativeLayout pingjiaLayoutScoll;
    @Bind(R.id.kehou_scoll)
    TextView kehouScoll;
    @Bind(R.id.kehou_bord_scoll)
    View kehouBordScoll;
    @Bind(R.id.kehou_layout_scoll)
    RelativeLayout kehouLayoutScoll;
    @Bind(R.id.layout_main)
    RelativeLayout layoutMain;
    @Bind(R.id.video_img)
    ImageView videoImg;
    @Bind(R.id.video_layout)
    RelativeLayout videoLayout;
    @Bind(R.id.video_play)
    ImageView videoPlay;
    @Bind(R.id.add_courser)
    TextView addCourser;
    @Bind(R.id.shoucang_layout)
    LinearLayout shoucangLayout;
    @Bind(R.id.zixun_layout)
    LinearLayout zixunLayout;

    MessageFragment messageFragment;   //详情
    EvaluateFragment evaluateFragment;   //课程评价
    AfterClassFragment afterClassFragment;   //课后分享
    String courserId;
    CourserBO courserBO;
    private boolean isCollect = false;    //课程是否已收藏

    private boolean isPlay;
    private OrientationUtils orientationUtils;
    TextView headerText[];
    TextView scollText[];
    View[] headerBord;
    View[] scollBord;

    @Override
    protected int getLayout() {
        return R.layout.act_courser_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("课程详情");
        setRightButton(R.drawable.fenxiang, this);

        courserId = getIntent().getExtras().getString("id", "");
        invition();
        setListener();
        resolveNormalVideoUI();
        showProgress("加载中...");
        mPresenter.loadCourserMessage(courserId);
    }

    /**
     * 初始化界面
     */
    private void invition() {
        ViewGroup.LayoutParams params = videoLayout.getLayoutParams();
        params.width = ScreenUtils.getScreenWidth();
        params.height = ScreenUtils.getScreenWidth() / 16 * 9;
        videoLayout.setLayoutParams(params);
        headerText = new TextView[]{message, pingjia, kehou};
        headerBord = new View[]{remenBord, pingjiaBord, kehouBord};
        scollText = new TextView[]{messageScoll, pingjiaScoll, kehouScoll};
        scollBord = new View[]{remenBordScoll, pingjiaBordScoll, kehouBordScoll};
    }

    /**
     * 初始化视频设置
     */
    private void inviVideo() {
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setUrl(courserBO.getVideo().getOrigUrl())
                .setCacheWithPlay(false)
                .setVideoTitle("课程介绍")
                .setStandardVideoAllCallBack(new SampleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        isPlay = true;
                    }
                })
                .build(detailPlayer);
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourserMessageActivity.this, FullVideoActivity.class);
                intent.putExtra("url", courserBO.getVideo().getOrigUrl());
                intent.putExtra("startTime", detailPlayer.getCurrentPositionWhenPlaying());
                startActivityForResult(intent, 1);
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
        messageLayout.setOnClickListener(this);
        pingjiaLayout.setOnClickListener(this);
        kehouLayout.setOnClickListener(this);
        messageLayoutScoll.setOnClickListener(this);
        pingjiaLayoutScoll.setOnClickListener(this);
        kehouLayoutScoll.setOnClickListener(this);
        videoPlay.setOnClickListener(this);
        addCourser.setOnClickListener(this);
        shoucangLayout.setOnClickListener(this);
        zixunLayout.setOnClickListener(this);
    }


    /**
     * 为界面设置数据
     */
    private void setUIData() {
        courserName.setText(courserBO.getName());
        ratingbar.setStar(Float.parseFloat(courserBO.getScore()));
        ratingnum.setText(courserBO.getScore());
        if (StringUtils.isEmpty(courserBO.getCurrentPrice())) {
            price.setText(String.valueOf("¥ " + courserBO.getPrice()));
        } else {
            price.setText(String.valueOf("¥ " + courserBO.getCurrentPrice()));
        }
        courserBuy.setText(String.valueOf(courserBO.getBuyCount() + "人学过"));
        if ("0".equals(courserBO.getCollectStatus())) {
            shoucangImg.setImageResource(R.drawable.weishoucang);
            isCollect = false;
        } else if ("1".equals(courserBO.getCollectStatus())) {
            shoucangImg.setImageResource(R.drawable.shoucang);
            isCollect = true;
        }
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
        stopProgress();
        this.courserBO = courserBO;
        scollView.setVisibility(View.VISIBLE);
        setUIData();
        if (courserBO.getVideo() == null) {
            detailPlayer.setVisibility(View.GONE);
            videoImg.setVisibility(View.VISIBLE);
            videoPlay.setVisibility(View.GONE);
            Glide.with(this).load(courserBO.getImageUrl()).into(videoImg);
        } else {
            inviVideo();
            videoImg.setVisibility(View.VISIBLE);
            Glide.with(this).load(courserBO.getImageUrl()).into(videoImg);
        }
        messageFragment = MessageFragment.getInstance(courserBO);
        evaluateFragment = EvaluateFragment.getInstance(courserBO);
        goToFragment(messageFragment);
    }

    /**
     * 点击收藏成功
     */
    @Override
    public void getSurcess() {
        stopProgress();
        if (isCollect) {
            shoucangImg.setImageResource(R.drawable.weishoucang);
            isCollect = false;
        } else {
            shoucangImg.setImageResource(R.drawable.shoucang);
            isCollect = true;
        }
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.love_anim);
        shoucangImg.startAnimation(animation);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:
            case R.id.message_layout_scoll:
                classifyUI(0);
                goToFragment(messageFragment);
                break;
            case R.id.pingjia_layout:
            case R.id.pingjia_layout_scoll:
                classifyUI(1);
                goToFragment(evaluateFragment);
                break;
            case R.id.kehou_layout:
            case R.id.kehou_layout_scoll:
                classifyUI(2);
                break;
            case R.id.video_play:   //播放
                videoImg.setVisibility(View.GONE);
                videoPlay.setVisibility(View.GONE);
                detailPlayer.startPlayLogic();
                break;
            case R.id.right_img_layout:    //分享
                ShareDialog dialog = new ShareDialog(this, null);
                dialog.showAtLocation(bottomLayout, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.add_courser:     //参加课程
                setShowDialog();
                break;
            case R.id.shoucang_layout:     //收藏
                if (isCollect) {
                    mPresenter.loadCollectCourser(courserBO.getId(), "0");
                } else {
                    mPresenter.loadCollectCourser(courserBO.getId(), "1");
                }
                showProgress("加载中...");
                break;
            case R.id.zixun_layout:     //咨询
                Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.love_anim);
                zixunImg.startAnimation(animation1);
                break;
        }
    }

    private void classifyUI(int type) {
        for (int i = 0; i < headerText.length; i++) {
            if (i == type) {
                headerText[i].setTextColor(ContextCompat.getColor(this, R.color.D));
                headerText[i].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                scollText[i].setTextColor(ContextCompat.getColor(this, R.color.D));
                scollText[i].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                headerBord[i].setVisibility(View.VISIBLE);
                scollBord[i].setVisibility(View.VISIBLE);
            } else {
                headerText[i].setTextColor(ContextCompat.getColor(this, R.color.G));
                headerText[i].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                scollText[i].setTextColor(ContextCompat.getColor(this, R.color.G));
                scollText[i].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                headerBord[i].setVisibility(View.GONE);
                scollBord[i].setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 1:
                long startTime = data.getIntExtra("startTime", 0);
                detailPlayer.setSeekOnStart(startTime);
                detailPlayer.startPlayLogic();
                break;
        }
    }


    AlertDialog dialog;

    /**
     * 点击参加课程
     */
    private void setShowDialog() {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_pay_message, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView price = (TextView) view.findViewById(R.id.price);
        title.setText("确认参加课程《" + courserBO.getName() + "》");
        price.setText(this.price.getText());
        dialog = new AlertDialog.Builder(this).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.getWindow().setWindowAnimations(R.style.dialog_anim);
        dialog.show();
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
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
        super.onDestroy();
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
