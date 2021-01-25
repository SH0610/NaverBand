package com.example.band.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BandsResult {
    @SerializedName("bandInfo")
    private ArrayList<BandsInfo> bandsInfos = null;

    public BandsResult(ArrayList<BandsInfo> bandsInfos) {
        this.bandsInfos = bandsInfos;
    }

    public ArrayList<BandsInfo> getBandsInfos() {
        return bandsInfos;
    }
}
