package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iot_project.MyProduct.MyProductActivity;
import com.example.iot_project.salesRecord.PaymentActivity;
import com.example.iot_project.salesRecord.SalesRecordActivity;
import com.example.iot_project.sellerStore.SellerStoreActivity;

public class MyStoreActivity extends AppCompatActivity {

    private TextView textViewMyStore_name,textViewMyStore_account,textViewMyStore_checkSalesRecord,textViewMyStore_toBeShipped,textViewMyStore_invaild,textViewMyStore_return,textViewMyStore_myProduct,textViewMyStore_payment;
    private Button buttonMyStore_checkStore;
    private static final String DB_FILE = "demo.db";
    private static final String DB_TABLE= "create_goodsSQL";
    private DBHelper dbHelper;
    private ImageView imageView_myPicture;
    private TextView textView_myStore_checkStore;
    private Button button_myStore_editStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store);
        setWindow();
        imageView_myPicture = (ImageView)findViewById(R.id.imageView_myStore_picture);
        textViewMyStore_name=(TextView)findViewById(R.id.textView_myStore_name);
        textViewMyStore_account = (TextView)findViewById(R.id.textView_myStore_account);
        textViewMyStore_checkSalesRecord = (TextView)findViewById(R.id.textView_myStore_checkSalesRecord);
        textViewMyStore_toBeShipped = (TextView)findViewById(R.id.textView_myStore_toBeShipped);
        textViewMyStore_invaild = (TextView)findViewById(R.id.textView_myStore_invalid);
        textViewMyStore_return = (TextView)findViewById(R.id.textView_myStore_return);
        textViewMyStore_myProduct = (TextView)findViewById(R.id.textView_myStore_myProduct);
        textViewMyStore_payment = (TextView)findViewById(R.id.textView_myStore_payment);
        textView_myStore_checkStore = (TextView)findViewById(R.id.textView_myStore_checkStore);

        button_myStore_editStore = (Button)findViewById(R.id.button_myStore_editStore);

        imageView_myPicture.setImageResource(R.drawable.cat);

        button_myStore_editStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView_myStore_checkStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this, SellerStoreActivity.class);
                startActivity(intent);
            }
        });

        textViewMyStore_myProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this, MyProductActivity.class);
                startActivity(intent);
            }
        });

        textViewMyStore_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        textViewMyStore_checkSalesRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyStoreActivity.this, SalesRecordActivity.class);
                startActivity(intent);


            }
        });

    }
    private void setWindow() {
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(0xFFFFFF);
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}


// -----------------------------------------------------------------------------------
//  以下是予馨的願望
//  存取現在登入賣家的 商場照片 、 商場名稱 、賣家帳號
//  存取現在登入賣家的 "待出貨" 商品數量
//  存取現在登入賣家的 "不成立" 商品數量
//  存取現在登入賣家的 "退貨/退款" 商品數量