package com.example.band.src.main.fragments.home;

public class HomeItem {
    private int img_band;
    private String txt_title;

    public HomeItem(int img_band, String txt_title) {
        this.img_band = img_band;
        this.txt_title = txt_title;
    }

    public int getImg_band() {
        return img_band;
    }

    public void setImg_band(int img_band) {
        this.img_band = img_band;
    }

    public String getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(String txt_title) {
        this.txt_title = txt_title;
    }
}
