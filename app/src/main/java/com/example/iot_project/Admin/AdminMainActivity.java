package com.example.iot_project.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.iot_project.Main.LogoutActivity;
import com.example.iot_project.Main.MainActivity;
import com.example.iot_project.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private TextView textView;
    private TextView textViewAccount;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private AdminMemberFragment AdminMemberFrag;
    private AdminSellerFragment AdminSellerFrag;
    private AdminProductFragment AdminProductFrag;
    private List<Map<String, Object>> ListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

//      建立側開表單 NavigationView，以Imagebutton為觸發元件 -----------------------------------------
////    reference : https://material.io/components/navigation-drawer/android#using-navigation-drawers
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_admin_id);
        drawerLayout.openDrawer(GravityCompat.START,true);
        navigationView = (NavigationView) findViewById(R.id.navigationView_admin_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_admin_id);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START,true);
            }
        });
//      預設headerLayout :
//      方法1 : 就是預設顯示頂端的貓貓圖片 + 下面兩行字 ，但是貓貓圖片就不能改
//      在 activity_main.xml 的 com.google.android.material.navigation.NavigationView
//      加上 這列 : app:headerLayout="@layout/header_navigation_drawer"
//      方法2 : 可以用 code 修改預設貓貓圖片，改成其他圖片，方法1加入的那列 app:headerLayout 要刪掉
        View headerView = navigationView.inflateHeaderView(R.layout.header_navigation_drawer);
        imageView = (ImageView) headerView.findViewById(R.id.imageView_id);
        imageView.setImageResource(R.drawable.photo);
        textView = (TextView) headerView.findViewById(R.id.textView_nav_identity);
        textView.setText("管理者");
        textViewAccount = (TextView) headerView.findViewById(R.id.textView_main_nav_account);
        Intent intent = getIntent();
        String adminAccount = intent.getStringExtra("adminAccount");
        textViewAccount.setText(adminAccount);

//      fragment : 顯示三個側拉選單的管理頁面
        fragmentManager = getSupportFragmentManager() ;
        AdminMemberFrag = AdminMemberFragment.newInstance("",""); //會員管理
        AdminSellerFrag = AdminSellerFragment.newInstance("",""); //賣家管理
        AdminProductFrag = AdminProductFragment.newInstance("",""); //商品管理

        navigationView.setNavigationItemSelectedListener(new MyNaviagtionListener());

    }

//  監聽側拉選單的menu
    private class MyNaviagtionListener implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment is_exist;

    @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.member_management:
                    //開始Transaction
                    FragmentTransaction fragTransit = fragmentManager.beginTransaction();
                    //      fragment要顯示的元件id , 物件 , fragment對應的tag
                    is_exist = fragmentManager.findFragmentByTag("adminFrag-M");
                    Log.d("main","is_exist="+is_exist);
                    if(is_exist==null){
                        fragTransit.add(R.id.frameLayout_admin_main,AdminMemberFrag,"adminFrag-M");
                    }else{
                        fragTransit.replace(R.id.frameLayout_admin_main,AdminMemberFrag,"adminFrag-M");
                    }

                    fragTransit.commit();

                    break;
                case R.id.seller_management:
                    fragTransit = fragmentManager.beginTransaction();
                    //      fragment要顯示的元件id , 物件 , fragment對應的tag
                    is_exist = fragmentManager.findFragmentByTag("adminFrag-S");
                    Log.d("main","is_exist="+is_exist);
                    if(is_exist==null){
                        fragTransit.add(R.id.frameLayout_admin_main, AdminSellerFrag, "adminFrag-S");
                    }else {
                        fragTransit.replace(R.id.frameLayout_admin_main, AdminSellerFrag, "adminFrag-S");
                    }

                    fragTransit.commit();
                    break;
                case R.id.item_management:
                    fragTransit = fragmentManager.beginTransaction();
                    //      fragment要顯示的元件id , 物件 , fragment對應的tag
                    is_exist = fragmentManager.findFragmentByTag("adminFrag-P");
                    Log.d("main","is_exist="+is_exist);
                    if(is_exist==null){
                        fragTransit.add(R.id.frameLayout_admin_main, AdminProductFrag, "adminFrag-P");
                    }else{
                        fragTransit.replace(R.id.frameLayout_admin_main, AdminProductFrag, "adminFrag-P");
                    }

                    fragTransit.commit();
                    break;
                case R.id.admin_logout:
                    Intent intentlogout = new Intent(AdminMainActivity.this, LogoutActivity.class);
                    startActivity(intentlogout);
                    break;
            }
            return false;
        }
    }

//  取得FireBase資料並轉成 List<Map<String,Object>> 方便放到 RecyclerView
    public List<Map<String,Object>> DownloadDataFromFireBase(String reference){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataref = database.getReference(reference);
        ListData = new ArrayList<Map<String,Object>>();
        dataref.addValueEventListener(new ValueEventListener() {
            private String dataKey;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("main","snapshot.getValue()="+snapshot.getValue());
                for(DataSnapshot data : snapshot.getChildren()){
                    dataKey = data.getKey();
                    dataref.child(dataKey).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Member memberdata= snapshot.getValue(Member.class);
//                                Log.d("main","Member.ToMap()="+memberdata.ToMap());
                            Map<String, Object> map = memberdata.ToMap();
                            map.put("member_Id",dataKey);
                            ListData.add(map);
//                                Log.d("main","ListData="+ListData);
//                                Log.d("main","ListData.size()="+ListData.size());

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
        return ListData;
    }

}