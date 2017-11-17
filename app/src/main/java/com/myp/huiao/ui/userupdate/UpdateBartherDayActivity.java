package com.myp.huiao.ui.userupdate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.myp.huiao.R;
import com.myp.huiao.base.BaseActivity;
import com.myp.huiao.ui.userupdate.updateinterest.UpdateInterestActivity;
import com.myp.huiao.util.TimeUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;

/**
 * Created by wuliang on 2017/9/14.
 * <p>
 * 修改用户生日页面
 */

public class UpdateBartherDayActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.next_button)
    TextView nextButton;
    @Bind(R.id.picker_layout)
    FrameLayout pickerLayout;

    TimePickerView pvTime;
    Bundle bundle;

    @Override
    protected int getLayout() {
        return R.layout.act_user_birthday;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("选择生日");

        bundle = getIntent().getExtras();
        setPickerLayout();
        nextButton.setOnClickListener(this);
    }

    /**
     * 设置时间选择器
     */
    private void setPickerLayout() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(selectedDate.get(Calendar.YEAR) - 100, 0, 1);
        endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));
        selectedDate.set(1990, 0, 1);
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                bundle.putString("birhday", TimeUtils.date2String(date, "yyyy-MM-dd"));
                gotoActivity(UpdateInterestActivity.class, bundle, false);
            }
        }).setLayoutRes(R.layout.pickerview_layout, new CustomListener() {
            @Override
            public void customLayout(View v) {
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDividerColor(ContextCompat.getColor(this, R.color.line_bg))
                .setTextColorCenter(ContextCompat.getColor(this, R.color.F))
                .setTextColorOut(ContextCompat.getColor(this, R.color.E))
                .setLineSpacingMultiplier(2.0f)    //行间距
                .setDate(selectedDate)
                .setTitleBgColor(ContextCompat.getColor(this, R.color.white))
                .setRangDate(startDate, endDate)
                .setBackgroundId(ContextCompat.getColor(this, R.color.white)) //设置外部遮罩颜色
                .setDecorView(pickerLayout)
                .build();
        pvTime.setKeyBackCancelable(false);//系统返回键监听屏蔽掉
        pvTime.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                pvTime.returnData();
                break;
        }
    }
}
