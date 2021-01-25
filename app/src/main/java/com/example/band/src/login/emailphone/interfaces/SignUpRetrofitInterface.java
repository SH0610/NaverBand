package com.example.band.src.login.emailphone.interfaces;

import com.example.band.src.login.emailphone.models.SignInEmailBody;
import com.example.band.src.login.emailphone.models.SignInEmailResponse;
import com.example.band.src.login.emailphone.models.SignInNumberBody;
import com.example.band.src.login.emailphone.models.SignInNumberResponse;
import com.example.band.src.login.emailphone.models.SignUpEmailBody;
import com.example.band.src.login.emailphone.models.SignUpEmailResponse;
import com.example.band.src.login.emailphone.models.SignUpNumberBody;
import com.example.band.src.login.emailphone.models.SignUpNumberResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    // SingUpNumberResponse는 Response의 타입으로, API가 반환해주는 response값을 parsing 해준다

    // 휴대폰 번호로 회원가입 (클라 -> 서버)
    @POST("/users")
    Call<SignUpNumberResponse> postRegisterNum(@Body SignUpNumberBody params);

    // 이메일로 회원가입 (클라 -> 서버)
    @POST("/users")
    Call<SignUpEmailResponse> postRegisterEmail(@Body SignUpEmailBody params);

    // 휴대폰 번호, 서버로부터 jwt값 발급받기 위해 로그인 보내기 (클라 -> 서버)
    @POST("/login")
    Call<SignInNumberResponse> postSignInNum(@Body SignInNumberBody params);

    // 이메일, 서버로부터 jwt값 발급받기 위해 로그인 보내기 (클라 -> 서버)
    @POST("/login")
    Call<SignInEmailResponse> postSignInEmail(@Body SignInEmailBody params);
}