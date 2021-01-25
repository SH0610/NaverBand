package com.example.band.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class BandsInfo {
    @SerializedName("bandId")
    private int bandId;

    @SerializedName("bandName")
    private String bandName;

    @SerializedName("bandImg")
    private String bandImg;

    @SerializedName("userType")
    private String userType;

    public BandsInfo(int bandId, String bandName, String bandImg, String userType) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.bandImg = bandImg;
        this.userType = userType;
    }

    public int getBandId() {
        return bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public String getBandImg() {
        return bandImg;
    }

    public String getUserType() {
        return userType;
    }
}
