package com.example.band.src.pageband.article.interfaces;

import com.example.band.src.pageband.article.models.WriteCommentBody;
import com.example.band.src.pageband.article.models.WriteCommentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WriteCommentRetrofitInterface {
    // 밴드 댓글쓰기
    @POST("/comment")
    Call<WriteCommentResponse> postComment(@Header("x-access-token") String token, @Body WriteCommentBody params);
}
