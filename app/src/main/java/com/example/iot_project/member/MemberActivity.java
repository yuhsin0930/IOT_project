package com.example.iot_project.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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

    public void showOrders(int whichTab) {
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

    public void showGoods(String barName) {
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

    public void showCoupon(String barName) {
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

    public void showOrdersDetailed(String barName) {
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        if (fragmentMgr.findFragmentByTag("memberOrdersDetailedFragment") == null) {
            memberOrdersDetailedFragment = MemberOrdersDetailedFragment.newInstance(barName);
            fragmentTrans.add(R.id.FrameLayout_member, memberOrdersDetailedFragment, "memberOrdersDetailedFragment");
            fragmentTrans.addToBackStack(null);
        } else {
            fragmentTrans.show(memberOrdersDetailedFragment);
        }
        fragmentTrans.hide(memberFragment);
        fragmentTrans.commit();
        setStatusBarColor("white");
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
            Log.d("main", "ccc");
        } else {
            Log.d("main", "ddd");
            getSupportFragmentManager().popBackStack();
            setStatusBarColor("orange");
        }
    }

}

// https://www.twblogs.net/a/5b800a5c2b717767c6b2fd52 Fragment各種操作與生命週期的關聯