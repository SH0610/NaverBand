package com.example.band.src.pageband.services;

import com.example.band.src.pageband.interfaces.BandEnterActivityView;
import com.example.band.src.pageband.interfaces.BandEnterRetrofitInterface;
import com.example.band.src.pageband.models.BandEnterBody;
import com.example.band.src.pageband.models.BandEnterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class BandEnterService {
    private final BandEnterActivityView mBandEnterActivityView;

    public BandEnterService(BandEnterActivityView mBandEnterActivityView) {
        this.mBandEnterActivityView = mBandEnterActivityView;
    }

    // 서버 통신
    public void getBandsEnter(final String token, final int bandId) {
        final BandEnterRetrofitInterface bandEnterRetrofitInterface = getRetrofit().create(BandEnterRetrofitInterface.class);
        bandEnterRetrofitInterface.getBandEnter("x-access-token", new BandEnterBody(bandId)).enqueue(new Callback<BandEnterResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<BandEnterResponse> call, Response<BandEnterResponse> response) {
                final BandEnterResponse bandEnterResponse = response.body();
                if (bandEnterResponse == null) {
                    mBandEnterActivityView.bandEnterFailure(null);
                    return;
                }
                else if (bandEnterResponse.getCode() == 100) {
                    mBandEnterActivityView.bandEnterSuccess(bandEnterResponse.getMessage());
                }
                else {
                    mBandEnterActivityView.bandEnterFailure(bandEnterResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BandEnterResponse> call, Throwable t) {
                mBandEnterActivityView.bandEnterFailure(null);
            }
        });
    }
}
