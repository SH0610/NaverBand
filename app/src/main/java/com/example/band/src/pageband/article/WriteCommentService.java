package com.example.band.src.pageband.article;

import com.example.band.src.pageband.article.interfaces.WriteCommentActivityView;
import com.example.band.src.pageband.article.interfaces.WriteCommentRetrofitInterface;
import com.example.band.src.pageband.article.models.WriteCommentBody;
import com.example.band.src.pageband.article.models.WriteCommentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class WriteCommentService {
    private final WriteCommentActivityView mWriteCommentActivityView;

    public WriteCommentService(WriteCommentActivityView mWriteCommentActivityView) {
        this.mWriteCommentActivityView = mWriteCommentActivityView;
    }

    // 서버 통신
    public void postComments(final String token, final int bandId, final int postId, final String text) {
        final WriteCommentRetrofitInterface writeCommentRetrofitInterface = getRetrofit().create(WriteCommentRetrofitInterface.class);
        writeCommentRetrofitInterface.postComment("x-access-token", new WriteCommentBody(bandId, postId, text)).enqueue(new Callback<WriteCommentResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<WriteCommentResponse> call, Response<WriteCommentResponse> response) {
                final WriteCommentResponse writeCommentResponse = response.body();
                if (writeCommentResponse == null) {
                    mWriteCommentActivityView.writeCommentFailure(null);
                    return;
                }
                else if (writeCommentResponse.getCode() == 100) {
                    mWriteCommentActivityView.writeCommentSuccess(writeCommentResponse.getMessage());
                }
                else {
                    System.out.println(writeCommentResponse.getCode());
                    mWriteCommentActivityView.writeCommentFailure(writeCommentResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<WriteCommentResponse> call, Throwable t) {
                mWriteCommentActivityView.writeCommentFailure(null);
            }
        });
    }
}
