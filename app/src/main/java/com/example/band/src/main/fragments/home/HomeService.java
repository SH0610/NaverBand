package com.example.band.src.main.fragments.home;

import com.example.band.src.main.fragments.home.interfaces.HomeFragmentView;
import com.example.band.src.main.fragments.home.interfaces.HomeRetrofitInterface;
import com.example.band.src.main.fragments.home.models.HomeAdsResponse;
import com.example.band.src.main.fragments.home.models.HomeBandsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class HomeService {
    private final HomeFragmentView homeFragmentView;

    public HomeService(HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
    }

    // 서버 통신
    public void getAds(final String token) {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.getAds("x-access-token").enqueue(new Callback<HomeAdsResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<HomeAdsResponse> call, Response<HomeAdsResponse> response) {
                final HomeAdsResponse homeAdsResponse = response.body();
                if (homeAdsResponse == null) {
                    homeFragmentView.adsFailure(null);
                    return;
                }
                else if (homeAdsResponse.getCode() == 100) {
                    homeFragmentView.adsSuccess(homeAdsResponse.getResult());
                }
                else {
                    homeFragmentView.adsFailure(homeAdsResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<HomeAdsResponse> call, Throwable t) {
                homeFragmentView.adsFailure(null);
            }
        });
    }

    // 서버 통신
    public void getBands(final String token) {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.getBands("x-access-token").enqueue(new Callback<HomeBandsResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<HomeBandsResponse> call, Response<HomeBandsResponse> response) {
                final HomeBandsResponse homeBandsResponse = response.body();
                if (homeBandsResponse == null) {
                    homeFragmentView.searchFailure(null);
                    return;
                }
                else if (homeBandsResponse.getCode() == 100) {
                    homeFragmentView.searchSuccess(homeBandsResponse.getResult().getBandsInfos());
                }
                else {
                    homeFragmentView.searchFailure(homeBandsResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<HomeBandsResponse> call, Throwable t) {
                homeFragmentView.searchFailure(null);
            }
        });
    }
}
