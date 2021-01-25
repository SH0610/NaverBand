package com.example.band.src.splash.models;

import com.google.gson.annotations.SerializedName;

public class SplashResponse {
    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public SplashResponse(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
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
