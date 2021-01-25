package com.example.band.src.main.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.example.band.src.main.fragments.home.create.BandCreateActivity;
import com.example.band.src.main.fragments.home.create.BandTypeDialog;
import com.example.band.src.main.fragments.home.interfaces.HomeFragmentView;
import com.example.band.src.main.fragments.home.models.Ads;
import com.example.band.src.main.fragments.home.models.BandsInfo;
import com.example.band.src.main.fragments.home.models.HomeBandsResponse;
import com.example.band.src.main.my.MyActivity;

import java.util.ArrayList;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeFragmentView {

    LinearLayout linearLayout, linearLayout2;
    BandTypeDialog bandTypeDialog;
    TextView home_btn_setList;
    ImageView mAdImg;
    String mAdImgUrl;
    HomeAdapter mHomeAdapter;
    ArrayList<BandsInfo> mBandDataList;
    HomeBandsResponse homeBandsResponse;
    String mBandName;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayout = view.findViewById(R.id.home_item_btn_makeBand);
        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BandCreateActivity.class);
                startActivity(intent);
            }
        });

        linearLayout2 = view.findViewById(R.id.home_item_btn_listOne);
        linearLayout2.setClickable(true);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BandCreateActivity.class);
                startActivity(intent);
            }
        });

        home_btn_setList = view.findViewById(R.id.home_btn_setList);
        home_btn_setList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 목록 관리
                bandTypeDialog = new BandTypeDialog(getActivity());
                bandTypeDialog.show();
            }
        });

        mAdImg = view.findViewById(R.id.home_img_ad);
        mAdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "웹페이지로 이동", Toast.LENGTH_SHORT).show();
            }
        });


        String token = sSharedPreferences.getString(X_ACCESS_TOKEN, "0");
        tryGetAds(token);
//        tryGetBands(token);

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
        // hide item (Home 화면 제외하고 전부 다 숨기기)
        menu.findItem(R.id.menu_feed).setVisible(false);
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.menu_chat).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            // 나의 활동으로 넘기기
            Intent intent = new Intent(getActivity(), MyActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        System.out.println("onResume_home");
        super.onResume();
        // homeFragment에서는 키보드 쓸 일 X
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle("");
        }

        RecyclerView recyclerView = getActivity().findViewById(R.id.home_recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);

        String token = sSharedPreferences.getString(X_ACCESS_TOKEN, "0");
        tryGetBands(token);

        mBandDataList = new ArrayList<>();

//        for (int i = 0; i < homeBandsResponse.getResult().getBandsInfos().size(); i++) {
//
//            dataList.add(new HomeItem(R.drawable.img_sample, "밴드 이름"));
//        }
//
//        dataList.add(new HomeItem(R.drawable.img_sample, "밴드이름"));
//        dataList.add(new HomeItem(R.drawable.img_sample, "밴드"));
//        dataList.add(new HomeItem(R.drawable.img_sample, "밴이름"));
//        dataList.add(new HomeItem(R.drawable.img_sample, "드이름"));

        /*for (int i = 0; i < 12; i++) {
            dataList.add(new HomeItem(R.drawable.img_sample, "밴드이름"));
        }*/
        mHomeAdapter = new HomeAdapter(getActivity(), mBandDataList);
        recyclerView.setAdapter(mHomeAdapter);
//        mHomeAdapter.setOnClickHomeRecyclerViewListener(this);
    }

    private void tryGetAds(String token) {
        final HomeService homeService = new HomeService(this);
        homeService.getAds(token);
    }

    private void tryGetBands(String token) {
        final HomeService homeService = new HomeService(this);
        homeService.getBands(token);
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
        System.out.println("밴드조회");
        mBandDataList.addAll(bandDataList);

        mHomeAdapter.notifyDataSetChanged();
    }


    @Override
    public void searchFailure(String message) {
        System.out.println(message);
    }
}