package com.example.iot_project.salesRecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderToBeShipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderToBeShipFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycleViewOrderToBeShip;
    private OrderToBeShipRecyclerViewAdapter OrderRecyclerViewAdapter;
    private LinearLayoutManager salesLayoutManager;
    private RecyclerView recyclerViewOrderToBeShip;
    private OrderToBeShipRecyclerViewAdapter OrderRecyclerAdapter;

    public OrderToBeShipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderToBeShipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderToBeShipFragment newInstance(String param1, String param2) {
        OrderToBeShipFragment fragment = new OrderToBeShipFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_to_be_ship, container, false);
        SalesRecordActivity salesRecordActivity = (SalesRecordActivity)getActivity();
        List<Map<String,Object>> orderList = new ArrayList<>();
        Map<String,Object> orderMap = new HashMap<>();
        for(int i=0;i<5;i++){
            orderMap.put("orderNum","F123456789");
            orderMap.put("productName","耳機");
            orderMap.put("productNum",2);
            orderMap.put("productPrice",200);
            orderMap.put("allProductNum",2);
            orderMap.put("totalPrice",400);
            orderList.add(orderMap);
        }

//      set the LayoutManager and Adapter of RecuclerView
//      LinearLayoutManager : reverseLayout = false，會按資料順序顯示，true則反轉資料顯示順序
//      LinearLayoutManager.VERTICAL 直向
        salesLayoutManager = new LinearLayoutManager(salesRecordActivity,LinearLayoutManager
                .VERTICAL,false);
        recyclerViewOrderToBeShip = (RecyclerView)v.findViewById(R.id.orderToBeShip_recycleView);
        recyclerViewOrderToBeShip.setLayoutManager(salesLayoutManager);
        OrderRecyclerAdapter = new OrderToBeShipRecyclerViewAdapter(salesRecordActivity,orderList);
        recyclerViewOrderToBeShip.setAdapter(OrderRecyclerAdapter);
        return v;
    }

}

//-------------------------------------------------------------------------------------------------
// 以下是予馨的願望:
// 從firebase存取同一賣家 訂單狀態="待出貨" 的所有訂單資訊
// 訂單編號
// 訂單中所有商品的 商品名稱  商品售價 購買數量
// 取件人姓名
// 買家帳號
// 付款方式
// 收件地址
// 取件方式
// 付款狀態
// 訂單總金額