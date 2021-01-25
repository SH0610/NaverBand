package com.example.band.src.main.fragments.notify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.band.R;

import java.util.ArrayList;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

    private final ArrayList<NotifyItem> mDataList;

    // 리스너
    public interface NotifyRecyclerClickListener {
        void onNotifyItemClicked(int position);
    }

    private NotifyRecyclerClickListener mListener;

    public void setOnClickNotifyRecyclerViewListener (NotifyRecyclerClickListener listener) {
        mListener = listener;
    }

    // 3. 외부에서 데이터를 받을 수 있게 constructor 생성
    // 4. 아이템 정의하기
    public NotifyAdapter(ArrayList<NotifyItem> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 2. 뷰홀더를 만드는 부분 (뷰 얻기)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notify_item_band, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 위에서 리턴해주면, 뷰홀더가 들어와서 데이터를 바인드 해주는 부분.
        // 6. 데이터 세팅
        NotifyItem item = mDataList.get(position); // 아이템 얻어오기

        // holder에 데이터 넣기
        holder.img_band.setImageResource(item.getImg_band());
        holder.txt_name.setText(item.getName());
        holder.txt_message.setText(item.getMessage());
        holder.txt_article.setText(item.getArticle());
        holder.txt_band_name.setText(item.getBandName());
        holder.txt_date.setText(item.getDate());

        if (mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onNotifyItemClicked(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        // 5. 어댑터가 갖는 아이템의 갯수 지정
        return mDataList.size();
    }

    // 1. 제일 먼저 뷰홀더 만들기
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_band;
        TextView txt_name, txt_message, txt_article, txt_band_name, txt_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_band = itemView.findViewById(R.id.notify_img_profile);
            txt_name = itemView.findViewById(R.id.notify_txt_name);
            txt_message = itemView.findViewById(R.id.notify_txt_comment);
            txt_article = itemView.findViewById(R.id.notify_txt_article);
            txt_band_name = itemView.findViewById(R.id.notify_txt_band_name);
            txt_date = itemView.findViewById(R.id.notify_txt_date);
        }
    }
}
