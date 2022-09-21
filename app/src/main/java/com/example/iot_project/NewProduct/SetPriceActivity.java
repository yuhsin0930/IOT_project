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

import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    private String[] productNormArray;
    private int productNormNum;
    Set<String> productNormSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        count = sp.getInt("NormFragmentCount",1);
        productNormSet = sp.getStringSet("normFragmentSet",null);
        FragManager = getSupportFragmentManager();
        if(productNormSet!=null){
            productNormArray = (String[]) productNormSet.toArray();
            Log.d("main","productNormArray = "+productNormArray.toString());
            productNormNum = productNormArray.length;
            for(int i=0;i<productNormNum;i++){
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data",productNormArray[i].toString() );
                fragTransit.add(R.id.linearLayout_setPrice,newSetPriceFrag,productNormArray[i].toString());
                fragTransit.commit();
                Log.d("main","normFragment = " +productNormArray[i].toString());
            }
        }else{
            fragSetPrice = SetPriceFragment.newInstance("Fragment SetPrice","setPrice"+count);
            fragTransit = FragManager.beginTransaction();
            fragTransit.add(R.id.linearLayout_setPrice,fragSetPrice,"setPrice"+count);
            fragTransit.commit();
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
                for(int i=0 ; i<productNormNum;i++ ){
                    String normFrag = productNormArray[i];
                    SharedPreferences newsp = getSharedPreferences(normFrag,MODE_PRIVATE);
                    String productNorm = newsp.getString("productNorm",null);
                    String productNormAmount = newsp.getString("productNormAmount",null);
                    String productNormPrice = newsp.getString("productNormPrice",null);
                }

                Intent intent = new Intent(SetPriceActivity.this,NewProductActivity.class);
                startActivity(intent);
            }
        });

    }
    public void deleteFragment(String tag) {
        Fragment f = FragManager.findFragmentByTag(tag);
        if (f != null) {
            fragTransit = FragManager.beginTransaction();
            fragTransit.remove(f);
            fragTransit.commit();
//            productPriceMap.remove(tag);
//            Log.d("main",productPriceMap.toString());
            productNormSet.remove(tag);
            SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
            sp.edit().putStringSet("normFragmentSet",productNormSet);

            SharedPreferences newsp = getSharedPreferences(tag,MODE_PRIVATE);
            newsp.edit().clear();

        }
    }

//    Map<String,Object> productPriceMap = new HashMap<>();
    public void saveFragment(String tag, Map map){
        Fragment f = FragManager.findFragmentByTag(tag);
        if (f != null) {
//            productPriceMap.put(tag,map);
//            Log.d("main",productPriceMap.toString());
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
}