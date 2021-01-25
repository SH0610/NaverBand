package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class SignInNumberBody {
    // 번호로 회원가입 성공 시, 서버에 보내야함
    @SerializedName("phone")
    private String phone;

    @SerializedName("password")
    private String password;

    public SignInNumberBody(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    //    {
//        "phone":"010-2223-7871",
//            "password":"kooki7871"
//    }
}