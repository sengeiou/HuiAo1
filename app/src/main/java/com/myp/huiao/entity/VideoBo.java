package com.myp.huiao.entity;

import com.boredream.bdvideoplayer.bean.IVideoInfo;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/22.
 * <p>
 * 视频bean
 */

public class VideoBo implements Serializable, IVideoInfo {

    /**
     * origUrl : http://vodd2uqc5at.vod.126.net/vodd2uqc5at/832b7fe8-e42a-44f6-ba99-014475e735dd.mp4
     * snapshotUrl : http://vodd2uqc5at.nosdn.127.net/15ba91c1-0c75-45ef-9f09-33ca961597c8.jpg
     */

    private String origUrl;
    private String snapshotUrl;

    public String getOrigUrl() {
        return origUrl;
    }

    public void setOrigUrl(String origUrl) {
        this.origUrl = origUrl;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    @Override
    public String getVideoTitle() {
        return "";
    }

    @Override
    public String getVideoPath() {
        return origUrl;
    }
}
