package com.example.iot_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class RegisterFragment extends Fragment {

    private FragmentManager fm;
    private FragmentTransaction ft;

    public RegisterFragment() {

    }

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
        RelativeLayout RelativeLayoutCity = (RelativeLayout) v.findViewById(R.id.RelativeLayout_register_city);

        RelativeLayoutCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterCityFragment registerCityFragment = new RegisterCityFragment();
                RegisterFragment registerFragment = new RegisterFragment();
                fm = getParentFragmentManager();
                ft = fm.beginTransaction();
                ft.hide(registerFragment);
                ft.add(R.id.FrameLayout_register, registerCityFragment, "registerFragment");
                ft.commit();
            }
        });

        return v;
    }
}