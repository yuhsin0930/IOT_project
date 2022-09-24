package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.SellerRegister.BecomeSellerActivity;
import com.example.iot_project.member.MemberActivity;
import com.example.iot_project.shoppingCart.ShoppingCartActivity;
import com.google.android.material.navigation.NavigationView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      To hide Action Bar
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        imagebuttonShoppingCart = (ImageButton) findViewById(R.id.imageButton_main_shoppingcart);

//      Login button => LoginActivity
        buttonLogin = (Button) findViewById(R.id.button_main_login);
//      (temp) Enroll button => BecomeSellerActivity.class
        buttonEnroll = (Button) findViewById(R.id.button_main_enroll);

//      目的 : 建立側開表單 NavigationView，以Imagebutton為觸發元件
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

        setButton(isLogin());

    }

    @Override
    protected void onResume() {
        super.onResume();
        setButton(isLogin());
    }


//    public void refleshButton(){
//        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
//        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//                if (key.equals("is_login")) {
//                    setButton(isLogin());
//                }
//            }
//        });
//    }


    public boolean isLogin() {
        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
        Boolean is_Login = sp.getBoolean("is_login", false);
        return is_Login;
    }

    public boolean isLogin(boolean status) {
        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
        sp.edit().putBoolean("is_login", status).commit();
        Boolean is_Login = sp.getBoolean("is_login", false);
        return is_Login;
    }

    public void setButton(boolean login) {
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
        if (login) {

            buttonLogin.setVisibility(View.INVISIBLE);
            buttonEnroll.setVisibility(View.INVISIBLE);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
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
                            Toast.makeText(MainActivity.this, "登出成功!!", Toast.LENGTH_SHORT).show();
                            recreate();
                            break;
                    }
//                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });


        } else {
            buttonLogin.setVisibility(View.VISIBLE);
            buttonEnroll.setVisibility(View.VISIBLE);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

            buttonEnroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, BecomeSellerActivity.class);
                    startActivity(intent);
                }
            });

        }


    }
}