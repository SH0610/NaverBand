package com.example.band.src.pageband.services;

import com.example.band.src.pageband.interfaces.BandLeaderMainActivityView;
import com.example.band.src.pageband.interfaces.BandLeaderMainRetrofitInterface;
import com.example.band.src.pageband.models.BandLeaderMainResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class BandLeaderService {
    private final BandLeaderMainActivityView mBandLeaderMainActivityView;

    public BandLeaderService(BandLeaderMainActivityView mBandLeaderMainActivityView) {
        this.mBandLeaderMainActivityView = mBandLeaderMainActivityView;
    }

    // 서버 통신
    public void getLeader(final String token, final int bandId) {
        final BandLeaderMainRetrofitInterface bandLeaderMainRetrofitInterface = getRetrofit().create(BandLeaderMainRetrofitInterface.class);
        bandLeaderMainRetrofitInterface.getBandLeader("x-access-token", bandId).enqueue(new Callback<BandLeaderMainResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<BandLeaderMainResponse> call, Response<BandLeaderMainResponse> response) {
                final BandLeaderMainResponse bandLeaderMainResponse = response.body();
                if (bandLeaderMainResponse == null) {
                    mBandLeaderMainActivityView.bandsLeaderFailure(null);
                    return;
                }
                else if (bandLeaderMainResponse.getCode() == 100) {
                    mBandLeaderMainActivityView.bandsLeaderSuccess(bandLeaderMainResponse.getResult());
                }
                else {
                    mBandLeaderMainActivityView.bandsLeaderFailure(bandLeaderMainResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BandLeaderMainResponse> call, Throwable t) {
                mBandLeaderMainActivityView.bandsLeaderFailure(null);
            }
        });
    }
}
