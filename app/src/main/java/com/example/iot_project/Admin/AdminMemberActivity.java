package com.example.iot_project.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;

import com.example.iot_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class AdminMemberActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_member);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        //      get base64 picture (String) from firebase and set in ImageView
        imageView = (ImageView) findViewById(R.id.imageView_admin_menber_id);
        SharedPreferences sp = getSharedPreferences("LoginInformation", MODE_PRIVATE);
        String base64Pic =sp.getString("picture","").toString();
//        String base64Pic = intent.getStringExtra("picture"); //intent 傳不過來，改存在SharedPreference
        Log.d("main", "base64Pic=" + base64Pic);
        byte[] decodedString = Base64.decode(base64Pic, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);
        imageView.setImageBitmap(decodedByte);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}