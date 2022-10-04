package com.example.iot_project.Cart;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.Main.MainRecyclerAdapter;
import com.example.iot_project.R;
import com.example.iot_project.Seller;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartItemBodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartItemBodyFragment extends Fragment {

    private View view;
    private TextView textViewMinus;
    private TextView textViewAdd;
    private TextView textViewGoodsSum;
    private int sum;
    private TextView textViewDelete;
    private ConstraintLayout constraintLayoutBody;
    private CheckBox checkBox_1;
    private CartActivity cartActivity;
    private TextView textViewGoodsPrice;
    private Map<String, Object> insideMap;
    private String tag, price;
    private Boolean checkBoxFlag, isExist;
    private CartAllProductFragment cartAllProductFragment;
    private ImageView imageViewPicture;
    private TextView textViewGoodsName;
    private Map hashMap;
    private DatabaseReference fireRef;

    public CartItemBodyFragment() {}

    public static CartItemBodyFragment newInstance(HashMap goodMap) {
        CartItemBodyFragment fragment = new CartItemBodyFragment();
        Bundle args = new Bundle();
        args.putSerializable("goodMap", goodMap);
        fragment.setArguments(args);
        return fragment;
    }


    // 資料庫下載商品資訊 + 買家在對應商品的選擇數量
    // 製作內層Map

    // 使用Map主要是因為要覆蓋 內層Map要把ID包出去 外層會取出來當外層KEY

    // 外 Map<商品id, Map<key, value>>

    // 內 譬如 Map<"id", tag>  // 暫時先用tag 之後使用真實商品Id， 為了綁Fragment創建時ID也一樣由Tag引入
    // 內 譬如 Map<"price", "10">
    // 內 譬如 Map<"sum", "5">

    // 利用put覆蓋特性 將更新完的內層Map拋給外面計算
    // 外面的外層Map一樣利用put更新
    // 再用foreach跳過商品id 將所有內層Map取出 再用Map.get("value"); 將價格、數量 取出 加總

    // 每一個按鍵最後都呼叫makeMap 在呼叫外面得setMap
    // 因為要跟textView互動 所以先全部存String 最後要計算再傳型



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_item_body, container, false);

        textViewMinus = (TextView)view.findViewById(R.id.textView_cart_body_minus);
        textViewAdd = (TextView)view.findViewById(R.id.textView_cart_body_add);
        textViewGoodsSum = (TextView)view.findViewById(R.id.textView_cart_goodsSum);
        textViewDelete = (TextView)view.findViewById(R.id.textView_cart_body_delete);
        textViewGoodsPrice = (TextView)view.findViewById(R.id.textView_cart_body_goodsPrice);
        textViewGoodsName = (TextView)view.findViewById(R.id.textView_car_body_goodsName);
        constraintLayoutBody = (ConstraintLayout)view.findViewById(R.id.ConstraintLayout_cart_body);
        checkBox_1 = (CheckBox)view.findViewById(R.id.checkBox_cart_body_1);
        imageViewPicture = (ImageView)view.findViewById(R.id.imageView_cardview_member_orders_picture);


        isExist = true;
//        tag = getArguments().getString("tag");
        hashMap = (HashMap) getArguments().getSerializable("goodMap");

