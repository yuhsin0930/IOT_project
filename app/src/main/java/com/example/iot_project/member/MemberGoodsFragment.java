package com.example.iot_project.member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iot_project.R;

public class MemberGoodsFragment extends Fragment {

    private MemberActivity memberActivity;
    private ImageView ImageViewBack;
    private RecyclerView RecyclerViewGoods;
    private TextView textViewGoodsBar;

    public static MemberGoodsFragment newInstance(String param1, String param2) {
        MemberGoodsFragment fragment = new MemberGoodsFragment();
        return fragment;
    }

    public MemberGoodsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_goods, container, false);
        memberActivity = (MemberActivity)getActivity();

        ImageViewBack = (ImageView)v.findViewById(R.id.ImageView_member_goods_back);
        RecyclerViewGoods = (RecyclerView)v.findViewById(R.id.RecyclerView_member_goods);
        textViewGoodsBar = (TextView)v.findViewById(R.id.TextView_member_goods_bar);


        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberActivity.onBackPressed();
            }
        });

        RecyclerViewGoods.setLayoutManager(new GridLayoutManager(getContext(),2));
        MemberGoodsRecyclerViewAdapter adapter = new MemberGoodsRecyclerViewAdapter();
        RecyclerViewGoods.setAdapter(adapter);

        return v;
    }

    public void setTextViewGoodsBar(String barName) {
        textViewGoodsBar.setText(barName);
    }
}