package com.example.iot_project.Cart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iot_project.R;
import com.example.iot_project.member.MemberCouponFragment;
import com.example.iot_project.member.MemberCouponItemFragment;

public class CartCouponItemFragment extends Fragment implements View.OnClickListener {


    private String thisFragTag;
    private CartActivity cartActivity;
    private CartCouponFragment cartCouponFragment;
    private View view;
    private TextView textViewCoupon;

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
        ConstraintLayout ConstraintLayoutCoupon = (ConstraintLayout) view.findViewById(R.id.ConstraintLayout_cart_coupon);
        textViewCoupon = (TextView) view.findViewById(R.id.textView_cart_coupon);
        textViewCoupon.setText(thisFragTag.substring("cartCouponItemFragment".length()));
        ConstraintLayoutCoupon.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        cartCouponFragment.deleteFragment(thisFragTag);
    }
}