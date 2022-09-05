package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SellerDetailActivity extends AppCompatActivity {


    private TextView textViewSellerDetail_name;
    private TextView textViewSellerDetail_nationalID;
    private TextView textViewSellerDetail_birthday;
    private TextView textViewSellerDetail_county;
    private TextView textViewSellerDetail_area;
    private EditText editTextSellerDetail_areaNumber;
    private EditText editTextSellerDetail_address;
    private Button buttonSellerDetail_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_detail);

        textViewSellerDetail_name = (TextView)findViewById(R.id.textView_sellerDetail_name);
        textViewSellerDetail_nationalID = (TextView)findViewById(R.id.textView_sellerDetail_nationalID);
        textViewSellerDetail_birthday = (TextView)findViewById(R.id.textView_sellerDetail_birthday);
        textViewSellerDetail_county = (TextView)findViewById(R.id.textView_sellerDetail_county);
        textViewSellerDetail_area = (TextView)findViewById(R.id.textView_sellerDetail_area);

        editTextSellerDetail_areaNumber = (EditText)findViewById(R.id.editText_sellerDetail_areaNumber);
        editTextSellerDetail_address = (EditText)findViewById(R.id.editText_sellerDetail_address);

        buttonSellerDetail_next = (Button) findViewById(R.id.button_sellerDetail_next);
        buttonSellerDetail_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerDetailActivity.this,BankAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}