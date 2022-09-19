package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.member.MemberActivity;
import com.example.iot_project.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextView textViewRegister;
    private Intent intent;
    private ImageView imageViewEyes;
    private EditText editTextPassword, editTextAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(0xffffffff);      // 最上面StatusBar白色底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        imageViewEyes = (ImageView)findViewById(R.id.imageView_login_eyes);
        buttonLogin = (Button)findViewById(R.id.button_login_login);
        textViewRegister = (TextView)findViewById(R.id.textView_login_register);
        editTextPassword = (EditText)findViewById(R.id.editText_login_password);
        editTextAccount = (EditText)findViewById(R.id.editText_login_account);

        imageViewEyes.setOnClickListener(new View.OnClickListener() {
            Boolean flag = true;
            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewEyes.setImageDrawable(ContextCompat.getDrawable(LoginActivity.this, R.drawable.outline_visibility_black_18));
                    Toast.makeText(LoginActivity.this, "顯示密碼", Toast.LENGTH_SHORT).show();
                } else {
                    flag = true;
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewEyes.setImageDrawable(ContextCompat.getDrawable(LoginActivity.this, R.drawable.outline_visibility_off_black_18));
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, MemberActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("name", "註冊");
                intent.putExtra("isFromRegister", true);
                startActivity(intent);
            }
        });

        editTextAccount.clearFocus();

    }
}