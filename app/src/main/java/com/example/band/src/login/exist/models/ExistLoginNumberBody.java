package com.example.band.src.login.exist.models;

import com.google.gson.annotations.SerializedName;

public class ExistLoginNumberBody {
    // 이메일로 로그인 시, 클라 -> 서버 해서 jwt 발급받기 (3)
    @SerializedName("phone")
    private String phone;

    @SerializedName("password")
    private String password;

    public ExistLoginNumberBody(String phone, String password) {
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
