package com.example.iot_project.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.example.iot_project.R;

public class RegisterActivity extends AppCompatActivity {

    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private RegisterFragment registerFragment;
    private RegisterCityFragment registerCityFragment;
    private RegisterDistrictFragment registerDistrictFragment;
    private boolean cityFlag = false, districtFlag = false;
    private Intent intent;
    private InputMethodManager kryboard;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(0xffffffff);      // 最上面StatusBar白色底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        kryboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        kryboard.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        intent = getIntent();
        registerFragment = new RegisterFragment();
        registerCityFragment = new RegisterCityFragment();
        registerFragment.setTextViewBarName(intent.getStringExtra("name"));

        // 假裝未登入
        registerFragment.isLoggedIn(intent.getBooleanExtra("isFromRegister", false));

        fragmentMgr = getSupportFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_register, registerFragment, "registerFragment");
        fragmentTrans.add(R.id.FrameLayout_register, registerCityFragment, "memberGoodsFragment");
        fragmentTrans.hide(registerCityFragment);
        fragmentTrans.commit();
    }

    private long timeTemp;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - timeTemp) > 2000) {
            timeTemp = System.currentTimeMillis();
            if (districtFlag) {
                districtFlag = false;
                cityFlag = false;
                fragmentTrans = fragmentMgr.beginTransaction();
                fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
                fragmentTrans.hide(registerDistrictFragment);
                fragmentTrans.show(registerFragment);
                fragmentTrans.commit();
            } else if (cityFlag) {
                cityFlag = false;
                fragmentTrans = fragmentMgr.beginTransaction();
                fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
                fragmentTrans.hide(registerCityFragment);
                fragmentTrans.show(registerFragment);
                fragmentTrans.commit();
            } else {
                super.onBackPressed();
            }
        }
    }

    public void addDistrictFragment() {
        registerDistrictFragment = new RegisterDistrictFragment();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_register, registerDistrictFragment, "registerDistrictFragment");
        fragmentTrans.hide(registerDistrictFragment);
        fragmentTrans.commit();
    }


    public void showCity() {
        cityFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(registerCityFragment);
        fragmentTrans.hide(registerFragment);
        fragmentTrans.commit();
    }

    public void showDistrict() {
        districtFlag = true;
        registerDistrictFragment.setCityName(cityName);
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(registerDistrictFragment);
        fragmentTrans.hide(registerCityFragment);
        fragmentTrans.commit();
    }

    public void setCityName(String cityName) {
        registerFragment.setCityName(cityName);
        this.cityName = cityName;
    }

    public void setDistrictName(String districtName) {
        registerFragment.setDistrictName(districtName);
    }
    
}