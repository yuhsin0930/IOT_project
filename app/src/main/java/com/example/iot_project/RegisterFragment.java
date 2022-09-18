package com.example.iot_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    private RegisterActivity registerActivity;
    private ImageView ImageViewBack;
    private RelativeLayout RelativeLayoutCity;
    private Button buttonSubmit;
    private Button buttonLogout;
    private TextView textViewBarName;
    private String barName;
    private boolean isFromRegister;
    private TextView textViewCity;
    private String cityName;


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
        registerActivity = (RegisterActivity)getActivity();

        ImageViewBack = (ImageView)v.findViewById(R.id.ImageView_register_back);
        RelativeLayoutCity = (RelativeLayout) v.findViewById(R.id.RelativeLayout_register_city);
        buttonSubmit = (Button)v.findViewById(R.id.button_register_submit);
        buttonLogout = (Button)v.findViewById(R.id.button_register_logout);
        textViewBarName = (TextView)v.findViewById(R.id.textView_register_barName);
        textViewCity = (TextView)v.findViewById(R.id.textView_register_city);
        textViewBarName.setText(barName);

        if (isFromRegister) {
            buttonSubmit.setVisibility(View.VISIBLE);
            buttonLogout.setVisibility(View.GONE);
        } else {
            buttonSubmit.setVisibility(View.GONE);
            buttonLogout.setVisibility(View.VISIBLE);
        }

        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerActivity.onBackPressed();
            }
        });

        RelativeLayoutCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActivity.showCity();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) textViewCity.setText(cityName);
    }

    public void setTextViewBarName(String barName) {
        this.barName = barName;
    }

    public void setIsFromRegister(boolean isFromRegister) {
        this.isFromRegister = isFromRegister;
    }

    public void setCityName(String cityName) { this.cityName = cityName; }

}