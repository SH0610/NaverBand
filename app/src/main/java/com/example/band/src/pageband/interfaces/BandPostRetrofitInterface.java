package com.example.band.src.pageband.interfaces;

import com.example.band.src.pageband.models.BandPostResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BandPostRetrofitInterface {
    @GET("/band/{bandId}/posts")
    Call<BandPostResponse> getPostArticle(@Header("x-access-token") String token, @Path("bandId") int bandId, @Query("paging") final int paging);
}