//
//        //seller.storeName 取出賣場名稱
//        dataRef.child("seller").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("goodsList", "[4]goodsList=" + goodsList);
//
//                for (DataSnapshot d : snapshot.getChildren()) {
//                    Seller sellerData = d.getValue(Seller.class);
//                    String Id = sellerData.getSeller_id();
//
//                    if(goodsMap.get("storeName")!=null) break;
//                    if (Id.equals(seller_id)) {
//                        String storeName = sellerData.getStoreName();
//                        goodsMap.put("storeName", storeName);
//                        Log.d("main", "storeName=" + storeName);
//
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        //(goods.goods_name && goods.seller_id)
//        //goodsNorm.price
//        dataRef.child("goodsNorm").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot d2 : snapshot.getChildren()) {
//                    Log.d("main","[d2]snapshot.getChildrenCount="+snapshot.getChildrenCount());
//                    Map<String, Object> goodsNormData = (HashMap<String, Object>) d2.getValue();
//                    String name = goodsNormData.get("goods_name").toString();
//                    String sellerId = goodsNormData.get("seller_id").toString();
//
//                    if(goodsMap.get("price")!=null) break;
//
//                    if (sellerId.equals(seller_id) && name.equals(goods_name)) {
//                        String price = goodsNormData.get("price").toString();
//                        goodsMap.put("price", price);
//                        Log.d("main", "price=" + price);
//                        break;
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
        //goodsPic.goodsPicture
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        fireRef = firebase.getReference("goodsPic");
        Log.d("cart", "ccccccccccccccccccccccccccc");
        fireRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("cart", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                for (DataSnapshot d3 : snapshot.getChildren()) {
                    Log.d("main","[d3]snapshot.getChildrenCount="+snapshot.getChildrenCount());
                    Map<String, Object> goodsPicData = (HashMap<String, Object>) d3.getValue();
                    String name = goodsPicData.get("goods_name").toString();
                    String sellerId = goodsPicData.get("seller_id").toString();
//                    if(goodsMap.get("goodsPicture")!=null) break;
                    if (sellerId.equals(hashMap.get("seller_id").toString()) && name.equals(hashMap.get("goods_name").toString())) {
                        Log.d("cart", "aaaaaaaaaaaaaaaaaaaaaa");
                        String goodsPicture = goodsPicData.get("goodsPicture").toString();
                        byte[] decodedString = Base64.decode(goodsPicture, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageViewPicture.setImageBitmap(decodedByte);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });





        sum = 1;
        insideMap = new HashMap<>();
        textViewGoodsSum.setText(String.valueOf(sum));
        checkBoxFlag = false;
        price = "40";

        textViewGoodsPrice.setText(price);

        imageViewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cartActivity, "前往商品頁面", Toast.LENGTH_SHORT).show();
            }
        });

        textViewGoodsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cartActivity, "前往商品頁面", Toast.LENGTH_SHORT).show();
            }
        });


        checkBox_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxFlag = isChecked;
                makeAndSendInsideMap();
            }
        });

        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewGoodsSum.setText(String.valueOf(++sum));
                makeAndSendInsideMap();
            }
        });

        textViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sum <= 1) { // 如果總數被減到等於0 跳出Dialog確認是否將品項刪除
                    Dialog dialogCartBodyDelete = new Dialog(getContext());
                    dialogCartBodyDelete.setContentView(R.layout.dialog_cart_itembody_delete);
                    TextView textViewYes = (TextView) dialogCartBodyDelete.findViewById(R.id.textView_dialog_cart_body_yes);
                    TextView textViewCancel = (TextView) dialogCartBodyDelete.findViewById(R.id.textView_dialog_cart_body_cancel);
                    textViewCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogCartBodyDelete.dismiss();
                        }
                    });
                    textViewYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Fragment f = getParentFragmentManager().findFragmentByTag(tag);
                            cartActivity.getSupportFragmentManager().beginTransaction().remove((CartItemBodyFragment)f).commit();
                            isExist = false;
                            makeAndSendInsideMap();
                            dialogCartBodyDelete.dismiss();
                        }
                    });
                    dialogCartBodyDelete.show();
                    dialogCartBodyDelete.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                } else {
                    textViewGoodsSum.setText(String.valueOf(--sum));
                    makeAndSendInsideMap();
                }
            }
        });


        // fragment找出自己然後使用 Activity自己刪掉
        textViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = getParentFragmentManager().findFragmentByTag(getArguments().getString("tag"));
                cartActivity.getSupportFragmentManager().beginTransaction().remove((CartItemBodyFragment)f).commit();
                isExist = false;
                makeAndSendInsideMap();
            }
        });


        // 監聽整體的左右滑動來顯示或隱藏刪除鍵
        constraintLayoutBody.setOnTouchListener(new View.OnTouchListener() {
            private float x1 = 0, x2 = 0, x = 0;
            private int absX = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x2 = motionEvent.getX();
                        x = x2 - x1;
                        absX = (int)Math.abs(x);
                        Log.d("cart", "x = " + x);
                        if (x < -100) { // 左滑
                            textViewDelete.setVisibility(View.VISIBLE);
                        } else if (x > 1) { // 右滑
                            textViewDelete.setVisibility(View.GONE);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
//                        if (absX < 50)Toast.makeText(getContext(), "前往商品", Toast.LENGTH_SHORT).show();
//                        Log.d("cart", "Math.abs(x) = " + Math.abs(x));
                        break;
                }
                return true;
            }
        });

//        makeAndSendInsideMap();
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
        cartActivity = (CartActivity)getActivity();
        cartAllProductFragment = (CartAllProductFragment)getParentFragmentManager().findFragmentByTag("f0");
        // 從pageView中找到Fragment
        // 參考: https://learnpainless.com/android/how-to-get-fragment-from-viewpager-android/
        // 參考: https://stackoverflow.com/questions/55728719/get-current-fragment-with-viewpager2
    }

    // 每個監聽都呼叫此方法
    private void makeAndSendInsideMap() {
        insideMap.put("id", tag);
        insideMap.put("price", price);
        insideMap.put("sum", sum);
        insideMap.put("checkBoxFlag", checkBoxFlag);
        insideMap.put("isExist", isExist);
        cartAllProductFragment.setOutSideMap(insideMap);
    }



}