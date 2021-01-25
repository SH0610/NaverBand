package com.example.band.src.main.fragments.search;

public class SearchPopularItem {
    private int bandImg;
    private String bandName;
    private String bandIntro;
    private String memberCount;

    public SearchPopularItem(int bandImg, String bandName, String bandIntro, String memberCount) {
        this.bandImg = bandImg;
        this.bandName = bandName;
        this.bandIntro = bandIntro;
        this.memberCount = memberCount;
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

    public String getBandIntro() {
        return bandIntro;
    }

    public void setBandIntro(String bandIntro) {
        this.bandIntro = bandIntro;
    }

    public String getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(String memberCount) {
        this.memberCount = memberCount;
    }
}
