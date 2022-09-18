package com.example.iot_project.register;

import android.content.Context;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iot_project.R;

public class RegisterFragment extends Fragment implements View.OnClickListener{

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
    private Button buttonSubmit, buttonLogout;
    private String barName, cityName, address;
    private RegisterActivity registerActivity;
    private InputMethodManager kryboard;
    private boolean isFromRegister, addressFlag;

    public RegisterFragment() {}

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        relativeLayoutAccount = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_account);
        relativeLayoutPassword_1 = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_password_1);
        relativeLayoutPassword_2 = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_password_2);
        relativeLayoutName = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_name);
        relativeLayoutBirthday = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_birthday);
        relativeLayoutPhone = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_phone);
        relativeLayoutEmail = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_email);
        relativeLayoutCity = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_city);
        relativeLayoutBankNumber = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_bankNumber);
        relativeLayoutBankAccount = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_bankAccount);
        relativeLayoutLogout = (RelativeLayout)v.findViewById(R.id.RelativeLayout_register_logout);
        relativeLayouAddressDrop = (RelativeLayout) v.findViewById(R.id.RelativeLayout_register_address_drop);
        linearLayoutAddress = (LinearLayout)v.findViewById(R.id.LinearLayout_register_address);
        editTextAccount = (EditText)v.findViewById(R.id.edittext_register_account);
        editTextPassword_1 = (EditText)v.findViewById(R.id.edittext_register_password_1);
        editTextPassword_2 = (EditText)v.findViewById(R.id.edittext_register_password_2);
        editTextName = (EditText)v.findViewById(R.id.edittext_register_name);
        editTextPhone = (EditText)v.findViewById(R.id.edittext_register_phone);
        editTextEmail = (EditText)v.findViewById(R.id.edittext_register_email);
        editTextAddress = (EditText)v.findViewById(R.id.editText_register_address);
        editTextBankNumber = (EditText)v.findViewById(R.id.edittext_register_bankNumber);
        editTextBankAccount = (EditText)v.findViewById(R.id.edittext_register_bankAccount);
        imageViewAddress_X = (ImageView)v.findViewById(R.id.imageView_register_address_x);
        imageViewAddress_Arrow = (ImageView)v.findViewById(R.id.imageView_register_address_arrow);
        imageViewBack = (ImageView)v.findViewById(R.id.imageView_register_back);
        textViewBarName = (TextView)v.findViewById(R.id.textView_register_barName);
        textViewBirthday = (TextView)v.findViewById(R.id.textView_register_birthday);
        textViewCity = (TextView)v.findViewById(R.id.textView_register_city);
        textViewAddress = (TextView)v.findViewById(R.id.textView_register_address);
        buttonSubmit = (Button)v.findViewById(R.id.button_register_submit);
        buttonLogout = (Button)v.findViewById(R.id.button_register_logout);

        registerActivity = (RegisterActivity)getActivity();
        kryboard = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        kryboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
        address = "桃園市楊梅區幼獅路一段23號"; // 等資料庫這要改，預設address = "", 再由資料庫下載該帳號已有地止
        textViewBarName.setText(barName);
        editTextAddress.setText(address);
        textViewAddress.setText(editTextAddress.getText().toString().substring(0,6) + "..");
        isFromRegister = false;
        addressFlag = true;

        if (isFromRegister) {   // 改判斷是否為登入中
            buttonSubmit.setVisibility(View.VISIBLE);
            relativeLayoutLogout.setVisibility(View.GONE);
        } else {
            buttonSubmit.setVisibility(View.GONE);
            relativeLayoutLogout.setVisibility(View.VISIBLE);
        }

        if (editTextAddress.getText().toString().length() > 0) {
            imageViewAddress_X.setVisibility(View.VISIBLE);
        } else {
            imageViewAddress_X.setVisibility(View.GONE);
        }

        // 搜尋輸入 監聽
        editTextAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                address = editTextAddress.getText().toString();
                if (address.length() > 0) {
                    imageViewAddress_X.setVisibility(View.VISIBLE);
                    textViewAddress.setText(address.length() > 6 ? (address.substring(0,6) + "..") : address);
                } else {
                    imageViewAddress_X.setVisibility(View.GONE);
                    textViewAddress.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

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

        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) textViewCity.setText(cityName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RelativeLayout_register_account:
                editTextAccount.requestFocus();
                kryboard.showSoftInput(editTextAccount, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_password_1:
                editTextPassword_1.requestFocus();
                kryboard.showSoftInput(editTextPassword_1, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_password_2:
                editTextPassword_2.requestFocus();
                kryboard.showSoftInput(editTextPassword_2, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_name:
                editTextName.requestFocus();
                kryboard.showSoftInput(editTextName, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_birthday:
                kryboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                textViewBirthday.setText("2022/09/18");
                break;
            case R.id.RelativeLayout_register_phone:
                editTextPhone.requestFocus();
                kryboard.showSoftInput(editTextPhone, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_email:
                editTextEmail.requestFocus();
                kryboard.showSoftInput(editTextEmail, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_city:
                kryboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                registerActivity.showCity();
                break;
            case R.id.RelativeLayout_register_bankNumber:
                editTextBankNumber.requestFocus();
                kryboard.showSoftInput(editTextBankNumber, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.RelativeLayout_register_bankAccount:
                editTextBankAccount.requestFocus();
                kryboard.showSoftInput(editTextBankAccount, InputMethodManager.SHOW_IMPLICIT);
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
                Toast.makeText(registerActivity, "button_register_submit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_register_logout:
                Toast.makeText(registerActivity, "button_register_logout", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setTextViewBarName(String barName) {
        this.barName = barName;
    }

    public void setIsFromRegister(boolean isFromRegister) {
        this.isFromRegister = isFromRegister;
    }

    public void setCityName(String cityName) { this.cityName = cityName; }

}