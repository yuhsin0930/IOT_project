package com.example.iot_project.Admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.iot_project.R;
import com.google.android.material.navigation.NavigationView;

public class AdminMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private TextView textView;
    private TextView textViewAccount;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //      目的 : 建立側開表單 NavigationView，以Imagebutton為觸發元件 -----------------------------------------
////    reference : https://material.io/components/navigation-drawer/android#using-navigation-drawers
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_admin_id);
        navigationView = (NavigationView) findViewById(R.id.navigationView_admin_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_admin_id);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START,true);
            }
        });
        //      預設headerLayout :
//      方法1 : 就是預設顯示頂端的貓貓圖片 + 下面兩行字 ，但是貓貓圖片就不能改
//      在 activity_main.xml 的 com.google.android.material.navigation.NavigationView
//      加上 這列 : app:headerLayout="@layout/header_navigation_drawer"
//      方法2 : 可以用 code 修改預設貓貓圖片，改成其他圖片，方法1加入的那列 app:headerLayout 要刪掉
        View headerView = navigationView.inflateHeaderView(R.layout.header_navigation_drawer);
        imageView = (ImageView) headerView.findViewById(R.id.imageView_id);
        imageView.setImageResource(R.drawable.photo);
        textView = (TextView) headerView.findViewById(R.id.textView_nav_identity);
        textView.setText("管理者");
        textViewAccount = (TextView) headerView.findViewById(R.id.textView_main_nav_account);
        Intent intent = getIntent();
        String adminAccount = intent.getStringExtra("adminAccount");
        textViewAccount.setText(adminAccount);

//      fragment
        fragmentManager = getSupportFragmentManager() ;



    }


}