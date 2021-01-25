package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class SignUpEmailBody {
    // When users sign up with number, Client must send informations like this.
    // 이메일로 회원가입 시, 내가 서버에 밑의 정보들을 보내야함
//    {
//        "name" : "김성원",
//            "email" : "20150220@soongsil.ac.kr",
//            "profileImg" : "abc/abc.jpg",
//            "password" : "abcd1234",
//            "birthday" : "1995-03-23"
//    }
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

//    @SerializedName("profileImge")
//    private String profileImge;

    @SerializedName("password")
    private String password;

    @SerializedName("birthday")
    private String birthday;

    public SignUpEmailBody(String name, String email, String password, String birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }
}
