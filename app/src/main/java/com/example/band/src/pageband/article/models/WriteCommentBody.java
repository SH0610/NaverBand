package com.example.band.src.pageband.article.models;

import com.google.gson.annotations.SerializedName;

public class WriteCommentBody {
//    {
//        "bandId" : 73,
//            "postId" : 74,
//            "text" : "Hello"
//    }
    @SerializedName("bandId")
    private int bandId;

    @SerializedName("postId")
    private int postId;

    @SerializedName("text")
    private String text;

    public WriteCommentBody(int bandId, int postId, String text) {
        this.bandId = bandId;
        this.postId = postId;
        this.text = text;
    }

    public int getBandId() {
        return bandId;
    }

    public int getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }
}
