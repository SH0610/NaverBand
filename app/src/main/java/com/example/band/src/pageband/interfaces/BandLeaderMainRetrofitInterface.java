package com.example.band.src.pageband.interfaces;

import com.example.band.src.pageband.models.BandLeaderMainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface BandLeaderMainRetrofitInterface {
    // 밴드 리더 상세 정보 조회
    @GET("/bandLeader/{bandId}")
    Call<BandLeaderMainResponse> getBandLeader(@Header("x-access-token") String token, @Path("bandId") int bandId);
}