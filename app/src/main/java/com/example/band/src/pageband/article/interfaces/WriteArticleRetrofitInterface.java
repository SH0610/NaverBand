package com.example.band.src.pageband.article.interfaces;

import com.example.band.src.pageband.article.models.WriteArticleBody;
import com.example.band.src.pageband.article.models.WriteArticleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WriteArticleRetrofitInterface {
    // 밴드 글쓰기
    @POST("/post")
    Call<WriteArticleResponse> postArticle(@Header("x-access-token") String token, @Body WriteArticleBody params);
}
