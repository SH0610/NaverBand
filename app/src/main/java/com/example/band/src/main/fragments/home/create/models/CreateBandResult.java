package com.example.band.src.main.fragments.home.create.models;

import com.google.gson.annotations.SerializedName;

public class CreateBandResult {
    @SerializedName("bandId")
    private int bandId;

    @SerializedName("bandName")
    private String bandName;

    @SerializedName("sinceLeaderDate")
    private String sinceLeaderDate;

    public CreateBandResult(int bandId, String bandName, String sinceLeaderDate) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.sinceLeaderDate = sinceLeaderDate;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getSinceLeaderDate() {
        return sinceLeaderDate;
    }

    public void setSinceLeaderDate(String sinceLeaderDate) {
        this.sinceLeaderDate = sinceLeaderDate;
    }
}
