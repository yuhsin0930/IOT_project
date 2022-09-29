package com.example.iot_project.Cart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iot_project.R;
import com.example.iot_project.member.MemberCouponItemFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartCouponFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartCouponFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private View view_cart_coupon1, view_cart_coupon2;
    private CartActivity cartActivity;
    private ImageView imageViewDown;
    private FragmentManager fragmentMgr;
    private int i;
    private FragmentTransaction fragmentTrans;
    private CartCouponItemFragment fragmentItem;

    public CartCouponFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartCouponFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartCouponFragment newInstance(String param1, String param2) {
        CartCouponFragment fragment = new CartCouponFragment();
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
        view = inflater.inflate(R.layout.fragment_cart_coupon, container, false);

        findView();
        setData();
        setListener();

        return view;
    }

    private void findView() {
        view_cart_coupon1 = (View)view.findViewById(R.id.view_cart_coupon1);
        view_cart_coupon2 = (View)view.findViewById(R.id.view_cart_coupon2);
        imageViewDown = (ImageView)view.findViewById(R.id.imageView_cart_coupon_down);
    }

    private void setData(){
        fragmentMgr = getParentFragmentManager();
        for (i = 0; i < 5; i++) {
            fragmentTrans = fragmentMgr.beginTransaction();
            fragmentItem = CartCouponItemFragment.newInstance("CartCouponItemFragment" + i);
            fragmentTrans.add(R.id.LinearLayout_crat_coupon, fragmentItem, "CartCouponItemFragment" + i);
            fragmentTrans.commit();
        }
    }

    private void setListener() {
        view_cart_coupon1.setOnClickListener(this);
        view_cart_coupon2.setOnClickListener(this);
        imageViewDown.setOnClickListener(this);
    }

    public void deleteFragment(String tag) {
        fragmentMgr.beginTransaction().remove(fragmentMgr.findFragmentByTag(tag)).commit();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cartActivity = (CartActivity)getActivity();
    }

    @Override
    public void onClick(View view) {
        cartActivity.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("cart", "coupon - onDestroy()");
    }
}