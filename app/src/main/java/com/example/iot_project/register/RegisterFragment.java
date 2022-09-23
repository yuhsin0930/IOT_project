package com.example.iot_project.register;

import static android.content.Context.MODE_PRIVATE;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iot_project.LoginActivity;
import com.example.iot_project.MainActivity;
import com.example.iot_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout relativeLayoutAccount, relativeLayoutPassword_1, relativeLayoutPassword_2;
    private RelativeLayout relativeLayoutName, relativeLayoutBirthday, relativeLayoutPhone;
    private RelativeLayout relativeLayoutEmail, relativeLayoutCity, relativeLayoutBankNumber;
    private RelativeLayout relativeLayoutBankAccount, relativeLayoutLogout, relativeLayouAddressDrop;
    private LinearLayout linearLayoutAddress;
    private EditText editTextAccount, editTextPassword_1, editTextPassword_2, editTextName;
    private EditText editTextPhone, editTextEmail;
    private EditText editTextAddress, editTextBankNumber, editTextBankAccount;
    private ImageView imageViewAddress_X, imageViewAddress_Arrow, imageViewBack;
    private TextView textViewBarName, textViewBirthday, textViewAddress, textViewCity;
    private TextView textViewAccountWarn, textViewPasswordWarn_1, textViewPasswordWarn_2, textViewNameWarn;
    private TextView textViewPhoneWarn, textViewEmailWarn, textViewBankNumberWarn, textViewBankAccountWarn;
    private Button buttonSubmit, buttonLogout;
    private RegisterActivity registerActivity;
    private InputMethodManager keyboard;
    private boolean addressDropFlag, isSubmitEnable;
    private Intent intent;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePicker;
    private Map<String, Object> fireMap;
    private String account, password_1, password_2, name, birthday, phone;
    private String email, city, district, address, bankNumber, bankAccount;
    private View view;
    private Boolean[] submitFlag;
    private ScrollView ScrollViewRegister;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        findView();
        setData();
        setListener();
        return view;
    }

    private void setData() {
        addressDropFlag = true;
        isSubmitEnable = false;
        registerActivity = (RegisterActivity) getActivity();
        keyboard = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        calendar = Calendar.getInstance();
        submitFlag = new Boolean[9];
        for (int i = 0; i < submitFlag.length; i++) submitFlag[i] = false;
        address = "";
        city = "";
        district = "";

//        [isLoggedIn : 判斷帳號是否已登入]
//       SharedPreferences : "LoginInformation" 儲存已登入帳號資訊
       SharedPreferences sp = registerActivity.getSharedPreferences("LoginInformation", MODE_PRIVATE);
//       "is_login" : 帳號是否登入， true 為登入，false為登出
       Boolean isLoggedIn = sp.getBoolean("is_login",false);
//       "member_id" : 帳號ID
       String memberId= sp.getString("member_id","0");
//       "account_name" : 帳號名稱
       String account = sp.getString("account_name","user");
       Log.d("register", isLoggedIn +" "+ memberId +" "+ account);

        if (isLoggedIn) {
            buttonSubmit.setVisibility(View.GONE);
            relativeLayoutLogout.setVisibility(View.VISIBLE);
            textViewBarName.setText("帳號設定");
        } else {
            buttonSubmit.setVisibility(View.VISIBLE);
            relativeLayoutLogout.setVisibility(View.GONE);
            textViewBarName.setText("註冊");
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            city = registerActivity.getCityName();
            district = registerActivity.getDistrictName();
            textViewCity.setText(city + district);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RelativeLayout_register_account:
                editTextAccount.requestFocus();
                keyboard.showSoftInput(editTextAccount, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_password_1:
                editTextPassword_1.requestFocus();
                keyboard.showSoftInput(editTextPassword_1, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_password_2:
                editTextPassword_2.requestFocus();
                keyboard.showSoftInput(editTextPassword_2, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_name:
                editTextName.requestFocus();
                keyboard.showSoftInput(editTextName, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_birthday:
                keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        datePicker, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                dialog.show();
                break;
            case R.id.RelativeLayout_register_phone:
                editTextPhone.requestFocus();
                keyboard.showSoftInput(editTextPhone, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_email:
                editTextEmail.requestFocus();
                keyboard.showSoftInput(editTextEmail, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_city:
                keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                registerActivity.setCityName("");
                registerActivity.setDistrictName("");
                registerActivity.showCityFragment();
                break;
            case R.id.RelativeLayout_register_bankNumber:
                editTextBankNumber.requestFocus();
                keyboard.showSoftInput(editTextBankNumber, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_bankAccount:
                editTextBankNumber.requestFocus();
                keyboard.showSoftInput(editTextBankAccount, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.LinearLayout_register_address:
                if (addressDropFlag) {
                    imageViewAddress_Arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.outline_keyboard_arrow_down_black_18_1));
                    relativeLayouAddressDrop.setVisibility(View.VISIBLE);
                    editTextAddress.setText(address);
                    editTextAddress.requestFocus();
                    addressDropFlag = false;
                } else {
                    imageViewAddress_Arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.outline_arrow_forward_ios_black_18));
                    relativeLayouAddressDrop.setVisibility(View.GONE);
                    addressDropFlag = true;
                }
                break;
            case R.id.editText_register_address:
                keyboard.showSoftInput(editTextAddress, InputMethodManager.SHOW_IMPLICIT);
                ScrollViewRegister.fullScroll(ScrollView.FOCUS_DOWN);
                editTextAddress.requestFocus();
                break;
            case R.id.imageView_register_address_x:
                editTextAddress.setText("");
                break;
            case R.id.imageView_register_back:
                registerActivity.onBackPressed();
                break;
            case R.id.button_register_submit:
                if (submitFlag[8] && city.length() > 0 && district.length() > 0 && address.length() > 0) {
                    account = editTextAccount.getText().toString();
                    password_1 = editTextPassword_1.getText().toString();
                    name = editTextName.getText().toString();
                    birthday = textViewBirthday.getText().toString();
                    phone = editTextPhone.getText().toString();
                    email = editTextEmail.getText().toString();
                    bankNumber = editTextBankNumber.getText().toString();
                    bankAccount = editTextBankAccount.getText().toString();
                    makeMap();
                    registerActivity.setFireMap(fireMap);
                    registerActivity.MapUploadToFireBase();
                    Toast.makeText(registerActivity, "註冊完成", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(registerActivity, "請先輸入完整資料", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_register_logout:
                SharedPreferences sp = getContext().getSharedPreferences("LoginInformation", MODE_PRIVATE);
                sp.edit().putBoolean("is_login", false).commit();
                Toast.makeText(registerActivity, "已登出", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        if (view.getId() != R.id.LinearLayout_register_address && view.getId() != R.id.editText_register_address
                && view.getId() != R.id.imageView_register_address_x) {
            imageViewAddress_Arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.outline_arrow_forward_ios_black_18));
            relativeLayouAddressDrop.setVisibility(View.GONE);
            addressDropFlag = true;
        }
    }

    public void makeMap() {
//      新增會員帳號建立時間
//      1. 取得台灣時區(Asia/Taipei)的目前日期時間
        ZonedDateTime NowTime = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));
//      2. 設定日期時間格式 : "uuuu-MM-dd HH:mm:ss" = "2022-09-20 20:27:17"
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
//      3. 將目前日期時間格式化，ex: 2022-09-20 20:27:17
        String createTime = NowTime.format(dateTimeFormat);

        fireMap = new HashMap<>();
//        2022-09-21 : account 似乎是firebase的保留字, 搜尋資料會有問題, 故改成account_name
//        fireMap.put("account", account);
        fireMap.put("account_name", account);
        fireMap.put("picture", "");
        fireMap.put("password", password_1);
        fireMap.put("name", name);
        fireMap.put("birthday", birthday);
        fireMap.put("phone", phone);
        fireMap.put("email", email);
        fireMap.put("city", city);
        fireMap.put("district", district);
        fireMap.put("address", address);
        fireMap.put("bankNumber", bankNumber);
        fireMap.put("bankAccount", bankAccount);
        fireMap.put("createTime", createTime);//新增會員建立時間
        fireMap.put("is_seller", "false");//判斷會員是否申請賣家通過
    }

    public void isAccountExistedInFirebase() {
        if (editTextAccount.getText().length() > 0) { // 判斷帳號是否已註冊
            account = editTextAccount.getText().toString();
            ///     使用 Firebase 服務
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            //      取得  Firebase 資料庫 member 資料表 (GET網址)
            DatabaseReference memberRef = database.getReference("member");
            //      搜尋會員資料: 至在 member 資料表下，搜尋以 uniqueKey 儲存的會員資料，account_name 為帳號名稱
            memberRef.orderByChild("account_name").equalTo(account)
                    .addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.d("main", "[memberRef]snapshot.exists()=" + snapshot.exists());
                            if (snapshot.exists()) {
                                textViewAccountWarn.setText("(帳號已存在)");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        }
    }

    // 即時監聽
    private class myTextWatcher implements TextWatcher {
        private int whichEdit;
        myTextWatcher(int whichEdit) {
            this.whichEdit = whichEdit;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (whichEdit) {
                case R.id.edittext_register_account:
                    isAccountExistedInFirebase();
                    account = editTextAccount.getText().toString();
                    if (account.length() == 0) {
                        textViewAccountWarn.setText("");
                        submitFlag[0] = false;
                    } else if (account.matches("^[a-z]\\w*$")) {
                        textViewAccountWarn.setText("");
                        submitFlag[0] = true;
                    }
                    else {
                        textViewAccountWarn.setText("(開頭須為a-z)");
                        submitFlag[0] = false;
                    }
                    if (!account.matches("^\\w*$")) {
                        textViewAccountWarn.setText("(不可有特殊字元)");
                        submitFlag[0] = false;
                    }
                    break;
                case R.id.edittext_register_password_1:
                case R.id.edittext_register_password_2:
                    password_1 = editTextPassword_1.getText().toString();
                    password_2 = editTextPassword_2.getText().toString();
//
//                    if (password_1.length() == 0 || password_1.matches("^([a-zA-Z0-9]{0,6})(([a-zA-Z][0-9a-zA-Z]{5})|([0-9a-zA-Z]{5}[a-zA-Z])|([0-9a-zA-Z]{3}[a-zA-Z][0-9a-zA-Z]{2}))([a-zA-Z0-9]{0,6})$")){
//                    輸入"123456789a12345678"，超過12字但會過

                    if (password_1.length() == 0) {
                        textViewPasswordWarn_1.setText("");
                        submitFlag[1] = false;
                    } else if (password_1.length() > 5 && password_1.length() < 13 && password_1.matches("^([0-9]*[a-zA-Z][0-9]*)$")){
                        textViewPasswordWarn_1.setText("");
                        submitFlag[1] = true;
                    } else {
                        textViewPasswordWarn_1.setText("(密碼格式不符)");
                        submitFlag[1] = false;
                    }

                    if (password_2.length() == 0) {
                        textViewPasswordWarn_2.setText("");
                        submitFlag[2] = false;
                    } else if (password_2.equals(password_1)) {
                        textViewPasswordWarn_2.setText("");
                        submitFlag[2] = true;
                    } else {
                        textViewPasswordWarn_2.setText("(密碼不一致)");
                        submitFlag[2] = false;
                    }
                    break;
                case R.id.edittext_register_name:
                    name = editTextName.getText().toString();
                    if (name.length() == 0) {
                        textViewNameWarn.setText("");
                        submitFlag[3] = false;
                    } else if (name.matches("^[A-z\\u4e00-\\u9fa5 ]*$")) {
                        textViewNameWarn.setText("");
                        submitFlag[3] = true;
                    } else {
                        textViewNameWarn.setText("(姓名格式不符)");
                        submitFlag[3] = false;
                    }
                    break;
                case R.id.edittext_register_phone:
                    phone = editTextPhone.getText().toString();
                    if (phone.length() == 0) {
                        textViewPhoneWarn.setText("");
                        submitFlag[4] = false;
                    } else if (phone.length() == 0 || phone.matches("^(09)(\\d{2})(-)?(\\d{3})(-)?(\\d{3})")) {
                        textViewPhoneWarn.setText("");
                        submitFlag[4] = true;
                    } else {
                        textViewPhoneWarn.setText("(手機格式不符)");
                        submitFlag[4] = false;
                    }
                    break;
                case R.id.edittext_register_email:
                    email = editTextEmail.getText().toString();
                    if (email.length() == 0) {
                        textViewEmailWarn.setText("");
                        submitFlag[5] = false;
                    } else if (email.matches("(.+)(@){1}(\\w+)(\\.){1}(.*)")) {
                        textViewEmailWarn.setText("");
                        submitFlag[5] = true;
                    } else {
                        textViewEmailWarn.setText("(信箱格式不符)");
                        submitFlag[5] = false;
                    }
                    break;
                case R.id.editText_register_address:
                    address = editTextAddress.getText().toString();
                    address = address.trim();
                    if (address.length() > 0) {
                        imageViewAddress_X.setVisibility(View.VISIBLE);
                        textViewAddress.setText(address.length() > 6 ? (address.substring(0, 6) + "..") : address);
                    } else {
                        imageViewAddress_X.setVisibility(View.GONE);
                        textViewAddress.setText("");
                    }
                    break;
                case R.id.edittext_register_bankNumber:
                    bankNumber = editTextBankNumber.getText().toString();
                    if (bankNumber.length() == 0) {
                        textViewBankNumberWarn.setText("");
                        submitFlag[6] = false;
                    } else if (bankNumber.matches("^([0-9]{10,14})$")) {
                        textViewBankNumberWarn.setText("");
                        submitFlag[6] = true;
                    } else {
                        textViewBankNumberWarn.setText("(銀行帳號格式不符)");
                        submitFlag[6] = false;
                    }
                    break;
                case R.id.edittext_register_bankAccount:
                    bankAccount = editTextBankAccount.getText().toString();
                    if (bankAccount.length() == 0){
                        textViewBankAccountWarn.setText("");
                        submitFlag[7] = false;
                    } else if (bankAccount.matches("^[A-z\\u4e00-\\u9fa5 ]*$")) {
                        textViewBankAccountWarn.setText("");
                        submitFlag[7] = true;
                    } else {
                        textViewBankAccountWarn.setText("(戶名格式不符)");
                        submitFlag[7] = false;
                    }
                    break;
            }

            submitFlag[8] = true;
            for (int i = 0; i < submitFlag.length-1; i++) {
                submitFlag[8] &= submitFlag[i];
                Log.d("register", "submitFlag[" + i +"] = " + submitFlag[i]);
            }
            if (submitFlag[8] && city.length() > 0 && district.length() > 0 && address.length() > 0) {
                if (!isSubmitEnable) {
                    buttonSubmit.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Mycolor_1));
                    buttonSubmit.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    ScaleAnimation scaleAnimation = new ScaleAnimation(
                            1.0f,1.2f,1.0f,1.1f,
                            Animation.RELATIVE_TO_SELF,0.5f,
                            Animation.RELATIVE_TO_SELF,0.5f);
                    scaleAnimation.setDuration(120);
                    buttonSubmit.startAnimation(scaleAnimation);
                }
                isSubmitEnable = true;
            } else {
                buttonSubmit.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Mycolor_4));
                buttonSubmit.setTextColor(ContextCompat.getColor(getContext(), R.color.font_color));
                isSubmitEnable = false;
            }
            Log.d("register", "-> summitFlag[8] = " + submitFlag[8]);

        }
        @Override
        public void afterTextChanged(Editable s) {}
    }

    private void findView() {
        relativeLayoutAccount = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_account);
        relativeLayoutPassword_1 = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_password_1);
        relativeLayoutPassword_2 = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_password_2);
        relativeLayoutName = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_name);
        relativeLayoutBirthday = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_birthday);
        relativeLayoutPhone = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_phone);
        relativeLayoutEmail = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_email);
        relativeLayoutCity = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_city);
        relativeLayoutBankNumber = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_bankNumber);
        relativeLayoutBankAccount = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_bankAccount);
        relativeLayoutLogout = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_logout);
        relativeLayouAddressDrop = (RelativeLayout) view.findViewById(R.id.RelativeLayout_register_address_drop);
        linearLayoutAddress = (LinearLayout) view.findViewById(R.id.LinearLayout_register_address);
        editTextAccount = (EditText) view.findViewById(R.id.edittext_register_account);
        editTextPassword_1 = (EditText) view.findViewById(R.id.edittext_register_password_1);
        editTextPassword_2 = (EditText) view.findViewById(R.id.edittext_register_password_2);
        editTextName = (EditText) view.findViewById(R.id.edittext_register_name);
        editTextPhone = (EditText) view.findViewById(R.id.edittext_register_phone);
        editTextEmail = (EditText) view.findViewById(R.id.edittext_register_email);
        editTextAddress = (EditText) view.findViewById(R.id.editText_register_address);
        editTextBankNumber = (EditText) view.findViewById(R.id.edittext_register_bankNumber);
        editTextBankAccount = (EditText) view.findViewById(R.id.edittext_register_bankAccount);
        imageViewAddress_X = (ImageView) view.findViewById(R.id.imageView_register_address_x);
        imageViewAddress_Arrow = (ImageView) view.findViewById(R.id.imageView_register_address_arrow);
        imageViewBack = (ImageView) view.findViewById(R.id.imageView_register_back);
        textViewBarName = (TextView) view.findViewById(R.id.textView_register_barName);
        textViewBirthday = (TextView) view.findViewById(R.id.textView_register_birthday);
        textViewCity = (TextView) view.findViewById(R.id.textView_register_city);
        textViewAddress = (TextView) view.findViewById(R.id.textView_register_address);
        textViewAccountWarn = (TextView) view.findViewById(R.id.textView_register_account_warn);
        textViewPasswordWarn_1 = (TextView) view.findViewById(R.id.textView_register_password_warn_1);
        textViewPasswordWarn_2 = (TextView) view.findViewById(R.id.textView_register_password_warn_2);
        textViewNameWarn = (TextView) view.findViewById(R.id.textView_register_name_warn);
        textViewPhoneWarn = (TextView) view.findViewById(R.id.textView_register_phone_warn);
        textViewEmailWarn = (TextView) view.findViewById(R.id.textView_register_email_warn);
        textViewBankNumberWarn = (TextView) view.findViewById(R.id.textView_register_bankNumber_warn);
        textViewBankAccountWarn = (TextView) view.findViewById(R.id.textView_register_bankAccount_warn);
        buttonSubmit = (Button) view.findViewById(R.id.button_register_submit);
        buttonLogout = (Button) view.findViewById(R.id.button_register_logout);
        ScrollViewRegister = (ScrollView) view.findViewById(R.id.ScrollView_register);
    }

    private void setListener() {
        relativeLayoutAccount.setOnClickListener(this);
        relativeLayoutPassword_1.setOnClickListener(this);
        relativeLayoutPassword_2.setOnClickListener(this);
        relativeLayoutName.setOnClickListener(this);
        relativeLayoutBirthday.setOnClickListener(this);
        relativeLayoutPhone.setOnClickListener(this);
        relativeLayoutEmail.setOnClickListener(this);
        relativeLayoutCity.setOnClickListener(this);
        relativeLayoutBankNumber.setOnClickListener(this);
        relativeLayoutBankAccount.setOnClickListener(this);
        relativeLayoutLogout.setOnClickListener(this);
        relativeLayouAddressDrop.setOnClickListener(this);
        linearLayoutAddress.setOnClickListener(this);
        editTextAddress.setOnClickListener(this);
        imageViewAddress_X.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        editTextAccount.addTextChangedListener(new myTextWatcher(editTextAccount.getId()));
        editTextPassword_1.addTextChangedListener(new myTextWatcher(editTextPassword_1.getId()));
        editTextPassword_2.addTextChangedListener(new myTextWatcher(editTextPassword_2.getId()));
        editTextName.addTextChangedListener(new myTextWatcher(editTextName.getId()));
        editTextPhone.addTextChangedListener(new myTextWatcher(editTextPhone.getId()));
        editTextEmail.addTextChangedListener(new myTextWatcher(editTextEmail.getId()));
        editTextAddress.addTextChangedListener(new myTextWatcher(editTextAddress.getId()));
        editTextBankNumber.addTextChangedListener(new myTextWatcher(editTextBankNumber.getId()));
        editTextBankAccount.addTextChangedListener(new myTextWatcher(editTextBankAccount.getId()));
        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN);
                textViewBirthday.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

    }

}

