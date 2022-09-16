package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SalesRecordActivity extends AppCompatActivity {

    private ViewPager2 SalesRecord_ViewPager;
    private TabLayout SalesRecord_tabLayout;
    private List<Object> salesRecordtabTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_record);
        SalesRecord_ViewPager = (ViewPager2) findViewById(R.id.SalesRecord_ViewPager);
        SalesRecord_tabLayout = (TabLayout)findViewById(R.id.tabLayout_salesRecord);

        salesRecordtabTitle = new ArrayList<>();
        salesRecordtabTitle.add("待出貨");
        salesRecordtabTitle.add("不成立");
        salesRecordtabTitle.add("退貨/退款");
        salesRecordtabTitle.add("已出貨");
        salesRecordtabTitle.add("已完成");

        SalesRecordPageAdapter pageAdapter = new SalesRecordPageAdapter(this);
        SalesRecord_ViewPager.setAdapter(pageAdapter);

        new TabLayoutMediator(SalesRecord_tabLayout, SalesRecord_ViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText((CharSequence) salesRecordtabTitle.get(position));
            }
        }).attach();

    }
}