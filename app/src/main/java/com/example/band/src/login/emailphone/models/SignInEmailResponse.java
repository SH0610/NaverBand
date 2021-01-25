package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class SignInEmailResponse {
    // 서버에서 보내준 Response를 변수명에 맞춰 자동으로 parsing
    @SerializedName("result")
    private JWT result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public SignInEmailResponse(JWT result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public JWT getResult() {
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