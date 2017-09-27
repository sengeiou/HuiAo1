package com.myp.huiao.ui.coursermessage.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.TeachersBo;
import com.myp.huiao.mvp.MVPBaseFragment;
import com.myp.huiao.ui.teachermsg.TeacherMsgActivity;
import com.myp.huiao.widget.CustomExpandableListView;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * wuliang
 * <p>
 * 详情Fragment
 */

public class MessageFragment extends MVPBaseFragment<MessageContract.View, MessagePresenter>
        implements MessageContract.View {

    @Bind(R.id.jiangshi_recycle)
    RecyclerView jiangshiRecycle;
    @Bind(R.id.faxingshang_recycle)
    RecyclerView faxingshangRecycle;
    @Bind(R.id.expand_list)
    CustomExpandableListView expandList;
    @Bind(R.id.courser_message)
    TextView courserMessage;
    @Bind(R.id.courser_gaishu)
    TextView courserGaishu;


    CourserBO courserBO;


    public static MessageFragment getInstance(CourserBO courserBO) {
        MessageFragment myFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("courser", courserBO);
        myFragment.setArguments(bundle);
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_courser_message, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        courserBO = (CourserBO) getArguments().getSerializable("courser");
        invition();
        setTeatherAdapter();
        setPublishAdapter();
        setChapterAdapter();
    }


    /***
     * 初始化界面
     */
    private void invition() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        jiangshiRecycle.setLayoutManager(manager);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        faxingshangRecycle.setLayoutManager(manager1);
//        jiangshiRecycle.setNestedScrollingEnabled(false);
//        faxingshangRecycle.setNestedScrollingEnabled(false);
        courserMessage.setText(courserBO.getDetail());
        courserGaishu.setText(courserBO.getSummary());
    }


    /**
     * 设置课程讲师数据
     */
    private void setTeatherAdapter() {
        LGRecycleViewAdapter<TeachersBo> adapter = new LGRecycleViewAdapter<TeachersBo>(courserBO.getTeachers()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_teather_layout;
            }

            @Override
            public void convert(LGViewHolder holder, TeachersBo teachersBo, int position) {
                holder.setImageUrl(getActivity(), R.id.teather_img, teachersBo.getHeaderUrl());
                holder.setText(R.id.teather_name, teachersBo.getTeacherName());
                holder.setText(R.id.teather_zhiwei, teachersBo.getProfessional());
            }
        };
        adapter.setOnItemClickListener(R.id.teacher_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", courserBO.getTeachers().get(position).getId());
                gotoActivity(TeacherMsgActivity.class, bundle, false);
            }
        });
        jiangshiRecycle.setAdapter(adapter);
    }


    /**
     * 设置发行商数据
     */
    private void setPublishAdapter() {
        LGRecycleViewAdapter<CourserBO.PublishersBo> adapter =
                new LGRecycleViewAdapter<CourserBO.PublishersBo>(courserBO.getPublishers()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.item_publish_layout;
                    }

                    @Override
                    public void convert(LGViewHolder holder, CourserBO.PublishersBo publishersBo, int position) {
                        holder.setImageUrl(getActivity(), R.id.publish_img, publishersBo.getUploadImageUrl());
                        holder.setText(R.id.publish_name, publishersBo.getName());
                    }
                };
        faxingshangRecycle.setAdapter(adapter);
    }


    /**
     * 设置章节目录数据
     */
    private void setChapterAdapter() {
        ExpandListAdapter adapter = new ExpandListAdapter(getActivity(), courserBO.getChapters());
        expandList.setAdapter(adapter);
        int groupCount = expandList.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandList.expandGroup(i);
        }
        expandList.setGroupIndicator(null);
        expandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;   //默认为false，设为true时，点击事件不会展开Group
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
