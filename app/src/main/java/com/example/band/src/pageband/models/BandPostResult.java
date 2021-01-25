package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BandPostResult {
    @SerializedName("postInfo")
    private ArrayList<BandPostInfo> bandPostInfos = null;

    public BandPostResult(ArrayList<BandPostInfo> bandPostInfos) {
        this.bandPostInfos = bandPostInfos;
    }

    public ArrayList<BandPostInfo> getBandPostInfos() {
        return bandPostInfos;
    }
}
