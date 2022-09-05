package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyStoreActivity extends AppCompatActivity {

    private TextView textViewMyStore_name,textViewMyStore_account,textViewMyStore_checkSalesRecord,textViewMyStore_toBeShipped,textViewMyStore_invaild,textViewMyStore_return,textViewMyStore_myProduct,textViewMyStore_payment;
    private Button buttonMyStore_checkStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store);

        textViewMyStore_name=(TextView)findViewById(R.id.textView_myStore_name);
        textViewMyStore_account = (TextView)findViewById(R.id.textView_myStore_account);
        textViewMyStore_checkSalesRecord = (TextView)findViewById(R.id.textView_myStore_checkSalesRecord);
        textViewMyStore_toBeShipped = (TextView)findViewById(R.id.textView_myStore_toBeShipped);
        textViewMyStore_invaild = (TextView)findViewById(R.id.textView_myStore_invalid);
        textViewMyStore_return = (TextView)findViewById(R.id.textView_myStore_return);
        textViewMyStore_myProduct = (TextView)findViewById(R.id.textView_myStore_myProduct);
        textViewMyStore_payment = (TextView)findViewById(R.id.textView_myStore_payment);

        buttonMyStore_checkStore = (Button)findViewById(R.id.button_myStore_checkStore);
        buttonMyStore_checkStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this,SellerStoreActivity.class);
                startActivity(intent);
            }
        });

        textViewMyStore_myProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this,MyProductActivity.class);
                startActivity(intent);
            }
        });

        textViewMyStore_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });

        textViewMyStore_checkSalesRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoreActivity.this,SalesRecordActivity.class);
                startActivity(intent);
            }
        });

    }
}