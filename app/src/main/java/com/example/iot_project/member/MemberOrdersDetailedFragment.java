package com.example.iot_project.member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iot_project.R;

public class MemberOrdersDetailedFragment extends Fragment {

    private MemberActivity memberActivity;
    private ImageView ImageViewBack;
    private TextView textViewBar;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private MemberOrdersDetailedInfoFragment fragmentInfo;
    private MemberOrdersDetailedItemFragment fragmentItem;
    private MemberOrdersDetailedTimeFragment fragmentTime;

    public MemberOrdersDetailedFragment() {}

    public static MemberOrdersDetailedFragment newInstance(String param1, String param2) {
        MemberOrdersDetailedFragment fragment = new MemberOrdersDetailedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_orders_detailed, container, false);

        memberActivity = (MemberActivity)getActivity();
        ImageViewBack = (ImageView)v.findViewById(R.id.ImageView_member_back);
        textViewBar = (TextView)v.findViewById(R.id.TextView_member_bar);

        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberActivity.onBackPressed();
            }
        });

        fragmentMgr = getChildFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentInfo = new MemberOrdersDetailedInfoFragment();
        fragmentTrans.add(R.id.LinearLayout_orders_detailed, fragmentInfo, "fragmentInfo");
        fragmentTrans.commit();

        for (int i = 0; i < 5; i++) {
            fragmentTrans = fragmentMgr.beginTransaction();
//            fragmentItem = new MemberOrdersDetailedItemFragment.newInstance();
            fragmentTrans.add(R.id.LinearLayout_orders_detailed, fragmentItem, "fragmentItem");
            fragmentTrans.commit();
        }

        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTime = new MemberOrdersDetailedTimeFragment();
        fragmentTrans.add(R.id.LinearLayout_orders_detailed, fragmentTime, "fragmentTime");
        fragmentTrans.commit();

        return v;
    }

//    pubilc void deleteFragment(String) {
//        fragmentTrans = fragmentMgr.beginTransaction();
//        fragmentTrans.remove();
//        fragmentTrans.commit();
//    }

    public void setTextViewBar(String barName) {
        textViewBar.setText(barName);
    }

}