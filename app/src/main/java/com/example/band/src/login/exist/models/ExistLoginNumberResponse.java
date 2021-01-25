package com.example.band.src.login.exist.models;

import com.example.band.src.login.emailphone.models.JWT;
import com.google.gson.annotations.SerializedName;

public class ExistLoginNumberResponse {
    // 서버에서 보내준 Response를 변수명에 맞춰 자동으로 parsing
    @SerializedName("result")
    private JWT result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

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
