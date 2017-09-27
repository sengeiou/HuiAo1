package com.myp.huiao.ui.coursermessage.message;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.myp.huiao.R;
import com.myp.huiao.entity.ChaptersBo;

import java.util.List;

/**
 * Created by wuliang on 2017/9/25.
 * <p>
 * 课程章节目录适配器
 */

public class ExpandListAdapter implements ExpandableListAdapter {

    private Context mContext;
    private List<ChaptersBo> chaptersBos;

    ExpandListAdapter(Context context, List<ChaptersBo> chaptersBos) {
        this.mContext = context;
        this.chaptersBos = chaptersBos;
    }


    @Override
    public int getGroupCount() {
        return chaptersBos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chaptersBos.get(groupPosition).getSections().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return chaptersBos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chaptersBos.get(groupPosition).getSections().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHodler hodler;
        if (convertView == null) {
            hodler = new GroupHodler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_courser_group, null);
            hodler.classTitle = (TextView) convertView.findViewById(R.id.class_title);
            convertView.setTag(hodler);
        } else {
            hodler = (GroupHodler) convertView.getTag();
        }
        hodler.classTitle.setText(chaptersBos.get(groupPosition).getName());
        return convertView;
    }

    class GroupHodler {

        TextView classTitle;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHodler hodler;
        if (convertView == null) {
            hodler = new ChildHodler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_courser_child, null);
            hodler.smollTitle = (TextView) convertView.findViewById(R.id.class_smoll_text);
            hodler.smollTime = (TextView) convertView.findViewById(R.id.class_smoll_time);
            convertView.setTag(hodler);
        } else {
            hodler = (ChildHodler) convertView.getTag();
        }
        ChaptersBo.SectionBO sectionBO = chaptersBos.get(groupPosition).getSections().get(childPosition);
        hodler.smollTitle.setText(sectionBO.getName());
        hodler.smollTime.setText(getTime(sectionBO.getDuration()));
        return convertView;
    }


    class ChildHodler {
        TextView smollTitle;
        TextView smollTime;
    }

    /**
     * 将秒转为时分秒
     */
    private String getTime(String time) {
        StringBuffer buffer = new StringBuffer();
        int mTime = Integer.parseInt(time);
        int hour = mTime / 3600;
        int mntite, scond;
        if (hour > 0) {
            mntite = mTime % 3600 / 60;
            scond = mTime % 3600 % 60;
            if (hour < 10) {
                buffer.append("0" + hour + ":");
            } else {
                buffer.append(hour + ":");
            }
        } else {
            mntite = mTime / 60;
            scond = mTime % 60;
        }
        if (mntite < 10) {
            buffer.append("0" + mntite);
        } else {
            buffer.append(mntite);
        }
        if (scond < 10) {
            buffer.append(":0" + scond);
        } else {
            buffer.append(":" + scond);
        }
        return buffer.toString();
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }


    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
