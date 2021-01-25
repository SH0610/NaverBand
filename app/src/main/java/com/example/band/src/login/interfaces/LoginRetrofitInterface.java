package com.example.band.src.login.interfaces;

import com.example.band.src.login.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
    //    @GET("/test")
    // DefaultResponse는 Response의 타입으로, API가 반환해주는 response값을 parsing 해준다
    @GET("/test")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params);

    @POST("/test")
    Call<DefaultResponse> getTest(@Body RequestBody params);

}
