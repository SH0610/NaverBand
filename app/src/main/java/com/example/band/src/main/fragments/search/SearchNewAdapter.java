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

public class SearchNewAdapter extends RecyclerView.Adapter<SearchNewAdapter.ViewHolder> {

    private final ArrayList<SearchNewItem> mDataList;

    public SearchNewAdapter(ArrayList<SearchNewItem> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchNewItem item = mDataList.get(position);

        holder.img_newband.setImageResource(item.getBandImg());
        holder.txt_newbandname.setText(item.getBandName());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_newband;
        TextView txt_newbandname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_newband = itemView.findViewById(R.id.search_img_newband);
            txt_newbandname = itemView.findViewById(R.id.search_txt_newbandname);
        }
    }
}
