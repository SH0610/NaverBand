package com.example.band.src.login.interfaces;

import com.example.band.src.login.models.NaverInfo;

public interface RegisterActivityView {
    void naverTokenSuccess(NaverInfo naverInfo);

    void naverTokenFailure(String message);

    void registerSuccess(String text);

    void registerFailure(String message);
}
