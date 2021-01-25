package com.example.band.src.pageband;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.band.R;
import com.example.band.src.pageband.article.ArticleActivity;
import com.example.band.src.pageband.models.BandPostInfo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.ViewHolder> {

    private final ArrayList<BandPostInfo> mDataList;
    private Context mContext;

    public DessertAdapter(Context context, ArrayList<BandPostInfo> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_dessert, parent, false);
        return new DessertAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        BandPostInfo item = mDataList.get(position);

        Glide.with(mContext).load(item.getUserProfile());
        holder.mWriter.setText(item.getUserName());
        holder.mDate.setText(item.getPostCreatedAt());
        holder.mContent.setText(String.valueOf(item.getPostContent()));
        holder.mMoodCount.setText(String.valueOf(item.getNumOfExpression()));
        holder.mCommentCount.setText(String.valueOf(item.getNumOfComment()));
        holder.mViews.setText(String.valueOf(item.getNumOfView()));
//        holder.mEmoticon.setText(String.valueOf(item.getNumOfExpression()));
        holder.mCommentWriter.setText(item.getCommentUserName());
        holder.mCommentContent.setText(item.getCommentContent());
        holder.mCommentDate.setText(item.getCommentCreatedAt());

    if (item.getNumOfComment() != 0) {
        holder.relativeLayout.setVisibility(View.VISIBLE);
    }
    else {
        holder.relativeLayout.setVisibility(View.GONE);
    }

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mContext = view.getContext();

            SharedPreferences sharedPreferences = mContext.getSharedPreferences("article", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            int postId = mDataList.get(position).getPostId();

            editor.putInt("postId", postId);

            // 글 쓴 사람, 글 쓴 날짜, 글 내용, 표정 갯수, 댓글 갯수
            String writer = mDataList.get(position).getUserName();
            String writtenDate = mDataList.get(position).getPostCreatedAt();
            String articleContent = mDataList.get(position).getPostContent();
            int moodCount = mDataList.get(position).getNumOfExpression();
            int commentCount = mDataList.get(position).getNumOfComment();

            // 댓글 쓴 사람, 댓글 내용, 댓글 쓴 시간
            String commentWriter = mDataList.get(position).getCommentUserName();
            String commentContent = mDataList.get(position).getCommentContent();
            String commentTime = mDataList.get(position).getCommentCreatedAt();

            editor.putString("writer", writer);
            editor.putString("writtenDate", writtenDate);
            editor.putString("articleContent", articleContent);
            editor.putInt("moodCount", moodCount);
            editor.putInt("commentCount", commentCount);

            editor.putString("commentWriter", commentWriter);
            editor.putString("commentContent", commentContent);
            editor.putString("commentTime", commentTime);

            System.out.println("게시글 ID 됐을까여" + postId);

            editor.apply();

            Intent intent = new Intent(mContext, ArticleActivity.class);
            mContext.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        CircleImageView circleImageView;
        TextView mWriter, mDate, mContent, mMoodCount, mCommentCount, mViews, mEmoticon, mCommentWriter, mCommentContent, mCommentDate;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.comment_view);
            circleImageView = itemView.findViewById(R.id.page_profile);
            mWriter = itemView.findViewById(R.id.page_txt_writer);
            mDate = itemView.findViewById(R.id.page_txt_date);
            mContent = itemView.findViewById(R.id.page_txt_content);
            mMoodCount = itemView.findViewById(R.id.page_txt_moodcount);
            mCommentCount = itemView.findViewById(R.id.page_txt_commentcount);
            mViews = itemView.findViewById(R.id.page_txt_views);
            mEmoticon = itemView.findViewById(R.id.page_txt_emoticon);
            mCommentWriter = itemView.findViewById(R.id.comment_txt_writer);
            mCommentContent = itemView.findViewById(R.id.comment_content);
            mCommentDate = itemView.findViewById(R.id.comment_txt_date);
        }
    }
}
