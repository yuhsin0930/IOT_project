package com.example.iot_project.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iot_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminMemberActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonDeleteMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_member);

//       顯示返回鍵
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

//      取得圖片以外的會員資料
        Intent intent = getIntent();

//      由SharedPreference取得會員圖片base64字串並轉換成Bitmap
        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
        String base64Pic =sp.getString("picture","").toString();
//        String base64Pic = intent.getStringExtra("picture"); //intent 傳不過來，改存在SharedPreference
        Log.d("main", "base64Pic=" + base64Pic);
        byte[] decodedString = Base64.decode(base64Pic, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);

//      顯示此會員的代表圖片
        imageView = (ImageView) findViewById(R.id.imageView_admin_menber_id);
        imageView.setImageBitmap(decodedByte);

        String account_name = intent.getStringExtra("account_name");
        String title = "會員 " + account_name + " 的詳細資料";
        actionbar.setTitle(title);

        String member_id = intent.getStringExtra("member_Id").toString();

        buttonDeleteMember = (Button) findViewById(R.id.button_admin_member_delete);
        buttonDeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder altbuilder = new AlertDialog.Builder(AdminMemberActivity.this);

                altbuilder.setTitle("帳號刪除");
                altbuilder.setMessage("請確認是否刪除帳號?");
                altbuilder.setIcon(android.R.drawable.ic_dialog_alert);
                altbuilder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference dataRef = database.getReference("member");
                        dataRef.child(member_id).removeValue();//delete此帳號
                        onBackPressed();//關掉此activity
                    }
                });

                altbuilder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                altbuilder.show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){ //開啟返回鍵
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}