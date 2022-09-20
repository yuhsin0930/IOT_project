package com.example.iot_project.register;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    private RegisterActivity registerActivity;
    private InputMethodManager keyboard;
    private boolean isLoggedIn, addressFlag;
    private Intent intent;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePicker;
    private Map<String, Object> fireMap;
    private String account, password, name, birthday, phone, email, city, district;
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
        registerActivity = (RegisterActivity)getActivity();
        keyboard = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        address = "幼獅路一段23號"; // 等資料庫這要改，預設address = "", 再由資料庫下載該帳號已有地址
        textViewBarName.setText(barName);
        editTextAddress.setText(address);
        textViewAddress.setText(editTextAddress.getText().toString().substring(0,6) + "..");
        calendar = Calendar.getInstance();

        if (isLoggedIn) {   // 之後改判斷 if (為登入中)
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
    }

    private void findView() {
        relativeLayoutAccount = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_account);
        relativeLayoutPassword_1 = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_password_1);
        relativeLayoutPassword_2 = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_password_2);
        relativeLayoutName = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_name);
        relativeLayoutBirthday = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_birthday);
        relativeLayoutPhone = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_phone);
        relativeLayoutEmail = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_email);
        relativeLayoutCity = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_city);
        relativeLayoutBankNumber = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_bankNumber);
        relativeLayoutBankAccount = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_bankAccount);
        relativeLayoutLogout = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_logout);
        relativeLayouAddressDrop = (RelativeLayout)view.findViewById(R.id.RelativeLayout_register_address_drop);
        linearLayoutAddress = (LinearLayout)view.findViewById(R.id.LinearLayout_register_address);
        editTextAccount = (EditText)view.findViewById(R.id.edittext_register_account);
        editTextPassword_1 = (EditText)view.findViewById(R.id.edittext_register_password_1);
        editTextPassword_2 = (EditText)view.findViewById(R.id.edittext_register_password_2);
        editTextName = (EditText)view.findViewById(R.id.edittext_register_name);
        editTextPhone = (EditText)view.findViewById(R.id.edittext_register_phone);
        editTextEmail = (EditText)view.findViewById(R.id.edittext_register_email);
        editTextAddress = (EditText)view.findViewById(R.id.editText_register_address);
        editTextBankNumber = (EditText)view.findViewById(R.id.edittext_register_bankNumber);
        editTextBankAccount = (EditText)view.findViewById(R.id.edittext_register_bankAccount);
        imageViewAddress_X = (ImageView)view.findViewById(R.id.imageView_register_address_x);
        imageViewAddress_Arrow = (ImageView)view.findViewById(R.id.imageView_register_address_arrow);
        imageViewBack = (ImageView)view.findViewById(R.id.imageView_register_back);
        textViewBarName = (TextView)view.findViewById(R.id.textView_register_barName);
        textViewBirthday = (TextView)view.findViewById(R.id.textView_register_birthday);
        textViewCity = (TextView)view.findViewById(R.id.textView_register_city);
        textViewAddress = (TextView)view.findViewById(R.id.textView_register_address);
        buttonSubmit = (Button)view.findViewById(R.id.button_register_submit);
        buttonLogout = (Button)view.findViewById(R.id.button_register_logout);
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
                Toast.makeText(registerActivity, "註冊完成", Toast.LENGTH_SHORT).show();
                account = editTextAccount.getText().toString();
                password = editTextPassword_1.getText().toString();
                name = editTextName.getText().toString();
                birthday = textViewBirthday.getText().toString();
                phone = editTextPhone.getText().toString();
                email = editTextEmail.getText().toString();
                bankNumber = editTextBankNumber.getText().toString();
                bankAccount = editTextBankAccount.getText().toString();
                makeMap();
                registerActivity.setFireMap(fireMap);
                registerActivity.MapUploadToFireBase();
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button_register_logout:
                Toast.makeText(registerActivity, "已登出", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        if (view.getId() != R.id.LinearLayout_register_address) {
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
        fireMap = new HashMap<>();
        fireMap.put("account", account);
        fireMap.put("password", password);
        fireMap.put("name", name);
        fireMap.put("birthday", birthday);
        fireMap.put("phone", phone);
        fireMap.put("email", email);
        fireMap.put("city", city);
        fireMap.put("district", district);
        fireMap.put("address", address);
        fireMap.put("bankNumber", bankNumber);
        fireMap.put("bankAccount", bankAccount);
    }

}