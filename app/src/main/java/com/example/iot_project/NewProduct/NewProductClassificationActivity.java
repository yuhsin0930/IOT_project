package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.iot_project.R;


public class NewProductClassificationActivity extends AppCompatActivity {


    private FragmentManager fragManager;
    private ProductClassificationFragment fragProductClass;
    private FragmentTransaction fragTransit;
    private TextView textViewAddClass;
    private int count;
    private ProductClassificationFragment newProductClass;
    private String deleteNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product_classification);

//        fragManager = getSupportFragmentManager();
//        fragProductClass = ProductClassificationFragment.newInstance("Fragment ProductClass", "Data 1");
//        count = 1;
//        fragTransit = fragManager.beginTransaction();
//        fragTransit.add(R.id.linear_layout_productClass_fragment, fragProductClass, "fragProductClass");
//        fragTransit.commit();

        count = 1;
        fragManager = getSupportFragmentManager();
        fragProductClass = ProductClassificationFragment.newInstance("Fragment ProductClass", "" + count);
        fragTransit = fragManager.beginTransaction();
        fragTransit.add(R.id.linear_layout_productClass_fragment, fragProductClass, "" + count);
        fragTransit.commit();


        textViewAddClass = (TextView) findViewById(R.id.textView_newProductClass);
        textViewAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragTransit = fragManager.beginTransaction();
//                newProductClass = ProductClassificationFragment.newInstance("Add data", "count = " + count);
//                count++;
//                fragTransit.add(R.id.linear_layout_productClass_fragment, newProductClass, "fragProductClass" + count);
//                fragTransit.commit();

                count++;
                fragTransit = fragManager.beginTransaction();
                newProductClass = ProductClassificationFragment.newInstance("Add data", "" + count);
                fragTransit.add(R.id.linear_layout_productClass_fragment, newProductClass, "" + count);
                fragTransit.commit();

            }
        });

    }

    public void deleteFragment(String tag) {
        Fragment f = fragManager.findFragmentByTag(tag);
        if (f != null) {
            fragTransit = fragManager.beginTransaction();
            fragTransit.remove(f);
            fragTransit.commit();
        }
    }

}

