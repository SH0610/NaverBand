package com.example.band.src.main.fragments.home.create;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.band.src.main.fragments.home.create.interfaces.CreateBandActivityView;
import com.example.band.src.main.fragments.home.create.interfaces.CreateBandRetrofitInterface;
import com.example.band.src.main.fragments.home.create.models.CreateBandBody;
import com.example.band.src.main.fragments.home.create.models.CreateBandResponse;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.band.src.ApplicationClass.getRetrofit;

public class BandCreateService {
    private final CreateBandActivityView mCreateBandActivityView;
    Context mContext;

    public BandCreateService(CreateBandActivityView mCreateBandActivityView) {
        this.mCreateBandActivityView = mCreateBandActivityView;
    }

    // 서버 통신
    public void createBands(final String token, String bandName, String bandImg, String isOpened) {
        final CreateBandRetrofitInterface createBandRetrofitInterface = getRetrofit().create(CreateBandRetrofitInterface.class);
        createBandRetrofitInterface.postCreateBand("x-access-token", new CreateBandBody(bandName, bandImg, isOpened)).enqueue(new Callback<CreateBandResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<CreateBandResponse> call, Response<CreateBandResponse> response) {
                final CreateBandResponse createBandResponse = response.body();
                if (createBandResponse == null) {
                    mCreateBandActivityView.createBandFailure(null);
                    return;
                }
                else if (createBandResponse.getCode() == 100) {
                    mCreateBandActivityView.createBandSuccess(createBandResponse.getResult());
                }
                else {
                    mCreateBandActivityView.createBandFailure(createBandResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CreateBandResponse> call, Throwable t) {
                mCreateBandActivityView.createBandFailure(null);
            }
        });
    }

    public void uploadFileToFireBase(Uri imgUri) {
        final ProgressDialog progressDialog = new ProgressDialog((BandCreateActivity) mCreateBandActivityView);
        progressDialog.setTitle("Uploading..");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();

        final StorageReference storageRef = storage.getReferenceFromUrl("gs://band-b411c.appspot.com/")
                .child("images/" + imgUri.getLastPathSegment());

        UploadTask uploadTask = storageRef.putFile(imgUri);
        storageRef.putFile(imgUri)
                //성공시
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                    }
                })
                //실패시
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        mCreateBandActivityView.upLoadFireBaseFailure();
                    }
                })
                //진행중
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests")
                        double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                    }
                });
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return storageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();  // downloadUri -> 이게 업로드 완료된  url임
                    progressDialog.dismiss();
                    mCreateBandActivityView.upLoadFireBaseSuccess(downloadUri);
                } else {
                    progressDialog.dismiss();
                    mCreateBandActivityView.upLoadFireBaseFailure();
                }
            }
        });
    }
}
