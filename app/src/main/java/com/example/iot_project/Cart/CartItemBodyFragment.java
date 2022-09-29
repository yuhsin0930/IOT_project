package com.example.iot_project.Cart;

import android.annotation.SuppressLint;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartItemBodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartItemBodyFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private View view;
    private TextView textViewMinus;
    private TextView textViewAdd;
    private TextView textViewGoodsSum;
    private int sum;
    private TextView textViewDelete;
    private ConstraintLayout constraintLayoutBody;
    private float x1;
    private CheckBox checkBox_1;
    private CartActivity cartActivity;

    public CartItemBodyFragment() {}

    public static CartItemBodyFragment newInstance(String tag) {
        CartItemBodyFragment fragment = new CartItemBodyFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_item_body, container, false);

        textViewMinus = (TextView)view.findViewById(R.id.textView_cart_body_minus);
        textViewAdd = (TextView)view.findViewById(R.id.textView_cart_body_add);
        textViewGoodsSum = (TextView)view.findViewById(R.id.textView_cart_goodsSum);
        textViewDelete = (TextView)view.findViewById(R.id.textView_cart_body_delete);
        constraintLayoutBody = (ConstraintLayout)view.findViewById(R.id.ConstraintLayout_cart_body);
        checkBox_1 = (CheckBox)view.findViewById(R.id.checkBox_cart_body_1);

        sum = 1;
        textViewGoodsSum.setText(String.valueOf(sum));

        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewGoodsSum.setText(String.valueOf(++sum));

            }
        });

        textViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sum <= 1) {
                    Toast.makeText(getContext(), "88", Toast.LENGTH_SHORT).show();
                } else textViewGoodsSum.setText(String.valueOf(--sum));
            }
        });


        // fragment找出自己然後使用 Activity自己刪掉
        textViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = getParentFragmentManager().findFragmentByTag(getArguments().getString("tag"));
                cartActivity.getSupportFragmentManager().beginTransaction().remove((CartItemBodyFragment)f).commit();
                Toast.makeText(getContext(), "刪除: " + getArguments().getString("tag"), Toast.LENGTH_SHORT).show();
            }
        });


        // 監聽整體的左右滑動來顯示或隱藏刪除鍵
        constraintLayoutBody.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x2 = motionEvent.getX();
                        float x = x2 - x1;
                        Log.d("cart", "x = " + x);
                        if (x < -100) { // 左滑
                            textViewDelete.setVisibility(View.VISIBLE);
                        } else if (x > 1) { // 右滑
                            textViewDelete.setVisibility(View.GONE);
                        }
                        break;
                }
                return true;
            }
        });

        return view;
    }

    public void setCheckBox_1(Boolean b) {
        checkBox_1.setChecked(b);
    }

    public void setIsDeleteShow(Boolean b) {
        if (b) textViewDelete.setVisibility(View.VISIBLE);
        else textViewDelete.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cartActivity = (CartActivity)context;
    }
}