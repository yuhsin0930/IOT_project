package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.member.MemberActivity;
import com.example.iot_project.register.RegisterActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.iot_project.Main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextView textViewRegister, textViewForget;
    private Intent intent;
    private ImageView imageViewEyes, imageViewBack;
    private EditText editTextPassword, editTextAccount;
    private String account, password;
    private boolean membershipCheck;
    private InputMethodManager keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();                   // 隱藏ActionBar
        getWindow().setStatusBarColor(0xffffffff);      // 最上面StatusBar白色底
        getWindow().setNavigationBarColor(0xaaffffff);  // 最下面NavigationBar白色底
        getWindow().getDecorView()                      // 上面字設黑 | 下面虛擬按鈕深色
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        imageViewEyes = (ImageView) findViewById(R.id.imageView_login_eyes);
        imageViewBack = (ImageView) findViewById(R.id.imageView_login_back);
        buttonLogin = (Button) findViewById(R.id.button_login_login);
        textViewRegister = (TextView) findViewById(R.id.textView_login_register);
        textViewForget = (TextView) findViewById(R.id.textView_login_forget);
        editTextPassword = (EditText) findViewById(R.id.editText_login_password);
        editTextAccount = (EditText) findViewById(R.id.editText_login_account);



        intent = getIntent();
        editTextAccount.setText(intent.getStringExtra("account"));

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, MemberActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextAccount.length() * editTextPassword.length() > 0) {
                    keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    account = editTextAccount.getText().toString();
                    password = editTextPassword.getText().toString();
                    Log.d("main", "account=" + account);
                    Log.d("main", "password=" + password);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //      取得  Firebase 資料庫 (GET網址)
                    DatabaseReference dataref = database.getReference("member");
                    //      確認帳號密碼是否建立
                    membershipCheck = false;
                    //      搜尋 帳號 密碼 是否已存在Firebase資料庫，且密碼與對應的帳號密碼相同，則會員登入成功

                    dataref.orderByChild("account_name").equalTo(account).addListenerForSingleValueEvent(new ValueEventListener() {
                    //      這裡用 addListenerForSingleValueEvent 只監聽一次, 不登入狀態改密碼會出現"帳號或密碼錯誤"的吐司

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.d("login", "snapshot.exists() = " + snapshot.exists());
                            Log.d("login", "snapshot.getValue() = " + snapshot.getValue());
                            if (snapshot.exists()) {
                                for(DataSnapshot member : snapshot.getChildren()){
                                    String Id = member.getKey();
                                    Log.d("login","Id="+Id);
                                    Map<String,String> memberData = (Map<String,String>)member.getValue();
                                    String account_pwd = memberData.get("password");
//                                    Log.d("main","memberData.get(\"password\")="+memberData.get("password"));

                                    if (password.equals(account_pwd)) {
                                        membershipCheck = true;
                                        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
                                        sp.edit()
                                                .putBoolean("is_login", membershipCheck)
                                                .putString("member_id",Id)
                                                .putString("account_name",account)
                                                .commit();
                                        Log.d("login","[LoginInformation]sp.getall()"+sp.getAll());
                                        Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                                        intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "請輸入完整帳號密碼", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        editTextAccount.clearFocus();

    }

}