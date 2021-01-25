package com.example.band.src.main.fragments.chat;

public class ChatItem {
    private int profile; // 글 작성자의 프로필 이미지
    private String chatName; // 밴드 이름
    private String chatIntro; // 채팅방 소개

    public ChatItem(int profile, String chatName, String chatIntro) {
        this.profile = profile;
        this.chatName = chatName;
        this.chatIntro = chatIntro;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatIntro() {
        return chatIntro;
    }

    public void setChatIntro(String chatIntro) {
        this.chatIntro = chatIntro;
    }
}
