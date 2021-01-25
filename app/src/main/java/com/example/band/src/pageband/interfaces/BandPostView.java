package com.example.band.src.pageband.interfaces;

import com.example.band.src.pageband.models.BandPostInfo;

import java.util.ArrayList;

public interface BandPostView {
    void bandPostArticleSuccess(ArrayList<BandPostInfo> bandPostDataList);

    void bandPostArticleFailure(String message);
}
