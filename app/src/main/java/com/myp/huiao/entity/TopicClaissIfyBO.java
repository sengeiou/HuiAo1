package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/10/10.
 * <p>
 * 话题类别bean
 */

public class TopicClaissIfyBO implements Serializable {


    /**
     * iconUrl : http://imagetest.nos-eastchina1.126.net/1506566331219fbeef4d865ccaed0378cee18939a9c18.png
     * id : 8
     * name : 电影院呀
     * "imageUrl": "http://imagetest.nos-eastchina1.126.net/1506566331219fbeef4d865ccaed0378cee18939a9c18.png",
     * "summary": "汽车之旅"
     */

    private String iconUrl;
    private String id;
    private String newName;
    private String imageUrl;
    private String summary;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
