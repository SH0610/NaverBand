package com.example.band.src.login;

import com.example.band.src.login.interfaces.LoginActivityView;
import com.example.band.src.login.interfaces.LoginRetrofitInterface;
import com.example.band.src.login.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    // 서버 통신
    void getTest() {
        final LoginRetrofitInterface registerRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        registerRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSuccess(defaultResponse.getMessage());
            }
            //test
            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mLoginActivityView.validateFailure(null);
            }
        });
    }
}