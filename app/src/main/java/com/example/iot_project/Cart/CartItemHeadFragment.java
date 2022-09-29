package com.example.iot_project.Cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iot_project.R;

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

    public CartItemHeadFragment() {}

    public static CartItemHeadFragment newInstance(String tag) {
        CartItemHeadFragment fragment = new CartItemHeadFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tag = getArguments().getString("tag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_item_head, container, false);
        textViewShowDelete = (TextView)view.findViewById(R.id.textView_cart_head_showdelete);

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