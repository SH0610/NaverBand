package com.example.band.src.pageband.article.models;

import com.google.gson.annotations.SerializedName;

public class WriteCommentResponse {
//    {
//        "isSuccess": true,
//            "code": 100,
//            "message": "댓글 생성 성공"
//    }
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public WriteCommentResponse(boolean isSuccess, int code, String message) {
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
