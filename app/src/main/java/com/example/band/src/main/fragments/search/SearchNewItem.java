package com.example.band.src.main.fragments.search;

public class SearchNewItem {
    private int bandImg;
    private String bandName;

    public SearchNewItem(int bandImg, String bandName) {
        this.bandImg = bandImg;
        this.bandName = bandName;
    }

    public int getBandImg() {
        return bandImg;
    }

    public void setBandImg(int bandImg) {
        this.bandImg = bandImg;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
