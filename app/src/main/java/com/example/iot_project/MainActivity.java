package com.example.iot_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.iot_project.SellerRegister.BecomeSellerActivity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private TextView textViewBecomeSaller, textViewLogin;
    private Button buttonLogin,buttonEnroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      To hide Action Bar
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

//      Login button => LoginActivity
        buttonLogin = (Button) findViewById(R.id.button_main_login);
//      (temp) Enroll button => BecomeSellerActivity.class
        buttonEnroll = (Button) findViewById(R.id.button_main_enroll);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BecomeSellerActivity.class);
                startActivity(intent);
            }
        });



    }
}