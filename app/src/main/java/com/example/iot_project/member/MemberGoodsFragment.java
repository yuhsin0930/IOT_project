package com.example.iot_project.member;

import android.os.Bundle;

import androidx.annotation.Nullable;
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

    private View view;
    private MemberActivity memberActivity;
    private ImageView ImageViewBack;
    private RecyclerView recyclerViewGoods;
    private TextView textViewBar;

    public static MemberGoodsFragment newInstance(String barName) {
        MemberGoodsFragment fragment = new MemberGoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("barName", barName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_goods, container, false);
        findView();
        setData();
        setAdapter();
        setListener();
        return view;
    }

    private void findView(){
        ImageViewBack = (ImageView)view.findViewById(R.id.ImageView_member_back);
        recyclerViewGoods = (RecyclerView)view.findViewById(R.id.RecyclerView_member_goods);
        textViewBar = (TextView)view.findViewById(R.id.TextView_member_bar);
    }

    private void setData(){
        textViewBar.setText(getArguments().getString("barName"));
        memberActivity = (MemberActivity)getActivity();
    }

    private void setAdapter(){
        recyclerViewGoods.setLayoutManager(new GridLayoutManager(getContext(),2));
        MemberGoodsRecyclerViewAdapter adapter = new MemberGoodsRecyclerViewAdapter();
        recyclerViewGoods.setAdapter(adapter);
    }

    private void setListener(){
        ImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberActivity.onBackPressed();
            }
        });
    }

    public void setTextViewBar(String barName) {
//        textViewBar.setText(barName);
    }

}