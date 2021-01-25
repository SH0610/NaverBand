package com.example.band.src.login.exist.interfaces;

import com.example.band.src.login.exist.models.ExistLoginEmailBody;
import com.example.band.src.login.exist.models.ExistLoginEmailResponse;
import com.example.band.src.login.exist.models.ExistLoginNumberBody;
import com.example.band.src.login.exist.models.ExistLoginNumberResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ExistLoginRetrofitInterface {
    // 휴대폰 번호, 서버로부터 jwt값 발급받기 위해 로그인 보내기 (클라 -> 서버)
    @POST("/login")
    Call<ExistLoginNumberResponse> postLoginNum(@Body ExistLoginNumberBody params);

    // 이메일, 서버로부터 jwt값 발급받기 위해 로그인 보내기 (클라 -> 서버)
    @POST("/login")
    Call<ExistLoginEmailResponse> postLoginEmail(@Body ExistLoginEmailBody params);
}
