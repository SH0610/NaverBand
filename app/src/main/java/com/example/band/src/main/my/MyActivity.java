package com.example.band.src.main.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.band.R;

public class MyActivity extends AppCompatActivity {

    Toolbar toolbar;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로 가기
        getSupportActionBar().setTitle("나의 활동");

        relativeLayout = findViewById(R.id.myactivity_btn_user);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create:
                // 완료
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        toolbar.setTitle("나의 활동");
////        getSupportActionBar().setTitle(getResources().getString(R.string.tab_title));
//
////        actionBar = getSupportActionBar();
////        actionBar.setTitle("나의 활동");
    }

    @Override
    public void onResume() {
        System.out.println("onResume_myactivity");
        super.onResume();
    }
}