package com.example.band.src.login.emailphone.models;

import com.google.gson.annotations.SerializedName;

public class SignInEmailBody {
    // 이메일로 회원가입 성공 시, 서버에 보내야함
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public SignInEmailBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //    {
//        "email":"email1@gmail.com",
//            "password":"kooki7869"
//    }
}