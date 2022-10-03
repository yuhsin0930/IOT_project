package com.example.iot_project.salesRecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.iot_project.MyStore.MyStoreActivity;
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
    private OrderDetailFragment toBeShipDetailFrag;
    private OrderToBeShipFragment OrderToBeShipFragment;
    private ImageView imageViewSalesRecordBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_record);
        setWindow();
        SalesRecord_ViewPager = (ViewPager2) findViewById(R.id.SalesRecord_ViewPager);
        SalesRecord_tabLayout = (TabLayout)findViewById(R.id.tabLayout_salesRecord);
        imageViewSalesRecordBack = (ImageView)findViewById(R.id.imageView_salesRecord_back);

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

        imageViewSalesRecordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesRecordActivity.this, MyStoreActivity.class);
                startActivity(intent);
            }
        });

    }

    public void orderToBeShipDetail(Map<String,Object> orderMap,int allProductNum,int totalPrice){
        Map<String, Object> data = orderMap;
        String orderNum = data.get("orders_id").toString(); //訂單編號
        String productName = data.get("goods_name").toString(); //商品名稱
        int productPrice = (int)data.get("price"); //商品售價
        int productNum = (int)data.get("sum"); //商品數量
        byte[] goodsPicture = (byte[]) data.get("goodsPicture"); //商品照片

        String account_name = data.get("account_name").toString(); // 買家帳號
        String pickupName = data.get("pickupName").toString();  //取件人姓名
        String payWay = data.get("payway").toString(); //付款方式
        String payTime = data.get("payTime").toString(); //付款時間
        String pickupWay = data.get("pName").toString();  //取件方式
        String pickupTime = data.get("pickupTime").toString(); //取件時間
        String pickupPlace = data.get("pickupPlace").toString(); //取貨地址
        String createTime = data.get("createTime").toString(); // 訂單成立時間




        ArrayList<String> orderList = new ArrayList<>();
        orderList.add(orderNum);
        orderList.add(productName);
        orderList.add(String.valueOf(productNum));
        orderList.add(String.valueOf(productPrice));
        orderList.add(String.valueOf(allProductNum));
        orderList.add(String.valueOf(totalPrice));
        orderList.add(String.valueOf(goodsPicture));
        orderList.add(account_name);
        orderList.add(pickupName);
        orderList.add(payWay);
        orderList.add(payTime);
        orderList.add(pickupWay);
        orderList.add(pickupTime);
        orderList.add(pickupPlace);
        orderList.add(createTime);





        FragManager = getSupportFragmentManager();
        fragTransit = FragManager.beginTransaction();
        toBeShipDetailFrag = OrderDetailFragment.newInstance(orderList,orderNum);
        fragTransit.add(R.id.layout_salesRecord, toBeShipDetailFrag,orderNum)
                .addToBackStack("")
                .commit();
    }
    private void setWindow() {
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(0xFFFFFF);
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
    @Override
    public void onBackPressed() {
        FragmentManager fragManager = getSupportFragmentManager();
        if (fragManager.getBackStackEntryCount() < 1) finish();
        else fragManager.popBackStack();
    }


}






