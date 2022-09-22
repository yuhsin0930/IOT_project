package com.example.iot_project.MyProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iot_project.NewProduct.NewProductActivity;
import com.example.iot_project.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MyProductActivity extends AppCompatActivity {

    private ViewPager2 myProduct_ViewPager;
    private TabLayout myProudct_tabLayout;
    private List<Object> myProducttabTitle;
    private Button addProduct_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);

        myProduct_ViewPager = (ViewPager2) findViewById(R.id.myProduct_ViewPager);
        myProudct_tabLayout = (TabLayout)findViewById(R.id.tabLayout_myProduct);

        myProducttabTitle = new ArrayList<>();
        myProducttabTitle.add("架上商品");
        myProducttabTitle.add("已售完");
        myProducttabTitle.add("審核中");
        myProducttabTitle.add("已違規");

        MyProductPageAdapter pageAdapter = new MyProductPageAdapter(this);
        myProduct_ViewPager.setAdapter(pageAdapter);

        new TabLayoutMediator(myProudct_tabLayout, myProduct_ViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText((CharSequence) myProducttabTitle.get(position));
                if(position==0){

                }else if(position==1){

                }else if(position==2){

                }else if(position==3){

                }
            }
        }).attach();

        addProduct_button = (Button)findViewById(R.id.button_myProduct_addProduct);
        addProduct_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProductActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });
    }
}