package com.example.iot_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MemberFragment extends Fragment implements View.OnClickListener{

    private ImageView imageViewMystore, imageViewSetting, imageViewCart, imageViewMypic;
    private LinearLayout LinearLayoutOrders1, LinearLayoutOrders2, LinearLayoutOrders3;
    private RelativeLayout RelativeLayoutBecomeSeller, RelativeLayoutOrders;
    private RelativeLayout RelativeLayoutFavorite, RelativeLayoutBought, RelativeLayoutSeen;
    private RelativeLayout RelativeLayoutCoupon, RelativeLayoutPersonal;
    private Intent intent;
    private MemberActivity memberActivity;


    public static MemberFragment newInstance(String param1, String param2) {
        MemberFragment fragment = new MemberFragment();
        return fragment;
    }

    public MemberFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        imageViewMystore = (ImageView)v.findViewById(R.id.imageView_member_mystore);
        imageViewSetting = (ImageView)v.findViewById(R.id.imageView_member_setting);
        imageViewCart = (ImageView)v.findViewById(R.id.imageView_member_cart);
        imageViewMypic = (ImageView)v.findViewById(R.id.imageView_member_mypic);
        LinearLayoutOrders1 = (LinearLayout)v.findViewById(R.id.LinearLayout_member_orders1);
        LinearLayoutOrders2 = (LinearLayout)v.findViewById(R.id.LinearLayout_member_orders2);
        LinearLayoutOrders3 = (LinearLayout)v.findViewById(R.id.LinearLayout_member_orders3);
        RelativeLayoutBecomeSeller = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_becomeSeller);
        RelativeLayoutOrders = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_orders);
        RelativeLayoutFavorite = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_favorite);
        RelativeLayoutBought = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_bought);
        RelativeLayoutSeen = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_seen);
        RelativeLayoutCoupon = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_coupon);
        RelativeLayoutPersonal = (RelativeLayout)v.findViewById(R.id.RelativeLayout_member_personal);
        imageViewMystore.setOnClickListener(this);
        imageViewSetting.setOnClickListener(this);
        imageViewCart.setOnClickListener(this);
        imageViewMypic.setOnClickListener(this);
        LinearLayoutOrders1.setOnClickListener(this);
        LinearLayoutOrders2.setOnClickListener(this);
        LinearLayoutOrders3.setOnClickListener(this);
        RelativeLayoutBecomeSeller.setOnClickListener(this);
        RelativeLayoutOrders.setOnClickListener(this);
        RelativeLayoutFavorite.setOnClickListener(this);
        RelativeLayoutBought.setOnClickListener(this);
        RelativeLayoutSeen.setOnClickListener(this);
        RelativeLayoutCoupon.setOnClickListener(this);
        RelativeLayoutPersonal.setOnClickListener(this);
        imageViewMypic.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.headshot));
        memberActivity = (MemberActivity)getActivity();
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_member_mystore:
                intent = new Intent(getContext(), MyStoreActivity.class);
                Log.d("main", "getContext() = " + getContext());
                startActivity(intent);
                break;
            case R.id.imageView_member_setting:
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_member_cart:
                Log.d("main", "getContext() = " + getContext());
                Toast.makeText(getContext(), "這是購物車..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_member_mypic:
                Toast.makeText(getContext(), "imageView_member_mypic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.LinearLayout_member_orders1:
                memberActivity.showOrders();
                break;
            case R.id.LinearLayout_member_orders2:
                memberActivity.showOrders();
                break;
            case R.id.LinearLayout_member_orders3:
                memberActivity.showOrders();
                break;
            case R.id.RelativeLayout_member_becomeSeller:
                Log.d("main", "getContext() = " + getContext());
                intent = new Intent(getContext(), BecomeSellerActivity.class);
                startActivity(intent);
//                RelativeLayoutBecomeSeller.setVisibility(View.GONE);
                break;
            case R.id.RelativeLayout_member_orders:
                memberActivity.showOrders();
                break;
            case R.id.RelativeLayout_member_favorite:
                memberActivity.showGoods();
                break;
            case R.id.RelativeLayout_member_bought:
                memberActivity.showGoods();
                break;
            case R.id.RelativeLayout_member_seen:
                memberActivity.showGoods();
                break;
            case R.id.RelativeLayout_member_coupon:
                Toast.makeText(getContext(), "RelativeLayout_member_coupon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.RelativeLayout_member_personal:
                int a;
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }




}
