package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class JWT {
    @SerializedName("jwt")
    private String jwt;

    public JWT(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}