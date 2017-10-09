package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/27.
 * 
 * 今日新知的文章bean
 */

public class ArticleBO implements Serializable{


    /**
     * articleTagName : 科技
     * articleUrl : http://localhost:8080/huiao/message/article/articles/read?id=11
     * imageUrl : http://imagetest.nos-eastchina1.126.net/15064203956115c84546cc35906b81f0defbe20a4219c.jpg
     * pageView : 0
     * publishTime : 2017-09-26 18:08:51
     * title : Google发布Pixel 2，骁龙835加持！
     */

    private String articleTagName;
    private String articleUrl;
    private String imageUrl;
    private String pageView;
    private String publishTime;
    private String title;

    public String getArticleTagName() {
        return articleTagName;
    }

    public void setArticleTagName(String articleTagName) {
        this.articleTagName = articleTagName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
