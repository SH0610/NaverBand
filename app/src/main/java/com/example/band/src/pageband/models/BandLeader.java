package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class BandLeader {
    //    {
//        "result": {
//        "bandId": 1,
//                "bandName": "changeBandName",
//                "isOpened": "N",
//                "color": "#1EC900",
//                "bandMemberNo": 11,
//                "bandImg": "www.change.png",
//                "bandIntroduction": "우리 밴드는 멋있다.",
//                "createdAt": "2020년 09월",
//                "restrictMemeberNo": 0,
//                "registerQuestion": "내 나이는 몇살이게?",
//                "registerGender": "NULL",
//                "minAge": 20,
//                "maxAge": 25,
//                "isRegisterNoticed": "N",
//                "isSecretAvailable": "N"
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "밴드 리더 상세 정보 조회 성공"
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

    @SerializedName("isRegisterNoticed")
    private String isRegisterNoticed;

    @SerializedName("isSecretAvailable")
    private String isSecretAvailable;

    public BandLeader(int bandId, String bandName, String isOpened, String color, String bandImg, String createdAt, String isRegisterNoticed, String isSecretAvailable) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.isOpened = isOpened;
        this.color = color;
//        this.bandMemberNo = bandMemberNo;
        this.bandImg = bandImg;
//        this.bandIntroduction = bandIntroduction;
        this.createdAt = createdAt;
//        this.restrictMemeberNo = restrictMemeberNo;
//        this.registerQuestion = registerQuestion;
//        this.registerGender = registerGender;
//        this.minAge = minAge;
//        this.maxAge = maxAge;
        this.isRegisterNoticed = isRegisterNoticed;
        this.isSecretAvailable = isSecretAvailable;
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

//    public String getBandMemberNo() {
//        return bandMemberNo;
//    }

    public String getBandImg() {
        return bandImg;
    }

//    public String getBandIntroduction() {
//        return bandIntroduction;
//    }

    public String getCreatedAt() {
        return createdAt;
    }

//    public int getRestrictMemeberNo() {
//        return restrictMemeberNo;
//    }

//    public String getRegisterQuestion() {
//        return registerQuestion;
//    }
//
//    public String getRegisterGender() {
//        return registerGender;
//    }
//
//    public int getMinAge() {
//        return minAge;
//    }
//
//    public int getMaxAge() {
//        return maxAge;
//    }

    public String getIsRegisterNoticed() {
        return isRegisterNoticed;
    }

    public String getIsSecretAvailable() {
        return isSecretAvailable;
    }
}
