package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/27.
 * <p>
 * 我的课程bean
 */

public class MyCourserBO implements Serializable {


    /**
     * collectStatus : 0
     * course : {"buyCount":0,"currentPrice":110,"detail":"职场法则","id":2,"imageUrl":"http://imagetest.nos-eastchina1.126.net/15058913268116767df370dfd536b462c84933bca8eb7.png","name":"职场法则","price":160,"showImageUrl":"http://imagetest.nos-eastchina1.126.net/1505891326075ca3dd77a5e1ff121d3a9c7eb37f514c4.png"}
     * learnSecond : 70
     * sectionNum : 1
     * sectionTotalNum : 0
     */

    private String collectStatus;
    private CourserBO course;
    private String learnSecond;
    private String sectionNum;
    private String sectionTotalNum;

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public CourserBO getCourse() {
        return course;
    }

    public void setCourse(CourserBO course) {
        this.course = course;
    }

    public String getLearnSecond() {
        return learnSecond;
    }

    public void setLearnSecond(String learnSecond) {
        this.learnSecond = learnSecond;
    }

    public String getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(String sectionNum) {
        this.sectionNum = sectionNum;
    }

    public String getSectionTotalNum() {
        return sectionTotalNum;
    }

    public void setSectionTotalNum(String sectionTotalNum) {
        this.sectionTotalNum = sectionTotalNum;
    }

}
