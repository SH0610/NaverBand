package com.example.band.src.main.fragments.notify;

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
 * Use the {@link NotifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyFragment extends Fragment implements NotifyAdapter.NotifyRecyclerClickListener {

    public static NotifyFragment newInstance() {
        return new NotifyFragment();
    }

    public NotifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notify, container, false);

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
        // hide item (Notify 화면 제외하고 전부 다 숨기기)
        menu.findItem(R.id.menu_home).setVisible(false);
        menu.findItem(R.id.menu_feed).setVisible(false);
        menu.findItem(R.id.menu_chat).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.menu_notify) {
            // 기능 구현
            Toast.makeText(getActivity(), "Notify", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView recyclerView = getActivity().findViewById(R.id.notify_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);

        ArrayList<NotifyItem> dataList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            dataList.add(new NotifyItem(R.drawable.img_sample, "상희", "댓글 내용", "글 제목", "밴드 이름", "9월 4일"));
        }
        NotifyAdapter notifyAdapter = new NotifyAdapter(dataList);
        recyclerView.setAdapter(notifyAdapter);
        notifyAdapter.setOnClickNotifyRecyclerViewListener(this);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        System.out.println("onResume_notify");
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle("새소식");
        }
    }

    @Override
    public void onNotifyItemClicked(int position) {
        Toast.makeText(getActivity(), "Notify Item Click", Toast.LENGTH_SHORT).show();
    }
}