package com.myp.huiao.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliang on 2017/9/19.
 * <p>
 * 课程bean
 */

public class CourserBO implements Serializable {


    /**
     * buyCount : 0
     * chapters : [{"id":3,"name":"第一章 java基础","sections":[]}]
     * collectStatus : 0
     * currentPrice : 188
     * detail : Java程序设计
     * facilityValue : 1
     * id : 1
     * imageUrl : http://imagetest.nos-eastchina1.126.net/150589135451653ceaf240de9dd517583c86498bb172c.png
     * name : Java程序设计
     * price : 200
     * publishers : [{"id":3,"name":"人民出版社","uploadImage":{"url":"http://imagetest.nos-eastchina1.126.net/1505185942681e0302740c3f27f2c10d9aba0cbb56136.jpg"}}]
     * score : null
     * scoreUser : null
     * showImageUrl : http://imagetest.nos-eastchina1.126.net/1505891353764970f07214f1de56184635d9b52f6c380.png
     * summary : Java程序设计
     * teachers : [{"fromWhere":"复旦","headerUrl":"http://imagetest.nos-eastchina1.126.net/1505704043197c1cb95b74d90c5507acde74cc0036750.jpg","id":4,"level":5,"professional":1,"teacherName":"陈道明"},{"fromWhere":"交大","headerUrl":"http://imagetest.nos-eastchina1.126.net/1505704043197c1cb95b74d90c5507acde74cc0036750.jpg","id":5,"level":3,"professional":3,"teacherName":"李易峰"},{"fromWhere":"交大","headerUrl":"http://imagetest.nos-eastchina1.126.net/1505704043197c1cb95b74d90c5507acde74cc0036750.jpg","id":6,"level":2,"professional":3,"teacherName":"范冰冰"}]
     * video : {"origUrl":"http://vodd2uqc5at.vod.126.net/vodd2uqc5at/832b7fe8-e42a-44f6-ba99-014475e735dd.mp4","snapshotUrl":"http://vodd2uqc5at.nosdn.127.net/15ba91c1-0c75-45ef-9f09-33ca961597c8.jpg"}
     */

    private String buyCount;
    private String collectStatus;
    private String currentPrice;
    private String detail;
    private String facilityValue;
    private String id;
    private String imageUrl;
    private String name;
    private String price;
    private String score;
    private String scoreUser;
    private String showImageUrl;
    private String summary;
    private VideoBo video;
    private List<ChaptersBo> chapters;
    private List<PublishersBo> publishers;
    private List<TeachersBo> teachers;

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFacilityValue() {
        return facilityValue;
    }

    public void setFacilityValue(String facilityValue) {
        this.facilityValue = facilityValue;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreUser() {
        return scoreUser;
    }

    public void setScoreUser(String scoreUser) {
        this.scoreUser = scoreUser;
    }

    public String getShowImageUrl() {
        return showImageUrl;
    }

    public void setShowImageUrl(String showImageUrl) {
        this.showImageUrl = showImageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public VideoBo getVideo() {
        return video;
    }

    public void setVideo(VideoBo video) {
        this.video = video;
    }

    public List<ChaptersBo> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChaptersBo> chapters) {
        this.chapters = chapters;
    }

    public List<PublishersBo> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<PublishersBo> publishers) {
        this.publishers = publishers;
    }

    public List<TeachersBo> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeachersBo> teachers) {
        this.teachers = teachers;
    }

    public static class PublishersBo implements Serializable{
        /**
         * id : 3
         * name : 人民出版社
         * uploadImage : {"url":"http://imagetest.nos-eastchina1.126.net/1505185942681e0302740c3f27f2c10d9aba0cbb56136.jpg"}
         */

        private String id;
        private String name;
        private String uploadImageUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUploadImageUrl() {
            return uploadImageUrl;
        }

        public void setUploadImageUrl(String uploadImageUrl) {
            this.uploadImageUrl = uploadImageUrl;
        }
    }


}
