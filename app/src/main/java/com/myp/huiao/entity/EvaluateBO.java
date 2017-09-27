package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/26.
 *
 * 课程评价bean
 */

public class EvaluateBO implements Serializable{


    /**
     * appUser : {"nickName":"60059017","uploadImage":null}
     * createTime : 2017-09-26 11:14:31
     * detail : 董井君呀
     * score : 3
     */

    private AppUserBo appUser;
    private String createTime;
    private String detail;
    private String score;

    public AppUserBo getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserBo appUser) {
        this.appUser = appUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static class AppUserBo implements Serializable{
        /**
         * nickName : 60059017
         * uploadImage : null
         */

        private String nickName;
        private String headerUrl;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeaderUrl() {
            return headerUrl;
        }

        public void setHeaderUrl(String headerUrl) {
            this.headerUrl = headerUrl;
        }
    }
}
