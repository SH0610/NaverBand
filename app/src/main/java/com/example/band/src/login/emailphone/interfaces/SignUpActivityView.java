package com.example.band.src.login.emailphone.interfaces;

public interface SignUpActivityView {
    void signUpNumberSuccess(String text);

    void signUpNumberFailure(String message);

    void signUpEmailSuccess(String text);

    void signUpEmailFailure(String message);

    void signInNumberSuccess(String jwt);

    void signInNumberFailure(String message);

    void signInEmailSuccess(String jwt);

    void signInEmailFailure(String message);
}