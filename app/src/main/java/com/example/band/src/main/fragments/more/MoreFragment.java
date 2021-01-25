package com.example.band.src.main.fragments.more;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.main.MainActivity;
import com.example.band.src.main.fragments.home.HomeService;
import com.example.band.src.main.fragments.home.interfaces.HomeFragmentView;
import com.example.band.src.main.fragments.home.models.Ads;
import com.example.band.src.main.fragments.home.models.BandsInfo;
import com.example.band.src.main.my.MyActivity;

import java.util.ArrayList;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment implements HomeFragmentView {

    LinearLayout btn_notify, btn_invitation, btn_stickershop, btn_setting, btn_game, btn_giftshop, btn_fashion, btn_book;
    RelativeLayout relativeLayout;
    TextView textView;
    ImageView mAdImg;
    String mAdImgUrl;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        textView = view.findViewById(R.id.more_txt_moreSticker);
//        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notSupportedFunction();
//                Toast.makeText(getActivity(), "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        relativeLayout = (RelativeLayout) view.findViewById(R.id.more_layout_user);
        relativeLayout.setClickable(true);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 나의 활동으로 넘기기
                Intent intent = new Intent(getActivity(), MyActivity.class);
                startActivity(intent);
            }
        });

        btn_notify = (LinearLayout) view.findViewById(R.id.btn_notify);


        btn_invitation = (LinearLayout) view.findViewById(R.id.btn_invitation);


        btn_stickershop = (LinearLayout) view.findViewById(R.id.btn_stickershop);
        btn_stickershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notSupportedFunction();
            }
        });

        btn_setting = (LinearLayout) view.findViewById(R.id.btn_setting);


        btn_game = (LinearLayout) view.findViewById(R.id.btn_game);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notSupportedFunction();
            }
        });

        btn_giftshop = (LinearLayout) view.findViewById(R.id.btn_giftshop);
        btn_giftshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notSupportedFunction();
            }
        });

        btn_fashion = (LinearLayout) view.findViewById(R.id.btn_fashion);
        btn_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notSupportedFunction();
            }
        });

        btn_book = (LinearLayout) view.findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notSupportedFunction();
            }
        });

        mAdImg = view.findViewById(R.id.more_img_ad);
        mAdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "웹페이지로 이동", Toast.LENGTH_SHORT).show();
            }
        });

//        Glide.with(this).load(R.drawable.img_sample).into(mAdImg);

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
        // hide item (More 화면은 앱바에 메뉴 없으므로 다 숨기기)
        menu.findItem(R.id.menu_home).setVisible(false);
        menu.findItem(R.id.menu_feed).setVisible(false);
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.menu_chat).setVisible(false);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        // handle menu item clicks
//        int id = item.getItemId();
//
//        if (id == R.id.menu_home) {
//            // 기능 구현
//            Toast.makeText(getActivity(), "Home", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onResume() {
        System.out.println("onResume_more");
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle("더보기");
        }
    }

    // 지원하지 않는 기능입니다.
    public void notSupportedFunction() {
        Toast.makeText(getActivity(), "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show();
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