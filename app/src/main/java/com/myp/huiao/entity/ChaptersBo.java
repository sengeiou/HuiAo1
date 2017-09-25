package com.myp.huiao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliang on 2017/9/22.
 * <p>
 * 课程章节bean
 */

public class ChaptersBo implements Serializable {

    /**
     * id : 3
     * name : 第一章 java基础
     * sections : []
     */

    private String id;
    private String name;
    private List<SectionBO> sections;

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

    public List<SectionBO> getSections() {
        return sections;
    }

    public void setSections(List<SectionBO> sections) {
        this.sections = sections;
    }

    public static class SectionBO {


        /**
         * duration : 60
         * id : null
         * name : 1.3 勇于探索
         */

        private String duration;
        private String id;
        private String name;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

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
    }


}
