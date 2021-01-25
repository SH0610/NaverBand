package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class BandEnterResponse {
//    {
//        "isSuccess": true,
//            "code": 100,
//            "message": "밴드 들어가기 성공."
//    }
    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public BandEnterResponse(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
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
