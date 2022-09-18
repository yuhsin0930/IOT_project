package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private RegisterFragment registerFragment;
    private RegisterCityFragment registerCityFragment;
    private boolean fragFlag = false;;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(0xffffffff);      // 最上面StatusBar白色底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        intent = getIntent();
        registerFragment = new RegisterFragment();
        registerCityFragment = new RegisterCityFragment();
        registerFragment.setTextViewBarName(intent.getStringExtra("name"));
        registerFragment.setIsFromRegister(intent.getBooleanExtra("isFromRegister", true));
        fragmentMgr = getSupportFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_register, registerFragment, "registerFragment");
        fragmentTrans.add(R.id.FrameLayout_register, registerCityFragment, "memberGoodsFragment");
        fragmentTrans.hide(registerCityFragment);
        fragmentTrans.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragFlag) {
            fragFlag = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.hide(registerCityFragment);
            fragmentTrans.show(registerFragment);
            fragmentTrans.commit();
        } else {
            super.onBackPressed();
        }
    }

    public void showCity() {
        fragFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(registerCityFragment);
        fragmentTrans.hide(registerFragment);
        fragmentTrans.commit();
    }

    public void setCityName(String cityName) {
        registerFragment.setCityName(cityName);
    }

}