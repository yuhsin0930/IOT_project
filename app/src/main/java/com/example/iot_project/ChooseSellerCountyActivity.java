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

public class ChooseSellerCountyActivity extends AppCompatActivity {

    private ListView listViewChooseSellerCounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seller_county);
        listViewChooseSellerCounty = (ListView)findViewById(R.id.sellerChooseCounty_listView);
        String [] county = getResources().getStringArray(R.array.taiwan_city);
        List<Map<String,String>> countylist = new ArrayList<>();
        for(int i=0;i<county.length;i++){
            Map<String,String> item = new HashMap<>();
            item.put("county",county[i]);
            countylist.add(item);
        }
        SimpleAdapter simAdapListViewCounty = new SimpleAdapter(ChooseSellerCountyActivity.this,countylist,
                R.layout.basic_listview_items,new String[] {"county"},new int[] {R.id.textView_basiclistView});
        listViewChooseSellerCounty.setAdapter(simAdapListViewCounty);

        listViewChooseSellerCounty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> data = (Map<String,String>)parent.getItemAtPosition(position);
                String countyName = (String)data.get("county");
                Log.d("main","county = "+countyName);
                SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
                sp.edit().putString("county",countyName).commit();
                Intent intent = new Intent(ChooseSellerCountyActivity.this,SellerDetailActivity.class);
                startActivity(intent);
            }
        });


    }
}