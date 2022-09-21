package com.example.iot_project.NewProduct;

import android.app.FragmentTransaction;
import android.content.Context;
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
import java.util.List;
import java.util.Map;

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
    private ListView ListViewProductClass;
    private Spinner spinnerProductClass;
    private Switch switchProductClass;
    private ImageButton imageButtonDeleteClass;
    private int deleteFlag;
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
        switchProductClass = (Switch)v.findViewById(R.id.switch_openProductClass);

        //-----------------------------------------------------------------------------------------
        imageButtonDeleteClass=(ImageButton)v.findViewById(R.id.imageButton_deleteClass);
//        imageButtonDeleteClass.setOnClickListener(newProductClassificationActivity);

        imageButtonDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newProductClassificationActivity.deleteFragment(mParam2);
                Log.d("main",mParam2);
            }
        });

        //-----------------------------------------------------------------------------------------
        Map<String,Object> productClassItem = new HashMap<String,Object>();

        spinnerProductClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private int ProductClassUseFlag;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        productClassName = "最新商品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 1:
                        productClassName = "熱賣商品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 2 :
                        productClassName = "男女衣著";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 3 :
                        productClassName = "男女鞋";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 4:
                        productClassName = "3C與筆電";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 5 :
                        productClassName = "飾品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 6 :
                        productClassName = "家電影音";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 7:
                        productClassName = "寵物用品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 8 :
                        productClassName = "美食伴手禮";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 9:
                        productClassName = "文創商品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 10:
                        productClassName = "手機平板周邊";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 11 :
                        productClassName = "生活用品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 12 :
                        productClassName = "嬰幼兒用品";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 13:
                        productClassName = "戶外/旅行";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());
                        break;
                    case 14 :
                        productClassName = "美妝保健";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 15 :
                        productClassName = "汽機車零件";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 16 :
                        productClassName = "運動/健身";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());
                        break;
                    case 17 :
                        productClassName = "書籍及雜誌期刊";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 18 :
                        productClassName = "娛樂/收藏";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                    case 19 :
                        productClassName = "其他類別";
                        switchProductClass.setChecked(true);
                        ProductClassUseFlag = 1;
                        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked==true){
                                    ProductClassUseFlag = 1;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                                else{
                                    ProductClassUseFlag = 0;
                                    productClassItem.clear();
                                    productClassItem.put(productClassName,ProductClassUseFlag);
                                    Log.d("main",productClassItem.toString());

                                    newProductClassificationActivity.saveClass(mParam2,productClassItem);
                                }
                            }
                        });
                        productClassItem.clear();
                        productClassItem.put(productClassName,ProductClassUseFlag);
                        Log.d("main",productClassItem.toString());

                        newProductClassificationActivity.saveClass(mParam2,productClassItem);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

}