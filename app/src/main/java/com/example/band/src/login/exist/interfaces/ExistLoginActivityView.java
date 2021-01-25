package com.example.band.src.login.exist.interfaces;

public interface ExistLoginActivityView {
    void loginNumberSuccess(String jwt);

    void loginNumberFailure(String message);

    void loginEmailSuccess(String jwt);

    void loginEmailFailure(String message);
}
