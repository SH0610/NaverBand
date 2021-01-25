package com.example.band.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class HomeBandsResponse {
//    {
//        "result": {
//        "bandInfo": [
//        {
//            "bandId": 1,
//                "bandName": "밴드 A",
//                "bandImg": "https://dispatch.cdnser.be/wp-content/uploads/2018/12/20181208134050_7.jpg",
//                "userType": "리더"
//        },
//        {
//            "bandId": 2,
//                "bandName": "밴드 B",
//                "bandImg": "https://topclass.chosun.com/news_img/1901/1901_062_1.jpg",
//                "userType": "일반"
//        },
//        {
//            "bandId": 3,
//                "bandName": "밴드 C",
//                "bandImg": "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99E1C4405BAA67762B",
//                "userType": "일반"
//        }
//        ]
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "유저가 가입한 밴드 조회 성공."
//    }
    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("result")
    private BandsResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public HomeBandsResponse(BandsResult result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public BandsResult getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
