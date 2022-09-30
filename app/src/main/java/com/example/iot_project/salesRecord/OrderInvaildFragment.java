package com.example.iot_project.salesRecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
 * Use the {@link OrderInvaildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderInvaildFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayoutManager salesLayoutManager;
    private RecyclerView recyclerViewOrderInvalid;
    private OrderInvalidRecyclerAdapter OrderRecyclerAdapter;

    public OrderInvaildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OderInvaildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderInvaildFragment newInstance(String param1, String param2) {
        OrderInvaildFragment fragment = new OrderInvaildFragment();
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
        View v = inflater.inflate(R.layout.fragment_order_invaild, container, false);
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
            orderMap.put("orderState","不成立");
            orderList.add(orderMap);
        }
        //      set the LayoutManager and Adapter of RecuclerView
//      LinearLayoutManager : reverseLayout = false，會按資料順序顯示，true則反轉資料顯示順序
//      LinearLayoutManager.VERTICAL 直向
        salesLayoutManager = new LinearLayoutManager(salesRecordActivity,LinearLayoutManager
                .VERTICAL,false);
        recyclerViewOrderInvalid = (RecyclerView)v.findViewById(R.id.orderInvalidRecyclerView);
        recyclerViewOrderInvalid.setLayoutManager(salesLayoutManager);
        OrderRecyclerAdapter = new OrderInvalidRecyclerAdapter(salesRecordActivity,orderList);
        recyclerViewOrderInvalid.setAdapter(OrderRecyclerAdapter);
        return v;
    }
}

//--------------------------------------------------------------------------------------------------
// 以下是予馨的願望:
// 從 orders 資料表 存取目前登入賣家(seller_id)  訂單狀態(orderStatus) = "不成立" 的所有訂單資訊
// 訂單編號(orders_id)
// 訂單中所有商品的
    // 商品名稱(goods_name)
    // 商品售價(price)
    // 購買數量(sum資料表中的sum欄位)
    // 第一張圖片(goodsPicture)
// 取件人姓名(pickupName)
// 買家帳號(account_name)
// 付款方式(payway)
// 收件地址(pickupPlace)
// 取件方式(pName)
// 付款時間(payTime)
// 取件時間(pickupTime)
// 出貨時間(shippingTime)
// 訂單成立時間 (createTime)
// 不成立原因 (invalidReason)
//-------------------------------------
// 訂單總金額 ...... 沒有這一欄予馨自己算