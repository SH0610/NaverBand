package com.example.band.src.main.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.band.src.main.fragments.chat.ChatFragment;
import com.example.band.src.main.fragments.feed.FeedFragment;
import com.example.band.src.main.fragments.home.HomeFragment;
import com.example.band.src.main.fragments.more.MoreFragment;
import com.example.band.src.main.fragments.notify.NotifyFragment;
import com.example.band.src.main.fragments.search.SearchFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(@NonNull FragmentManager fm, int tabCount) {
//        super(fm); 처음 실행 시 프래그먼트 2개 호출하고 그 이후로는 1개씩.. 이상해 (deprecated!)
        //
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Tab의 position에 따라 화면 변환해주기
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                FeedFragment feedFragment = new FeedFragment();
                return feedFragment;
            case 2:
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;
            case 3:
                NotifyFragment notifyFragment = new NotifyFragment();
                return notifyFragment;
            case 4:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 5:
                MoreFragment moreFragment = new MoreFragment();
                return moreFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
