package com.example.iot_project.Cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iot_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartItemHeadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartItemHeadFragment extends Fragment {

    private View view;
    private boolean b1, b2;
    private String tag;
    private TextView textViewShowDelete;
    private String seller_id;
    private TextView textViewSeller;
    private DatabaseReference fireRef;
    private ValueEventListener fireListener;
    private String sellerName;


    public CartItemHeadFragment() {}

    public static CartItemHeadFragment newInstance(String seller_id) {
        CartItemHeadFragment fragment = new CartItemHeadFragment();
        Bundle args = new Bundle();
        args.putString("seller_id", seller_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            seller_id = getArguments().getString("seller_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_item_head, container, false);
        textViewShowDelete = (TextView)view.findViewById(R.id.textView_cart_head_showdelete);
        textViewSeller = (TextView)view.findViewById(R.id.textView_cart_head_seller);

        // "SELECT storeName FROM seller WHERE seller_id = seller_id"
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        fireRef = firebase.getReference("seller");
        //orderByChild???
        fireListener = fireRef.orderByKey().equalTo(seller_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot sellerMap: snapshot.getChildren()) {
                        sellerName = ((Map<String, String>)sellerMap.getValue()).get("storeName");
                        textViewSeller.setText(sellerName);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });


//        textViewSeller.setText(seller_id);




        b1 = true;
        b2 = true;
        textViewShowDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("cart", tag);




                if (tag.equals("cartItemHeadFragment0")) {

                    Fragment f = getParentFragmentManager().findFragmentByTag("cartItemBodyFragment0");
                    if (f != null) {
                        if (b1 == true) {
                            ((CartItemBodyFragment)f).setIsDeleteShow(b1);
                            b1 = false;
                        } else {
                            ((CartItemBodyFragment)f).setIsDeleteShow(b1);
                            b1 = true;
                        }
                    }


                    Fragment f2 = getParentFragmentManager().findFragmentByTag("cartItemBodyFragment1");
                    if (f2 != null) {
                        if (b2 == true) {
                            ((CartItemBodyFragment) f2).setIsDeleteShow(b2);
                            b2 = false;
                        } else {
                            ((CartItemBodyFragment) f2).setIsDeleteShow(b2);
                            b2 = true;
                        }
                    }
                }








                if (tag.equals("cartItemHeadFragment1")) {

                    Fragment f = getParentFragmentManager().findFragmentByTag("cartItemBodyFragment2");
                    if (f != null) {
                        if (b1 == true) {
                            ((CartItemBodyFragment) f).setIsDeleteShow(b1);
                            b1 = false;
                        } else {
                            ((CartItemBodyFragment) f).setIsDeleteShow(b1);
                            b1 = true;
                        }
                    }

                    Fragment f2 = getParentFragmentManager().findFragmentByTag("cartItemBodyFragment3");
                    if (f2 != null) {
                        if (b2 == true) {
                            ((CartItemBodyFragment) f2).setIsDeleteShow(b2);
                            b2 = false;
                        } else {
                            ((CartItemBodyFragment) f2).setIsDeleteShow(b2);
                            b2 = true;
                        }
                    }
                }





            }
        });

        return view;
    }

}