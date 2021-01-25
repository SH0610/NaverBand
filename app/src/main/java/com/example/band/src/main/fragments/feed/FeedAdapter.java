package com.example.band.src.main.fragments.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.band.R;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final ArrayList<FeedItem> mDataList;

    public FeedAdapter(ArrayList<FeedItem> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item_articles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItem item = mDataList.get(position);

        holder.img_profile.setImageResource(item.getImg_profile());
        holder.img_emoticon.setImageResource(item.getImg_emoticon());
        holder.txt_bandname.setText(item.getBandName());
        holder.txt_writer.setText(item.getWriter());
        holder.txt_date.setText(item.getDate());
        holder.txt_content.setText(item.getContent());
        holder.txt_moodcount.setText(item.getMoodCount());
        holder.txt_commentcount.setText(item.getCommentCount());
        holder.txt_views.setText(item.getViews());
        holder.txt_emoticon.setText(item.getEmoticon());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_profile, img_emoticon;
        TextView txt_bandname, txt_writer, txt_date, txt_content, txt_moodcount, txt_commentcount, txt_views, txt_emoticon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_profile = itemView.findViewById(R.id.feed_img_profile); // 글 작성자의 프로필 이미지
            img_emoticon = itemView.findViewById(R.id.feed_img_emoticon); // 이모티콘 이미지
            txt_bandname = itemView.findViewById(R.id.feed_txt_bandname); // 밴드 이름
            txt_writer = itemView.findViewById(R.id.feed_txt_writer); // 작성자 이름
            txt_date = itemView.findViewById(R.id.feed_txt_date); // 글 작성 시간
            txt_content = itemView.findViewById(R.id.feed_txt_content); // 글 내용
            txt_moodcount = itemView.findViewById(R.id.feed_txt_moodcount); // 표정 갯수
            txt_commentcount = itemView.findViewById(R.id.feed_txt_commentcount); // 댓글 갯수
            txt_views = itemView.findViewById(R.id.feed_txt_views); // 글 조회수
            txt_emoticon = itemView.findViewById(R.id.feed_txt_emoticon); // 표정 이름
        }
    }
}
