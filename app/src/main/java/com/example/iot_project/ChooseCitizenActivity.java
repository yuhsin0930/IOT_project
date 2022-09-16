package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCitizenActivity extends AppCompatActivity {

    private ListView listView_chooseCitizen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_citizen);
        listView_chooseCitizen = (ListView)findViewById(R.id.choose_citizenship_listview);
        String[] citylist = getResources().getStringArray(R.array.citizenship);
        List<Map<String,String>> citizenlist = new ArrayList<Map<String,String>>();
        for(int i=0;i<citylist.length;i++){
            Map<String,String> item = new HashMap<String,String>();
            item.put("citizenship",citylist[i]);
            citizenlist.add(item);
        }
        SimpleAdapter simAdapListViewCitizen = new SimpleAdapter(
                ChooseCitizenActivity.this,citizenlist,
                R.layout.becomeseller_citizenship_listview_items,
                new String[] {"citizenship"},
                new int[] {R.id.textView_becomeseller_citizenship});
        listView_chooseCitizen.setAdapter(simAdapListViewCitizen);

        listView_chooseCitizen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> citizenship = (Map<String, String>) parent.getItemAtPosition(position);
                String citizen =(String) citizenship.get("citizenship");
                Log.d("main",citizen);
                SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
                sp.edit().putString("citizenship",citizen).commit();
                Intent intent = new Intent(ChooseCitizenActivity.this,BecomeSellerActivity.class);
                startActivity(intent);

            }
        });

    }
}