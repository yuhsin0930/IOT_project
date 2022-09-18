package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProductDescribeActivity extends AppCompatActivity {


    private Editable ProductDescribe;
    private String DescribeWordNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_describe);
        TextView textViewDescribeLength = (TextView) findViewById(R.id.textView_productDescribe_describeLength);
        EditText editTextProductDescirbe = (EditText) findViewById(R.id.editTextTextMultiLine_ProductDescribe);

        editTextProductDescirbe.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3000)});
        editTextProductDescirbe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //改變前
                //這個方法被調用，說明在s字符串中，從start位置開始的count個字符即將被長度為after的新文本所取代。在這個方法里面改變s，會報錯。
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //改變中
                //這個方法被調用，說明在s字符串中，從start位置開始的count個字符刚刚取代了長度為before的舊文本。在這個方法里面改變s，會報錯。

                DescribeWordNum = String.valueOf(s.length());
                textViewDescribeLength.setText(DescribeWordNum);


            }

            @Override
            public void afterTextChanged(Editable s) {
                //改變後
                //這個方法被使用，那麼說明s字符串的某個地方已經被改變。
                ProductDescribe = s;
                Log.d("main","describe = "+ProductDescribe);
            }
        });
        //------------------------------------------------------------------------------------------
        Button buttonFinish = (Button) findViewById(R.id.button_productDescribe_finish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ProductDescribeActivity.this, NewProductActivity.class);
                intent.putExtra("describeLength",DescribeWordNum);
                intent.putExtra("describe",ProductDescribe);
                startActivity(intent);
            }
        });

    }
}