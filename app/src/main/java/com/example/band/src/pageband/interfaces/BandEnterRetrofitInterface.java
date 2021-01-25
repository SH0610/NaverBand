package com.example.band.src.pageband.interfaces;

import com.example.band.src.pageband.models.BandEnterBody;
import com.example.band.src.pageband.models.BandEnterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BandEnterRetrofitInterface {
    // 밴드 들어가기
    @POST("/bandEnter")
    Call<BandEnterResponse> getBandEnter(@Header("x-access-token") String token, @Body BandEnterBody params);
}
