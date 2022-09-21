package com.example.iot_project.sellerStore;

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

public class SellerStoreFragment extends Fragment {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private List<String> tabTitle;
    private View view;
    private SellerStoreActivity sellerStoreActivity;
    private ImageView imageViewBack;

    public static SellerStoreFragment newInstance() {
        return new SellerStoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_seller_store, container, false);
        sellerStoreActivity = (SellerStoreActivity)getActivity();
        viewPager2 = (ViewPager2)view.findViewById(R.id.ViewPager_seller_store);
        tabLayout = (TabLayout)view.findViewById(R.id.TabLayout_seller_store);
        imageViewBack = (ImageView)view.findViewById(R.id.imageView_seller_store_type_show_back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellerStoreActivity.onBackPressed();
            }
        });
        tabTitle = new ArrayList<>();
        tabTitle.add("全部商品");
        tabTitle.add("商品分類");

        SellerStoreAdapter pageAdapter = new SellerStoreAdapter(sellerStoreActivity);
        viewPager2.setAdapter(pageAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitle.get(position));
            }
        }).attach();
        
        return view;
    }
}