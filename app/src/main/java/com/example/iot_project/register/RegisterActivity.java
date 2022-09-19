package com.example.iot_project.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.example.iot_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.Map;

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
    private Map<String, Object> fireMap;




    public void setFireMap(Map fireMap) {
        this.fireMap = fireMap;
    }

    public void Log_d_fireMap() {
        String account = fireMap.get("account").toString();
        Log.d("main", "account: " + account);
        Log.d("main", "fireMap: " + fireMap);
//       [BUG] 只收到部分資料，但是在輸入時所有欄位都有輸入資料
//        fireMap: {birthday=null, bankNumber=null, bankAccount=null,
//        password=null, address=幼獅路一段23號, phone=null,
//        city=新竹市, district=北 區, name=null, account=user1, email=null}
    }

    public void MapUploadToFireBase() {
//      使用 Firebase 服務
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//      取得  Firebase 資料庫 (GET網址)
        DatabaseReference dataref = database.getReference();
        dataref.child("user").push().setValue(fireMap);
    }





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
        if ((System.currentTimeMillis() - timeTemp) > 300) {
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