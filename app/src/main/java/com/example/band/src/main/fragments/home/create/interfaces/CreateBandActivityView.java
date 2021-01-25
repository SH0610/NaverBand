package com.example.band.src.main.fragments.home.create.interfaces;

import android.net.Uri;

import com.example.band.src.main.fragments.home.create.models.CreateBandResult;

public interface CreateBandActivityView {
    void createBandSuccess(CreateBandResult createBandResult);

    void createBandFailure(String message);

    void upLoadFireBaseSuccess(Uri uri);

    void upLoadFireBaseFailure();
}
