package com.example.band.src.main.fragments.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.ArrayList;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment implements HomeFragmentView {

    ImageView mAdImg;
    String mAdImgUrl;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        mAdImg = view.findViewById(R.id.chat_img_ad);
        mAdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "웹페이지로 이동", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(this).load(R.drawable.img_sample).into(mAdImg);

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
    public void onStart() {
        super.onStart();
//        RecyclerView recyclerView = getActivity().findViewById(R.id.chat_recyclerview);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        ArrayList<ChatItem> dataList = new ArrayList<>();
//        dataList.add(new ChatItem(R.drawable.img_sample1, "옴팡이", "옴팡이가 좋아"));
//        ChatAdapter chatAdapter = new ChatAdapter(dataList);
//        recyclerView.setAdapter(chatAdapter);
//        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // inflate menu
        inflater.inflate(R.menu.menu_main, menu);
        // hide item (Chat 화면 제외하고 전부 다 숨기기)
        menu.findItem(R.id.menu_home).setVisible(false);
        menu.findItem(R.id.menu_feed).setVisible(false);
        menu.findItem(R.id.menu_notify).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.menu_chat) {
            // 기능 구현
            Toast.makeText(getActivity(), "Chat", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        System.out.println("onResume_chat");
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle("채팅");
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