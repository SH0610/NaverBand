package com.example.band.src.login.interfaces;

import com.example.band.src.login.models.SignUpNaverResponse;
import com.example.band.src.login.models.RegisterResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterRetrofitInterface {
    // 네이버로 로그인하면, 회원 정보를 받아온다 (네이버 -> 클라)
    @GET("/users/info")
    Call<SignUpNaverResponse> getNaverId(@Query("naver") final String naverToken);

    // 받아온 회원 정보 + 추가 입력 정보 보내기 (클라 -> 서버)
    @POST("/users")
    Call<RegisterResponse> register(@Body HashMap<String, Object> hashMap);
}
