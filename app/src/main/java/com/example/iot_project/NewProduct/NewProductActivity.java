package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.iot_project.MyProduct.MyProductActivity;
import com.example.iot_project.R;

public class NewProductActivity extends AppCompatActivity {

    private EditText editTextNewProduct_Name;
    private TextView textViewNewProduct_describe;
    private TextView textViewNewProduct_classification;
    private TextView textViewNewProduct_setPrice;
    private EditText editTextNumberNewProduct_Quatity;
    private TextView textViewNewProduct_shippiingFee;
    private Button button_newProduct_launch;
    private Button buttonNewProduct_save;
    private TextView textViewNewProduct_NameLength;
    private TextView textViewNewProduct_describeLength;
    private String NewProductNameLength;
    private String NewProductName;
    private ImageButton imagebuttonAddProductNum;
    private ImageButton imagebuttonMinusProductNum;
    private Integer ProductNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);

        textViewNewProduct_NameLength = (TextView)findViewById(R.id.textView_newProduct_NameLength);
        textViewNewProduct_describeLength = (TextView)findViewById(R.id.textView_newProduct_describeLength);
        textViewNewProduct_describe = (TextView)findViewById(R.id.textView_newProduct_Describe);
        textViewNewProduct_classification = (TextView)findViewById(R.id.textView_newProduct_Classification);
        textViewNewProduct_setPrice = (TextView)findViewById(R.id.textView_newProduct_SetPrice);
        textViewNewProduct_shippiingFee = (TextView)findViewById(R.id.textView_newProduct_shippiingFee);

        editTextNewProduct_Name = (EditText)findViewById(R.id.editTextText_newProduct_Name);
        editTextNumberNewProduct_Quatity = (EditText)findViewById(R.id.editTextNumber_newProduct_Quatity);

        //------------------------------------------------------------------------------------------
        NewProductNameLength=sp.getString("productNameLength","0");
        String productName = sp.getString("productName","");
        editTextNewProduct_Name.setText(productName);
        editTextNewProduct_Name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        editTextNewProduct_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NewProductNameLength = String.valueOf(s.length());
                textViewNewProduct_NameLength.setText(NewProductNameLength);
            }

            @Override
            public void afterTextChanged(Editable s) {
                NewProductName =  s.toString();
                NewProductNameLength = String.valueOf(NewProductName.length());
                sp.edit().putString("productNameLength",NewProductNameLength)
                        .putString("productName",NewProductName).commit();

            }
        });
        textViewNewProduct_NameLength.setText(NewProductNameLength);

        //------------------------------------------------------------------------------------------
        String  describeLength = sp.getString("DescribeWordNum","0");
        textViewNewProduct_describe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(NewProductActivity.this, ProductDescribeActivity.class);
               startActivity(intent);
            }
        });
        textViewNewProduct_describeLength.setText(describeLength);

        //------------------------------------------------------------------------------------------
        textViewNewProduct_classification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, NewProductClassificationActivity.class);
                startActivity(intent);
            }
        });
        //-----------------------------------------------------------------------------------------
        textViewNewProduct_setPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, SetPriceActivity.class);
                startActivity(intent);
            }
        });
        //------------------------------------------------------------------------------------------
        imagebuttonAddProductNum  = (ImageButton)findViewById(R.id.imageButton_productNumAdd);
        imagebuttonMinusProductNum = (ImageButton)findViewById(R.id.imageButton_productNum_minus);
        ProductNum = Integer.valueOf(editTextNumberNewProduct_Quatity.getText().toString());
        sp.edit().putInt("productNum",ProductNum);

        //------------------------------------------------------------------------------------------
        imagebuttonAddProductNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductNum = Integer.valueOf(editTextNumberNewProduct_Quatity.getText().toString());
                ProductNum++;
                if(ProductNum>99999){
                    ProductNum=99999;
                }
                else{
                    editTextNumberNewProduct_Quatity.setText(String.valueOf(ProductNum));
                }

            }
        });
        //------------------------------------------------------------------------------------------
        imagebuttonMinusProductNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductNum = Integer.valueOf(editTextNumberNewProduct_Quatity.getText().toString());
                ProductNum--;
                if(ProductNum<0){
                    ProductNum=0;
                }else{
                    editTextNumberNewProduct_Quatity.setText(String.valueOf(ProductNum));
                }


            }
        });
        //------------------------------------------------------------------------------------------
        editTextNumberNewProduct_Quatity.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)} );
        //------------------------------------------------------------------------------------------
        textViewNewProduct_shippiingFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, ShippingFeeActivity.class);
                startActivity(intent);
            }
        });


        //------------------------------------------------------------------------------------------

        button_newProduct_launch = (Button) findViewById(R.id.button_newProduct_launch);
        button_newProduct_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, MyProductActivity.class);
                startActivity(intent);
            }
        });



        //------------------------------------------------------------------------------------------
//        商品照片(picture_id)(int)
//        商品名稱(gName)(Text)
//        商品描述(info)(Text)
//        商品分類(type)(Text)
//        商品規格(productNorm)(Text)
//        商品價格(price)、商品數量)(int)
//        商品數量(soldQuantity)(int)

//        包裹尺寸-長(packageSizeLength)(int)
//        包裹尺寸-寬(packageSizeWidth)(int)
//        包裹尺寸-高(packageSizeHeight)(int)

//        運送方法-全家(shippingMethod)(Boolean)
//        運送方法-7-11(shippingMethod)(Boolean)
//        運送方法-黑貓(shippingMethod)(Boolean)
//        運送方法-郵局(shippingMethod)(Boolean)

//        運費價格-全家(shippingFeeFamilyMart)(int)
//        運費價格-7-11(shippingFeeSeven)(int)
//        運費價格-黑貓(shippingFeeBlackCat)(int)
//        運費價格-郵局(shippingFeePostOffice)(int)
    }
}