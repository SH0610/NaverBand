package com.example.band.src.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.login.exist.LoginEmailActivity;
import com.example.band.src.login.exist.LoginNumberActivity;
import com.example.band.src.main.MainActivity;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

public class AlreadyActivity extends BaseActivity {

    Button btn_close, btn_help, btn_phone, btn_email, btn_naver, btn_facebook;
    OAuthLogin mOAuthLoginModule;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already);

        btn_close = findViewById(R.id.already_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_phone = findViewById(R.id.already_btn_phone);
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlreadyActivity.this, LoginNumberActivity.class);
                startActivity(intent);
            }
        });

        btn_email = findViewById(R.id.already_btn_email);
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlreadyActivity.this, LoginEmailActivity.class);
                startActivity(intent);
            }
        });

        btn_facebook = findViewById(R.id.already_btn_facebook);
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("지원하지 않는 기능입니다.");
            }
        });

        btn_help = findViewById(R.id.already_btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("지원하지 않는 기능입니다.");
            }
        });

        btn_naver = findViewById(R.id.already_btn_naver);
        btn_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOAuthLoginModule = OAuthLogin.getInstance();
                mOAuthLoginModule.init(
                        AlreadyActivity.this
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

                            Intent intent = new Intent(AlreadyActivity.this, MainActivity.class);
//                            intent.putExtra("state", "launch");
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
                mOAuthLoginModule.startOauthLoginActivity(AlreadyActivity.this, mOAuthLoginHandler);

            }
        });
    }
}