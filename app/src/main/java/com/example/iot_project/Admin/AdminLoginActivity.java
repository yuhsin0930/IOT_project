package com.example.iot_project.Admin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iot_project.R;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText editTextAdminAccount, editTextAdminPassword;
    private Button buttonSubmit, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

//       editText receive administer's account and password
        editTextAdminAccount = (EditText) findViewById(R.id.editText_adminlogin_account);
        editTextAdminPassword = (EditText) findViewById(R.id.editText_adminlogin_password);

//       button to submit or cancel the input
        buttonSubmit = (Button) findViewById(R.id.button_adminlogin_submit);
        buttonCancel = (Button) findViewById(R.id.button_adminlogin_cancel);

//        1. submit button:
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAdminAccount.length()>0 && editTextAdminPassword.length()>0){
                    String adminAccount = editTextAdminAccount.getText().toString();
                    String adminPassword = editTextAdminPassword.getText().toString();
                    if(adminAccount.equals("admin")&&adminPassword.equals("admin1234")){
                        Intent intent = new Intent(AdminLoginActivity.this, AdminMainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(AdminLoginActivity.this, "帳號密碼輸入錯誤", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
//        2. cancel button:
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAdminPassword.setText("");
                editTextAdminAccount.setText("");
            }
        });
    }
}