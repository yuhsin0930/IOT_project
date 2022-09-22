package com.example.iot_project.register;

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
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private boolean isLoggedIn, addressFlag;
    private Intent intent;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePicker;
    private Map<String, Object> fireMap;
    private String account, password_1, password_2, name, birthday, phone, email, city, district;
    private String address, bankNumber, bankAccount, barName;
    private View view;

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
        Log.d("main", "isLoggedIn = " + isLoggedIn);
        return view;
    }

    private void setData() {
        addressFlag = true;
        registerActivity = (RegisterActivity) getActivity();
        keyboard = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        address = "幼獅路一段23號"; // 等資料庫這要改，預設address = "", 再由資料庫下載該帳號已有地址
        textViewBarName.setText(barName);
        editTextAddress.setText(address);
//        textViewExists.setText("");
//        textViewPassword.setText("");
//        textViewAddress.setText(editTextAddress.getText().toString().substring(0, 6) + "..");
        calendar = Calendar.getInstance();

//        [isLoggedIn : 判斷帳號是否已登入]
//       SharedPreferences : "LoginInformation" 儲存已登入帳號資訊
       SharedPreferences sp = registerActivity.getSharedPreferences("LoginInformation", registerActivity.MODE_PRIVATE);
//       "is_login" : 帳號是否登入， true 為登入，false為登出
       Boolean isLoggedIn = sp.getBoolean("is_login",false);
//       "member_id" : 帳號ID
       String memberId= sp.getString("member_id","0");
//       "account_name" : 帳號名稱
       String account = sp.getString("account_name","user");
       Log.d("register", isLoggedIn +" "+ memberId +" "+ account);

//        if (isLoggedIn) {
//            buttonSubmit.setVisibility(View.GONE);
//            relativeLayoutLogout.setVisibility(View.VISIBLE);
//        } else {
//            buttonSubmit.setVisibility(View.VISIBLE);
//            relativeLayoutLogout.setVisibility(View.GONE);
//        }

        if (editTextAddress.getText().toString().length() > 0) {
            imageViewAddress_X.setVisibility(View.VISIBLE);
        } else {
            imageViewAddress_X.setVisibility(View.GONE);
        }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) textViewCity.setText(city + district);
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
                city = "";
                district = "";
                registerActivity.showCity();
                break;
            case R.id.RelativeLayout_register_bankNumber:
                editTextBankNumber.requestFocus();
                keyboard.showSoftInput(editTextBankNumber, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_bankAccount:
                editTextBankAccount.requestFocus();
                keyboard.showSoftInput(editTextBankAccount, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.LinearLayout_register_address:
                // 預設地址方式與資料庫互動方式要改
                if (addressFlag) {
                    imageViewAddress_Arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.outline_keyboard_arrow_down_black_18_1));
                    relativeLayouAddressDrop.setVisibility(View.VISIBLE);
                    editTextAddress.setText(address);
                    // 自動選定edit與開啟鍵盤
                    editTextAddress.requestFocus();
                    addressFlag = false;
                } else {
                    imageViewAddress_Arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.outline_arrow_forward_ios_black_18));
                    relativeLayouAddressDrop.setVisibility(View.GONE);
                    addressFlag = true;
                }
                break;
            case R.id.imageView_register_address_x:
                editTextAddress.setText("");
                break;
            case R.id.imageView_register_back:
                registerActivity.onBackPressed();
                break;
            case R.id.button_register_submit:
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
                break;
            case R.id.button_register_logout:
//                SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
//                sp.edit().putBoolean("is_login", false).commit();
                Toast.makeText(registerActivity, "已登出", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        if (view.getId() != R.id.LinearLayout_register_address && view.getId() != R.id.editText_register_address
                && view.getId() != R.id.imageView_register_address_x) {
            imageViewAddress_Arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.outline_arrow_forward_ios_black_18));
            relativeLayouAddressDrop.setVisibility(View.GONE);
            addressFlag = true;
        }
    }

    public void setTextViewBarName(String barName) {
        this.barName = barName;
    }

    public void isLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setCityName(String city) {
        this.city = city;
    }

    public void setDistrictName(String district) {
        this.district = district;
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

    public void isInputDataFinish() {

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

    // 及時監聽
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
                    if (account.length() == 0 || account.matches("^[a-zA-Z]\\w*$")) {
                        textViewAccountWarn.setText("");
                    } else if (account.matches("^[a-z]")){
                        textViewAccountWarn.setText("(開頭須為英文小寫)");
                    } else if (account.matches("[^a-zA-Z0-9]")) {
                        textViewAccountWarn.setText("(不可有特殊字元)");
                    }
                    break;
                case R.id.edittext_register_password_1:
                case R.id.edittext_register_password_2:
                    password_1 = editTextPassword_1.getText().toString();
                    password_2 = editTextPassword_2.getText().toString();
                    if (password_1.length() == 0 || password_1.matches(".*[a-zA-Z]+[0-9&a-zA-Z]{6,12}")){
                        textViewPasswordWarn_1.setText("");
                    } else {
                        textViewPasswordWarn_1.setText("(密碼格式不符)");
                    }
                    if (password_2.length() == 0 || password_2.equals(password_1)) {
                        textViewPasswordWarn_2.setText("");
                        Log.d("main", "true: " + password_2 + " : " + password_1);
                    } else {
                        textViewPasswordWarn_2.setText("(密碼不一致)");
                        Log.d("main", "false: " + password_2 + " : " + password_1);
                    }
                    break;
                case R.id.edittext_register_name:
                    textViewNameWarn.setText("(姓名格式不符)");
                    name = editTextName.getText().toString();
                    break;
                case R.id.edittext_register_phone:
                    phone = editTextPhone.getText().toString();
                    if (phone.length() == 0 || phone.matches("^(09)(\\d{2})(-)?(\\d{3})(-)?(\\d{3})")) {
                        textViewPhoneWarn.setText("");
                    } else {
                        textViewPhoneWarn.setText("(手機格式不符)");
                    }
                    break;
                case R.id.edittext_register_email:

                    email = editTextEmail.getText().toString();
                    if (email.length() == 0 || email.matches("(.+)(@){1}(\\w+)(\\.){1}(.*)")) {
                        textViewEmailWarn.setText("");
                    } else {
                        textViewEmailWarn.setText("(信箱格式不符)");
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
                    textViewBankNumberWarn.setText("(銀行帳號格式不符)");
                    bankNumber = editTextBankNumber.getText().toString();
                    break;
                case R.id.edittext_register_bankAccount:
                    textViewBankAccountWarn.setText("(戶名格式不符)");
                    bankAccount = editTextBankAccount.getText().toString();
                    break;
            }
        }
        @Override
        public void afterTextChanged(Editable s) {}
    }

}

