package com.example.band.src.login.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
