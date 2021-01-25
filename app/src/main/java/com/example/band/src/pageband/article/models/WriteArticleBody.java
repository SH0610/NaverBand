package com.example.band.src.pageband.article.models;

import com.google.gson.annotations.SerializedName;

public class WriteArticleBody {
//    {
//        "bandId" : 73,
//            "text" : "Hello"
//    }
    @SerializedName("bandId")
    private int bandId;

    @SerializedName("text")
    private String text;

    public WriteArticleBody(int bandId, String text) {
        this.bandId = bandId;
        this.text = text;
    }

    public int getBandId() {
        return bandId;
    }

    public String getText() {
        return text;
    }
}
