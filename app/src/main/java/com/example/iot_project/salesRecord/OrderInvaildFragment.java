package com.example.iot_project.salesRecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iot_project.R;

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
        return inflater.inflate(R.layout.fragment_order_invaild, container, false);
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link OrderDetailFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class OrderDetailFragment extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public OrderDetailFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment orderDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static OrderDetailFragment newInstance(String param1, String param2) {
            OrderDetailFragment fragment = new OrderDetailFragment();
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
            return inflater.inflate(R.layout.fragment_to_be_ship_detail, container, false);
        }
    }
}

//-------------------------------------------------------------------------------------------------
// 以下是予馨的願望:
// 從firebase存取同一賣家 訂單狀態="不成立" 的所有訂單資訊
// 訂單編號
// 訂單中所有商品的 商品名稱  商品售價 購買數量
// 取件人姓名
// 買家帳號
// 付款方式
// 收件地址
// 取件方式
// 付款狀態
// 訂單總金額