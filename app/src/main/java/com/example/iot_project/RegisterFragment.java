package com.example.iot_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RegisterFragment extends Fragment {

    private RegisterActivity registerActivity;
    private ImageView ImageViewBack;
    private RelativeLayout RelativeLayoutCity;


    public RegisterFragment() {}

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        registerActivity = (RegisterActivity)getActivity();

        ImageViewBack = (ImageView)v.findViewById(R.id.ImageView_register_back);
        RelativeLayoutCity = (RelativeLayout) v.findViewById(R.id.RelativeLayout_register_city);

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

        return v;
    }

}