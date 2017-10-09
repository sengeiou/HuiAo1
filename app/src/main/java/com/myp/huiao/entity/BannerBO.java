package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/19.
 * <p>
 * 轮播图的bean
 */

public class BannerBO implements Serializable {


    /**
     * courseid : null
     * imageUrl : http://imagetest.nos-eastchina1.126.net/1506417768476245eb6387f37409f359701a2295b56e7.png
     * playType : null
     * redirectUrl : www.baidu.com
     * remark : 测试测试
     * showName : null
     */

    private String courseid;
    private String imageUrl;
    private String playType;
    private String redirectUrl;
    private String remark;
    private String showName;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
