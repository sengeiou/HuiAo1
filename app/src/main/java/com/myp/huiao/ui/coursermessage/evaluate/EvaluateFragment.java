package com.myp.huiao.ui.coursermessage.evaluate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hedgehog.ratingbar.RatingBar;
import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.EvaluateBO;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.TimeUtils;
import com.myp.huiao.widget.MyListView;
import com.myp.huiao.widget.superadapter.CommonAdapter;
import com.myp.huiao.widget.superadapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 课程评价页面
 */

public class EvaluateFragment extends MVPBaseFragment<EvaluateContract.View, EvaluatePresenter>
        implements EvaluateContract.View {


    @Bind(R.id.list_view)
    MyListView listView;

    CourserBO courserBO;
    List<EvaluateBO> evaluateBOs;


    public static EvaluateFragment getInstance(CourserBO courserBO) {
        EvaluateFragment myFragment = new EvaluateFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("courser", courserBO);
        myFragment.setArguments(bundle);
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_courser_pingjia, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        courserBO = (CourserBO) getArguments().getSerializable("courser");
        mPresenter.loadCourserPingjia(courserBO.getId());
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getEvaluateList(List<EvaluateBO> evaluateBOs) {
        this.evaluateBOs = evaluateBOs;
        setAdapter();
    }


    /***
     * 设置页面数据
     */
    private void setAdapter() {
        CommonAdapter<EvaluateBO> adapter = new CommonAdapter<EvaluateBO>(getActivity(), R.layout.item_pingjia, evaluateBOs) {

            @Override
            protected void convert(ViewHolder holder, EvaluateBO evaluateBO, int position) {
                holder.setText(R.id.user_name, evaluateBO.getAppUser().getNickName());
                holder.setText(R.id.user_message, evaluateBO.getDetail());
                RatingBar ratingBar = holder.getView(R.id.user_ratingbar);
                ratingBar.setStar(Float.parseFloat(evaluateBO.getScore()));
                holder.setImageUrl(R.id.user_img, evaluateBO.getAppUser().getHeaderUrl());
                holder.setText(R.id.user_time, TimeUtils.getFriendlyTimeSpanByNow(evaluateBO.getCreateTime()));
            }
        };
        listView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
