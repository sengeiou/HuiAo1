package com.myp.huiao.ui.courserselect;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.mvp.MVPBaseActivity;
import com.myp.huiao.util.LogUtils;
import com.myp.huiao.util.StringUtils;
import com.myp.huiao.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.huiao.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.Bind;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CourserSelectActivity extends MVPBaseActivity<CourserSelectContract.View,
        CourserSelectPresenter> implements CourserSelectContract.View, View.OnClickListener {

    @Bind(R.id.edit_message)
    EditText editMessage;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.right_button)
    TextView rightButton;

    boolean isCancle = true;    //默认是取消

    @Override
    protected int getLayout() {
        return R.layout.act_courser_select;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        rightButton.setOnClickListener(this);
        setEditListener();
    }


    /**
     * 监听edittext 的动静
     */
    private void setEditListener() {
        editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() != 0) {
                    isCancle = false;
                    rightButton.setText("搜索");
                } else {
                    isCancle = true;
                    rightButton.setText("取消");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_button:
                if (isCancle) {
                    finish();
                } else {
                    mPresenter.loadCourserList(null, null, "desc", null, null,
                            editMessage.getText().toString().trim(), false);
                }
                break;
        }
    }

    @Override
    public void getCourserList(List<CourserBO> courserBOs, boolean isPageAdd) {
        LGRecycleViewAdapter<CourserBO> adapter = new LGRecycleViewAdapter<CourserBO>(courserBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_class;
            }

            @Override
            public void convert(LGViewHolder viewHolder, CourserBO item, int position) {
                viewHolder.setImageUrl(CourserSelectActivity.this, R.id.class_img, item.getShowImageUrl());
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
                    oldPrice.setText("¥ " + item.getPrice());
                }
            }
        };
        recycle.setAdapter(adapter);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }
}
