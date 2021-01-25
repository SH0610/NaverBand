package com.example.band.src.login.exist;

import com.example.band.src.login.exist.interfaces.ExistLoginActivityView;
import com.example.band.src.login.exist.interfaces.ExistLoginRetrofitInterface;
import com.example.band.src.login.exist.models.ExistLoginEmailBody;
import com.example.band.src.login.exist.models.ExistLoginEmailResponse;
import com.example.band.src.login.exist.models.ExistLoginNumberBody;
import com.example.band.src.login.exist.models.ExistLoginNumberResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class ExistLoginService {
    private final ExistLoginActivityView mExistLoginActivityView;

    public ExistLoginService(ExistLoginActivityView mExistLoginActivityView) {
        this.mExistLoginActivityView = mExistLoginActivityView;
    }

    void postLoginNumber(String phone, String password) {
        final ExistLoginRetrofitInterface existLoginRetrofitInterface = getRetrofit().create(ExistLoginRetrofitInterface.class);
        existLoginRetrofitInterface.postLoginNum(new ExistLoginNumberBody(phone, password)).enqueue(new Callback<ExistLoginNumberResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<ExistLoginNumberResponse> call, Response<ExistLoginNumberResponse> response) {
                final ExistLoginNumberResponse existLoginNumberResponse = response.body();
                if (existLoginNumberResponse == null) {
                    mExistLoginActivityView.loginNumberFailure("aaaxxx 1");
                    return;
                }
                else if (existLoginNumberResponse.getCode() == 100) {
                    mExistLoginActivityView.loginNumberSuccess(existLoginNumberResponse.getResult().getJwt());
                }
                else {
                    mExistLoginActivityView.loginNumberFailure(existLoginNumberResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ExistLoginNumberResponse> call, Throwable t) {
                mExistLoginActivityView.loginNumberFailure("aaaxxx 2");
            }
        });
    }

    void postLoginEmail(String email, String password) {
        final ExistLoginRetrofitInterface existLoginEmailRetrofitInterface = getRetrofit().create(ExistLoginRetrofitInterface.class);
        existLoginEmailRetrofitInterface.postLoginEmail(new ExistLoginEmailBody(email, password)).enqueue(new Callback<ExistLoginEmailResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<ExistLoginEmailResponse> call, Response<ExistLoginEmailResponse> response) {
                final ExistLoginEmailResponse existLoginEmailResponse = response.body();
                if (existLoginEmailResponse == null) {
                    mExistLoginActivityView.loginEmailFailure("aaaxxx 1");
                    return;
                }
                else if (existLoginEmailResponse.getCode() == 100) {
                    mExistLoginActivityView.loginEmailSuccess(existLoginEmailResponse.getResult().getJwt());
                }
                else {
                    mExistLoginActivityView.loginEmailFailure(existLoginEmailResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ExistLoginEmailResponse> call, Throwable t) {
                mExistLoginActivityView.loginEmailFailure("aaaxxx 2");
            }
        });
    }
}
