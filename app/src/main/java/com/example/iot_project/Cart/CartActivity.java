package com.example.iot_project.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import com.example.iot_project.R;
import com.example.iot_project.member.MemberCouponFragment;

public class CartActivity extends AppCompatActivity {

    private FragmentManager fragmentMgr;
    private CartFragment cartFragment;
    private CartCouponFragment cartCouponFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setWindow();
        setFragment();

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
        if (fragmentMgr.findFragmentByTag("memberCouponFragment") == null) {
            cartCouponFragment = new CartCouponFragment();
            fragmentMgr.beginTransaction()
                    .setCustomAnimations(R.anim.trans_in_from_botton, R.anim.no_anim, R.anim.no_anim, R.anim.trans_out_to_bottom)
                    .add(R.id.FrameLayout_Cart, cartCouponFragment, "cartCouponFragment")
                    .addToBackStack("")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentMgr.getBackStackEntryCount() < 2) finish();
        else fragmentMgr.popBackStack();
    }

}