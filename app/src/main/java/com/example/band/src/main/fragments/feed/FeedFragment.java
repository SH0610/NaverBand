package com.example.band.src.main.fragments.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.band.R;
import com.example.band.src.main.MainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
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
        // hide item (Feed 화면 제외하고 전부 다 숨기기)
        menu.findItem(R.id.menu_home).setVisible(false);
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.menu_chat).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.menu_feed) {
            // 기능 구현
            Toast.makeText(getActivity(), "Feed", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView recyclerView = getActivity().findViewById(R.id.feed_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<FeedItem> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new FeedItem(R.drawable.img_sample, R.drawable.ompangi_origin, "밴드 이름이다.", "땅히짱", "2020년 9월 5일 토요일 오전 12시 30분", "글 내용내용내용 소프트스퀘어드 네이버 밴드 모의 외주 클론 코딩 뀨", "100", "5", "3", "짜증나요"));
        }
        FeedAdapter feedAdapter = new FeedAdapter(dataList);
        recyclerView.setAdapter(feedAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        System.out.println("onResume_feed");
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle("새글 피드");
        }
    }
}