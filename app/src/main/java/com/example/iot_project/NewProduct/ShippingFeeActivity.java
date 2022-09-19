package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.iot_project.R;

import java.util.List;

public class ShippingFeeActivity extends AppCompatActivity {

    private TextView textView_setWeight;
    private EditText editTextNumber_productWidth;
    private EditText editTextNumber_productLength;
    private EditText editTextNumber_productHeight;
    private Switch switch_postOffice;
    private Switch switch_blackCat;
    private Switch switch_seven;
    private Switch switch_familyMart;
    private Button buttonFinishSettingShippingFee;
    private int shippingFlag_postOffice;
    private int shippingFlag_blackCat;
    private int shippingFlag_seven;
    private int shippingFlag_familyMart;
    private int productHeight;
    private int productLength;
    private int productWidth;
    private int shippingFee_postoffice,shippingFee_seven,shippingFee_familyMart,shippingFee_blackCat;
    private TextView textView_shippingDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_fee);

        editTextNumber_productWidth = (EditText)findViewById(R.id.editTextNumber_productWidth);
        editTextNumber_productLength = (EditText)findViewById(R.id.editTextNumberproductLength);
        editTextNumber_productHeight = (EditText)findViewById(R.id.editTextNumberHeight);
        //------------------------------------------------------------------------------------------
        productHeight = Integer.valueOf(editTextNumber_productHeight.getText().toString());
        productLength = Integer.valueOf(editTextNumber_productLength.getText().toString());
        productWidth = Integer.valueOf(editTextNumber_productWidth.getText().toString());

        if(productWidth>45||productLength>45||productHeight>45){
            switch_familyMart.setClickable(false);
            switch_seven.setClickable(false);
        }

        //------------------------------------------------------------------------------------------
        switch_postOffice = (Switch)findViewById(R.id.switch_postOffice);
        switch_postOffice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    shippingFlag_postOffice = 1;
                }else{
                    shippingFlag_postOffice = 0;
                }
            }
        });
        switch_blackCat = (Switch)findViewById(R.id.switch_blackCat);
        switch_blackCat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    shippingFlag_blackCat = 1;
                }else{
                    shippingFlag_blackCat = 0;
                }
            }
        });
        switch_seven = (Switch)findViewById(R.id.switch_seven);
        switch_seven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    shippingFlag_seven = 1;
                }else{
                    shippingFlag_seven = 0;
                }
            }
        });
        switch_familyMart = (Switch)findViewById(R.id.switch_familyMart);
        switch_familyMart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    shippingFlag_familyMart = 1;
                }else{
                    shippingFlag_familyMart = 0;
                }
            }
        });
        //-----------------------------------------------------------------------------------------
        textView_shippingDetail = (TextView)findViewById(R.id.textView_shippingDetail);
        textView_shippingDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //-----------------------------------------------------------------------------------------
        buttonFinishSettingShippingFee = (Button)findViewById(R.id.button_finishSetShipping);
        buttonFinishSettingShippingFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(ShippingFeeActivity.this,NewProductActivity.class);
                startActivity(intent);
            }
        });
    }
}