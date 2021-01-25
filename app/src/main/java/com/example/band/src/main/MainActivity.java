package com.example.band.src.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.example.band.R;
import com.example.band.src.main.fragments.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Fragment homeFragment, feedFragment, searchFragment, notifyFragment, chatFragment, moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        getSupportActionBar().setLogo(R.drawable.icon_appbar_bandlogo);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab_feed));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab_search));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab_notify));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab_chat));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab_more));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);

        coordinatorLayout = findViewById(R.id.main_root);

        // TabPagerAdapter 생성
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPagerAdapter);

        // PageChangeListener 생성
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // 최초로 앱 실행 시 홈화면이 뜨기 때문에, 홈 아이콘은 초기 아이콘 색상을 검은색으로 설정
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 뷰페이저에 탭의 포지션에 해당하는 레이아웃 설정
                viewPager.setCurrentItem(tab.getPosition());
                for (int i = 0; i < 6; i++) {
                    if (tab.getPosition() == i) {
                        // 탭이 선택되었을 경우, 아이콘 색상은 검은색
                        tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                for (int i = 0; i < 6; i++) {
                    if (tab.getPosition() == i) {
                        // 탭이 선택되지 않았을 경우, 아이콘 색상은 회색
                        tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id){
//            case android.R.id.home:
//                Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_root, fragment).commit(); // Fragment로 사용할 MainActivity 내의 layout 공간을 선택
    }

    // 프래그먼트마다 액션바 타이틀 다르게 해주기
    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}