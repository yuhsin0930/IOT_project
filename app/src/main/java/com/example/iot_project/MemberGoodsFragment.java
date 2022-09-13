package com.example.iot_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberGoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberGoodsFragment extends Fragment {

    private RecyclerView RecyclerViewGoods;

    public static MemberGoodsFragment newInstance(String param1, String param2) {
        MemberGoodsFragment fragment = new MemberGoodsFragment();
        return fragment;
    }

    public MemberGoodsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_goods, container, false);
        RecyclerViewGoods = (RecyclerView)v.findViewById(R.id.RecyclerView_member_goods);
        RecyclerViewGoods.setLayoutManager(new GridLayoutManager(getContext(),2));
        MemberRecyclerViewAdapter adapter = new MemberRecyclerViewAdapter();
        RecyclerViewGoods.setAdapter(adapter);
        return v;
    }
}