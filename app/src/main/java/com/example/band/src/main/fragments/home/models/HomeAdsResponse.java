package com.example.band.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class HomeAdsResponse {
//    {
//        "result": {
//        "adsId": 5,
//                "adsMainImg": "https://cookieserver.site/kakao_friends5.jpg",
//                "adsUrl": "https://daum.net"
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "메인 페이지 광고 이미지 랜덤 조회 성공"
//    }
    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("result")
    private Ads result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public HomeAdsResponse(Ads result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public Ads getResult() {
        return result;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
