package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class NormalResponse {
//    {
//        "result": {
//        "bandId": 67,
//                "bandName": "으알",
//                "isOpened": "N",
//                "color": "#1EC800",
//                "bandMemberNo": 4,
//                "bandImg": "https://firebasestorage.googleapis.com/v0/b/band-b411c.appspot.com/o/images%2F1750?alt=media&token=5e387b7f-31e5-47d9-b76e-9e627b8e461d",
//                "bandIntroduction": "NULL",
//                "createdAt": "2020년 09월"
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "밴드 일반 유저 간단 정보 조회 성공."
//    }

    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("result")
    private Normal result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public NormalResponse(Normal result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public Normal getResult() {
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
