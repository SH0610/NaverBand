package com.example.band.src.main.fragments.notify;

public class NotifyItem {

    private int img_band; // 밴드 이미지
    private String name; // 알림 보낸 사람의 이름
    private String message; // 댓글 내용
    private String article; // 글 제목
    private String bandName; // 밴드 이름
    private String date; // 알림 온 시간

    public NotifyItem(int img_band, String name, String message, String article, String bandName, String date) {
        this.img_band = img_band;
        this.name = name;
        this.message = message;
        this.article = article;
        this.bandName = bandName;
        this.date = date;
    }

    public int getImg_band() {
        return img_band;
    }

    public void setImg_band(int img_band) {
        this.img_band = img_band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
