package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iot_project.DBHelper;
import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class NewProductClassificationActivity extends AppCompatActivity {

    private FragmentTransaction fragTransit;
    private TextView textViewAddClass;
    private int count;
    private ProductClassificationFragment newProductClass;
    private Button buttonNewClassFinished;
    private FragmentManager FragManager;
    private String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product_classification);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        productName = sp.getString("productName","");
        DBHelper dbHelper = new DBHelper(NewProductClassificationActivity.this);
        SQLiteDatabase productClassDatabase = dbHelper.getWritableDatabase();
        FragManager = getSupportFragmentManager();
        Cursor classCursor = productClassDatabase.rawQuery( "SELECT * FROM goodsType;", null);
        count = classCursor.getCount();
        if(count!=0){
            for(int i=1; i<count+1 ;i++){
                fragTransit = FragManager.beginTransaction();
                newProductClass = ProductClassificationFragment.newInstance("Add data","productClass" +i);
                fragTransit.add(R.id.linear_layout_productClass_fragment, newProductClass,"productClass" +i);
                fragTransit.commit();
            }

        }
        productClassDatabase.close();
        dbHelper.close();
        textViewAddClass = (TextView) findViewById(R.id.textView_newProductClass);
        textViewAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                fragTransit = FragManager.beginTransaction();
                newProductClass = ProductClassificationFragment.newInstance("Add data", "productClass" + count);
                fragTransit.add(R.id.linear_layout_productClass_fragment, newProductClass, "productClass" + count);
                fragTransit.commit();
                DBHelper dbHelper = new DBHelper(NewProductClassificationActivity.this);
                SQLiteDatabase productClassDatabase = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("good_name",productName);
                cv.put("fragType","productClass" + count);
                long id = productClassDatabase.insert("goodsType",null,cv);
                Log.d("main","productClass id = "+id);
                productClassDatabase.close();
                dbHelper.close();
            }
        });
        //------------------------------------------------------------------------------------------
        buttonNewClassFinished = (Button)findViewById(R.id.button_newClass_finished);
        buttonNewClassFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductClassificationActivity.this,NewProductActivity.class);
                startActivity(intent);
            }
        });

    }

    public void deleteFragment(String tag) {
        Fragment f = FragManager.findFragmentByTag(tag);
        if (f != null) {
            fragTransit = FragManager.beginTransaction();
            fragTransit.remove(f);
            fragTransit.commit();

            DBHelper dbHelper = new DBHelper(NewProductClassificationActivity.this);
            SQLiteDatabase productClassDatabase = dbHelper.getWritableDatabase();
            Cursor classCursor = productClassDatabase.rawQuery( "SELECT * FROM goodsType WHERE fragType='"+tag+"';", null);
            productClassDatabase.delete("goodsType","fragType ='"+tag+"';",null);
            productClassDatabase.close();
            dbHelper.close();
        }
    }
}

