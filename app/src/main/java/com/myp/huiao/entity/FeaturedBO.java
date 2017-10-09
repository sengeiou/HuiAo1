package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/29.
 * <p>
 * 精选话题bean
 */

public class FeaturedBO implements Serializable {


    /**
     * appUser : {"headerUrl":"http://imagetest.nos-eastchina1.126.net/1505891353764970f07214f1de56184635d9b52f6c380.png","nickName":"61866388"}
     * id : 4
     * imageUrl : http://imagetest.nos-eastchina1.126.net/1505184846124393bcdedba8a866bd352a833d250e247.png
     */

    private UserBO appUser;
    private int id;
    private String imageUrl;

    public UserBO getAppUser() {
        return appUser;
    }

    public void setAppUser(UserBO appUser) {
        this.appUser = appUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
