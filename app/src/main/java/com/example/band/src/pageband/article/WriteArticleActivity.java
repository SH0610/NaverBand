package com.example.band.src.pageband.article;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.pageband.BandMainActivity;
import com.example.band.src.pageband.article.interfaces.WriteArticleActivityView;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

public class WriteArticleActivity extends BaseActivity implements WriteArticleActivityView {
    // 글 쓸 때 보이는 페이지

    TextView mBandName, mComplete;
    EditText editText;
    int bandId;
    String mText, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        token = sSharedPreferences.getString(X_ACCESS_TOKEN, "0");

        mBandName = findViewById(R.id.write_bandname);
        editText = findViewById(R.id.write_text);

        SharedPreferences sharedPreferences = getSharedPreferences("bandId", MODE_PRIVATE);
        bandId = sharedPreferences.getInt("bandId", bandId);
        System.out.println("밴드 Main Id 가져온건가" + bandId);

        mComplete = findViewById(R.id.page_write_complete);
        mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText = editText.getText().toString();
                tryPostArticle(token, bandId, mText);
            }
        });

        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("bandName",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String bandName = sf.getString("bandName","");
        mBandName.setText(bandName);
    }

    private void tryPostArticle(String token, int bandId, String text) {
        System.out.println("tryPostArticle");
        final WriteArticleService writeArticleService = new WriteArticleService(this);
        writeArticleService.postArticles(token, bandId, text);
    }

    @Override
    public void writeArticleSuccess(String text) {
        System.out.println("writeArticleSuccess");
        Intent intent = new Intent(getApplicationContext(), BandMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void writeArticleFailure(String message) {
        System.out.println("writeArticleFailure");
    }
}