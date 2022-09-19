package com.example.iot_project.NewProduct;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
    private ImageView imageButtonDeleteClass;
    private int deleteFlag;
    private String productClassitem;

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
            }
        });

        //-----------------------------------------------------------------------------------------
        spinnerProductClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        productClassitem = "最新商品";
                        break;
                    case 1:
                        productClassitem = "熱賣商品";
                        break;
                    case 2 :
                        productClassitem = "男女衣著";
                        break;
                    case 3 :
                        productClassitem = "男女鞋";
                        break;
                    case 4:
                        productClassitem = "3C與筆電";
                        break;
                    case 5 :
                        productClassitem = "飾品";
                        break;
                    case 6 :
                        productClassitem = "家電影音";
                        break;
                    case 7:
                        productClassitem = "寵物用品";
                        break;
                    case 8 :
                        productClassitem = "美食伴手禮";
                        break;
                    case 9:
                        productClassitem = "文創商品";
                        break;
                    case 10:
                        productClassitem = "手機平板周邊";
                        break;
                    case 11 :
                        productClassitem = "生活用品";
                        break;
                    case 12 :
                        productClassitem = "嬰幼兒用品";
                        break;
                    case 13:
                        productClassitem = "戶外/旅行";
                        break;
                    case 14 :
                        productClassitem = "美妝保健";
                        break;
                    case 15 :
                        productClassitem = "汽機車零件";
                        break;
                    case 16 :
                        productClassitem = "運動/健身";
                        break;
                    case 17 :
                        productClassitem = "書籍及雜誌期刊";
                        break;
                    case 18 :
                        productClassitem = "娛樂/收藏";
                        break;
                    case 19 :
                        productClassitem = "其他類別";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //------------------------------------------------------------------------------------------
        switchProductClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private int ProductClassUseFlag;

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    ProductClassUseFlag = 1;
                }
                else{
                    ProductClassUseFlag = 0;
                }
            }
        });
        //------------------------------------------------------------------------------------------

        return v;
    }

}