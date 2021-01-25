package com.example.band.src.main.fragments.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.band.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final ArrayList<ChatItem> mDataList;


    public ChatAdapter(ArrayList<ChatItem> mDataList) {
        this.mDataList = mDataList;
    }


    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        ChatItem item = mDataList.get(position);


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView chat_img_profile;
        TextView chat_txt_name, chat_txt_intro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_img_profile = itemView.findViewById(R.id.chat_img_profile);
            chat_txt_name = itemView.findViewById(R.id.chat_txt_name);
            chat_txt_intro = itemView.findViewById(R.id.chat_txt_intro);
        }
    }
}
