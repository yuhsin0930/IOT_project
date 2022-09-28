package com.example.iot_project.Cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.List;

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
    private TextView textViewCheckout;
    private TextView textViewTotal;
    private RelativeLayout RelativeLayoutCoupon;
    private CartActivity cartActivity;
    private CheckBox checkBoxSelectAll;
    private List<Fragment> fragmentList;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_all_product, container, false);

        fragmentMgr = getParentFragmentManager();
        textViewCheckout = (TextView)view.findViewById(R.id.textView_cart_checkout);
        textViewTotal = (TextView)view.findViewById(R.id.textView_cart_total);
        RelativeLayoutCoupon = (RelativeLayout)view.findViewById(R.id.RelativeLayout_cart_coupon);
        checkBoxSelectAll = (CheckBox)view.findViewById(R.id.checkBox_cart_selectAll);
        fragmentList = new ArrayList<>();

        checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (Fragment f : fragmentList) {
                    ((CartItemBodyFragment)f).setCheckBox_1(b);
                }
            }
        });

        textViewTotal.setText("123465");
        int sum = 100;
        textViewCheckout.setText("去買單(" + sum +")");

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

        makeCard(3);

        return view;
    }

    public void makeCard(int count) {
        for (; count  > 0; count--) {
            fragmentTrans = fragmentMgr.beginTransaction();
            cartItemHeadFragment = new CartItemHeadFragment();
            fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemHeadFragment, "cartItemHeadFragment");
            fragmentTrans.commit();

            for (int i = 0; i < 5; i++) {
                fragmentTrans = fragmentMgr.beginTransaction();
                cartItemBodyFragment = new CartItemBodyFragment();
                fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemBodyFragment, "cartItemBodyFragment" + i);
                fragmentTrans.commit();
                fragmentList.add(cartItemBodyFragment);
            }

            fragmentTrans = fragmentMgr.beginTransaction();
            cartItemFooterFragment = new CartItemFooterFragment();
            fragmentTrans.add(R.id.LinearLayout_allproduct, cartItemFooterFragment, "cartItemFooterFragment");
            fragmentTrans.commit();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cartActivity = (CartActivity)getActivity();
    }
}