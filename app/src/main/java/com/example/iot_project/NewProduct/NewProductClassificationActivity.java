package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class NewProductClassificationActivity extends AppCompatActivity {


    private FragmentManager fragManager;
    private ProductClassificationFragment fragProductClass;
    private FragmentTransaction fragTransit;
    private TextView textViewAddClass;
    private int count;
    private ProductClassificationFragment newProductClass;
    private Button buttonNewClassFinished;
    List<String> productClassFragList = new ArrayList<>();
    Map<String,String> productClassMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product_classification);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        count = sp.getInt("productClassFragCount",1);
        fragManager = getSupportFragmentManager();
        SharedPreferences newsp = getSharedPreferences("productClass",MODE_PRIVATE);
        productClassMap= (Map<String, String>) newsp.getAll();
        for(String key : productClassMap.keySet()){
            productClassFragList.add(key);
        }
        Log.d("main",productClassFragList.toString());
        if(productClassFragList.size()!=0){
            for(int i=0;i<productClassFragList.size();i++){
                fragProductClass = ProductClassificationFragment.newInstance("Fragment ProductClass","productClass"+count );
                fragTransit = fragManager.beginTransaction();
                fragTransit.add(R.id.linear_layout_productClass_fragment, fragProductClass,"productClass"+count );
                fragTransit.commit();
            }
        }
        textViewAddClass = (TextView) findViewById(R.id.textView_newProductClass);
        textViewAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                sp.edit().putInt("productClassFragCount",count);
                fragTransit = fragManager.beginTransaction();
                newProductClass = ProductClassificationFragment.newInstance("Add data", "productClass" + count);
                fragTransit.add(R.id.linear_layout_productClass_fragment, newProductClass, "productClass" + count);
                fragTransit.commit();

            }
        });
        //------------------------------------------------------------------------------------------
        buttonNewClassFinished = (Button)findViewById(R.id.button_newClass_finished);
        buttonNewClassFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductClassificationActivity.this,NewProductActivity.class);
                startActivity(intent);
            }
        });

    }

    public void deleteFragment(String tag) {
        Fragment f = fragManager.findFragmentByTag(tag);
        if (f != null) {
            fragTransit = fragManager.beginTransaction();
            fragTransit.remove(f);
            fragTransit.commit();

            SharedPreferences newsp = getSharedPreferences("productClass",MODE_PRIVATE);
            newsp.edit().remove(tag).commit();
            Log.d("main",newsp.getAll().toString());
        }
    }

    public void saveClass(String tag,String classname){
        Fragment f = fragManager.findFragmentByTag(tag);
        productClassFragList.add(tag);
        SharedPreferences newsp = getSharedPreferences("productClass",MODE_PRIVATE);
        newsp.edit().putString(tag,classname).commit();
        Log.d("main",newsp.getAll().toString());
    }
}

