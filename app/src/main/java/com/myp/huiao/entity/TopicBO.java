package com.myp.huiao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliang on 2017/10/10.
 * <p>
 * 用户发布的话题bean
 */

public class TopicBO implements Serializable {


    /**
     * appUser : {"headerUrl":"http://imagetest.nos-eastchina1.126.net/1505891353764970f07214f1de56184635d9b52f6c380.png","id":3,"nickName":"61866388"}
     * attention : false
     * click : false
     * content : 最近敦刻尔克大电影大家去看了，诺兰出品必属精品啊！
     * createTime : 2017-09-13 09:02:14
     * discussNum : 0
     * id : 5
     * imageUrl : http://imagetest.nos-eastchina1.126.net/15051848765887ab3b1dadd7f2dd0868b871e4ddfe289.jpg
     * likeNum : 0
     * topicCategories : [{"id":4,"name":"电影"},{"id":3,"name":"汽车"}]
     */

    private AppUserBo appUser;
    private boolean attention;
    private boolean click;
    private String content;
    private String createTime;
    private int discussNum;
    private int id;
    private String imageUrl;
    private int likeNum;
    private List<TopicClaissIfyBO> topicCategories;

    public AppUserBo getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserBo appUser) {
        this.appUser = appUser;
    }

    public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean attention) {
        this.attention = attention;
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

    public int getDiscussNum() {
        return discussNum;
    }

    public void setDiscussNum(int discussNum) {
        this.discussNum = discussNum;
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

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public List<TopicClaissIfyBO> getTopicCategories() {
        return topicCategories;
    }

    public void setTopicCategories(List<TopicClaissIfyBO> topicCategories) {
        this.topicCategories = topicCategories;
    }

    public static class AppUserBo implements Serializable {
        /**
         * headerUrl : http://imagetest.nos-eastchina1.126.net/1505891353764970f07214f1de56184635d9b52f6c380.png
         * id : 3
         * nickName : 61866388
         */

        private String headerUrl;
        private int id;
        private String nickName;

        public String getHeaderUrl() {
            return headerUrl;
        }

        public void setHeaderUrl(String headerUrl) {
            this.headerUrl = headerUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

}
