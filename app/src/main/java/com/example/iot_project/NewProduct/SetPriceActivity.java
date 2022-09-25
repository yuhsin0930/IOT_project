package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.DBHelper;
import com.example.iot_project.NewProduct.NewProductActivity;
import com.example.iot_project.NewProduct.SetPriceFragment;
import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetPriceActivity extends AppCompatActivity {

    private FragmentManager FragManager;
    private FragmentTransaction fragTransit;
    private TextView textViewAddNorm;
    private SetPriceFragment newSetPriceFrag;
    private Button buttonFinishedSetNorm;
    private String goodsNorm_id;
    private String productName;
    private String fragNum;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        productName = sp.getString("productName","");
        FragManager = getSupportFragmentManager();

        DBHelper dbHelper = new DBHelper(SetPriceActivity.this);
        SQLiteDatabase setPriceDataBase = dbHelper.getWritableDatabase();
        Cursor setPriceCusor = setPriceDataBase.rawQuery(" SELECT * FROM " + "goodsNorm WHERE good_name='"+productName+"';", null);
        if(setPriceCusor.getCount()!=0){
            setPriceCusor.moveToFirst();
            while(!setPriceCusor.isAfterLast()) {
                count = setPriceCusor.getInt(setPriceCusor.getColumnIndexOrThrow("count"));
                fragNum = setPriceCusor.getString(setPriceCusor.getColumnIndexOrThrow("fragNum"));

                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data",fragNum);
                fragTransit.add(R.id.linearLayout_setPrice, newSetPriceFrag,fragNum);
                fragTransit.commit();
                setPriceCusor.moveToNext();
            }
        }
        setPriceDataBase.close();
        dbHelper.close();

        textViewAddNorm = (TextView)findViewById(R.id.textView_addNorm);
        textViewAddNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data", "setPrice" + count);
                fragTransit.add(R.id.linearLayout_setPrice,newSetPriceFrag,"setPrice"+count);
                fragTransit.commit();

                DBHelper dbHelper = new DBHelper(SetPriceActivity.this);
                SQLiteDatabase setPriceDatabase = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("fragNum","setPrice"+count);
                cv.put("good_name",productName);
                cv.put("count",count);
                long id = setPriceDatabase.insert("goodsNorm",null,cv);
                Log.d("main","Insert norm id = "+id);

                Map<String, Object> normInfoMap = new HashMap<>();
                Cursor normCursor = setPriceDatabase.rawQuery(" SELECT * FROM goodsNorm;", null);
                normCursor.moveToFirst();
                while(!normCursor.isAfterLast()) {
                    int goodsNorm_id = normCursor.getInt(normCursor.getColumnIndexOrThrow("goodsNorm_id"));
                    String fragNum = normCursor.getString(normCursor.getColumnIndexOrThrow("fragNum"));
                    String norm = normCursor.getString(normCursor.getColumnIndexOrThrow("norm"));
                    int price = normCursor.getInt(normCursor.getColumnIndexOrThrow("price"));
                    int normNum = normCursor.getInt(normCursor.getColumnIndexOrThrow("normNum"));
                    normInfoMap.put("goodsNorm_id",goodsNorm_id);
                    normInfoMap.put("fragNum",fragNum);
                    normInfoMap.put("norm",norm);
                    normInfoMap.put("price",price);
                    normInfoMap.put("normNum",normNum);
                    Log.d("main","normInfoMap = "+normInfoMap.toString());
                    normCursor.moveToNext();
                }
                setPriceDatabase.close();
                dbHelper.close();
            }
        });

        buttonFinishedSetNorm = (Button)findViewById(R.id.button_finishedSetNorm);
        buttonFinishedSetNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SetPriceActivity.this, NewProductActivity.class);
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

            DBHelper dbHelper = new DBHelper(SetPriceActivity.this);
            SQLiteDatabase setPriceDatabase = dbHelper.getWritableDatabase();
            setPriceDatabase.delete("goodsNorm","fragNum ='"+tag+"';",null);
            setPriceDatabase.close();
            dbHelper.close();
        }
    }

}