package com.example.band.src.pageband.article;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.pageband.BandMainActivity;
import com.example.band.src.pageband.article.interfaces.WriteCommentActivityView;

public class ArticleActivity extends BaseActivity implements WriteCommentActivityView {
    // 밴드 메인화면에서 게시물 클릭했을 때 보이는 페이지
    Button mClose;
    ImageView mAddButton, mSendButton;
    TextView mBandName, mWriterName, mWrittenDate, mContent, mMoodCount, mCommentCount;
    TextView mCommentWriter, mCommentContent, mCommentTime;
    RelativeLayout relativeLayout;
    EditText mWriteComment;
    String token, comment;
    int bandId, postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        SharedPreferences sharedPreferences = getSharedPreferences("bandId", MODE_PRIVATE);
        bandId = sharedPreferences.getInt("bandId", bandId);
        System.out.println("ArticleActivity Band Id 가져온건가" + bandId);

        mClose = findViewById(R.id.article_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBandName = findViewById(R.id.article_bandName);
        mWriterName = findViewById(R.id.article_txt_writer);
        mWrittenDate = findViewById(R.id.article_txt_date);
        mContent = findViewById(R.id.article_txt_content);
        mMoodCount = findViewById(R.id.article_txt_moodcount);
        mCommentCount = findViewById(R.id.article_txt_commentcount);

        relativeLayout = findViewById(R.id.articlepage_comment_view);
        mCommentWriter = findViewById(R.id.article_comment_txt_writer);
        mCommentContent = findViewById(R.id.article_comment_content);
        mCommentTime = findViewById(R.id.article_comment_txt_date);

        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("bandName",MODE_PRIVATE);
        SharedPreferences sf2 = getSharedPreferences("article", MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String bandName = sf.getString("bandName","");
        String writerName = sf2.getString("writer", "");
        String writtenDate = sf2.getString("writtenDate", "");
        String articleContent = sf2.getString("articleContent", "");
        int moodCount = sf2.getInt("moodCount", 0);
        int commentCount = sf2.getInt("commentCount", 0);
        postId = sf2.getInt("postId", 0);
        System.out.println("게시물ID" + postId);

        String commentWriter = sf2.getString("commentWriter", "");
        String commentContent = sf2.getString("commentContent", "");
        String commentTime = sf2.getString("commentTime", "");

        System.out.println("밴드이름 : " + bandName);
        System.out.println("글쓴이 : " + writerName);
        System.out.println("글쓴날짜 : " + writtenDate);
        System.out.println("글내용 : " + articleContent);
        System.out.println("표정갯수 : " + moodCount);
        System.out.println("댓글갯수 : " + commentCount);
        System.out.println("댓쓴이 : " + commentWriter);
        System.out.println("댓글내용 : " + commentContent);
        System.out.println("댓쓴시간 : " + commentTime);

        mBandName.setText(bandName);
        mWriterName.setText(writerName);
        mWrittenDate.setText(writtenDate);
        mContent.setText(articleContent);
        mMoodCount.setText(String.valueOf(moodCount));
        mCommentCount.setText(String.valueOf(commentCount));
        mCommentWriter.setText(commentWriter);
        mCommentContent.setText(commentContent);
        mCommentTime.setText(commentTime);

        if (moodCount == 0 & commentCount == 0) {
            relativeLayout.setVisibility(View.GONE);
        }
        else {
            relativeLayout.setVisibility(View.VISIBLE);
        }

        mWriteComment = findViewById(R.id.comment_et_write);
        mAddButton = findViewById(R.id.comment_btn_plus);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("지원하지 않는 기능입니다.");
            }
        });

        mSendButton = findViewById(R.id.comment_btn_send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment = mWriteComment.getText().toString();
                tryPostComment(token, bandId, postId, comment);
                Intent intent = new Intent(getApplicationContext(), BandMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void tryPostComment(String token, int bandId, int postId, String text) {
        System.out.println("tryPostComment");
        final WriteCommentService writeCommentService = new WriteCommentService(this);
        writeCommentService.postComments(token, bandId, postId, text);
    }

    @Override
    public void writeCommentSuccess(String text) {
        System.out.println("writeCommentSuccess");
    }

    @Override
    public void writeCommentFailure(String message) {
        System.out.println("writeCommentFailure");
    }
}