package com.example.band.src.main.fragments.home.create.models;

import com.google.gson.annotations.SerializedName;

public class CreateBandResponse {
//    {
//        "result": {
//        "bandId": 7,
//                "bandName": "밴드 E",
//                "sinceLeaderDate": "2020년 09월 10일"
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "유저가 가입한 밴드 조회 성공."
//    }
// 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("result")
    private CreateBandResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public CreateBandResponse(CreateBandResult result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public CreateBandResult getResult() {
        return result;
    }

    public void setResult(CreateBandResult result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
