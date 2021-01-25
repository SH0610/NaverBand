package com.example.band.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.login.interfaces.RegisterActivityView;
import com.example.band.src.login.models.NaverInfo;
import com.example.band.src.main.MainActivity;

public class RegisterActivity extends BaseActivity implements RegisterActivityView {
    TextView mTvName;
    ImageView mIvProfileImg;
    EditText birth, phone_number, password;
    RadioGroup radioGroup;
    String mType, mGender, mName, mProfileImgUrl;
    int mNaverId;
    Button btn_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTvName = findViewById(R.id.register_name_tv);
        mIvProfileImg = findViewById(R.id.register_profile_img);

        birth = findViewById(R.id.register_birth);
        phone_number = findViewById(R.id.register_phonenumber);
        password = findViewById(R.id.register_password);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (radioGroup.getId() == R.id.radiogroup) {
                    switch (checkedId) {
                        case R.id.register_male:
                            mType = "M";
                            showCustomToast("남성입니다.");
                            Toast.makeText(RegisterActivity.this, "남성입니다.", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.register_female:
                            mType = "F";
                            showCustomToast("여성입니다.");
                            break;
                    }
                }
            }
        });

        btn_complete = findViewById(R.id.already_btn_complete);
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(mName, phone_number.getText().toString(), mProfileImgUrl, birth.getText().toString(), mNaverId,mGender);
            }
        });


        if(getIntent() != null) {
            String naverToken = getIntent().getStringExtra("naverToken");
            getNaverId(naverToken);
        }

    }

    // 서비스 객체 생성하고 서비스에 있는 함수 호출
    private void getNaverId(String naverToken){
        RegisterService registerService = new RegisterService(this);
        registerService.getNaverId(naverToken);
    }

    private void register(String name, String phone, String profileImg, String birthday, int naverId, String gender){
        RegisterService registerService = new RegisterService(this);
        registerService.registerUser(name, phone, profileImg, birthday, naverId, gender);
    }

    @Override
    public void naverTokenSuccess(NaverInfo naverInfo) {
        if(naverInfo == null){
            finish();
        }

        mName = naverInfo.getName();
        mProfileImgUrl = naverInfo.getProfileImg();
        mGender =  naverInfo.getGender();
        mNaverId = naverInfo.getId();

        mTvName.setText(mName);
        Glide.with(getApplicationContext()).load(mProfileImgUrl).into(mIvProfileImg);

    }



    @Override
    public void naverTokenFailure(String message) {

    }

    @Override
    public void registerSuccess(String text) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void registerFailure(String message) {

    }
}