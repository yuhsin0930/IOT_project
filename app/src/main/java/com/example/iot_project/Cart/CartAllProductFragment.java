package com.example.iot_project.Cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartAllProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartAllProductFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View view;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private CartItemHeadFragment cartItemHeadFragment;
    private CartItemBodyFragment cartItemBodyFragment;
    private CartItemFooterFragment cartItemFooterFragment;
    private TextView textViewCheckout, textViewShipSelect, textViewDiscountSelect, textViewNoSelect;
    private TextView textViewTotal;
    private ConstraintLayout RelativeLayoutCoupon;
    private CartActivity cartActivity;
    private CheckBox checkBoxSelectAll;
    private List<Fragment> fragmentList;
    private Map<String, Map<String, Object>> outsideMap;
    private Map<String, Map<String, Object>> outsideCouponMap;
    private int subTotal, allSum, discount, shippingFree;
    private boolean isSelectAll;
    private boolean hasDiscount;
    private CartCouponItemFragment cartCouponItemFragment;

    public CartAllProductFragment() {}

    public static CartAllProductFragment newInstance(String param1, String param2) {
        CartAllProductFragment fragment = new CartAllProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_all_product, container, false);

        findView();
        setData();
        setListener();
        makeCard(2);

        return view;
    }









    // 將CartBody傳來的 insideMap 存入 outsideMap，  ( outsideMap<id, insideMap> )
    public void setOutSideMap(Map insideMap) {
        this.outsideMap.put(insideMap.get("id").toString(), insideMap);
        Log.d("cart", "outsideMap = " + outsideMap);
        subTotal(); // 再由subTotal扒開來計算
    }

    // 將CartBody傳來的Map遍歷，計算總金額與 判斷設定是否為全選選
    public void subTotal() {

        subTotal = 0;
        allSum = 0;
        isSelectAll = true;

        outsideMap.forEach((id, insideMap) -> {

            // 存在
            if ((boolean)insideMap.get("isExist")) {
                // 被勾選
                if ((boolean)insideMap.get("checkBoxFlag")) {
                    int price = Integer.parseInt(insideMap.get("price").toString());
                    int sum = (int) insideMap.get("sum");
                    subTotal += price * sum;
                    allSum += sum;
                // 未勾選
                } else {
                    // 有 存在+未勾選 = "未全選"
                    isSelectAll = false;
                }
            }

        });

        checkBoxSelectAll.setChecked(isSelectAll);  // 設定是否全選
        // 再總金額被扣之前判斷優惠券是否滿足低消
        outsideCouponMap.forEach((id, insideMap) -> {
            ((CartCouponItemFragment) getParentFragmentManager().findFragmentByTag(id)).checkMinimum(subTotal);
        });


        subTotal -= discount + shippingFree;                             // 扣除折扣與免運
        textViewTotal.setText("" + (subTotal > 0 ? subTotal : 0));  // 計算總金額，若小於零為零
        textViewCheckout.setText("去買單(" + allSum +")");          // 計算商品數量

        // 順便傳給CouponItem，看誰符合這個價格的自己亮起來 (CouponItem內部有自己的優惠條件，來自資料庫)
        // 不符合的變淺色且取消勾選


    }
    // ------------------------- insideMap中對應的資料型態
    // id = String
    // price = String
    // sum = int
    // checkBoxFlag = Boolean
    // isExist = Boolean








    // 將CouponItem傳來的 insideMap 存入 outsideCouponMap，  ( outsideCouponMap<id, insideMap> )
    public void setOutSideCouponMap(Map insideMap) {
        this.outsideCouponMap.put(insideMap.get("id").toString(), insideMap);
        Log.d("cart", "outsideCouponMap = " + outsideMap);
        setMarkShowsAndWhiteVisibility();   // 再由setMarkShowsAndWhiteVisibility判斷 其他優惠券該如何顯示 與 呼叫設定/計算價格
    }


    public void setMarkShowsAndWhiteVisibility() {

        hasDiscount = false;
        discount = 0;
        textViewNoSelect.setVisibility(View.VISIBLE);       // 顯示 [選擇優惠券]
        textViewDiscountSelect.setVisibility(View.GONE);    // 隱藏 [已選折價]

        outsideCouponMap.forEach((id, insideMap) -> {

            hasDiscount |= (boolean)insideMap.get("checkBoxFlag");
            // 先假設沒有人被選
            ((CartCouponItemFragment) getParentFragmentManager().findFragmentByTag(id)).setViewWhiteVisibility(false);
            if ((boolean)insideMap.get("checkBoxFlag")) whatDiscount(insideMap.get("id").toString());

        });

        if (hasDiscount) {    // 只要有checkbox被選

            outsideCouponMap.forEach((id, insideMap) -> {
                // 告知所有的CouponItem已有人被選了，請它們判斷是不是自己，不是就自己變淺色且取消勾選 (不能被選狀態)
                ((CartCouponItemFragment) getParentFragmentManager().findFragmentByTag(id)).setViewWhiteVisibility(true);
            });

            textViewNoSelect.setVisibility(View.GONE);          // 隱藏 [選擇優惠券]
            textViewDiscountSelect.setVisibility(View.VISIBLE); // 顯示 [已選折價]
        } else {
            textViewNoSelect.setVisibility(View.VISIBLE);
            textViewDiscountSelect.setVisibility(View.GONE);
        }

        subTotal();
    }

    // 判斷優惠總類，並設定金額，將來給subTotal使用   /////// 這部分 優惠價格應該由資料庫抓取
    private void whatDiscount(String s) {
        if (s.matches("CartCouponItemFragment.")) {
            discount = 30;
        }
        if (s.matches("shippingFee.")) {

        }
    }














    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cartActivity = (CartActivity)getActivity();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListener() {
        checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isSelectAll || b) {
                    for (Fragment f : fragmentList) {
                        ((CartItemBodyFragment) f).setCheckBox_1(b);
                    }
                }
            }
        });

        RelativeLayoutCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartActivity.showCouponFragment();
            }
        });

        textViewCheckout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        textViewCheckout.setBackgroundColor(Color.parseColor("#FF6060"));
                        break;
                    case MotionEvent.ACTION_UP:
                        textViewCheckout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Mycolor_1));
                        Toast.makeText(getContext(), "去買單", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void setData() {
        fragmentMgr = getParentFragmentManager();
        fragmentList = new ArrayList<>();
        outsideMap = new HashMap<>();
        outsideCouponMap = new HashMap<>();
        subTotal = 0;
        allSum = 0;
        discount = 0;
        shippingFree = 0;
        textViewTotal.setText("0");
        int sum = 0;
        textViewCheckout.setText("去買單(" + sum +")");
    }



    // 模擬
    public void makeCard(int count) {

        fragmentTrans = fragmentMgr.beginTransaction();
        cartItemHeadFragment = CartItemHeadFragment.newInstance("cartItemHeadFragment" + 0);
        fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemHeadFragment, "cartItemHeadFragment" + 0);
        fragmentTrans.commit();

        for (int i = 0; i < 2; i++) {
            fragmentTrans = fragmentMgr.beginTransaction();
            cartItemBodyFragment = CartItemBodyFragment.newInstance("cartItemBodyFragment" + i);
            fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemBodyFragment, "cartItemBodyFragment" + i);
            fragmentTrans.commit();
            fragmentList.add(cartItemBodyFragment);
        }

        fragmentTrans = fragmentMgr.beginTransaction();
        cartItemFooterFragment = new CartItemFooterFragment();
        fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemFooterFragment, "cartItemFooterFragment" + 0);
        fragmentTrans.commit();








        fragmentTrans = fragmentMgr.beginTransaction();
        cartItemHeadFragment = CartItemHeadFragment.newInstance("cartItemHeadFragment" + 1);
        fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemHeadFragment, "cartItemHeadFragment" + 1);
        fragmentTrans.commit();

        for (int i = 2; i < 4; i++) {
            fragmentTrans = fragmentMgr.beginTransaction();
            cartItemBodyFragment = CartItemBodyFragment.newInstance("cartItemBodyFragment" + i);
            fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemBodyFragment, "cartItemBodyFragment" + i);
            fragmentTrans.commit();
            fragmentList.add(cartItemBodyFragment);
        }

        fragmentTrans = fragmentMgr.beginTransaction();
        cartItemFooterFragment = new CartItemFooterFragment();
        fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemFooterFragment, "cartItemFooterFragment" + 1);
        fragmentTrans.commit();

    }

    private void findView() {
        textViewCheckout = (TextView)view.findViewById(R.id.textView_cart_checkout);
        textViewTotal = (TextView)view.findViewById(R.id.textView_cart_total);
        textViewNoSelect = (TextView)view.findViewById(R.id.textView_cart_no_select);
        textViewShipSelect = (TextView)view.findViewById(R.id.textView_cart_ship_select);
        textViewDiscountSelect = (TextView)view.findViewById(R.id.textView_cart_discount_select);
        RelativeLayoutCoupon = (ConstraintLayout)view.findViewById(R.id.RelativeLayout_cart_coupon);
        checkBoxSelectAll = (CheckBox)view.findViewById(R.id.checkBox_cart_selectAll);
    }
}