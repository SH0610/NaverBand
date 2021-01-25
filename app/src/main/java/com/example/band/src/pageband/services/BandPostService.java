package com.example.band.src.pageband.services;

import com.example.band.src.pageband.interfaces.BandPostRetrofitInterface;
import com.example.band.src.pageband.interfaces.BandPostView;
import com.example.band.src.pageband.models.BandPostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class BandPostService {
    private final BandPostView mBandPostView;

    public BandPostService(BandPostView mBandPostView) {
        this.mBandPostView = mBandPostView;
    }

    // 서버 통신
    public void getPostArticle(final String token, final int bandId, final int paging) {
        final BandPostRetrofitInterface bandPostRetrofitInterface = getRetrofit().create(BandPostRetrofitInterface.class);
        bandPostRetrofitInterface.getPostArticle("x-access-token", bandId, paging).enqueue(new Callback<BandPostResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<BandPostResponse> call, Response<BandPostResponse> response) {
                final BandPostResponse bandPostResponse = response.body();
                if (bandPostResponse == null) {
                    mBandPostView.bandPostArticleFailure(null);
                    return;
                }
                else if (bandPostResponse.getCode() == 100) {
                    mBandPostView.bandPostArticleSuccess(bandPostResponse.getResult().getBandPostInfos());
                }
                else if (bandPostResponse.getCode() == 101) {
                    mBandPostView.bandPostArticleFailure(bandPostResponse.getMessage());
                    System.out.println("게시물 없음.");
                }
                else {
                    mBandPostView.bandPostArticleFailure(bandPostResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BandPostResponse> call, Throwable t) {
                mBandPostView.bandPostArticleFailure(null);
            }
        });
    }
}
