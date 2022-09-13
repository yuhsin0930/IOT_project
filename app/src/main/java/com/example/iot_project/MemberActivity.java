package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {

    private MemberGoodsFragment memberGoodsFragment;
    private MemberOrdersFragment memberOrdersFragment;
    private MemberFragment memberFragment;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private boolean flag_Orders = false;
    private boolean flag_Goods = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Mycolor_1));      // 最上面StatusBar橘底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        memberFragment = new MemberFragment();
        memberOrdersFragment = new MemberOrdersFragment();
        memberGoodsFragment = new MemberGoodsFragment();
        fragmentMgr = getSupportFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.add(R.id.FrameLayout_member, memberFragment, "memberFragment");
        fragmentTrans.commit();
    }

    public void showOrders() {
       flag_Orders = true;
       fragmentTrans = fragmentMgr.beginTransaction();
       fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
       fragmentTrans.add(R.id.FrameLayout_member, memberOrdersFragment, "memberOrdersFragment");
       fragmentTrans.commit();
    }
    public void showGoods() {
        flag_Goods = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.add(R.id.FrameLayout_member, memberGoodsFragment, "memberGoodsFragment");
        fragmentTrans.commit();
    }

    @Override
    public void onBackPressed() {
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
        if (flag_Orders) {
            flag_Orders = false;
            fragmentTrans.hide(memberOrdersFragment);
        }
        if (flag_Goods) {
            flag_Goods = false;
            fragmentTrans.hide(memberGoodsFragment);
        }
        fragmentTrans.show(memberFragment);
        fragmentTrans.commit();
        if (!flag_Orders && !flag_Goods) super.onBackPressed();
    }

}




// https://www.twblogs.net/a/5b800a5c2b717767c6b2fd52 Fragment各種操作與生命週期的關聯
//            for (int i = 0; i < fragmentMgr.getBackStackEntryCount(); ++i) {
//                fragmentMgr.popBackStack();
//            }