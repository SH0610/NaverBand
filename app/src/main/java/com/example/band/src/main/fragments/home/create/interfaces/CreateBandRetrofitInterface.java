package com.example.band.src.main.fragments.home.create.interfaces;

import com.example.band.src.main.fragments.home.create.models.CreateBandBody;
import com.example.band.src.main.fragments.home.create.models.CreateBandResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CreateBandRetrofitInterface {
    // CreateBandResponse Response의 타입으로, API가 반환해주는 response값을 parsing 해준다

    // 밴드 생성하기 (클라 -> 서버)
    @POST("/band")
    Call<CreateBandResponse> postCreateBand(@Header("x-access-token") String token, @Body CreateBandBody params);
}
