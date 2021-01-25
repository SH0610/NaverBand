package com.example.band.src.login.emailphone;

import com.example.band.src.login.emailphone.interfaces.SignUpActivityView;
import com.example.band.src.login.emailphone.interfaces.SignUpRetrofitInterface;
import com.example.band.src.login.emailphone.models.SignInEmailBody;
import com.example.band.src.login.emailphone.models.SignInEmailResponse;
import com.example.band.src.login.emailphone.models.SignInNumberBody;
import com.example.band.src.login.emailphone.models.SignInNumberResponse;
import com.example.band.src.login.emailphone.models.SignUpEmailBody;
import com.example.band.src.login.emailphone.models.SignUpEmailResponse;
import com.example.band.src.login.emailphone.models.SignUpNumberBody;
import com.example.band.src.login.emailphone.models.SignUpNumberResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class SignUpService {

    private final SignUpActivityView mSignUpActivityView;

    public SignUpService(SignUpActivityView mSignUpActivityView) {
        this.mSignUpActivityView = mSignUpActivityView;
    }

    // 서버 통신
    void postSignUpNumber(String name, String phone, String birthday, String password) {
        final SignUpRetrofitInterface signUpNumberRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpNumberRetrofitInterface.postRegisterNum(new SignUpNumberBody(name, phone, birthday, password)).enqueue(new Callback<SignUpNumberResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<SignUpNumberResponse> call, Response<SignUpNumberResponse> response) {
                final SignUpNumberResponse signUpNumberResponse = response.body();
                if (signUpNumberResponse == null) {
                    mSignUpActivityView.signUpNumberFailure(null);
                    return;
                }
                else if (signUpNumberResponse.getCode() == 100) {
                    mSignUpActivityView.signUpNumberSuccess(signUpNumberResponse.getMessage());
                }
                else {
                    mSignUpActivityView.signUpNumberFailure(signUpNumberResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignUpNumberResponse> call, Throwable t) {
                mSignUpActivityView.signUpNumberFailure(null);
            }

        });
    }

    void postSignUpEmail(String name, String email, String password, String birthday) {
        final SignUpRetrofitInterface signUpEmailRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpEmailRetrofitInterface.postRegisterEmail(new SignUpEmailBody(name, email, password, birthday)).enqueue(new Callback<SignUpEmailResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<SignUpEmailResponse> call, Response<SignUpEmailResponse> response) {
                final SignUpEmailResponse signUpEmailResponse = response.body();
                if (signUpEmailResponse == null) {
                    mSignUpActivityView.signUpEmailFailure(null);
                    return;
                }
                else if (signUpEmailResponse.getCode() == 100) {
                    mSignUpActivityView.signUpEmailSuccess(signUpEmailResponse.getMessage());
                }
                else {
                    mSignUpActivityView.signUpEmailFailure(signUpEmailResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignUpEmailResponse> call, Throwable t) {
                mSignUpActivityView.signUpEmailFailure(null);
            }
        });
    }

    void postSignInNumber(String phone, String password) {
        final SignUpRetrofitInterface signInNumberRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signInNumberRetrofitInterface.postSignInNum(new SignInNumberBody(phone, password)).enqueue(new Callback<SignInNumberResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<SignInNumberResponse> call, Response<SignInNumberResponse> response) {
                final SignInNumberResponse signInNumberResponse = response.body();
                if (signInNumberResponse == null) {
                    mSignUpActivityView.signInNumberFailure("aaaxxx 1");
                    return;
                }
                else if (signInNumberResponse.getCode() == 100) {
                    mSignUpActivityView.signInNumberSuccess(signInNumberResponse.getResult().getJwt());
                }
                else {
                    mSignUpActivityView.signInNumberFailure(signInNumberResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignInNumberResponse> call, Throwable t) {
                mSignUpActivityView.signInNumberFailure("aaaxxx 2");
            }
        });
    }

    void postSignInEmail(String email, String password) {
        final SignUpRetrofitInterface signInEmailRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signInEmailRetrofitInterface.postSignInEmail(new SignInEmailBody(email, password)).enqueue(new Callback<SignInEmailResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<SignInEmailResponse> call, Response<SignInEmailResponse> response) {
                final SignInEmailResponse signInEmailResponse = response.body();
                if (signInEmailResponse == null) {
                    mSignUpActivityView.signInEmailFailure(null);
                    return;
                }
                else if (signInEmailResponse.getCode() == 100) {
                    mSignUpActivityView.signInEmailSuccess(signInEmailResponse.getResult().getJwt());
                }
                else {
                    mSignUpActivityView.signInEmailFailure(signInEmailResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignInEmailResponse> call, Throwable t) {
                mSignUpActivityView.signInEmailFailure(null);
            }
        });
    }
}
