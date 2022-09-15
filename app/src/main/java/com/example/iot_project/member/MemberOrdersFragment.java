package com.example.iot_project.member;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iot_project.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MemberOrdersFragment extends Fragment {

    private MemberActivity memberActivity;
    private ImageView ImageViewBack;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private List<String> tabTitle;

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
        memberActivity = (MemberActivity)getActivity();

        ImageViewBack = (ImageView)v.findViewById(R.id.ImageView_member_orders_back);

        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberActivity.onBackPressed();
            }
        });





        viewPager2 = (ViewPager2)v.findViewById(R.id.viewPager2);
        tabLayout = (TabLayout)v.findViewById(R.id.t1);


        tabTitle = new ArrayList<>();

        tabTitle.add("待付款");
        tabTitle.add("待出貨");
        tabTitle.add("待收貨");

        MemberOrdersPagerAdapter pageAdapter = new MemberOrdersPagerAdapter(memberActivity);
        viewPager2.setAdapter(pageAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitle.get(position));
            }
        }).attach();
        
        
        
        
        
        
        
        
        
        
        

        return v;
    }
}