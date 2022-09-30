package com.example.iot_project.Cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.R;

import java.util.HashMap;
import java.util.Map;

public class CartCouponItemFragment extends Fragment implements View.OnClickListener {


    private String thisFragTag;
    private CartActivity cartActivity;
    private CartCouponFragment cartCouponFragment;
    private View view;
    private CheckBox checkBoxSelect;
    private View viewWhite;
    private CardView cardViewCoupon;
    private Map<String, Object> insideMap;
    private boolean isExist;
    private CartAllProductFragment cartAllProductFragment;

    public CartCouponItemFragment() {}

    public static CartCouponItemFragment newInstance(String thisFragTag) {
        CartCouponItemFragment fragment = new CartCouponItemFragment();
        Bundle args = new Bundle();
        args.putString("thisFragTag", thisFragTag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            thisFragTag = getArguments().getString("thisFragTag");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cartActivity = (CartActivity)getActivity();
        cartCouponFragment = (CartCouponFragment)getParentFragmentManager().findFragmentById(R.id.FrameLayout_Cart);
        cartAllProductFragment = (CartAllProductFragment)getParentFragmentManager().findFragmentByTag("f0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_coupon_item, container, false);

        findView();
        setData();
        setListener();

        makeAndSendInsideMap();
        checkMinimum(0);

        return view;
    }

    private void findView() {
        cardViewCoupon = (CardView) view.findViewById(R.id.CardView_cart_coupon);
        checkBoxSelect = (CheckBox)view.findViewById(R.id.checkBox_cart_coupon_select);
        viewWhite = (View)view.findViewById(R.id.view_cart_coupon_white);
    }

    private void setData() {
        insideMap = new HashMap<>();
        isExist = true;
    }

    private void setListener() {
        viewWhite.setOnClickListener(this);
        cardViewCoupon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.CardView_cart_coupon:
                //        cartCouponFragment.deleteFragment(thisFragTag); // 刪除自身fragment
                checkBoxSelect.setChecked(!checkBoxSelect.isChecked());
                makeAndSendInsideMap();
                break;
            case R.id.view_cart_coupon_white:
                Toast.makeText(cartActivity, "未符合使用條件", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // 每個監聽都呼叫此方法
    private void makeAndSendInsideMap() {
        insideMap.put("id", thisFragTag);
        insideMap.put("checkBoxFlag", checkBoxSelect.isChecked());
        insideMap.put("isExist", isExist);
        Log.d("cart", "insideMap(coupon) = " + insideMap);
        cartAllProductFragment.setOutSideCouponMap(insideMap);
    }

    // CartAllProduct那邊收到有人被選擇時 會呼叫此方法要大家檢查是不是自己 boolean b = true 代表有人被呼叫
    public void setViewWhiteVisibility(boolean b) {
        Log.d("cartItem", "b = " + b);
        if (!checkBoxSelect.isChecked() && b) {
            viewWhite.setVisibility(View.VISIBLE);
        } else {
            viewWhite.setVisibility(View.GONE);
        }
    }


    // 確認自身條件是否符合目前金額
    public void checkMinimum(int subTotal) {
        Log.d("cart", "subTotal = " + subTotal);
        if (subTotal >= 50) {   // 條件50來自資料庫
            viewWhite.setVisibility(View.GONE);
        } else {
            viewWhite.setVisibility(View.VISIBLE);
            checkBoxSelect.setChecked(false);
        }
    }







// 一進這裡就先給一個假金額為0的呼叫
// 將不符合的全關上+取消勾選 符合的維持開啟 (譬如金額需 > 50)    // 這裡會全開
// 剩下還打開的判斷有沒有人被選 再把沒被選的關上 // 如果沒有自然發生就去呼叫ALL裡的方法
//
//
//


}