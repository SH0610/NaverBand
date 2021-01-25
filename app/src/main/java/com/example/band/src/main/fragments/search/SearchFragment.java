package com.example.band.src.main.fragments.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.main.MainActivity;
import com.example.band.src.main.fragments.home.HomeService;
import com.example.band.src.main.fragments.home.interfaces.HomeFragmentView;
import com.example.band.src.main.fragments.home.models.Ads;
import com.example.band.src.main.fragments.home.models.BandsInfo;

import java.util.ArrayList;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements HomeFragmentView {

    ImageView mAdImg;
    String mAdImgUrl;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mAdImg = view.findViewById(R.id.search_img_ad);
        mAdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "웹페이지로 이동", Toast.LENGTH_SHORT).show();
            }
        });

        String token = sSharedPreferences.getString(X_ACCESS_TOKEN, "0");
        tryGetAds(token);

        return view;
    }

    /* Enable options menu in this fragment */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // inflate menu
        inflater.inflate(R.menu.menu_main, menu);
        // hide item (Search 화면은 앱바에 메뉴 없으므로 다 숨기기)
        menu.findItem(R.id.menu_home).setVisible(false);
        menu.findItem(R.id.menu_feed).setVisible(false);
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.menu_chat).setVisible(false);
    }


    @Override
    public void onStart() {
        super.onStart();
        // 첫번째 리사이클러뷰
        RecyclerView recyclerView = getActivity().findViewById(R.id.search_recyclerview_new);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4, RecyclerView.HORIZONTAL,false));

        ArrayList<SearchNewItem> dataList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dataList.add(new SearchNewItem(R.drawable.img_sample, "밴드 이름이다."));
        }
        SearchNewAdapter searchNewAdapter = new SearchNewAdapter(dataList);
        recyclerView.setAdapter(searchNewAdapter);

        // 두번째 리사이클러
        RecyclerView recyclerView1 = getActivity().findViewById(R.id.search_recyclerview_popular);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 4, RecyclerView.HORIZONTAL, false));

        ArrayList<SearchPopularItem> dataList2 = new ArrayList<>();
        dataList2.add(new SearchPopularItem(R.drawable.img_popular1, "그림 그리는 사람들", "우리 같이 그림 그려요!", "234"));
        dataList2.add(new SearchPopularItem(R.drawable.img_popular2, "당신을 위한 감성 다락방", "감성을 공유하는 감성 다락방입니다.", "1952"));
        dataList2.add(new SearchPopularItem(R.drawable.img_popular3, "미드사랑", "미드를 사랑하는 사람들의 공간임다^^", "3,434"));
        dataList2.add(new SearchPopularItem(R.drawable.img_popular4, "바다생활낚시", "전국 낚시 정보를 함께 공유해요", "421"));
//        for (int j = 0; j < 4; j++) {
//            dataList2.add(new SearchPopularItem(R.drawable.img_sample, "밴드 이름", "밴드 소개", "1000"));
//        }
        SearchPopularAdapter searchPopularAdapter = new SearchPopularAdapter(dataList2);
        recyclerView1.setAdapter(searchPopularAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        System.out.println("onResume_search");
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle("찾기");
        }
    }

    private void tryGetAds(String token) {
        final HomeService homeService = new HomeService(this);
        homeService.getAds(token);
    }

    @Override
    public void adsSuccess(Ads ads) {
        mAdImgUrl = ads.getAdsMainImg();
        Glide.with(getActivity()).load(mAdImgUrl).into(mAdImg);
        Log.d("url", mAdImgUrl);
    }

    @Override
    public void adsFailure(String message) {

    }

    @Override
    public void searchSuccess(ArrayList<BandsInfo> bandDataList) {

    }
    

    @Override
    public void searchFailure(String message) {

    }
}