package com.example.band.src.login.exist.models;

import com.google.gson.annotations.SerializedName;

public class ExistLoginEmailBody {
    // 이메일로 로그인 시, 클라 -> 서버 해서 jwt 발급받기 (3)
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public ExistLoginEmailBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
