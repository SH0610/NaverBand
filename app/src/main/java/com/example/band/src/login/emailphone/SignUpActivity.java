package com.example.band.src.login.emailphone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.band.R;
import com.example.band.src.BaseActivity;
import com.example.band.src.login.emailphone.interfaces.SignUpActivityView;
import com.example.band.src.main.MainActivity;

import java.util.regex.Pattern;

import static com.example.band.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.band.src.ApplicationClass.sSharedPreferences;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    TextView txt_signnum_number;
    EditText et_phone, et_password, et_name, et_birth;
    Button btn_email, btn_phone, btn_close, btn_next;
    boolean check_phone, check_password, check_birth;

//    int y, m, d;
    String phone, password, birth;
//    ReturnDate returnDate;
//    String year, month, day;
//    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_phone = findViewById(R.id.signnum_number_et); // 휴대폰 번호 또는 이메일
        et_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        et_password = findViewById(R.id.signnum_pw_et); // 비밀번호
        et_name = findViewById(R.id.signnum_name_et); // 이름
        et_birth = findViewById(R.id.signnum_birth_et); // 생년월일

//        returnDate = new ReturnDate();
//        year = returnDate.returnYear();
//        month = returnDate.returnMonth();
//        day = returnDate.returnDay();
//
//        et_birth.setText(year+ "0" + month + "-" + day);
//        datePickerDialog = new DatePickerDialog(this, listener, 2020, 9, 17);
//        // 날짜를 출력하는 editText 오늘 날짜 설정.
//        Calendar cal = Calendar.getInstance();
//
//        et_birth.setText(cal.get(Calendar.YEAR) +"-"+ "0"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));

        txt_signnum_number = findViewById(R.id.signnum_number_txt); // 휴대폰 번호 또는 이메일

        btn_close = findViewById(R.id.signnum_close_btn);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_next = findViewById(R.id.signnum_btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = et_phone.getText().toString();
                password = et_password.getText().toString();
                birth = et_birth.getText().toString();

                check_phone = Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone);
                check_password = Pattern.matches("^[0-9A-Za-z]{8,16}$", password);
                check_birth = Pattern.matches("^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$", birth);

                if (txt_signnum_number.getText().toString().equals("이메일 주소")) {
//                    System.out.println(et_phone.getText().toString());
//                    System.out.println(et_password.getText().toString());
//                    System.out.println(et_birth.getText().toString());
                    // 이메일 주소 유효성
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_phone.getText().toString()).matches()) {
                        showCustomToast("이메일을 형식에 맞게 입력해주세요.");
                        et_phone.setText("");
                        et_phone.requestFocus();
                    }
                    else if (!check_password) {
                        showCustomToast("비밀번호를 형식에 맞게 설정해주세요.");
                        et_password.setText("");
                        et_password.requestFocus();
                    }
                    else if (!check_birth) {
                        showCustomToast("올바른 생년월일을 입력해주세요.");
                        et_birth.setText("");
                        et_birth.requestFocus();
                    }
                    else {
                         // 유효성 검사 통과
                        tryPostSignUpEmail(et_name.getText().toString(), et_phone.getText().toString(), et_password.getText().toString(), et_birth.getText().toString());
                    }
                }
                else {
                    // 휴대폰 번호 유효성
                    if (!check_phone) {
                        showCustomToast("올바른 핸드폰 번호를 입력해주세요.");
                        et_phone.setText("");
                        et_phone.requestFocus();
                    }
                    else if (!check_password) {
                        showCustomToast("비밀번호를 형식에 맞게 설정해주세요.");
                        et_password.setText("");
                        et_password.requestFocus();
                    }
                    else if (!check_birth) {
                        showCustomToast("올바른 생년월일을 입력해주세요.");
                        et_birth.setText("");
                        et_birth.requestFocus();
                    }
                    else {
                        // 유효성 검사 통과
                        tryPostSignUpNumber(et_name.getText().toString(), et_phone.getText().toString(), et_birth.getText().toString(), et_password.getText().toString());
                    }
                }
            }
        });

        // 이메일 번호로 가입
        btn_email = findViewById(R.id.signnum_email_btn);
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_signnum_number.setText("이메일 주소");
                et_phone.setText("");
                et_phone.setHint("이메일 주소");
                et_phone.setInputType(InputType.TYPE_CLASS_TEXT);
                et_password.setText("");
                et_password.setHint("영어, 숫자 포함 8~16자");
                btn_email.setVisibility(View.GONE);
                btn_phone.setVisibility(View.VISIBLE);
            }
        });

        // 휴대폰 번호로 가입
        btn_phone = findViewById(R.id.signnum_phone_btn);
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_signnum_number.setText("휴대폰 번호");
                et_phone.setText("");
                et_phone.setHint("010-1234-5678");
                et_password.setText("");
                et_password.setHint("영어, 숫자 포함 8~16자");
                et_phone.setInputType(InputType.TYPE_CLASS_PHONE);
                btn_phone.setVisibility(View.GONE);
                btn_email.setVisibility(View.VISIBLE);
            }
        });
    }

    private void tryPostSignUpNumber(String name, String phone, String birthday, String password) {
        showProgressDialog();

        final SignUpService signUpService = new SignUpService(this);
        signUpService.postSignUpNumber(name, phone, birthday, password);
    }

    private void tryPostSignUpEmail(String name, String email, String password, String birthday) {
        showProgressDialog();

        final SignUpService signUpService = new SignUpService(this);
        signUpService.postSignUpEmail(name, email, password, birthday);
    }

    private void tryPostSignInNumber(String phone, String password) {
        showProgressDialog();
        System.out.println("aaaxxx : " + phone);
        System.out.println("aaaxxx : " + password);

        final SignUpService signUpService = new SignUpService(this);
        signUpService.postSignInNumber(phone, password);
    }

    private void tryPostSignInEmail(String email, String password) {
        showProgressDialog();

        final SignUpService signUpService = new SignUpService(this);
        signUpService.postSignInEmail(email, password);
    }

    @Override
    public void signUpNumberSuccess(String text) {
        showCustomToast("번호 회원가입에 성공하였습니다.");
        System.out.println("번호 회원가입 성공~");
        tryPostSignInNumber(phone, password);
    }

    @Override
    public void signUpNumberFailure(String message) {
        hideProgressDialog();
        showCustomToast("번호 회원가입 실패");
        System.out.println("번호 회원가입 실패~");
    }

    @Override
    public void signUpEmailSuccess(String text) {
        showCustomToast("이메일 회원가입에 성공하였습니다.");
        System.out.println("이메일 회원가입 성공~");

        tryPostSignInEmail(phone, password);
    }

    @Override
    public void signUpEmailFailure(String message) {
        showCustomToast("이메일 회원가입 실패~");
        System.out.println("이메일 회원가입 실패~");
        hideProgressDialog();
    }

    @Override
    public void signInNumberSuccess(String jwt) {
        hideProgressDialog();
        System.out.println("번호 jwt 발급 성공~");
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(X_ACCESS_TOKEN, jwt);
        editor.apply();
        System.out.println(jwt);
        showCustomToast("번호 jwt 발급 성공~");

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signInNumberFailure(String message) {
        System.out.println(message);
        showCustomToast(message);
        hideProgressDialog();
    }

    @Override
    public void signInEmailSuccess(String jwt) {
        hideProgressDialog();
        System.out.println("이메일 jwt 발급 성공~");
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(X_ACCESS_TOKEN, jwt);
        editor.apply();
        System.out.println(jwt);
        showCustomToast("이메일 jwt 발급 성공~");

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signInEmailFailure(String message) {
        System.out.println(message);
        showCustomToast(message);
        hideProgressDialog();
    }
//
//    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
//            y = year;
//            m = month;
//            d = dayOfMonth;
//            et_birth.setText(String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d));
//        }
//    };
//
//    public class ReturnDate {
//        long now;
//        Date date;
//        SimpleDateFormat sdf;
//
//        ReturnDate() {
//            now = System.currentTimeMillis();
//            date = new Date(now);
//        }
//
//        public String returnYear() {
//            sdf = new SimpleDateFormat("yyyy");
//            String year = sdf.format(date);
//            return year;
//        }
//
//        public String returnMonth() {
//            sdf = new SimpleDateFormat("MM");
//            String month = sdf.format(date);
//            return month;
//        }
//
//        public String returnDay() {
//            sdf = new SimpleDateFormat("dd");
//            String day = sdf.format(date);
//            return day;
//        }
//    }
}