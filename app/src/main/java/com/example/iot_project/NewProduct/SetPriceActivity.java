package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iot_project.R;

public class SetPriceActivity extends AppCompatActivity {

    private FragmentManager FragManager;
    private int count;
    private SetPriceFragment fragSetPrice;
    private FragmentTransaction fragTransit;
    private TextView textViewAddNorm;
    private SetPriceFragment newSetPriceFrag;
    private Button buttonFinishedSetNorm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        count = 1;
        FragManager = getSupportFragmentManager();
        fragSetPrice = SetPriceFragment.newInstance("Fragment SetPrice","setPrice"+count);
        fragTransit = FragManager.beginTransaction();
        fragTransit.add(R.id.linearLayout_setPrice,fragSetPrice,"setPrice"+count);
        fragTransit.commit();

        textViewAddNorm = (TextView)findViewById(R.id.textView_addNorm);
        textViewAddNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data", "setPrice" + count);
                fragTransit.add(R.id.linearLayout_setPrice,newSetPriceFrag,"setPrice"+count);
                fragTransit.commit();
            }
        });

        buttonFinishedSetNorm = (Button)findViewById(R.id.button_finishedSetNorm);
        buttonFinishedSetNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        }
    }
}