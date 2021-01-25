package com.example.band.src.login;

import com.example.band.src.login.interfaces.RegisterActivityView;
import com.example.band.src.login.interfaces.RegisterRetrofitInterface;
import com.example.band.src.login.models.SignUpNaverResponse;
import com.example.band.src.login.models.RegisterResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class RegisterService {
    private final RegisterActivityView mRegisterActivityView;

    RegisterService(final RegisterActivityView registerActivityView) {
        this.mRegisterActivityView = registerActivityView;
    }

    void getNaverId(String naverToken) {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);

        registerRetrofitInterface.getNaverId(naverToken).enqueue(new Callback<SignUpNaverResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<SignUpNaverResponse> call, Response<SignUpNaverResponse> response) {
                final SignUpNaverResponse signUpNaverResponse = response.body();
                if (signUpNaverResponse == null) {
                    mRegisterActivityView.naverTokenFailure(null);
                    return;
                }else if(signUpNaverResponse.getCode() == 100){
                    mRegisterActivityView.naverTokenSuccess(signUpNaverResponse.getResult());
                }else{
                    mRegisterActivityView.naverTokenFailure(signUpNaverResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignUpNaverResponse> call, Throwable t) {
                mRegisterActivityView.naverTokenFailure(null);
            }
        });
    }

    void registerUser(String name, String phone, String profileImg, String birthday, int naverId, String gender) {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("phone", phone);
        hashMap.put("profileImg", profileImg);
        hashMap.put("birthday", birthday);
        hashMap.put("naverId", naverId);
        hashMap.put("gender", gender);

        registerRetrofitInterface.register(hashMap).enqueue(new Callback<RegisterResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                final RegisterResponse registerResponse = response.body();
                if (registerResponse == null) {
                    mRegisterActivityView.registerFailure(null);
                    return;
                }

                mRegisterActivityView.registerSuccess(registerResponse.getMessage()); // API 통신으로 반환된 response를 activity에 전달
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                mRegisterActivityView.registerFailure(null);
            }
        });
    }
}
