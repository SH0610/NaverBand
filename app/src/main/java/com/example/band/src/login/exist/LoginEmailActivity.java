package com.example.band.src.login.exist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.login.exist.interfaces.ExistLoginActivityView;
import com.example.band.src.main.MainActivity;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

public class LoginEmailActivity extends BaseActivity implements ExistLoginActivityView {

    Button close, help, ok;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        email = findViewById(R.id.login_email_email_et);
        password = findViewById(R.id.login_email_pw_et);

        close = findViewById(R.id.login_email_close_btn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        help = findViewById(R.id.login_email_help_btn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("지원하지 않는 기능입니다.");
            }
        });

        ok = findViewById(R.id.login_email_ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이메일 로그인
                tryPostLoginEmail(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void tryPostLoginEmail(String email, String password) {
        showProgressDialog();
        System.out.println("aaaxxx : " + email);
        System.out.println("aaaxxx : " + password);

        final ExistLoginService existLoginService = new ExistLoginService(this);
        existLoginService.postLoginEmail(email, password);
    }

    @Override
    public void loginNumberSuccess(String jwt) {

    }

    @Override
    public void loginNumberFailure(String message) {

    }

    @Override
    public void loginEmailSuccess(String jwt) {
        hideProgressDialog();
        System.out.println("로그인.이메일 jwt 발급 성공~");
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(X_ACCESS_TOKEN, jwt);
        editor.apply();
        System.out.println(jwt);
        showCustomToast("로그인.이메일 jwt 발급 성공~");

        Intent intent = new Intent(LoginEmailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginEmailFailure(String message) {

    }
}