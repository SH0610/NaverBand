package com.example.band.src.login.models;

import com.google.gson.annotations.SerializedName;

public class NaverInfo {
    @SerializedName("profileImg")
    private String profileImg;

    @SerializedName("gender")
    private String gender;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public String getProfileImg() {
        return profileImg;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
