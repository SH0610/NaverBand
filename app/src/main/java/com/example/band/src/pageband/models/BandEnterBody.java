package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class BandEnterBody {
//    {
//        "bandId":2
//    }
    @SerializedName("bandId")
    private int bandId;

    public BandEnterBody(int bandId) {
        this.bandId = bandId;
    }

    public int getBandId() {
        return bandId;
    }
}
