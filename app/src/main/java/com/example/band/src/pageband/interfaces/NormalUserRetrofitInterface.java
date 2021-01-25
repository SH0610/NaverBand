package com.example.band.src.pageband.interfaces;

import com.example.band.src.pageband.models.NormalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface NormalUserRetrofitInterface {
    // 밴드 들어가기
    @GET("/bandNormal/{bandId}")
    Call<NormalResponse> getNormal(@Header("x-access-token") String token, @Path("bandId") int bandId);
}