package com.example.band.src.pageband.article;

import com.example.band.src.pageband.article.interfaces.WriteArticleActivityView;
import com.example.band.src.pageband.article.interfaces.WriteArticleRetrofitInterface;
import com.example.band.src.pageband.article.models.WriteArticleBody;
import com.example.band.src.pageband.article.models.WriteArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class WriteArticleService {
    private final WriteArticleActivityView mWriteArticleActivityView;

    public WriteArticleService(WriteArticleActivityView mWriteArticleActivityView) {
        this.mWriteArticleActivityView = mWriteArticleActivityView;
    }

    // 서버 통신
    public void postArticles(final String token, final int bandId, final String text) {
        final WriteArticleRetrofitInterface writeArticleRetrofitInterface = getRetrofit().create(WriteArticleRetrofitInterface.class);
        writeArticleRetrofitInterface.postArticle("x-access-token", new WriteArticleBody(bandId, text)).enqueue(new Callback<WriteArticleResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<WriteArticleResponse> call, Response<WriteArticleResponse> response) {
                final WriteArticleResponse writeArticleResponse = response.body();
                if (writeArticleResponse == null) {
                    mWriteArticleActivityView.writeArticleFailure(null);
                    return;
                }
                else if (writeArticleResponse.getCode() == 100) {
                    mWriteArticleActivityView.writeArticleSuccess(writeArticleResponse.getMessage());
                }
                else {
                    System.out.println(writeArticleResponse.getCode());
                    mWriteArticleActivityView.writeArticleFailure(writeArticleResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<WriteArticleResponse> call, Throwable t) {
                mWriteArticleActivityView.writeArticleFailure(null);
            }
        });
    }
}
