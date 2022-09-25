package com.example.iot_project.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.LoginActivity;
import com.example.iot_project.R;
import com.example.iot_project.SellerRegister.BecomeSellerActivity;
import com.example.iot_project.member.MemberActivity;
import com.example.iot_project.register.RegisterActivity;
import com.example.iot_project.shoppingCart.ShoppingCartActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TextView textViewBecomeSaller, textViewLogin;
    private Button buttonLogin, buttonEnroll;
    private RelativeLayout relativeLayout;
    private ImageButton imageButtonMember;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View headerView;
    private ImageView imageView;
    private ImageButton imagebuttonShoppingCart;
    private MainViewPagerAdapter pagerAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      To hide Action Bar
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        imagebuttonShoppingCart = (ImageButton) findViewById(R.id.imageButton_main_shoppingcart);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout_main_bottom);
//      Login button => LoginActivity
        buttonLogin = (Button) findViewById(R.id.button_main_login);
//      (temp) Enroll button => BecomeSellerActivity.class
        buttonEnroll = (Button) findViewById(R.id.button_main_enroll);

//      目的 : 建立側開表單 NavigationView，以Imagebutton為觸發元件 -----------------------------------------
////    reference : https://material.io/components/navigation-drawer/android#using-navigation-drawers
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_id);
        navigationView = (NavigationView) findViewById(R.id.navigationView_id);
        //      預設headerLayout :
//      方法1 : 就是預設顯示頂端的貓貓圖片 + 下面兩行字 ，但是貓貓圖片就不能改
//      在 activity_main.xml 的 com.google.android.material.navigation.NavigationView
//      加上 這列 : app:headerLayout="@layout/header_navigation_drawer"
//      方法2 : 可以用 code 修改預設貓貓圖片，改成其他圖片，方法1加入的那列 app:headerLayout 要刪掉
        headerView = navigationView.inflateHeaderView(R.layout.header_navigation_drawer);
        imageView = (ImageView) headerView.findViewById(R.id.imageView_id);
        imageView.setImageResource(R.drawable.pearls);
        imageButtonMember = (ImageButton) findViewById(R.id.imageButton_main_member);

//        -------------------------------------------------------------------------------
//       目的: 建立Tablayout
//        1. set fragment content : create ViewPagerAdapter(extends FragmentStateAdapter)
        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

//        2. set fragment content on ViewPager2 widget.
        viewPager = (ViewPager2) findViewById(R.id.ViewPager_main_id);
        viewPager.setAdapter(pagerAdapter);

//        3. using TabLayoutMediator().attach() :
//        combine TabLayout widget and ViewPager2 widget.
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_main_id);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Tab 1");
                        break;
                    case 1:
                        tab.setText("Tab 2");
                        break;
                    case 2:
                        tab.setText("Tab 3");
                        break;
                    case 3:
                        tab.setText("Tab 4");
                        break;
                }
            }
        }).attach();

//      set button with Login status
        setButton(isLogin());

    }

    @Override
    protected void onResume() {
        super.onResume();
        // set button with Login status
        setButton(isLogin());
    }

    //判斷使用者登入狀態 : SharedPreferences"LoginInformation" : "is_login" key，
    // true為已登入，false為未登入
    private boolean isLogin() {
        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
        Boolean is_Login = sp.getBoolean("is_login", false);
        return is_Login;
    }

    private boolean isLogin(boolean status) {
        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
        sp.edit().putBoolean("is_login", status).commit();
        Boolean is_Login = sp.getBoolean("is_login", false);
        return is_Login;
    }

    private void setButton(boolean login) {
        imageButtonMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.imageButton_main_member) {

                    if (login) {
                        drawerLayout.openDrawer(GravityCompat.START);
                    } else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });

        imagebuttonShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.imageButton_main_shoppingcart) {
                    if (login) {
                        Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        if (login) {//已登入
//          不顯示首頁下方的兩個登入註冊按鈕
            linearLayout.setVisibility(View.INVISIBLE);

            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); //開啟NavigationView
            TextView textViewNavAccount = (TextView) headerView.findViewById(R.id.textView_main_nav_account);
            SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
            String accountNAme = sp.getString("account_name", "account");
            textViewNavAccount.setText(accountNAme);

            //        按下 NavigationView 對應的 menu 會顯示 Toast
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    String message = "";
                    switch (item.getItemId()) {
                        case R.id.menu_main_member:
//                            message = "item1";
                            Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.menu_main_logout:
//                            message = "item5";
                            isLogin(false);
//                            Toast.makeText(MainActivity.this, "登出成功!!", Toast.LENGTH_SHORT).show();
//                            recreate();
//                          跳至登出成功頁面 : LogoutActivity => 顯示 1 秒後重新導向至首頁 MainActivity
                            Intent intentlogout = new Intent(MainActivity.this, LogoutActivity.class);
                            startActivity(intentlogout);
                            break;
                    }
//                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });


        } else { //未登入
//          顯示首頁下方的兩個登入註冊按鈕
            linearLayout.setVisibility(View.VISIBLE);

            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//鎖住NavigationView

//           Login button => LoginActivity 登入頁面
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
//          Enroll button => RegisterActivity
            buttonEnroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

        }


    }

    @Override
    public void onBackPressed() { //set user cannot return to previous activity
        moveTaskToBack(true);
    }
}