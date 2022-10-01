package com.example.iot_project.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.iot_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminBecomeSellerActivity extends AppCompatActivity {

    private DatabaseReference dataref;
    private ValueEventListener sellerListener;
    private ListView ListViewBecomeSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_become_seller);
//       顯示返回鍵
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("成為賣家申請審核");

//        seller/key/sState : 通過
//        seller/key/seller_id
//        member/seller_id/is_seller/ : true
//
//        seller/key/sState : 不通過

        ListViewBecomeSeller = (ListView) findViewById(R.id.listView_admin_become_seller_id);
        //      製作表格 id 與 對應表格的帳戶名稱
        String reference = "seller";
        String ID = reference.concat("_Id");
        String account = "sName";

//      使用 FireBase 服務
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d("main", "database=" + database);
//      使用  reference 節點進入FireBase
        dataref = database.getReference(reference);
        Log.d("main", "dataref=" + dataref);

        List<Map<String, Object>> ListData = new ArrayList<Map<String, Object>>();

//         從reference為起點下去撈會員資料
        sellerListener = dataref.orderByKey().equalTo("審核中").addValueEventListener(new ValueEventListener() { //每次資料更新都會監聽

            private String dataKey;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //怕更新List會有重複資料，所以如果ListData已經有資料要清掉，不然List會一直add上去
                if (ListData.isEmpty() == false) ListData.clear();
                //   取得申請成為賣家人數 = 幾個 Children
//                memberNumber = snapshot.getChildrenCount();
//                textViewMemberNum.setText(String.valueOf(memberNumber));
//                if (editTextSearch.length() == 0) { //如果沒有執行搜尋，搜尋結果等於會員數量
//                    textViewMemberDataCount.setText(String.valueOf(memberNumber));
//                }
                Log.d("main","snapshot.getValue()="+snapshot.getValue());
                for (DataSnapshot data : snapshot.getChildren()) {
                    dataKey = data.getKey();
                    Log.d("main","dataKey="+dataKey);
                    dataref.child(dataKey).addListenerForSingleValueEvent(new ValueEventListener() { //只監聽一次
                        private Map<String, Object> map;

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String memberKey = snapshot.getKey();
                            Log.d("main","memberKey="+memberKey);
                            if (reference.equals("seller")) {
                                Seller sellerdata= snapshot.getValue(Seller.class);
//                                Log.d("main","Member.ToMap()="+memberdata.ToMap());
                                map = sellerdata.ToMap();
//                                Log.d("main","ListData="+ListData);
//                                Log.d("main","ListData.size()="+ListData.size());
                            }

                            map.put(ID, memberKey);
                            Log.d("main", "map=" + map);
                            ListData.add(map);
//                          Log.d("main","ListData()="+ListData);

                            SimpleAdapter adpter = new SimpleAdapter(AdminBecomeSellerActivity.this, ListData, R.layout.listview_admin_member
                                    , new String[]{"account_name", ID,"createTime"}
                                    , new int[]{R.id.textView_admin_member_account, R.id.textView_admin_member_id
                                    ,R.id.textView_admin_member_createTime});
                            adpter.notifyDataSetChanged();
                            ListViewBecomeSeller.setAdapter(adpter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        ListViewMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
//                Log.d("main","item="+item);
//                Intent intent = new Intent(activity, AdminMemberActivity.class);
//                for(Object key: item.keySet().toArray()){
//                    String mapKey = key.toString();
//                    Object mapValue = item.get(mapKey);
//                    Log.d("main","mapKey[mapValue]="+mapKey+" "+mapValue);
//                    if(mapKey.equals("is_seller")){
//                        Boolean is_seller = (Boolean) mapValue;
//                        intent.putExtra(mapKey,is_seller);
//                    }else if(!mapKey.equals("picture")){
//                        String StringData = mapValue.toString();
//                        intent.putExtra(mapKey,StringData);
//                    }else{
//                        String PicData = mapValue.toString();
//                        sp.edit().putString(mapKey,PicData).commit();
//                    }
//                }
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){ //開啟返回鍵
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}