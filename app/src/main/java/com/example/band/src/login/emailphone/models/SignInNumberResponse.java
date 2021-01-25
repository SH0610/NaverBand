package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class SignInNumberResponse {
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

    //    {
//        "result": 24,
//            "isSuccess": true,
//            "code": 100,
//            "message": "유저 아이디 생성 성공"
//    }
}