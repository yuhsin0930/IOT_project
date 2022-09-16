package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SellerDetailActivity extends AppCompatActivity {


    private Button buttonSellerDetail_next;
    private TextView textViewSellerName_sellerDetail;
    private TextView textViewSellerId_sellerDetail;
    private TextView textViewSellerBirthday_sellerDetial;
    private TextView textViewSellerCounty_sellerDetail;
    private TextView textViewSellerArea_sellerDetail;
    private EditText editTextSellerAddressNumber,editTextSellerAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_detail);
        SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
        String sellerName = sp.getString("sellerName",null);
        String sellerId = sp.getString("sellerId",null);
        String sellerBirthday = sp.getString("sellerBirthday",null);
        String county = sp.getString("county","選擇");
        String area = sp.getString("area","選擇");
        //------------------------------------------------------------------------------------------
        textViewSellerName_sellerDetail = (TextView)findViewById(R.id.textView_sellerDetail_name);
        textViewSellerId_sellerDetail = (TextView)findViewById(R.id.textView_sellerDetail_nationalID);
        textViewSellerBirthday_sellerDetial = (TextView)findViewById(R.id.textView_sellerDetail_birthday);

        textViewSellerId_sellerDetail.setText(sellerId);
        textViewSellerName_sellerDetail.setText(sellerName);
        textViewSellerBirthday_sellerDetial.setText(sellerBirthday);
        //------------------------------------------------------------------------------------------
        textViewSellerCounty_sellerDetail = (TextView)findViewById(R.id.textView_sellerDetail_county);
        textViewSellerCounty_sellerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SellerDetailActivity.this,ChooseSellerCountyActivity.class);
                startActivity(intent);
            }
        });
        textViewSellerCounty_sellerDetail.setText(county+"  > ");
       //-------------------------------------------------------------------------------------------
        textViewSellerArea_sellerDetail = (TextView)findViewById(R.id.textView_sellerDetail_area);
        textViewSellerArea_sellerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(county=="選擇"){
                    Toast.makeText(SellerDetailActivity.this, "請選擇城市", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(SellerDetailActivity.this,ChooseSellerAreaActivity.class);
                    startActivity(intent);
                }

            }
        });
        textViewSellerArea_sellerDetail.setText(area+"  > ");
        //------------------------------------------------------------------------------------------

        editTextSellerAddressNumber = (EditText)findViewById(R.id.editText_sellerDetail_areaNumber);
        editTextSellerAddress = (EditText)findViewById(R.id.editText_sellerDetail_address);

        //------------------------------------------------------------------------------------------
        buttonSellerDetail_next = (Button)findViewById(R.id.button_sellerDetail_next);
        buttonSellerDetail_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSellerAddress.length()==0||editTextSellerAddressNumber.length()==0||textViewSellerArea_sellerDetail.getText()=="選擇  > "||textViewSellerCounty_sellerDetail.getText()=="選擇  > "){
                    Toast.makeText(SellerDetailActivity.this, "請輸入完整資料", Toast.LENGTH_SHORT).show();
                }
                else{
                    String SellerAddressNumber = editTextSellerAddressNumber.getText().toString();
                    String SellerAddress = editTextSellerAddress.getText().toString();
                    SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
                    sp.edit().putString("sellerAddressNumber",SellerAddressNumber)
                            .putString("sellerAddress",SellerAddress)
                            .commit();
                    Intent intent = new Intent(SellerDetailActivity.this,BankAccountActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}