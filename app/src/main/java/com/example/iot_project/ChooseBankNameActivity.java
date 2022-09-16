package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseBankNameActivity extends AppCompatActivity {

    private ListView BankNameListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_bank_name);
        BankNameListView = (ListView)findViewById(R.id.chooseBankName_listVeiw);
        String [] bankName  = getResources().getStringArray(R.array.bank);
        List<Map<String,String>> bankNamelist = new ArrayList<>();
        for(int i=0;i<bankName.length;i++){
            Map<String,String> item = new HashMap<>();
            item.put("bankName",bankName[i]);
            bankNamelist.add(item);
        }
        SimpleAdapter simAdapter = new SimpleAdapter(ChooseBankNameActivity.this,bankNamelist,R.layout.basic_listview_items,
                new String[]{"bankName"},new int[]{R.id.textView_basiclistView});
        BankNameListView.setAdapter(simAdapter);

        BankNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> data = (Map<String,String>)parent.getItemAtPosition(position);
                String bankName = data.get("bankName");
                Log.d("main",bankName);
                SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
                sp.edit().putString("bankName",bankName).commit();
                Intent intent = new Intent(ChooseBankNameActivity.this,BankAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}