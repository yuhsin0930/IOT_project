package com.example.iot_project.NewProduct;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iot_project.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetPriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetPriceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imageViewSetNormDelete;
    private EditText editTextSetNorm_productNorm;
    private EditText editTextSetNorm_productAmount;
    private EditText editTextSetNorm_productPrice;
    private String productNorm;
    private String productNormPrice;
    private String productNormAmount;


    public SetPriceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetPriceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetPriceFragment newInstance(String param1, String param2) {
        SetPriceFragment fragment = new SetPriceFragment();
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
        View v = inflater.inflate(R.layout.fragment_set_price, container, false);
        SetPriceActivity setPriceActivity = (SetPriceActivity) getActivity();
        editTextSetNorm_productNorm = (EditText)v.findViewById(R.id.editText_setNorm_productNorm);
        editTextSetNorm_productAmount = (EditText)v.findViewById(R.id.editText_setNorm_productAmount);
        editTextSetNorm_productPrice = (EditText)v.findViewById(R.id.editText_setNorm_productPrice);

        SharedPreferences newsp = setPriceActivity.getSharedPreferences(mParam2, Context.MODE_PRIVATE);
        productNorm = newsp.getString("productNorm",null);
        productNormPrice = newsp.getString("productNormPrice",null);
        productNormAmount = newsp.getString("productNormAmount",null);

        editTextSetNorm_productNorm.setText(productNorm);
        editTextSetNorm_productPrice.setText(productNormPrice);
        editTextSetNorm_productAmount.setText(productNormAmount);

        Map<String,String> productPrice = new HashMap<>();
        productPrice.put("productNorm",productNorm);
        productPrice.put("productNormPrice",productNormPrice);
        productPrice.put("productNormAmount",productNormAmount);
        Log.d("main",productPrice.toString());
        setPriceActivity.saveFragment(mParam2,productPrice);

        editTextSetNorm_productNorm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0){
                    productNorm = s.toString();
                    productPrice.put("productNorm",productNorm);
                    Log.d("main",productPrice.toString());
                }else{
                    productNorm = null;
                    productPrice.put("productNorm",productNorm);
                    Log.d("main",productPrice.toString());
                }
                setPriceActivity.saveFragment(mParam2,productPrice);
            }
        });
        editTextSetNorm_productPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0){
                    productNormPrice = s.toString();
                    productPrice.put("productNormPrice",productNormPrice);
                    Log.d("main",productPrice.toString());
                }else{
                    productNormPrice=null;
                    productPrice.put("productNormPrice",productNormPrice);
                    Log.d("main",productPrice.toString());
                }
                setPriceActivity.saveFragment(mParam2,productPrice);
            }
        });
        editTextSetNorm_productAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0){
                    productNormAmount= s.toString();
                    productPrice.put("productNormAmount",productNormAmount);
                    Log.d("main",productPrice.toString());
                }else{
                    productNormPrice=null;
                    productPrice.put("productNormAmount",productNormAmount);
                    Log.d("main",productPrice.toString());
                }
                setPriceActivity.saveFragment(mParam2,productPrice);
            }
        });

        productPrice.put("productNorm",productNorm);
        productPrice.put("productNormPrice",productNormPrice);
        productPrice.put("productNormAmount",productNormAmount);
        Log.d("main",productPrice.toString());

        imageViewSetNormDelete = (ImageView)v.findViewById(R.id.imageView_setNorm_delete);
        imageViewSetNormDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPriceActivity.deleteFragment(mParam2);
                Log.d("main",mParam2);
            }
        });


        return v;
    }
}