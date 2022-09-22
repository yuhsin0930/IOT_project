package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetPriceActivity extends AppCompatActivity {

    private FragmentManager FragManager;
    private int count;
    private SetPriceFragment fragSetPrice;
    private FragmentTransaction fragTransit;
    private TextView textViewAddNorm;
    private SetPriceFragment newSetPriceFrag;
    private Button buttonFinishedSetNorm;
    private int productNormNum;
    Set<String> productNormSet = new HashSet<String>();
    private Iterator<String> it;
    private int NormEmptyFlag=0;
    private int NormZeroFlag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        count = sp.getInt("NormFragmentCount",1);
        FragManager = getSupportFragmentManager();
        productNormSet = sp.getStringSet("normFragmentSet",new HashSet<String>());
        List<String> productNormArray = new ArrayList<>();
        it = productNormSet.iterator();
        while(it.hasNext()){
            productNormArray.add(it.next());
        }
        if(productNormSet.isEmpty()==false) {
            productNormNum = productNormArray.size();
            for (int i = 0; i < productNormNum; i++) {
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data", productNormArray.get(i));
                fragTransit.add(R.id.linearLayout_setPrice, newSetPriceFrag, productNormArray.get(i));
                fragTransit.commit();
                Log.d("main", "normFragment = " + productNormArray.get(i));
            }
        }

        textViewAddNorm = (TextView)findViewById(R.id.textView_addNorm);
        textViewAddNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data", "setPrice" + count);
                fragTransit.add(R.id.linearLayout_setPrice,newSetPriceFrag,"setPrice"+count);
                fragTransit.commit();
                sp.edit().putInt("NormFragmentCount",count).commit();
                productNormSet.add("setPrice"+count);
            }
        });

        buttonFinishedSetNorm = (Button)findViewById(R.id.button_finishedSetNorm);
        buttonFinishedSetNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productNormSet = sp.getStringSet("normFragmentSet",new HashSet<String>());
                it = productNormSet.iterator();
                while(it.hasNext()){
                    productNormArray.add(it.next());
                }
                if(productNormSet.isEmpty()==false){
                    productNormNum = productNormArray.size();
                     for(int i=0 ; i<productNormNum;i++ ) {
                        SharedPreferences newsp = getSharedPreferences(productNormArray.get(i), MODE_PRIVATE);
                        String productNorm = newsp.getString("productNorm", "");
                        String productNormAmount = newsp.getString("productNormAmount", "");
                        String productNormPrice = newsp.getString("productNormPrice", "");
                        if(productNorm==""||productNormAmount==""||productNormPrice=="") {
                            NormEmptyFlag += 1;
                        }
                        if(productNorm.equals("0")||productNormAmount.equals("0")||productNormPrice.equals("0")){
                            NormZeroFlag += 1;
                        }
                    }
                }
                if(NormEmptyFlag!=0||NormZeroFlag!=0){
                    Toast.makeText(SetPriceActivity.this, "輸入欄位不得為空或為0", Toast.LENGTH_SHORT).show();
                    NormEmptyFlag=0;
                    NormZeroFlag=0;
                }else{
                    Intent intent = new Intent(SetPriceActivity.this,NewProductActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void deleteFragment(String tag) {
        Fragment f = FragManager.findFragmentByTag(tag);
        if (f != null) {
            fragTransit = FragManager.beginTransaction();
            fragTransit.remove(f);
            fragTransit.commit();
//            SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
//            productNormSet = sp.getStringSet("normFragmentSet",new HashSet<String>());
//            Log.d("main","SetSize = " +productNormSet.size());
//            productNormSet.remove(tag);
//            Log.d("main","SetSize = " +productNormSet.size());
//            sp.edit().putStringSet("normFragmentSet",productNormSet).commit();

            SharedPreferences newsp = getSharedPreferences(tag,MODE_PRIVATE);
            newsp.edit().clear();

        }
    }


    public void saveFragment(String tag, Map map){
        Fragment f = FragManager.findFragmentByTag(tag);
        if (f != null) {
            productNormSet.add(tag);
            SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
            sp.edit().putStringSet("normFragmentSet",productNormSet).commit();

            String Norm = String.valueOf(map.get("productNorm"));
            String NormPrice = String.valueOf(map.get("productNormPrice"));
            String NormAmount = String.valueOf(map.get("productNormAmount"));

            SharedPreferences newsp = getSharedPreferences(tag,MODE_PRIVATE);
            newsp.edit().putString("productNorm",Norm)
                    .putString("productNormPrice",NormPrice)
                    .putString("productNormAmount",NormAmount)
                    .commit();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}