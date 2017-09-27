package com.myp.huiao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliang on 2017/9/22.
 * <p>
 * 讲师bean
 */

public class TeachersBo implements Serializable {


    /**
     * createTime : 2017-09-12 10:55:45
     * deleteTime : null
     * fromWhere : 复旦
     * headerUrl : http://imagetest.nos-eastchina1.126.net/1505704043197c1cb95b74d90c5507acde74cc0036750.jpg
     * id : 4
     * level : 5
     * modifyTime : null
     * professional : 1
     * summary : 陈道明
     * teacherName : 陈道明
     * uploadAlbums : [{"createTime":"2017-09-12 10:55:14","deleteTime":"2017-09-14 13:43:39","id":8,"imgType":null,"modifyTime":null,"name":"1505184914420cf3f1bf076ecbd7627fcad8b6a8e6ad7.jpg","status":1,"url":"http://imagetest.nos-eastchina1.126.net/1505184914420cf3f1bf076ecbd7627fcad8b6a8e6ad7.jpg","valid":false,"version":3}]
     * uploadImages : [{"createTime":"2017-09-12 10:55:13","deleteTime":null,"id":7,"imgType":null,"modifyTime":null,"name":"1505184913644cf3f1bf076ecbd7627fcad8b6a8e6ad7.jpg","status":1,"url":"http://imagetest.nos-eastchina1.126.net/1505184913644cf3f1bf076ecbd7627fcad8b6a8e6ad7.jpg","valid":true,"version":1}]
     * valid : true
     * version : 0
     */

    private String createTime;
    private String deleteTime;
    private String fromWhere;
    private String headerUrl;
    private String id;
    private String level;
    private String modifyTime;
    private String professional;
    private String summary;
    private String teacherName;
    private String valid;
    private String version;
    private List<UploadAlbumsBo> uploadAlbums;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String isValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<UploadAlbumsBo> getUploadAlbums() {
        return uploadAlbums;
    }

    public void setUploadAlbums(List<UploadAlbumsBo> uploadAlbums) {
        this.uploadAlbums = uploadAlbums;
    }

    public static class UploadAlbumsBo implements Serializable {
        /**
         * createTime : 2017-09-12 10:55:14
         * deleteTime : 2017-09-14 13:43:39
         * id : 8
         * imgType : null
         * modifyTime : null
         * name : 1505184914420cf3f1bf076ecbd7627fcad8b6a8e6ad7.jpg
         * status : 1
         * url : http://imagetest.nos-eastchina1.126.net/1505184914420cf3f1bf076ecbd7627fcad8b6a8e6ad7.jpg
         * valid : false
         * version : 3
         */

        private String createTime;
        private String deleteTime;
        private String id;
        private String imgType;
        private String modifyTime;
        private String name;
        private String status;
        private String url;
        private String valid;
        private String version;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(String deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgType() {
            return imgType;
        }

        public void setImgType(String imgType) {
            this.imgType = imgType;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String isValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
