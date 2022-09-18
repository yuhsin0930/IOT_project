package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.iot_project.SellerRegister.BecomeSellerActivity;
import com.example.iot_project.shoppingCart.ShoppingCartActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewBecomeSaller, textViewLogin;
    private TextView textViewShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewBecomeSaller = (TextView)findViewById(R.id.textView_BecomeSaller);
        textViewLogin = (TextView)findViewById(R.id.textView_login);
        textViewShoppingCart = (TextView) findViewById(R.id.textView_shoppingCart);
        textViewBecomeSaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BecomeSellerActivity.class);
                startActivity(intent);
            }
        });
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        textViewShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }
}