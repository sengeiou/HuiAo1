package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/20.
 *
 * 课程分类bean
 */

public class CourserClassifyBO implements Serializable{


    /**
     * classifyName : 人力管理
     * classifySort : 1
     * id : 2
     * imageUrl : http://imagetest.nos-eastchina1.126.net/15051860577137f32ea8055fb1ce0062bd74d5ad50454.png
     */

    private String classifyName;
    private String classifySort;
    private String id;
    private String imageUrl;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifySort() {
        return classifySort;
    }

    public void setClassifySort(String classifySort) {
        this.classifySort = classifySort;
    }

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
}
