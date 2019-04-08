package com.astronout.testmagangdot.model;

import com.google.gson.annotations.SerializedName;

public class Malang {

    @SerializedName("caption")
    private String mCaption;
    @SerializedName("thumbnail")
    private String mPoster;
    @SerializedName("image")
    private String mImage;

    public Malang(String mCaption, String mPoster, String mImage) {
        this.mCaption = mCaption;
        this.mPoster = mPoster;
        this.mImage = mImage;
    }

    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmPoster() {
        return mPoster;
    }

    public void setmPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
