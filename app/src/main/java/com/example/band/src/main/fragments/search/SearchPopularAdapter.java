package com.example.band.src.main.fragments.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.band.R;

import java.util.ArrayList;

public class SearchPopularAdapter extends RecyclerView.Adapter<SearchPopularAdapter.ViewHolder> {

    private final ArrayList<SearchPopularItem> mDataList;

    public SearchPopularAdapter(ArrayList<SearchPopularItem> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_popular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchPopularItem item = mDataList.get(position);

        holder.img_popularband.setImageResource(item.getBandImg());
        holder.txt_popularbandname.setText(item.getBandName());
        holder.txt_popularbandintros.setText(item.getBandIntro());
        holder.txt_popularbandmember.setText(item.getMemberCount());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_popularband;
        TextView txt_popularbandname, txt_popularbandintros, txt_popularbandmember;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_popularband = itemView.findViewById(R.id.search_img_popularband);
            txt_popularbandname = itemView.findViewById(R.id.search_txt_popularbandname);
            txt_popularbandintros = itemView.findViewById(R.id.search_txt_popularbandintro);
            txt_popularbandmember = itemView.findViewById(R.id.search_txt_popularmembercount);
        }
    }
}
