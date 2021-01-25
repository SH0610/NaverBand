package com.example.band.src.main.fragments.home.interfaces;

import com.example.band.src.main.fragments.home.models.HomeAdsResponse;
import com.example.band.src.main.fragments.home.models.HomeBandsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HomeRetrofitInterface {
    // HomeAdResponse는 Response의 타입으로, API가 반환해주는 response값을 parsing해준다

    @GET("/ads")
    Call<HomeAdsResponse> getAds(@Header("x-access-token") String token);

    @GET("/bands")
    Call<HomeBandsResponse> getBands(@Header("x-access-token") String token);
}