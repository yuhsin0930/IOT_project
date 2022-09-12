package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {

    private ImageView imageViewMystore, imageViewCart;
    private ImageView imageViewMypic;
    private RelativeLayout relativeLayoutBecomeSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.button_color1));      // 最上面StatusBar橘底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        imageViewMystore = (ImageView)findViewById(R.id.imageView_member_mystore);
        imageViewCart = (ImageView)findViewById(R.id.imageView_member_cart);
        imageViewMypic = (ImageView)findViewById(R.id.imageView_member_mypic);
        relativeLayoutBecomeSeller = (RelativeLayout)findViewById(R.id.relativeLayout_becomeSeller);

        imageViewMypic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.headshot));

        imageViewMystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberActivity.this, MyStoreActivity.class);
                startActivity(intent);
            }
        });

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MemberActivity.this, "這是購物車..", Toast.LENGTH_SHORT).show();
            }
        });

        relativeLayoutBecomeSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberActivity.this, BecomeSellerActivity.class);
                startActivity(intent);
                relativeLayoutBecomeSeller.setVisibility(View.GONE);
            }
        });

    }
}