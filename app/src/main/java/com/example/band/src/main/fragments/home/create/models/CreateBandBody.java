package com.example.band.src.main.fragments.home.create.models;

import com.google.gson.annotations.SerializedName;

public class CreateBandBody {
    // When users sign up with number, Client must send informations like this.
    // 밴드 생성 시, 밑의 정보들을 클라 -> 서버로 POST
//    {
//        "bandName":"밴드 E",
//            "bandImg":"www.bestimg.png",
//            "isOpened":"N"
//    }
    @SerializedName("bandName")
    private String bandName;

    @SerializedName("bandImg")
    private String bandImg;

    @SerializedName("isOpened")
    private String isOpened;

    public CreateBandBody(String bandName, String bandImg, String isOpened) {
        this.bandName = bandName;
        this.bandImg = bandImg;
        this.isOpened = isOpened;
    }

    public String getBandName() {
        return bandName;
    }

    public String getBandImg() {
        return bandImg;
    }

    public String isOpened() {
        return isOpened;
    }
}
