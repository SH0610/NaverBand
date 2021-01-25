package com.example.band.src.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.band.src.BaseActivity;
import com.example.band.src.login.LoginActivity;
import com.example.band.src.main.MainActivity;
import com.example.band.src.splash.interfaces.SplashActivityView;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity implements SplashActivityView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String token = sSharedPreferences.getString(X_ACCESS_TOKEN, "NoJWT");
        System.out.println(token);

        // Jwt 값이 존재하지 않는다면, 로그인 창 (회원가입 창)으로 가기
        if (token == "NoJWT") {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else { // Jwt 값이 존재한다면, 자동 로그인. 바로 메인으로 가기
            tryGetAutoLogin(token);
        }
    }

    private void tryGetAutoLogin(String token) {
        final SplashService splashService = new SplashService(this);
        splashService.getAutoLogin(token);
    }

    @Override
    public void autoLoginSuccess(String text) {
        showCustomToast("자동 로그인 성공하였습니다.");
        System.out.println("자동 로그인 성공");

        // Splash 다음에 넘어갈 화면
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void autoLoginFailure(String message) {

    }
}