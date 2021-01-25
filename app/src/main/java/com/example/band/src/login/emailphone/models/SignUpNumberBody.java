package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class SignUpNumberBody {
    // When users sign up with number, Client must send informations like this.
    // 번호로 회원가입 시, 내가 서버에 밑의 정보들을 보내야함
//        {
//        "name" : "옴팡이",
//            "phone" : "010-1111-1111",
//            "profileImg" : "abc/abc.jpg",
//            "birthday" : "1995-12-23",
//            "password" : "abcd1234"
//    }
    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

//    @SerializedName("profileImge")
//    private String profileImge;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("password")
    private String password;

    public SignUpNumberBody(String name, String phone, String birthday, String password) {
        this.name = name;
        this.phone = phone;
//        this.profileImge = profileImge;
        this.birthday = birthday;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

//    public String getProfileImge() {
//        return profileImge;
//    }

    public String getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }
}
