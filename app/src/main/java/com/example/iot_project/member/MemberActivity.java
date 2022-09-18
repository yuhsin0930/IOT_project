package com.example.iot_project.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.example.iot_project.R;

public class MemberActivity extends AppCompatActivity {

    private MemberGoodsFragment memberGoodsFragment;
    private MemberOrdersFragment memberOrdersFragment;
    private MemberFragment memberFragment;
    private MemberOrdersDetailedFragment memberOrdersDetailedFragment;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private Window window;
    private boolean DetailFlag = false;
    private boolean OrdersGoodsFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        window = getWindow();
        getSupportActionBar().hide();              // 隱藏ActionBar
        window.setNavigationBarColor(0xaaffffff);  // 下面NavigationBar白色底
        setWindowOrange();
        initFragment();
    }

    @Override
    public void onBackPressed() {
        if (DetailFlag) {
            DetailFlag = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.hide(memberOrdersDetailedFragment);
            fragmentTrans.show(memberOrdersFragment);
            fragmentTrans.commit();
        } else if (OrdersGoodsFlag) {
            OrdersGoodsFlag = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.hide(memberOrdersFragment);
            fragmentTrans.hide(memberGoodsFragment);
            fragmentTrans.show(memberFragment);
            fragmentTrans.commit();
            setWindowOrange();
        } else {
            super.onBackPressed();
        }
    }

    public void initFragment() {
        memberFragment = new MemberFragment();
        memberOrdersFragment = new MemberOrdersFragment();
        memberGoodsFragment = new MemberGoodsFragment();
        memberOrdersDetailedFragment = new MemberOrdersDetailedFragment();
        fragmentMgr = getSupportFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_member, memberFragment, "memberFragment");
        fragmentTrans.add(R.id.FrameLayout_member, memberOrdersFragment, "memberOrdersFragment");
        fragmentTrans.add(R.id.FrameLayout_member, memberGoodsFragment, "memberGoodsFragment");
        fragmentTrans.add(R.id.FrameLayout_member, memberOrdersDetailedFragment, "memberOrdersDetailedFragment");
        fragmentTrans.hide(memberOrdersFragment);
        fragmentTrans.hide(memberGoodsFragment);
        fragmentTrans.hide(memberOrdersDetailedFragment);
        fragmentTrans.commit();
    }

    public void setWindowWhite() {
        window.setStatusBarColor(0xffffffff);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);  // 黑字
    }

    public void setWindowOrange() {
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.Mycolor_1));
//        window.setStatusBarColor(Color.parseColor("#FF4E41"));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
    }

    public void showOrders(int whichTab) {
        OrdersGoodsFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(memberOrdersFragment);
        fragmentTrans.hide(memberFragment);
        fragmentTrans.commit();
        memberOrdersFragment.selectWhichTab(whichTab);
        setWindowWhite();
    }

    public void showGoods(String barName) {
        OrdersGoodsFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(memberGoodsFragment);
        fragmentTrans.hide(memberFragment);
        fragmentTrans.commit();
        memberGoodsFragment.setTextViewBar(barName);
        setWindowWhite();
    }

    public void showOrdersDetailed(String barName) {
        DetailFlag = true;
        memberOrdersDetailedFragment.setTextViewBar(barName);
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(memberOrdersDetailedFragment);
        fragmentTrans.hide(memberOrdersFragment);
        fragmentTrans.commit();
    }

}

// https://www.twblogs.net/a/5b800a5c2b717767c6b2fd52 Fragment各種操作與生命週期的關聯