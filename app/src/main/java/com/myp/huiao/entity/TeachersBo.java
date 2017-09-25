package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/22.
 * <p>
 * 讲师bean
 */

public class TeachersBo implements Serializable {


    /**
     * fromWhere : 复旦
     * headerUrl : http://imagetest.nos-eastchina1.126.net/1505704043197c1cb95b74d90c5507acde74cc0036750.jpg
     * id : 4
     * level : 5
     * professional : 1
     * teacherName : 陈道明
     */

    private String fromWhere;
    private String headerUrl;
    private String id;
    private String level;
    private String professional;
    private String teacherName;

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
