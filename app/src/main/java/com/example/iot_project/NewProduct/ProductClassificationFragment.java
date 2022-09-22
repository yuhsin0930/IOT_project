package com.example.iot_project.NewProduct;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.iot_project.MainActivity;
import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductClassificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductClassificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinnerProductClass;
    private ImageButton imageButtonDeleteClass;
    private String productClassName;

    public ProductClassificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductClassicificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductClassificationFragment newInstance(String param1, String param2) {
        ProductClassificationFragment fragment = new ProductClassificationFragment();
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
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_classification, container, false);
        NewProductClassificationActivity newProductClassificationActivity = (NewProductClassificationActivity) getActivity();
        spinnerProductClass = (Spinner)v.findViewById(R.id.spinner_productClass);
        imageButtonDeleteClass=(ImageButton)v.findViewById(R.id.imageButton_deleteClass);
        String[] productClassArray  = getResources().getStringArray(R.array.productClass);
        SharedPreferences sp = newProductClassificationActivity.getSharedPreferences(mParam2,Context.MODE_PRIVATE);

        spinnerProductClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productClassName = productClassArray[position];

                sp.edit().putString("productClass",productClassName).commit();
                Log.d("main",sp.getAll().toString());
                newProductClassificationActivity.saveClass(mParam2,productClassName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        imageButtonDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProductClassificationActivity.deleteFragment(mParam2);
            }
        });
        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}