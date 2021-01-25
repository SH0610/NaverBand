package com.example.band.src.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.login.emailphone.SignUpActivity;
import com.example.band.src.login.interfaces.LoginActivityView;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    ImageView img_background;
    Button btn_login_number, btn_login_naver, btn_login_facebook, btn_login_already;
    OAuthLogin mOAuthLoginModule;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        img_background = findViewById(R.id.login_img_background);
        Glide.with(this).load(R.raw.background_login).into(img_background);

        btn_login_number = findViewById(R.id.login_btn_number);
        btn_login_naver = findViewById(R.id.login_btn_naver);
        btn_login_facebook = findViewById(R.id.login_btn_facebook);
        btn_login_already = findViewById(R.id.login_btn_already);

        btn_login_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("지원하지 않는 기능입니다.");
            }
        });

        // 이미 회원가입 되어있는 경우
        btn_login_already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AlreadyActivity.class);
                startActivity(intent);
            }
        });

        // 네이버 로그인
        btn_login_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOAuthLoginModule = OAuthLogin.getInstance();
                mOAuthLoginModule.init(
                        LoginActivity.this
                        ,getString(R.string.naver_client_id)
                        ,getString(R.string.naver_client_secret)
                        ,getString(R.string.naver_client_name)
                        //,OAUTH_CALLBACK_INTENT
                        // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
                );

                @SuppressLint("HandlerLeak")
                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {
                        if (success) {
                            // 로그인 성공
                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                            String tokenType = mOAuthLoginModule.getTokenType(mContext);

                            Log.i("LoginData","accessToken : "+ accessToken);
                            Log.i("LoginData","refreshToken : "+ refreshToken);
                            Log.i("LoginData","expiresAt : "+ expiresAt);
                            Log.i("LoginData","tokenType : "+ tokenType);

                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            intent.putExtra("naverToken", accessToken);
                            startActivity(intent);
                            finish();

                        } else {
                            String errorCode = mOAuthLoginModule
                                    .getLastErrorCode(mContext).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                            // 여기서 getApplicationContext()가 아닌, mContext를 사용하면 null Object 가리킨다.
//                            Toast.makeText(getApplicationContext(), "errorCode:" + errorCode
//                                    + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                        }
                    };
                };
                mOAuthLoginModule.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);

            }
        });
    }

    private void tryGetTest() {
        // 통신 시작 전에 로딩 시작
        showProgressDialog();

        final LoginService loginService = new LoginService(this); // this는 자기 자신으로, 넣는 이유는..? => MainActivityView를 implements함
        loginService.getTest();
    }

    // Activity가 interface를 implements 했다는 것은, 그 인터페이스 함수에 대해서 반드시 정의를 내려줘야한다
    // 따라서 MainActivity는 MainActivityView를 정의해줘야한다.
    @Override
    public void validateSuccess(String text) {
        // 통신 끝나면 hide!
        hideProgressDialog();
        showCustomToast(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}