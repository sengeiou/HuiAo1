package com.myp.huiao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliang on 2017/11/2.
 * <p>
 * 评论的javabean
 */

public class PinLunBO implements Serializable {


    /**
     * appUser : {"gender":2,"headerUrl":"http://imagetest.nos-eastchina1.126.net/15058913268116767df370dfd536b462c84933bca8eb7.png","nickName":"吕布"}
     * children : [{"content":null,"owner":"吕布","toWho":"吕布"}]
     * click : false
     * content : xxxx
     * createTime : 2017-11-02 17:26:19
     * id : 8
     * owner : 吕布
     * parent : null
     * thumb : 0
     * toWho : null
     */

    private AppUserBo appUser;
    private boolean click;
    private String content;
    private String createTime;
    private int id;
    private String owner;
    private String parent;
    private int thumb;
    private String toWho;
    private List<ChildrenBo> children;
    private int chiNum;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public int getChiNum() {
        return chiNum;
    }

    public void setChiNum(int chiNum) {
        this.chiNum = chiNum;
    }

    public AppUserBo getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserBo appUser) {
        this.appUser = appUser;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public List<ChildrenBo> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBo> children) {
        this.children = children;
    }

    public static class AppUserBo implements Serializable {
        /**
         * gender : 2
         * headerUrl : http://imagetest.nos-eastchina1.126.net/15058913268116767df370dfd536b462c84933bca8eb7.png
         * nickName : 吕布
         */

        private int gender;
        private String headerUrl;
        private String nickName;

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHeaderUrl() {
            return headerUrl;
        }

        public void setHeaderUrl(String headerUrl) {
            this.headerUrl = headerUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

    public static class ChildrenBo implements Serializable {
        /**
         * content : null
         * owner : 吕布
         * toWho : 吕布
         */

        private Object content;
        private String owner;
        private String toWho;

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getToWho() {
            return toWho;
        }

        public void setToWho(String toWho) {
            this.toWho = toWho;
        }
    }
}
