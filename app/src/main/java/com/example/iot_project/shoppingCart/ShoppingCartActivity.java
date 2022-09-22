package com.example.iot_project.shoppingCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.iot_project.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private ViewPager2 ShoppingCartViewPager;
    private TabLayout shoppingTabLayout;
    private List<Object> shoppingCartTabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ShoppingCartViewPager = (ViewPager2) findViewById(R.id.ShoppingCart_ViewPager);
        shoppingTabLayout = (TabLayout)findViewById(R.id.shoppingCart_tabLayout);

        shoppingCartTabTitle = new ArrayList<>();
        shoppingCartTabTitle.add("所有商品");
        shoppingCartTabTitle.add("再買一次");
        PagerAdapter pageAdapter = new PagerAdapter(this);
        ShoppingCartViewPager.setAdapter(pageAdapter);

        new TabLayoutMediator(shoppingTabLayout, ShoppingCartViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText((CharSequence) shoppingCartTabTitle.get(position));
            }
        }).attach();


    }
}