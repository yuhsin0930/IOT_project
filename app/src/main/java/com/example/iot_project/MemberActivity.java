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
    private boolean flag_orders = false;
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
        fragmentTrans.add(R.id.FrameLayout_member, memberFragment, "registerFragment");
        fragmentTrans.add(R.id.FrameLayout_member, memberOrdersFragment, "memberOrdersFragment");
        fragmentTrans.add(R.id.FrameLayout_member, memberGoodsFragment, "memberGoodsFragment");
        fragmentTrans.hide(memberOrdersFragment);
        fragmentTrans.hide(memberGoodsFragment);
        fragmentTrans.addToBackStack(null);                          // 多寫這行當使用者按上一頁frag1會收到背景作業
        fragmentTrans.commit();
    }

   public void showOrders() {
        flag_orders = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(memberOrdersFragment);
        fragmentTrans.hide(memberFragment);
        fragmentTrans.hide(memberGoodsFragment);
        fragmentTrans.commit();
    }

    public void showGoods() {
        flag_Goods = true;
        fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.setCustomAnimations(R.anim.trans_in_from_right, R.anim.no_anim);
        fragmentTrans.show(memberGoodsFragment);
        fragmentTrans.hide(memberFragment);
        fragmentTrans.hide(memberOrdersFragment);
        fragmentTrans.commit();
    }

    @Override
    public void onBackPressed() {
        if (flag_orders || flag_Goods) {
            flag_orders = false;
            flag_Goods = false;
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentTrans.setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_right);
            fragmentTrans.show(memberFragment);
            fragmentTrans.hide(memberOrdersFragment);
            fragmentTrans.hide(memberGoodsFragment);
            fragmentTrans.commit();
        } else {
            for (int i = 0; i < fragmentMgr.getBackStackEntryCount(); ++i) {
                fragmentMgr.popBackStack();
            }
            super.onBackPressed();
        }
    }
}