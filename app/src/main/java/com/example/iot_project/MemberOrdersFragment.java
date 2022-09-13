package com.example.iot_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberOrdersFragment extends Fragment {

    public static MemberOrdersFragment newInstance(String param1, String param2) {
        MemberOrdersFragment fragment = new MemberOrdersFragment();
        return fragment;
    }

    public MemberOrdersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_orders, container, false);

        return v;
    }
}