package com.example.band.src.pageband;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.pageband.article.WriteArticleActivity;
import com.example.band.src.pageband.interfaces.BandEnterActivityView;
import com.example.band.src.pageband.interfaces.BandLeaderMainActivityView;
import com.example.band.src.pageband.interfaces.BandPostView;
import com.example.band.src.pageband.interfaces.NormalUserView;
import com.example.band.src.pageband.models.BandLeader;
import com.example.band.src.pageband.models.BandPostInfo;
import com.example.band.src.pageband.services.BandEnterService;
import com.example.band.src.pageband.services.BandLeaderService;
import com.example.band.src.pageband.services.BandPostService;
import com.example.band.src.pageband.services.NormalService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

public class BandMainActivity extends BaseActivity implements BandLeaderMainActivityView, BandEnterActivityView, NormalUserView, BandPostView {

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;

    private DessertAdapter dessertAdapter;

    private Menu collapsedMenu;
    private boolean appBarExpanded = true;
    private StorageReference mStorageRef;

    ImageView mBandHeader, mBtnChat, mBtnAlbum, mBtnCalendar, mBtnFile, mBtnMember, mBtnSetting;
    FloatingActionButton mFloatingActionButton;

    String mBandName, bandImg;
    int bandId;
    BandLeader bandLeader;
    ArrayList<BandPostInfo> mArticleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_main);

        mArticleList = new ArrayList<>();

        mFloatingActionButton = findViewById(R.id.writing);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteArticleActivity.class);
                startActivity(intent);
                killActivity();
            }
        });

        final Toolbar toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout = findViewById(R.id.appbar);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        mBandHeader = findViewById(R.id.header);

        recyclerView = findViewById(R.id.scrollableview);
        //  Use when your list size is constant for better performance
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        dessertAdapter = new DessertAdapter(getApplicationContext(), mArticleList);
        recyclerView.setAdapter(dessertAdapter);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(BandMainActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });

        mBtnChat = findViewById(R.id.bottom_chat);
        mBtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BandChatActivity.class);
                startActivity(intent);
            }
        });

        mBtnAlbum = findViewById(R.id.bottom_album);
        mBtnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BandAlbumActivity.class);
                startActivity(intent);
            }
        });

        mBtnCalendar = findViewById(R.id.bottom_calendar);
        mBtnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BandCalendarActivity.class);
                startActivity(intent);
            }
        });

        mBtnFile = findViewById(R.id.bottom_file);
        mBtnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BandFileActivity.class);
                startActivity(intent);
            }
        });

        mBtnMember = findViewById(R.id.bottom_member);
        mBtnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BandMemberActivity.class);
                startActivity(intent);
            }
        });

        mBtnSetting = findViewById(R.id.bottom_setting);
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BandSettingActivity.class);
                startActivity(intent);
            }
        });

//        ArrayList<DessertItem> dataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            dataList.add(new DessertItem("땅히짱", "2020년 9월 5일 토요일 오전 12시 30분", "글 내용내용내용 소프트스퀘어드 네이버 밴드 모의 외주 클론 코딩 뀨", "100", "5", "3", "짜증나요"));
//        }
//        DessertAdapter dessertAdapter = new DessertAdapter(this, dataList);
        recyclerView.setAdapter(dessertAdapter);

//        recyclerView.addOnItemTouchListener(new Recycler);


        recyclerView.getAdapter().notifyDataSetChanged();

        String token = sSharedPreferences.getString(X_ACCESS_TOKEN, "0");

        SharedPreferences sharedPreferences = getSharedPreferences("bandId", MODE_PRIVATE);
        bandId = sharedPreferences.getInt("bandId", bandId);
        System.out.println("밴드 Main Id 됐나" + bandId);
        tryGetLeader(token, bandId);
        tryGetEnter(token, bandId);
        tryGetNormal(token, bandId);
        tryGetBandArticle(token, bandId, 0);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Add")
                    .setIcon(R.drawable.icon_appbar_feed)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            //expanded
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_page, menu);
//        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_search:
                Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_write:
                Toast.makeText(this, "글쓰기", Toast.LENGTH_SHORT).show();
                return true;
        }
        if (item.getTitle() == "search") {
            Toast.makeText(this, "clicked search", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle() == "write") {
            Toast.makeText(this, "글쓰기", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void tryGetLeader(String token, int bandId) {
        System.out.println("tryGetLeader");
        final BandLeaderService bandLeaderService = new BandLeaderService(this);
        bandLeaderService.getLeader(token, bandId);
    }

    private void tryGetEnter(String token, int bandId) {
        System.out.println("tryGetEnter");
        final BandEnterService bandEnterService = new BandEnterService(this);
        bandEnterService.getBandsEnter(token, bandId);
    }

    private void tryGetNormal(String token, int bandId) {
        System.out.println("tryGetNormal");
        final NormalService normalService = new NormalService(this);
        normalService.getNormal(token, bandId);
    }

    private void tryGetBandArticle(String token, int bandId, int paging) {
        System.out.println("tryGetBandArticle");
        final BandPostService bandPostService = new BandPostService(this);
        bandPostService.getPostArticle(token, bandId, paging);
    }

    @Override
    public void bandsLeaderSuccess(BandLeader bandLeader) {
        mBandName = bandLeader.getBandName();
        System.out.println("밴드이름 : " + mBandName);
        collapsingToolbar.setTitle(mBandName);
        bandImg = bandLeader.getBandImg();
        Glide.with(getApplicationContext()).load(bandImg).into(mBandHeader);

        SharedPreferences sharedBandName = getSharedPreferences("bandName", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedBandName.edit();
        mBandName = bandLeader.getBandName();

        editor.putString("bandName", mBandName);

        editor.apply();
//
//        SharedPreferences sharedBandId = getSharedPreferences("forWrite", MODE_PRIVATE);
//        SharedPreferences.Editor editor2 = sharedBandId.edit();
//
    }

    @Override
    public void bandsLeaderFailure(String message) {
    }

    @Override
    public void bandEnterSuccess(String text) {
        System.out.println("BandEnterSuccess");
    }

    @Override
    public void bandEnterFailure(String message) {
        System.out.println("BandEnterFailure");
    }

    @Override
    public void normalUserSuccess(String text) {
        System.out.println("normalUserSuccess");
    }

    @Override
    public void normalUserFailure(String message) {
        System.out.println("normalUserFailure");
    }

    @Override
    public void bandPostArticleSuccess(ArrayList<BandPostInfo> bandPostDataList) {
        System.out.println("bandPostArticleSuccess");
        mArticleList.addAll(bandPostDataList);

        dessertAdapter.notifyDataSetChanged();
    }

    @Override
    public void bandPostArticleFailure(String message) {
        System.out.println("bandPostArticleFailure");
    }
    private void killActivity() {
        finish();
    }

}