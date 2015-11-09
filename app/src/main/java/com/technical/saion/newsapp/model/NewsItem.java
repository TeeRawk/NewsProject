package com.technical.saion.newsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saion on 09.11.2015.
 */
public class NewsItem {
    @SerializedName("HeadLine")
    private String title;
    @SerializedName("Caption")
    private String desc;
    @SerializedName("DateLine")
    private String date;
    @SerializedName("WebURL")
    private String webURL;


    protected class Image {
        @SerializedName("Photo")
        private String imageLink;

        public Image(String imageLink) {
            this.imageLink = imageLink;
        }

        public String getImageLink() {
            return imageLink;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }
    }
    @SerializedName("Image")
    private Image mImage;

    public NewsItem() {
        this.title="No Title";
        mImage=null;
        this.desc="No description";
        this.date="No date";

    }

    public NewsItem(String title, String desc, String date, String imageLink,String webURL) {
        this.title = title;
        mImage =new Image(imageLink) ;
        this.desc = desc;
        this.date = date;
        this.webURL=webURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageLink() {
        return mImage.getImageLink();
    }

    public void setImageLink(String imageLink) {
        mImage.setImageLink(imageLink);
    }


    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
}
