package com.example.band.src.main.fragments.feed;

public class FeedItem {

    private int img_profile; // 글 작성자의 프로필 이미지
    private int img_emoticon; // 이모티콘 표정 이미지
    private String bandName; // 밴드 이름
    private String writer; // 글 작성자
    private String date; // 글 작성 시간
    private String content; // 글 내용
    private String moodCount; // 표정 갯수
    private String commentCount; // 댓글 갯수
    private String views; // 글 조회수
    private String emoticon; // 표정 이름

    public FeedItem(int img_profile, int img_emoticon, String bandName, String writer, String date, String content, String moodCount, String commentCount, String views, String emoticon) {
        this.img_profile = img_profile;
        this.img_emoticon = img_emoticon;
        this.bandName = bandName;
        this.writer = writer;
        this.date = date;
        this.content = content;
        this.moodCount = moodCount;
        this.commentCount = commentCount;
        this.views = views;
        this.emoticon = emoticon;
    }

    public int getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(int img_profile) {
        this.img_profile = img_profile;
    }

    public int getImg_emoticon() {
        return img_emoticon;
    }

    public void setImg_emoticon(int img_emoticon) {
        this.img_emoticon = img_emoticon;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoodCount() {
        return moodCount;
    }

    public void setMoodCount(String moodCount) {
        this.moodCount = moodCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getEmoticon() {
        return emoticon;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }
}
