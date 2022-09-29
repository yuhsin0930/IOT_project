package com.example.iot_project.Cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.R;

public class CartCouponItemFragment extends Fragment implements View.OnClickListener {


    private String thisFragTag;
    private CartActivity cartActivity;
    private CartCouponFragment cartCouponFragment;
    private View view;
    private TextView textViewCoupon;
    private CheckBox checkBoxSelect;
    private View viewWhite;
    private CardView cardViewCoupon;

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
        cartActivity = (CartActivity)context;
        cartCouponFragment = (CartCouponFragment)getParentFragmentManager().findFragmentById(R.id.FrameLayout_Cart);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_coupon_item, container, false);
        cardViewCoupon = (CardView) view.findViewById(R.id.CardView_cart_coupon);
        checkBoxSelect = (CheckBox)view.findViewById(R.id.checkBox_cart_coupon_select);
        viewWhite = (View)view.findViewById(R.id.view_cart_coupon_white);

        cardViewCoupon.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.CardView_cart_coupon:
                //        cartCouponFragment.deleteFragment(thisFragTag);
                checkBoxSelect.setChecked(!checkBoxSelect.isChecked());
                break;
            case R.id.view_cart_coupon_white:
                Toast.makeText(cartActivity, "未符合條件", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}