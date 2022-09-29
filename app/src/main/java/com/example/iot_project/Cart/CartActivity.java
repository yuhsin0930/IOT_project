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
    private Map<String, Map<String, Object>> outsideMap;
    int subTotal = 0, allSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setWindow();
        outsideMap = new HashMap<>();
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
        if (fragmentMgr.findFragmentByTag("cartCouponFragment") == null) {
            cartCouponFragment = new CartCouponFragment();
            fragmentMgr.beginTransaction()
                    .setCustomAnimations(R.anim.trans_in_from_botton, R.anim.no_anim, R.anim.no_anim, R.anim.trans_out_to_bottom)
                    .add(R.id.FrameLayout_Cart, cartCouponFragment, "cartCouponFragment")
                    .addToBackStack("")
                    .commit();
        }
    }

    public void setOutSideMap(Map insideMap) {
        this.outsideMap.put(insideMap.get("id").toString(), insideMap);
        Log.d("cart", "outsideMap = " + outsideMap);
        subTotalAndSend();
    }

    // id = String
    // price = String
    // sum = int
    // checkBoxFlag = Boolean
    // isExist = Boolean
    public void subTotalAndSend() {
        subTotal = 0;
        outsideMap.forEach((id, insideMap) -> {
            // 如果存在且被選擇 subTotal += 價格*數量
            if ((boolean)insideMap.get("isExist") && (boolean)insideMap.get("checkBoxFlag")) {
                int price = Integer.parseInt(insideMap.get("price").toString());
                int sum = (int)insideMap.get("sum");
                subTotal += price * sum;
//                allSum += sum;
            }
        });
        Log.d("cart", "subTotal = " + subTotal);
        Log.d("cart", "allSum = " + allSum);
//        CartAllProductFragment f = (CartAllProductFragment)getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.ShoppingCart_ViewPager + ":" + 0);
//        f.setTotalAmount(String.valueOf(subTotal), String.valueOf(allSum));
    }

    // 從pageView中找到Fragment
    // 參考: https://learnpainless.com/android/how-to-get-fragment-from-viewpager-android/


    @Override
    public void onBackPressed() {
        if (fragmentMgr.getBackStackEntryCount() < 2) finish();
        else fragmentMgr.popBackStack();
    }

}