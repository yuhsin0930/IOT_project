package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewProductActivity extends AppCompatActivity {

    private EditText editTextNewProduct_Name;
    private TextView textViewNewProduct_describe;
    private TextView textViewNewProduct_classification;
    private TextView textViewNewProduct_setPrice;
    private EditText editTextNumberNewProduct_Quatity;
    private TextView textViewNewProduct_shippiingFee;
    private Button button_newProduct_launch;
    private Button buttonNewProduct_save;
    private TextView textViewNewProduct_NameLength;
    private TextView textViewNewProduct_describeLength;
    private String NewProductNameLength;
    private String NewProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        textViewNewProduct_NameLength = (TextView)findViewById(R.id.textView_newProduct_NameLength);
        textViewNewProduct_describeLength = (TextView)findViewById(R.id.textView_newProduct_describeLength);
        textViewNewProduct_describe = (TextView)findViewById(R.id.textView_newProduct_Describe);
        textViewNewProduct_classification = (TextView)findViewById(R.id.textView_newProduct_Classification);
        textViewNewProduct_setPrice = (TextView)findViewById(R.id.textView_newProduct_SetPrice);
        textViewNewProduct_shippiingFee = (TextView)findViewById(R.id.textView_newProduct_shippiingFee);

        editTextNewProduct_Name = (EditText)findViewById(R.id.editTextText_newProduct_Name);
        editTextNumberNewProduct_Quatity = (EditText)findViewById(R.id.editTextNumber_newProduct_Quatity);

        //------------------------------------------------------------------------------------------
        String NewProductQuatity = editTextNumberNewProduct_Quatity.getText().toString();
        //------------------------------------------------------------------------------------------
        NewProductNameLength="0";
        editTextNewProduct_Name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        editTextNewProduct_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NewProductNameLength = String.valueOf(s.length());
                textViewNewProduct_NameLength.setText(NewProductNameLength);
            }

            @Override
            public void afterTextChanged(Editable s) {
                NewProductName =  s.toString();
            }
        });
        textViewNewProduct_NameLength.setText(NewProductNameLength);
        //------------------------------------------------------------------------------------------
        String  describeLength;
        textViewNewProduct_describe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(NewProductActivity.this, ProductDescribeActivity.class);
               startActivity(intent);
            }
        });
        //------------------------------------------------------------------------------------------
        Intent it = getIntent();
        if(it.getStringExtra("describeLength")==null){
            describeLength="0";
        }
        else{
            describeLength = it.getStringExtra("describeLength");
        }
        textViewNewProduct_describeLength.setText(describeLength);
        //------------------------------------------------------------------------------------------
        buttonNewProduct_save = (Button)findViewById(R.id.button_newProduct_save);
        button_newProduct_launch = (Button) findViewById(R.id.button_newProduct_launch);
    }
}