package com.example.iot_project.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.iot_project.R;
import com.example.iot_project.member.MemberCouponFragment;

import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private FragmentManager fragmentMgr;
    private CartFragment cartFragment;
    private CartCouponFragment cartCouponFragment;
    private boolean isCouponShow;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setWindow();
        setFragment();
        isCouponShow = false;
        i = 2;

    }

    private void setWindow() {
        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(0xffffffff);      // 最上面StatusBar白色底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
    }

    private void setFragment() {
        fragmentMgr = getSupportFragmentManager();
        cartFragment = new CartFragment();
        fragmentMgr.beginTransaction()
                .add(R.id.FrameLayout_Cart, cartFragment, "cartFragment")
                .addToBackStack("")
                .commit();
    }


    public void showCouponFragment() {
        if (fragmentMgr.findFragmentByTag("cartCouponFragment") == null) {
            cartCouponFragment = new CartCouponFragment();
            fragmentMgr.beginTransaction()
                    .setCustomAnimations(R.anim.trans_in_from_botton, R.anim.no_anim)
                    .add(R.id.FrameLayout_Cart, cartCouponFragment, "cartCouponFragment")
                    .addToBackStack("")
                    .commit();
            i = 3;
        } else {
            fragmentMgr.beginTransaction()
                    .setCustomAnimations(R.anim.trans_in_from_botton, R.anim.no_anim)
                    .show(cartCouponFragment)
                    .commit();
        }
        isCouponShow = true;
    }

    @Override
    public void onBackPressed() {
        if (isCouponShow) {
            fragmentMgr.beginTransaction()
                    .setCustomAnimations(R.anim.no_anim, R.anim.trans_out_to_bottom)
                    .hide(cartCouponFragment)
                    .commit();
            isCouponShow = false;
        } else {
            if (fragmentMgr.getBackStackEntryCount() < i) finish();
            else fragmentMgr.popBackStack();
        }
    }

}