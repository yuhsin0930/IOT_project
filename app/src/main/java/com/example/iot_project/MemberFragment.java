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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MemberFragment extends Fragment implements View.OnClickListener{

    private ImageView imageViewMystore, imageViewSetting, imageViewCart, imageViewMypic;
    private ImageView imageViewOrders1, imageViewOrders2, imageViewOrders3;
    private RelativeLayout RelativeLayoutBecomeSeller, RelativeLayoutOrders;
    private RelativeLayout RelativeLayoutFavorite, RelativeLayoutBought, RelativeLayoutSeen;
    private RelativeLayout RelativeLayoutCoupon, RelativeLayoutPersonal;
    private Intent intent;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
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
        imageViewOrders1 = (ImageView)v.findViewById(R.id.imageView_member_orders1);
        imageViewOrders2 = (ImageView)v.findViewById(R.id.imageView_member_orders2);
        imageViewOrders3 = (ImageView)v.findViewById(R.id.imageView_member_orders3);
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
        imageViewOrders1.setOnClickListener(this);
        imageViewOrders2.setOnClickListener(this);
        imageViewOrders3.setOnClickListener(this);
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
                Toast.makeText(getContext(), "imageView_member_setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_member_cart:
                Log.d("main", "getContext() = " + getContext());
                Toast.makeText(getContext(), "這是購物車..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_member_mypic:
                Toast.makeText(getContext(), "imageView_member_mypic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_member_orders1:
                Toast.makeText(getContext(), "imageView_member_orders1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_member_orders2:
                Toast.makeText(getContext(), "imageView_member_orders2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_member_orders3:
                Toast.makeText(getContext(), "imageView_member_orders3", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "RelativeLayout_member_bought", Toast.LENGTH_SHORT).show();
                break;
            case R.id.RelativeLayout_member_seen:
                Toast.makeText(getContext(), "RelativeLayout_member_seen", Toast.LENGTH_SHORT).show();
                break;
            case R.id.RelativeLayout_member_coupon:
                Toast.makeText(getContext(), "RelativeLayout_member_coupon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.RelativeLayout_member_personal:
                Toast.makeText(getContext(), "RelativeLayout_member_personal", Toast.LENGTH_SHORT).show();
                break;
        }
    }




}
