package com.shivnandan.duplicatvideoapp;

import java.io.Serializable;
//http://shivnandan123.000webhostapp.com//api.php?All_videos
public class Video implements Serializable {
    private String video_thumbnail_s;
    private String video_title;
    private String  video_type;
    private String video_url;

    public String getVideo_thumbnail_s() {
        return video_thumbnail_s;
    }

    public void setVideo_thumbnail_s(String video_thumbnail_s) {
        this.video_thumbnail_s = video_thumbnail_s;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }



}
