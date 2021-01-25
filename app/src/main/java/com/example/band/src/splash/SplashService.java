package com.example.band.src.splash;

import com.example.band.src.splash.interfaces.SplashActivityView;
import com.example.band.src.splash.interfaces.SplashRetrofitInterface;
import com.example.band.src.splash.models.SplashResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class SplashService {
    private final SplashActivityView mSplashActivityView;

    public SplashService(SplashActivityView mSplashActivityView) {
        this.mSplashActivityView = mSplashActivityView;
    }

    // 서버 통신
    void getAutoLogin(final String token) {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.getAutoLogin("x-access-token").enqueue(new Callback<SplashResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {
                final SplashResponse splashResponse = response.body();
                if (splashResponse == null) {
                    mSplashActivityView.autoLoginFailure(null);
                    return;
                }
                else if (splashResponse.getCode() == 100) {
                    mSplashActivityView.autoLoginSuccess(splashResponse.getMessage());
                }
                else {
                    mSplashActivityView.autoLoginFailure(splashResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                mSplashActivityView.autoLoginFailure(null);
            }
        });
    }

}
