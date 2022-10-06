package com.example.iot_project.Cart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iot_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartDoneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartDoneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private FragmentManager fragmentMgr;
    private FragmentTransaction fragmentTrans;
    private CartDoneItemHeadFragment cartDoneItemHeadFragment;
    private CartCheckItemBodyFragment cartCheckItemBodyFragment;
    private CartDoneItemFooterFragment cartDoneItemFooterFragment;
    private Button buttonDone;
    private CartActivity cartActivity;

    public CartDoneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartDoneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartDoneFragment newInstance(String param1, String param2) {
        CartDoneFragment fragment = new CartDoneFragment();
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
        view = inflater.inflate(R.layout.fragment_cart_done, container, false);

        buttonDone = (Button)view.findViewById(R.id.button_done);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartActivity.onBackPressed();
            }
        });




        fragmentMgr = getParentFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        cartDoneItemHeadFragment = new CartDoneItemHeadFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartDoneItemHeadFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();



        fragmentTrans = fragmentMgr.beginTransaction();
        cartCheckItemBodyFragment = new CartCheckItemBodyFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartCheckItemBodyFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();
        fragmentTrans = fragmentMgr.beginTransaction();
        cartCheckItemBodyFragment = new CartCheckItemBodyFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartCheckItemBodyFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();
        fragmentTrans = fragmentMgr.beginTransaction();
        cartCheckItemBodyFragment = new CartCheckItemBodyFragment();            // 或許能共用
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartCheckItemBodyFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();



        fragmentTrans = fragmentMgr.beginTransaction();
        cartDoneItemFooterFragment = new CartDoneItemFooterFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartDoneItemFooterFragment, "cartDoneItemFooterFragment");
        fragmentTrans.commit();













        fragmentMgr = getParentFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        cartDoneItemHeadFragment = new CartDoneItemHeadFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartDoneItemHeadFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();



        fragmentTrans = fragmentMgr.beginTransaction();
        cartCheckItemBodyFragment = new CartCheckItemBodyFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartCheckItemBodyFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();
        fragmentTrans = fragmentMgr.beginTransaction();
        cartCheckItemBodyFragment = new CartCheckItemBodyFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartCheckItemBodyFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();
        fragmentTrans = fragmentMgr.beginTransaction();
        cartCheckItemBodyFragment = new CartCheckItemBodyFragment();            // 或許能共用
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartCheckItemBodyFragment, "cartDoneItemHeadFragment");
        fragmentTrans.commit();



        fragmentTrans = fragmentMgr.beginTransaction();
        cartDoneItemFooterFragment = new CartDoneItemFooterFragment();
        fragmentTrans.add(R.id.LinearLayout_done_forFragment, cartDoneItemFooterFragment, "cartDoneItemFooterFragment");
        fragmentTrans.commit();


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cartActivity = (CartActivity)getActivity();
    }

}