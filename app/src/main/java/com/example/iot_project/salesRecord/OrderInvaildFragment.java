package com.example.iot_project.salesRecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iot_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private DatabaseReference dataRef;
    private ValueEventListener sumListener;
    private ValueEventListener ordersListener;
    private Map<String, Object> mapdata;
    private List<Map<String, Object>> listData;
    private int countforList;

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
            orderMap.put("productName","??????");
            orderMap.put("productNum",2);
            orderMap.put("productPrice",200);
            orderMap.put("allProductNum",2);
            orderMap.put("totalPrice",400);
            orderMap.put("orderState","?????????");
            orderList.add(orderMap);
        }
        //      set the LayoutManager and Adapter of RecuclerView
//      LinearLayoutManager : reverseLayout = false??????????????????????????????true???????????????????????????
//      LinearLayoutManager.VERTICAL ??????
        salesLayoutManager = new LinearLayoutManager(salesRecordActivity,LinearLayoutManager
                .VERTICAL,false);
        recyclerViewOrderInvalid = (RecyclerView)v.findViewById(R.id.orderInvalidRecyclerView);
        recyclerViewOrderInvalid.setLayoutManager(salesLayoutManager);
        OrderRecyclerAdapter = new OrderInvalidRecyclerAdapter(salesRecordActivity,orderList);
        recyclerViewOrderInvalid.setAdapter(OrderRecyclerAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//--------------------------------------------------------------------------------------------------
// ????????????????????????: [380??? listData ?????????????????????List????????????????????????Map??????????????????List???????????????????????????????????????]
// ??? orders ????????? ????????????????????????(seller_id)  ????????????(orderStatus) = "?????????" ?????????????????????
// ????????????(orders_id)
// ????????????????????????
        // ????????????(goods_name)
        // ????????????(price)
        // ????????????(sum???????????????sum??????)
        // ???????????????(goodsPicture)

        //    *****  ????????????(goodsNorm.norm)  10/3??????     ******

// ???????????????(pickupName)
// ????????????(account_name)
// ????????????(payway)
// ????????????(pickupPlace)
// ????????????(pName)
// ????????????(payTime)
// ????????????(pickupTime)
// ????????????(shippingTime)
// ?????????????????? (createTime)
// ??????????????? (invalidReason)
//-------------------------------------
// ??????????????? ...... ??????????????????????????????

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        dataRef = database.getReference();
//        SharedPreferences spData = getContext().getSharedPreferences("LoginInformation", MODE_PRIVATE);
////      ------------------------------------------------------------------------
//        String seller_id = spData.getString("member_id", "");
//        listData = new ArrayList<Map<String, Object>>();
//
////      ------------------------------------------------------------------------
////        String seller_id = spData.getString("member_id", "");
//        ordersListener = dataRef.child("orders").orderByChild("seller_id").equalTo(seller_id).addValueEventListener(new ValueEventListener() {

//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                listData.clear();
//                String key = snapshot.getKey();
//                Object value = snapshot.getValue();
//                Log.d("main", "key=" + key);
//                Log.d("main", "value=" + value);
//                for (DataSnapshot data : snapshot.getChildren()) {
////                  --------------------------------------------------
//                    Object ordersData = data.getValue();
//                    Log.d("main", "ordersData=" + ordersData);
//                    Map<String, Object> ordersMap = (Map<String, Object>) ordersData;
////                  ---------------------------------------------------------------
//                    String orderStatus = ordersMap.get("orderStatus").toString();
//                    Log.d("main", "orderStatus=" + orderStatus);
//                    if (orderStatus.equals("?????????")) {
//
//                        mapdata = new HashMap<>();
//
//                        Log.d("main", "ordersMap=" + ordersMap);
//                        String orders_id = data.getKey().toString();
//                        Log.d("main", "orders_id =" + orders_id);
//                        mapdata.put("orders_id", orders_id);
//                        String member_id = ordersMap.get("member_id").toString();
//                        Log.d("main", "member_id=" + member_id);
//                        mapdata.put("member_id", member_id);

//                        // ???????????????(pickupName)
//                        String pickupName = ordersMap.get("pickupName").toString();
//                        Log.d("main", "pickupName=" + pickupName);
//                        mapdata.put("pickupName", pickupName);

//                        // ????????????(payway)
//                        String payway = ordersMap.get("payway").toString();
//                        Log.d("main", "payway=" + payway);
//                        mapdata.put("payway", payway);

//                        // ????????????(pickupPlace)
//                        String pickupPlace = ordersMap.get("pickupPlace").toString();
//                        Log.d("main", "pickupPlace=" + pickupPlace);
//                        mapdata.put("pickupPlace", pickupPlace);
//
//                        // ????????????(payTime)
//                        String payTime = ordersMap.get("payTime").toString();
//                        Log.d("main", "payTime=" + payTime);
//                        mapdata.put("payTime", payTime);
//
//                        // ????????????(pickupTime)
//                        String pickupTime = ordersMap.get("pickupTime").toString();
//                        Log.d("main", "pickupTime=" + pickupTime);
//                        mapdata.put("pickupTime", pickupTime);

                        // ????????????(shippingTime)
//                          String shippingTime = ordersMap.get("shippingTime").toString();
//                        Log.d("main", "shippingTime=" + shippingTime);
//                        mapdata.put("shippingTime", shippingTime);
//
//                        // ?????????????????? (createTime)
//                        String createTime = ordersMap.get("createTime").toString();
//                        Log.d("main", "createTime=" + createTime);
//                        mapdata.put("createTime", createTime);

                        // ??????????????? (invalidReason)
        //                String invalidReason = ordersMap.get("invalidReason").toString();
//                        Log.d("main", "invalidReason=" + invalidReason);
//                        mapdata.put("invalidReason", invalidReason);

////                      mapdata : ????????? orders ????????? ????????????????????????(seller_id)  ????????????(orderStatus) = "?????????"  ???????????????
//                        String pickupWayid = ordersMap.get("pickupWay_id").toString();
//                        mapdata.put("pickupWay_id",pickupWayid);

////                      ????????????????????????Map???List
//                        listData.add(mapdata);
//                        Log.d("main", "listData=" + listData);
//
//                        // ????????????(pickupWay.pName)
//                        dataRef.child("pickupWay").child(pickupWayid).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Log.d("main","snapshot.getKey()="+snapshot.getKey());
//                                Object pickupdata = snapshot.getValue();
//                                Log.d("main","pickupdata="+pickupdata);
//                                Map<String, Object> pickupData = (Map<String, Object>) pickupdata;
//                                Log.d("main","pickupData="+pickupData);
//
//                                String pickupWayID = pickupData.get("pickupWay_id").toString();
//
//                                String pName = pickupData.get("pName").toString();
//                                Log.d("main","pName="+pName);
//
//                                for (int i = 0; i < listData.size(); i++) {
//                                    String pickupidCheck = listData.get(i).get("pickupWay_id").toString();
//                                    if (pickupidCheck.equals(pickupWayID)) {
//                                        listData.get(i).put("pName", pName);
//                                        break;
//                                    }
//                                }
//                                Log.d("main", "listData=" + listData);
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
////                        ??? account_name
//                        countforList = 0;
//                        dataRef.child("member").child(seller_id).limitToFirst(1).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//                            @Override
//                            public void onSuccess(DataSnapshot dataSnapshot) {
//
//                                Member account = dataSnapshot.getValue(Member.class);
//////                                    ---------------------------------------------
//                                String account_name = account.getAccount_name();
//                                Log.d("main", "account_name=" + account_name);

//
//                                listData.get(countforList).put("account_name", account_name);
//                                countforList++;
//                                Log.d("main", "listData=" + listData);
//                            }
//                        });

//                        Log.d("main", "orders_id=" + orders_id);
//                        dataRef.child("sum").orderByChild("orders_id").equalTo(orders_id).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Log.d("main", "dataSnapshot=" + snapshot.getValue());
//
//
//                                for (DataSnapshot sumdata : snapshot.getChildren()) {
//                                    Object sum = sumdata.getValue();
//                                    Map<String, Object> sumMap = (Map<String, Object>) sum;
////                                    ---------------------------------------------
//                                    String goods_id = sumMap.get("goods_id").toString(); // reference
//                                    String orderid = sumMap.get("orders_id").toString();
//                                    String Sum = sumMap.get("sum").toString();
//                                    Log.d("main", "orderid =" + orderid);
//                                    Log.d("main", "goods_id=" + goods_id);
//                                    Log.d("main", "Sum=" + Sum);

//
//                                    for (int i = 0; i < listData.size(); i++) {
//                                        String ordersidCheck = listData.get(i).get("orders_id").toString();
//                                        if (ordersidCheck.equals(orderid)) {
//                                            listData.get(i).put("goods_id", goods_id);
//                                            listData.get(i).put("sum", Sum);
//                                            break;
//                                        }
//                                    }
//                                    Log.d("main", "listData=" + listData);
//
//
//                                    dataRef.child("goods").child(goods_id).addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            Object goods = snapshot.getValue();
//                                            Map<String, Object> goodsMap = (Map<String, Object>) goods;
//                                            String goodsid = snapshot.getKey();
//                                            Log.d("main", "goodsid=" + goodsid);
//                                            String goods_name = goodsMap.get("goods_name").toString();
//                                            Log.d("main", "goods_name=" + goods_name);

//
//                                            for (int i = 0; i < listData.size(); i++) {
//                                                String goodsidCheck = listData.get(i).get("goods_id").toString();
//                                                if (goodsidCheck.equals(goodsid)) {
//                                                    listData.get(i).put("goods_name", goods_name);
//                                                    break;
//                                                }
//                                            }
//                                            Log.d("main", "listData=" + listData);
//
//                                            dataRef.child("goodsNorm").orderByChild("goods_name").equalTo(goods_name).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                    Object goodsData = snapshot.getValue();
//                                                    Log.d("main", "goodsData=" + goodsData);
//                                                    for (DataSnapshot goodsNorm : snapshot.getChildren()) {
//                                                        Map<String, Object> goodsDataMap = (Map<String, Object>) goodsNorm.getValue();
//                                                        Log.d("main", "goodsDataMap=" + goodsDataMap);
//                                                        String goodsName = goodsDataMap.get("goods_name").toString();
//                                                        Log.d("main", "goodsName=" + goodsName);
//                                                        Object priceData = goodsDataMap.get("price");
////                                                        Log.d("main", "priceData=" + priceData);
//                                                        Long price = (Long) priceData;
//                                                        Log.d("main", "price=" + price);

//                                                        for (int i = 0; i < listData.size(); i++) {
//                                                            String goodsNameCheck = listData.get(i).get("goods_name").toString();
//                                                            if (goodsNameCheck.equals(goodsName)) {
//                                                                listData.get(i).put("price", price);
//                                                                break;
//                                                            }
//                                                        }
//                                                        Log.d("main", "listData=" + listData);
//
//                                                    }

//
//                                                }
//
//                                                @Override
//                                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                                }
//                                            });
//                                            dataRef.child("goodsPic").orderByChild("goods_name").equalTo(goods_name).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                    Object goodsPic = snapshot.getValue();
//                                                    Log.d("main", "goodsPic=" + goodsPic);
//                                                    for (DataSnapshot Pic : snapshot.getChildren()) {
//                                                        Object PicValue = Pic.getValue();
//                                                        Map<String, Object> goodsPicMap = (Map<String, Object>) PicValue;
//                                                        String goodsName_ = goodsPicMap.get("goods_name").toString();
//                                                        Log.d("main", "goodsName=" + goodsName_);
//                                                        Object goodsPicture = goodsPicMap.get("goodsPicture");
//                                                        Log.d("main", "goodsPicture=" + goodsPicture);
//
//                                                        for (int i = 0; i < listData.size(); i++) {
//                                                            String goodsNameCheck = listData.get(i).get("goods_name").toString();
//                                                            if (goodsNameCheck.equals(goodsName_)) {
//                                                                listData.get(i).put("goodsPicture", goodsPicture.toString());
//                                                                break;
//                                                            }
//                                                        }
//
//
//                                                        Log.d("main", "listData=" + listData);
//
////                                                        ----------------------------------------------
////                                                        listData
////                                                        ???????????? listData ??????????????????
////                                                        --------------------------------------------

//                                                    }

//                                                }
//
//                                                @Override
//                                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                                }
//                                            });
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//
//                                        }
//                                    });
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }


//    @Override
//    public void onDetach() {
//        super.onDetach();
//        //      ??????Fragment??????????????????FireBase?????????????????????
//        dataRef.removeEventListener(ordersListener);
//    }
}

