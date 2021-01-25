package com.example.band.src.pageband;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.band.R;
import com.example.band.src.BaseActivity;

public class BandChatActivity extends BaseActivity {

    Button mClose, mSetting, mPlus;
    TextView mBandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_chat);

        mClose = findViewById(R.id.page_chat_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSetting = findViewById(R.id.page_chat_setting);
        mPlus = findViewById(R.id.page_chat_plus);

        mBandName = findViewById(R.id.page_chat_bandname);

        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("bandName",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String bandName = sf.getString("bandName","");
        mBandName.setText(bandName);
    }
}