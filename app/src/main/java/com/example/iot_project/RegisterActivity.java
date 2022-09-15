package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private RegisterFragment registerFragment;
    private RegisterCityFragment registerCityFragment;
    private boolean flag_City = false;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(0xffffffff);      // 最上面StatusBar白色底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        registerFragment = new RegisterFragment();
        registerCityFragment = new RegisterCityFragment();
        fragmentMgr = getSupportFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_register, registerFragment, "registerFragment");
        fragmentTrans.commit();
    }

    public void showCity() {
        flag_City = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.add(R.id.FrameLayout_register, registerCityFragment, "memberGoodsFragment");
        fragmentTrans.commit();
    }

    @Override
    public void onBackPressed() {
        if (flag_City) {
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.replace(R.id.FrameLayout_register, registerFragment, "registerFragment");
            fragmentTrans.commit();
            flag_City = false;
        } else {
            super.onBackPressed();
        }
    }
}