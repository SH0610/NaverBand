package com.example.band.src.main.fragments.feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.band.R;
import com.example.band.src.pageband.article.WriteArticleActivity;

public class Choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Intent intent = new Intent(this, WriteArticleActivity.class);
        startActivity(intent);
        finish();
    }
}