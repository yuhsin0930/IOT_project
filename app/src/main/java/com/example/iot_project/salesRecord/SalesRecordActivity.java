package com.example.iot_project.salesRecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.iot_project.NewProduct.SetPriceFragment;
import com.example.iot_project.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesRecordActivity extends AppCompatActivity {

    private ViewPager2 SalesRecord_ViewPager;
    private TabLayout SalesRecord_tabLayout;
    private List<Object> salesRecordtabTitle;
    private FragmentManager FragManager;
    private FragmentTransaction fragTransit;
    private ToBeShipDetailFragment toBeShipDetailFrag;
    private OrderToBeShipFragment OrderToBeShipFragment;


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

    public void orderToBeShipDetail(Map<String,Object> orderMap){
        Map<String, Object> data = orderMap;
        String orderNum = data.get("orderNum").toString();
        String productName = data.get("productName").toString();
        int productNum = (int)data.get("productNum");
        int productPrice = (int)data.get("productPrice");
        int allProductNum = (int)data.get("allProductNum");
        int totalPrice = (int)data.get("totalPrice");
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add(orderNum);
        orderList.add(productName);
        orderList.add(String.valueOf(productNum));
        orderList.add(String.valueOf(productPrice));
        orderList.add(String.valueOf(allProductNum));
        orderList.add(String.valueOf(totalPrice));

        FragManager = getSupportFragmentManager();
        fragTransit = FragManager.beginTransaction();
        toBeShipDetailFrag = ToBeShipDetailFragment.newInstance(orderList,orderNum);
        fragTransit.add(R.id.frameLayout_tobeship, toBeShipDetailFrag,orderNum)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragManager = getSupportFragmentManager();
        if (fragManager.getBackStackEntryCount() < 1) finish();
        else fragManager.popBackStack();
    }
}






