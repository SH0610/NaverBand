package com.example.band.src.main.fragments.home.create;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.main.fragments.home.create.interfaces.CreateBandActivityView;
import com.example.band.src.main.fragments.home.create.models.CreateBandResult;
import com.example.band.src.pageband.BandMainActivity;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

public class BandCreateActivity extends BaseActivity implements CreateBandActivityView {

    private static final String TAG = "ProfileFragment";
    LinearLayout linearLayout;
    BandTypeDialog mBandTypeDialog;
    BandImageDialog mBandImageDialog;
    EditText editText_bandName;
    TextView txt_bandtype, txt_description, txt_modify;
    ImageView img_band;
    final int GET_GALLERY_IMAGE = 200;
    final int REQUEST_IMAGE_CODE = 1001;
    String token = sSharedPreferences.getString(X_ACCESS_TOKEN, "0");
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    private StorageReference mStorageRef;
    Uri mImgUri;

    String mBandName, mIsOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_create);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로 가기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }

        // 밴드 만들기 누르자마자 자동으로 키보드 띄우기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        txt_bandtype = findViewById(R.id.txt_band_type);
        txt_description = findViewById(R.id.txt_band_type_description);
        txt_modify = findViewById(R.id.txt_modify);
        editText_bandName = (EditText) findViewById(R.id.create_name);
        editText_bandName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return true;
            }
        });

        img_band = findViewById(R.id.create_image);
        img_band.setClickable(true);
        img_band.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CODE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        linearLayout = findViewById(R.id.btn_bandtypecontent);
        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        // TODO Auto-generated method stub
        final String [] items = {"비공개 밴드", "공개 밴드"};
        AlertDialog.Builder builder = new AlertDialog.Builder(BandCreateActivity.this);
        builder.setTitle("밴드 타입설정");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
//                Toast.makeText(BandCreate.this, items[which], Toast.LENGTH_SHORT).show();
                if (items[which] == "비공개 밴드") {
                    txt_bandtype.setText("비공개 밴드");
                    txt_description.setText("밴드와 게시글이 공개되지 않습니다.\n초대를 통해서만 가입할 수 있습니다.");
                    txt_modify.setText("변경");
                    mIsOpened = "Y";
                }
                else if (items[which] == "공개 밴드"){
                    txt_bandtype.setText("공개 밴드");
                    txt_description.setText("누구나 밴드를 검색해 찾을 수 있고,\n밴드 소개와 게시글을 볼 수 있습니다.");
                    txt_modify.setText("변경");
                    mIsOpened = "N";
                }
                dialog.dismiss(); // 누르면 바로 닫히는 형태
            }
        });
        return builder.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CODE) {
            Uri image = data.getData();
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅
                    img_band.setImageBitmap(img);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                uploadImage(image);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bandcreate, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create:
//                // 완료
//                Intent intent = new Intent(this, BandMainActivity.class);
                mBandName = editText_bandName.getText().toString();
                tryPostBands(token, mBandName, mImgUri.toString(), mIsOpened);
//                System.out.println(mBandName);
//                System.out.println(mImgUri.toString());
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); // 키보드 내리기
//                startActivity(intent);
//                finish();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        }
        return false;
    }

    public void chooseImage(View view) {
        // 키보드 숨기기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 키보드 숨기기
        System.out.println("createpageonpause");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //찍은 사진의 Uri를 FireBase에 전송하고
    private void uploadImage(Uri imgUri) {
        final BandCreateService bandCreateService = new BandCreateService(this);
        bandCreateService.uploadFileToFireBase(imgUri);
    }
    //그 경로를 받아옵니다.
    @Override
    public void upLoadFireBaseSuccess(Uri uri) {
        mImgUri = uri;
        showCustomToast("firebase uri: " + mImgUri);
//        try {
            //upLoadUri(String.valueOf(mImgUri));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void upLoadFireBaseFailure() {
        showCustomToast("upload image firebase fail");
    }

    private void tryPostBands(String token, String mBandName, String mBandImg, String mIsOpened) {
        final BandCreateService bandCreateService = new BandCreateService(this);
        bandCreateService.createBands(token, mBandName, mBandImg, mIsOpened);
        System.out.println("tryPostBands");
    }

    @Override
    public void createBandSuccess(CreateBandResult createBandResult) {
        // 통신 끝나면 hide!
        hideProgressDialog();
        showCustomToast("생성된 밴드 이름" + createBandResult.getBandName());
        System.out.println("createBandSuccess");

        //SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences sharedPreferences = getSharedPreferences("bandId",MODE_PRIVATE);

        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int bandId = createBandResult.getBandId(); // 사용자가 입력한 저장할 데이터
        editor.putInt("bandId", bandId); // key, value를 이용하여 저장하는 형태
        System.out.println("밴드 Create 아이디 됐낭" + bandId);

        //최종 커밋
        editor.apply();

        // 완료
        Intent intent = new Intent(this, BandMainActivity.class);
        System.out.println(mBandName);
        System.out.println(mImgUri.toString());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); // 키보드 내리기
        startActivity(intent);
        finish();
    }

    @Override
    public void createBandFailure(String message) {
        System.out.println(message);
    }
}