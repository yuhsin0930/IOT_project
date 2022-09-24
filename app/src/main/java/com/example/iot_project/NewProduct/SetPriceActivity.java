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
    private int productNormNum;
    private String goodsNorm_id;
    private int countNorm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        String productName = sp.getString("productName","");
        FragManager = getSupportFragmentManager();
        DBHelper dbHelper = new DBHelper(SetPriceActivity.this);
        SQLiteDatabase setPriceDataBase = dbHelper.getWritableDatabase();
        Cursor c = setPriceDataBase.rawQuery(" SELECT * FROM " + "goodsNorm;", null);
        countNorm = c.getCount();
        if(countNorm!=0){
           for(int i=1; i< countNorm+1;i++){
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data","setPrice" +i);
                fragTransit.add(R.id.linearLayout_setPrice, newSetPriceFrag,"setPrice" +i);
                fragTransit.commit();
                Log.d("main", "normFragment = " +"setPrice" +i);
           }
        }
        setPriceDataBase.close();
        dbHelper.close();

        textViewAddNorm = (TextView)findViewById(R.id.textView_addNorm);
        textViewAddNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countNorm++;
                fragTransit = FragManager.beginTransaction();
                newSetPriceFrag = SetPriceFragment.newInstance("Add data", "setPrice" + countNorm);
                fragTransit.add(R.id.linearLayout_setPrice,newSetPriceFrag,"setPrice"+countNorm);
                fragTransit.commit();
                sp.edit().putInt("NormFragmentCount",countNorm).commit();

                DBHelper dbHelper = new DBHelper(SetPriceActivity.this);
                SQLiteDatabase setPriceDatabase = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("fragNum","setPrice"+countNorm);
                cv.put("good_name",productName);
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

            Cursor c = setPriceDatabase.rawQuery(" SELECT * FROM " + "goodsNorm"
                    + " WHERE fragNum =" + "'" + tag + "';", null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                goodsNorm_id = c.getString(0);
                c.moveToNext();
            }

            Map<String, Object> normInfoMap = new HashMap<>();
            Cursor normCursor = setPriceDatabase.rawQuery(" SELECT * FROM goodsNorm WHERE fragNum ='"+tag+"';", null);
            normCursor.moveToFirst();
            while(!normCursor.isAfterLast()) {
                int goodsNorm_id = normCursor.getInt(normCursor.getColumnIndexOrThrow("goodsNorm_id"));
                String norm = normCursor.getString(normCursor.getColumnIndexOrThrow("norm"));
                int price = normCursor.getInt(normCursor.getColumnIndexOrThrow("price"));
                int normNum = normCursor.getInt(normCursor.getColumnIndexOrThrow("normNum"));
                normInfoMap.put("goodsNorm_id",goodsNorm_id);
                normInfoMap.put("norm",norm);
                normInfoMap.put("price",price);
                normInfoMap.put("normNum",normNum);
                normCursor.moveToNext();
            }
            setPriceDatabase.delete("goodsNorm","goodsNorm_id ="+goodsNorm_id,null);
            setPriceDatabase.close();
            dbHelper.close();
        }
    }

}