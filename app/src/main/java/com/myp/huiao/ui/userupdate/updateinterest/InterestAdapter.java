package com.myp.huiao.ui.userupdate.updateinterest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.huiao.R;
import com.myp.huiao.entity.ChioceBO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wuliang on 2017/9/15.
 * <p>
 * 兴趣适配器
 */

class InterestAdapter extends BaseAdapter {


    private Context mContext;
    private List<ChioceBO> chioceBOs;
    private Map<String, ChioceBO> selectCho;    //选中的兴趣列表

    InterestAdapter(Context context, List<ChioceBO> chioceBOs) {
        this.mContext = context;
        this.chioceBOs = chioceBOs;
        selectCho = new HashMap<>();
    }


    /**
     * 选中某一个兴趣
     */
    Map<String, ChioceBO> setSelectCho(ChioceBO chioceBO) {
        if (selectCho.containsKey(chioceBO.getId())) {
            selectCho.remove(chioceBO.getId());
        } else {
            selectCho.put(chioceBO.getId(), chioceBO);
        }
        notifyDataSetChanged();
        return selectCho;
    }

    @Override
    public int getCount() {
        return chioceBOs.size();
    }

    @Override
    public Object getItem(int position) {
        return chioceBOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_user_update, null);
            holder = new Holder();
            holder.image = (CircleImageView) convertView.findViewById(R.id.image);
            holder.checkBorld = (ImageView) convertView.findViewById(R.id.check_brold);
            holder.name = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ChioceBO chioceBO = chioceBOs.get(position);
        if (selectCho.containsKey(chioceBO.getId())) {
            holder.checkBorld.setVisibility(View.VISIBLE);
        } else {
            holder.checkBorld.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(chioceBO.getImageUrl()).into(holder.image);
        holder.name.setText(chioceBO.getName());
        return convertView;
    }

    private class Holder {
        CircleImageView image;
        ImageView checkBorld;
        TextView name;
    }


}
