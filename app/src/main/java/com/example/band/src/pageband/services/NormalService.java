package com.example.band.src.pageband.services;

import com.example.band.src.pageband.interfaces.NormalUserRetrofitInterface;
import com.example.band.src.pageband.interfaces.NormalUserView;
import com.example.band.src.pageband.models.NormalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class NormalService {
    private final NormalUserView mNormalUserView;

    public NormalService(NormalUserView mNormalUserView) {
        this.mNormalUserView = mNormalUserView;
    }

    // 서버 통신
    public void getNormal(final String token, final int bandId) {
        final NormalUserRetrofitInterface normalUserRetrofitInterface = getRetrofit().create(NormalUserRetrofitInterface.class);
        normalUserRetrofitInterface.getNormal("x-access-token", bandId).enqueue(new Callback<NormalResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<NormalResponse> call, Response<NormalResponse> response) {
                final NormalResponse normalResponse = response.body();
                if (normalResponse == null) {
                    mNormalUserView.normalUserFailure(null);
                    return;
                }
                else if (normalResponse.getCode() == 100) {
                    mNormalUserView.normalUserSuccess(normalResponse.getMessage());
                }
                else {
                    mNormalUserView.normalUserFailure(normalResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<NormalResponse> call, Throwable t) {
                mNormalUserView.normalUserFailure(null);
            }
        });
    }
}
