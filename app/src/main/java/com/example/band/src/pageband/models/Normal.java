package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class Normal {
    //    {
//        "result": {
//        "bandId": 67,
//                "bandName": "으알",
//                "isOpened": "N",
//                "color": "#1EC800",
//                "bandMemberNo": 4,
//                "bandImg": "https://firebasestorage.googleapis.com/v0/b/band-b411c.appspot.com/o/images%2F1750?alt=media&token=5e387b7f-31e5-47d9-b76e-9e627b8e461d",
//                "bandIntroduction": "NULL",
//                "createdAt": "2020년 09월"
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "밴드 일반 유저 간단 정보 조회 성공."
//    }

    @SerializedName("bandId")
    private int bandId;

    @SerializedName("bandName")
    private String bandName;

    @SerializedName("isOpened")
    private String isOpened;

    @SerializedName("color")
    private String color;

//    @SerializedName("bandMemberNo")
//    private String bandMemberNo;

    @SerializedName("bandImg")
    private String bandImg;

//    @SerializedName("bandIntroduction")
//    private String bandIntroduction;

    @SerializedName("createdAt")
    private String createdAt;

//    @SerializedName("restrictMemeberNo")
//    private int restrictMemeberNo;
//
//    @SerializedName("registerQuestion")
//    private int registerQuestion;
//
//    @SerializedName("registerGender")
//    private String registerGender;
//
//    @SerializedName("minAge")
//    private int minAge;
//
//    @SerializedName("maxAge")
//    private int maxAge;


    public Normal(int bandId, String bandName, String isOpened, String color, String bandImg, String createdAt) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.isOpened = isOpened;
        this.color = color;
        this.bandImg = bandImg;
        this.createdAt = createdAt;
    }

    public int getBandId() {
        return bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public String getIsOpened() {
        return isOpened;
    }

    public String getColor() {
        return color;
    }

    public String getBandImg() {
        return bandImg;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
