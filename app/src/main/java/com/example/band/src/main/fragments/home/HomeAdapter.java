package com.example.band.src.main.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.main.fragments.home.models.BandsInfo;
import com.example.band.src.pageband.BandMainActivity;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final ArrayList<BandsInfo> mDataList;
    private Context mContext;

    public HomeAdapter(Context context, ArrayList<BandsInfo> dataList) {
        this.mContext = context;
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_bandlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        BandsInfo item = mDataList.get(position);

        Glide.with(mContext).load(item.getBandImg()).into(holder.img_band);
        holder.txt_title.setText(item.getBandName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext = view.getContext();

                SharedPreferences sharedPreferences = mContext.getSharedPreferences("bandId", Context.MODE_PRIVATE);

                //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int bandId = mDataList.get(position).getBandId(); // 사용자가 입력한 저장할 데이터
                editor.putInt("bandId", bandId); // key, value를 이용하여 저장하는 형태
                System.out.println("밴드 Adapter 아이디 됐낭" + bandId);

                editor.apply();

                Intent intent = new Intent(mContext, BandMainActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_band;
        TextView txt_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_band = itemView.findViewById(R.id.home_img_band);
            txt_title = itemView.findViewById(R.id.home_txt_title);
        }
    }
}
