package com.example.iot_project.member;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.MainActivity;
import com.example.iot_project.MyStoreActivity;
import com.example.iot_project.R;
import com.example.iot_project.register.RegisterActivity;
import com.example.iot_project.SellerRegister.BecomeSellerActivity;
import com.example.iot_project.shoppingCart.ShoppingCartActivity;

public class MemberFragment extends Fragment implements View.OnClickListener{

    private View view;
    private MemberActivity memberActivity;
    private Intent intent;
    private ImageView imageViewSetting, imageViewCart, imageViewMypic;
    private LinearLayout LinearLayoutOrders_0, LinearLayoutOrders_1, LinearLayoutOrders_2, LinearLayoutOrders_3;
    private RelativeLayout RelativeLayoutMystore, RelativeLayoutBecomeSeller, RelativeLayoutOrders;
    private RelativeLayout RelativeLayoutFavorite, RelativeLayoutBought, RelativeLayoutSeen;
    private RelativeLayout RelativeLayoutCoupon, RelativeLayoutPersonal;
    private TextView textViewName;
    private Button button_main;

    public static MemberFragment newInstance() {
        return new MemberFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member, container, false);

        findView();
        setData();
        setListener();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        memberActivity = (MemberActivity)getActivity();
    }

    private void findView() {
        button_main = (Button)view.findViewById(R.id.button_main);
        textViewName = (TextView)view.findViewById(R.id.textView_member_name);
        imageViewSetting = (ImageView)view.findViewById(R.id.imageView_member_setting);
        imageViewCart = (ImageView)view.findViewById(R.id.imageView_member_cart);
        imageViewMypic = (ImageView)view.findViewById(R.id.imageView_member_picture);
        LinearLayoutOrders_0 = (LinearLayout)view.findViewById(R.id.LinearLayout_member_orders_0);
        LinearLayoutOrders_1 = (LinearLayout)view.findViewById(R.id.LinearLayout_member_orders_1);
        LinearLayoutOrders_2 = (LinearLayout)view.findViewById(R.id.LinearLayout_member_orders_2);
        LinearLayoutOrders_3 = (LinearLayout)view.findViewById(R.id.LinearLayout_member_orders_3);
        RelativeLayoutMystore = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_mystore);
        RelativeLayoutBecomeSeller = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_becomeSeller);
        RelativeLayoutOrders = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_orders);
        RelativeLayoutFavorite = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_favorite);
        RelativeLayoutBought = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_bought);
        RelativeLayoutSeen = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_seen);
        RelativeLayoutCoupon = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_coupon);
        RelativeLayoutPersonal = (RelativeLayout)view.findViewById(R.id.RelativeLayout_member_personal);
    }

    private void setData(){
        imageViewMypic.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cat6));
        textViewName.setText(getContext().getSharedPreferences("LoginInformation", MODE_PRIVATE)
                .getString("account_name", "Apple2022"));
    }

    private void setListener(){
        button_main.setOnClickListener(this);
        imageViewSetting.setOnClickListener(this);
        imageViewCart.setOnClickListener(this);
        imageViewMypic.setOnClickListener(this);
        LinearLayoutOrders_0.setOnClickListener(this);
        LinearLayoutOrders_1.setOnClickListener(this);
        LinearLayoutOrders_2.setOnClickListener(this);
        LinearLayoutOrders_3.setOnClickListener(this);
        RelativeLayoutMystore.setOnClickListener(this);
        RelativeLayoutBecomeSeller.setOnClickListener(this);
        RelativeLayoutOrders.setOnClickListener(this);
        RelativeLayoutFavorite.setOnClickListener(this);
        RelativeLayoutBought.setOnClickListener(this);
        RelativeLayoutSeen.setOnClickListener(this);
        RelativeLayoutCoupon.setOnClickListener(this);
        RelativeLayoutPersonal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_main:
                intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.RelativeLayout_member_mystore:
                intent = new Intent(getContext(), MyStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_member_cart:
                intent = new Intent(getContext(), ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_member_picture:
                Toast.makeText(getContext(), "imageView_member_picture", Toast.LENGTH_SHORT).show();
                break;
            case R.id.RelativeLayout_member_orders:
            case R.id.LinearLayout_member_orders_0:
                memberActivity.showOrdersFragment(0);
                break;
            case R.id.LinearLayout_member_orders_1:
                memberActivity.showOrdersFragment(1);
                break;
            case R.id.LinearLayout_member_orders_2:
                memberActivity.showOrdersFragment(2);
                break;
            case R.id.LinearLayout_member_orders_3:
                memberActivity.showOrdersFragment(3);
                break;
            case R.id.RelativeLayout_member_becomeSeller:
                Log.d("main", "getContext() = " + getContext());
                intent = new Intent(getContext(), BecomeSellerActivity.class);
                startActivity(intent);
                RelativeLayoutBecomeSeller.setVisibility(View.GONE);
                break;
            case R.id.RelativeLayout_member_favorite:
                memberActivity.showGoodsFragment("按讚好物");
                break;
            case R.id.RelativeLayout_member_bought:
                memberActivity.showGoodsFragment("再買一次");
                break;
            case R.id.RelativeLayout_member_seen:
                memberActivity.showGoodsFragment("瀏覽紀錄");
                break;
            case R.id.RelativeLayout_member_coupon:
                memberActivity.showCouponFragment();
                break;
            case R.id.imageView_member_setting:
            case R.id.RelativeLayout_member_personal:
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
