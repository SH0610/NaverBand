package com.example.band.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class Ads {
    @SerializedName("adsId")
    private int adsId;

    @SerializedName("adsMainImg")
    private String adsMainImg;

    @SerializedName("adsUrl")
    private String adsUrl;

    public Ads(int adsId, String adsMainImg, String adsUrl) {
        this.adsId = adsId;
        this.adsMainImg = adsMainImg;
        this.adsUrl = adsUrl;
    }

    public int getAdsId() {
        return adsId;
    }

    public String getAdsMainImg() {
        return adsMainImg;
    }

    public String getAdsUrl() {
        return adsUrl;
    }
}
