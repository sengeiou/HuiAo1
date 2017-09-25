package com.myp.huiao.ui.main.classlibraay;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.util.StringUtils;

import java.util.List;

/**
 * Created by wuliang on 2017/9/19.
 * <p>
 * 热门课程适配器
 */

public class HotCourceAdapter extends PagerAdapter {

    private Context mContext;
    private List<CourserBO> courserBOs;


    HotCourceAdapter(List<CourserBO> courserBOs, Context context) {
        this.courserBOs = courserBOs;
        mContext = context;
    }


    @Override
    public int getCount() {
        return courserBOs.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_course, null);
        ImageView image = (ImageView) view.findViewById(R.id.class_img);
        if (StringUtils.isEmpty(courserBOs.get(position).getShowImageUrl())) {
            image.setImageResource(R.drawable.zhanwei2);
        } else {
            Glide.with(mContext).load(courserBOs.get(position).getImageUrl())
                    .into(image);
        }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
