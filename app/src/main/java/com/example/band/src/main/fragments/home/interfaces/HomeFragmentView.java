package com.example.band.src.main.fragments.home.interfaces;

import com.example.band.src.main.fragments.home.models.Ads;
import com.example.band.src.main.fragments.home.models.BandsInfo;

import java.util.ArrayList;

public interface HomeFragmentView {
    void adsSuccess(Ads ads);

    void adsFailure(String message);

    void searchSuccess(ArrayList<BandsInfo> bandDataList);

    void searchFailure(String message);
}
