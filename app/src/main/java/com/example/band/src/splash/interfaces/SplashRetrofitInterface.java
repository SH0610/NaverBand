package com.example.band.src.splash.interfaces;

import com.example.band.src.splash.models.SplashResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SplashRetrofitInterface {
    //    @GET("/test")
    // DefaultResponse는 Response의 타입으로, API가 반환해주는 response값을 parsing 해준다
    @GET("/autologin")
    Call<SplashResponse> getAutoLogin(@Header("x-access-token") String token);
}
