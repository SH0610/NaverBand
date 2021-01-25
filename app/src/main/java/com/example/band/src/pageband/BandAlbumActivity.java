package com.example.band.src.pageband;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.band.R;
import com.example.band.src.BaseActivity;

public class BandAlbumActivity extends BaseActivity {

    Button mClose;
    TextView mBandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_album);

        mClose = findViewById(R.id.page_album_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBandName = findViewById(R.id.page_album_bandname);

        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("bandName",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String bandName = sf.getString("bandName","");
        mBandName.setText(bandName);
    }
}