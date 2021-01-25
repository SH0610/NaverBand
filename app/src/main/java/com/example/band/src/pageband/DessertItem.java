package com.example.band.src.pageband;

public class DessertItem {

    private String writer, date, content, moodCount, commentCount, views, emoticon;

    public DessertItem(String writer, String date, String content, String moodCount, String commentCount, String views, String emoticon) {
        this.writer = writer;
        this.date = date;
        this.content = content;
        this.moodCount = moodCount;
        this.commentCount = commentCount;
        this.views = views;
        this.emoticon = emoticon;
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

//
//    public static List<DessertItem> prepareDesserts(String[] writers, String[] date, String[] content, String[] moodCount, String commentCount[], String[] emoticon, String[] views) {
//        List<DessertItem> desserts = new ArrayList<>(writers.length);
//
//        for (int i = 0; i < writers.length; i++) {
//            DessertItem dessert = new DessertItem(writers[i], date[i], content[i], moodCount[i], commentCount[i], emoticon[i], views[i]);
//            desserts.add(dessert);
//        }
//
//        return desserts;
//    }
}
