package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/14.
 * <p>
 * 学历 和 兴趣 bean
 */

public class ChioceBO implements Serializable {


    /**
     * id : 5
     * imageUrl : http://imagetest.nos-eastchina1.126.net/1505291864646d875ea6654b50cc7c0739906abb9edaf.jpg
     * name : 小学
     */

    private String id;
    private String imageUrl;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
