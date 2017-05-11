package com.example.honjaychen.dagger.gallery.gallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by honjayChen on 2017/4/29.
 */

public class Image {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("click")
    @Expose
    private String click;

    @SerializedName("update")
    @Expose
    private String update;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("video_url")
    @Expose
    private String vedioUrl;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getClick() {
        return click;
    }

    public String getUpdate() {
        return update;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}