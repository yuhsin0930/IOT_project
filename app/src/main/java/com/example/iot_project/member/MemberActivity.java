package com.example.iot_project.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.R;

public class MemberActivity extends AppCompatActivity {

    private Window window;
    private MemberGoodsFragment memberGoodsFragment;
    private MemberOrdersFragment memberOrdersFragment;
    private MemberFragment memberFragment;
    private MemberOrdersDetailedFragment memberOrdersDetailedFragment;
    private MemberCouponFragment memberCouponFragment;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private long timeTemp;
    private Boolean GoodsOrdersFlag, DetailedFlag, CouponFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        setData();
        setFragment();
    }

    private void setData(){
        getSupportActionBar().hide();              // 隱藏ActionBar
        window = getWindow();
        window.setNavigationBarColor(0xaaffffff);  // 下面NavigationBar白色底
        GoodsOrdersFlag = false;
        DetailedFlag = false;
        CouponFlag = false;
        setStatusBarColor("orange");
    }

    public void setFragment() {
        memberFragment = new MemberFragment();
        fragmentMgr = getSupportFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_member, memberFragment, "memberFragment");
        fragmentTrans.commit();
    }

    public void setStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        switch (color) {
            case "orange":
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.Mycolor_1));
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
                break;
            case "white":
                window.setStatusBarColor(0xffffffff);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);  // 黑字
                break;
        }
    }

    public void showOrdersFragment(int whichTab) {
        GoodsOrdersFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        if (fragmentMgr.findFragmentByTag("memberOrdersFragment") == null) {
            memberOrdersFragment = MemberOrdersFragment.newInstance(whichTab);
            fragmentTrans.add(R.id.FrameLayout_member, memberOrdersFragment, "memberOrdersFragment");
            fragmentTrans.addToBackStack(null);
        } else {
            fragmentTrans.show(memberOrdersFragment);
        }
        fragmentTrans.hide(memberFragment);
        fragmentTrans.commit();
        setStatusBarColor("white");
    }

    public void showGoodsFragment(String barName) {
        GoodsOrdersFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        if (fragmentMgr.findFragmentByTag("memberGoodsFragment") == null) {
            memberGoodsFragment = MemberGoodsFragment.newInstance(barName);
            fragmentTrans.add(R.id.FrameLayout_member, memberGoodsFragment, "memberGoodsFragment");
            fragmentTrans.addToBackStack(null);
        } else {
            fragmentTrans.show(memberGoodsFragment);
        }
        fragmentTrans.hide(memberFragment);
        fragmentTrans.commit();
        setStatusBarColor("white");
    }

    public void showCouponFragment(String barName) {
        CouponFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        if (fragmentMgr.findFragmentByTag("memberCouponFragment") == null) {
            memberCouponFragment = new MemberCouponFragment();
            fragmentTrans.add(R.id.FrameLayout_member, memberCouponFragment, "memberCouponFragment");
            fragmentTrans.addToBackStack(null);
        } else {
            fragmentTrans.show(memberCouponFragment);
        }
        fragmentTrans.hide(memberFragment);
        fragmentTrans.commit();
        setStatusBarColor("white");
    }

    public void showOrdersDetailedFragment(String barName) {
        DetailedFlag = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        if (fragmentMgr.findFragmentByTag("memberOrdersDetailedFragment") == null) {
            memberOrdersDetailedFragment = MemberOrdersDetailedFragment.newInstance(barName);
            fragmentTrans.add(R.id.FrameLayout_member, memberOrdersDetailedFragment, "memberOrdersDetailedFragment");
            fragmentTrans.addToBackStack(null);
        } else {
            fragmentTrans.show(memberOrdersDetailedFragment);
        }
        fragmentTrans.hide(memberOrdersFragment);
        fragmentTrans.commit();
        setStatusBarColor("white");
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - timeTemp) > 300) {
            timeTemp = System.currentTimeMillis();
        if (DetailedFlag) {
            DetailedFlag = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.hide(memberOrdersDetailedFragment);
            fragmentTrans.show(memberOrdersFragment);
            fragmentTrans.commit();
            Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        } else if (GoodsOrdersFlag) {
            GoodsOrdersFlag = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            if (fragmentMgr.findFragmentByTag("memberOrdersFragment") != null) {
                fragmentTrans.hide(memberOrdersFragment);
            }
            if (fragmentMgr.findFragmentByTag("memberGoodsFragment") != null) {
                fragmentTrans.hide(memberGoodsFragment);
            }
            fragmentTrans.show(memberFragment);
            fragmentTrans.commit();
            Toast.makeText(this, "BBB", Toast.LENGTH_SHORT).show();
        } else if (CouponFlag) {
            CouponFlag = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.hide(memberCouponFragment);
            fragmentTrans.show(memberFragment);
            fragmentTrans.commit();
            Toast.makeText(this, "ccc", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStack();
                    Log.d("member", "getBackStackEntryCount = " + i);
                }
                super.onBackPressed();
            }
        }
    }

}

// https://www.twblogs.net/a/5b800a5c2b717767c6b2fd52 Fragment各種操作與生命週期的關聯