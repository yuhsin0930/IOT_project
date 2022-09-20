package com.example.iot_project.member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iot_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberOrdersDetailedItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberOrdersDetailedItemFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String tag;



    public static MemberOrdersDetailedItemFragment newInstance(String tag) {
        MemberOrdersDetailedItemFragment fragment = new MemberOrdersDetailedItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tag = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_orders_detaileditem, container, false);

        return view;
    }
}