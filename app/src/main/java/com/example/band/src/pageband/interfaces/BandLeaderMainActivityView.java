package com.example.band.src.pageband.interfaces;

import com.example.band.src.pageband.models.BandLeader;

public interface BandLeaderMainActivityView {
    void bandsLeaderSuccess(BandLeader bandLeader);

    void bandsLeaderFailure(String message);
}