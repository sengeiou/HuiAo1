package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/19.
 * <p>
 * 轮播图的bean
 */

public class BannerBO implements Serializable {


    /**
     * imageUrl : http://imagetest.nos-eastchina1.126.net/150580617176409e168abbae424b3a6c452c9ce0b7a60.jpg
     * redirectUrl : www.huya.com
     * remark : NIHAO
     */

    private String imageUrl;
    private String redirectUrl;
    private String remark;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
