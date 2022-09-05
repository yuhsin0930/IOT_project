package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



public class BecomeSellerActivity extends AppCompatActivity {
    private TextView textViewSellerCheckAccount_citizenShip;
    private EditText editTextSellerCheckAccount_name;
    private TextView textViewSellerCheckAccount_birthday;
    private ImageButton imageButtonSellerCheckAccount_birthday;
    private EditText editTextSellerCheckAccount_nationalID;
    private CheckBox checkBoxSellerCheckAccount_agree;
    private Button buttonSellerCheckAccount_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_seller);
        Dialog becomeSellerNowDlg = new Dialog(BecomeSellerActivity.this);
        becomeSellerNowDlg.setContentView(R.layout.dialog_become_seller_now);

        Button buttonRegisterSellerNow = (Button) becomeSellerNowDlg.findViewById(R.id.button_registerSellerNow);
        buttonRegisterSellerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                becomeSellerNowDlg.dismiss();
            }
        });
        becomeSellerNowDlg.show();
        becomeSellerNowDlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        textViewSellerCheckAccount_citizenShip = (TextView) findViewById(R.id.textView_sellerCheckAccount_citizenShip);
        textViewSellerCheckAccount_birthday = (TextView) findViewById(R.id.textView_sellerCheckAccount_birthday);
        textViewSellerCheckAccount_birthday.setText("");

        editTextSellerCheckAccount_name = (EditText) findViewById(R.id.editText_sellerCheckAccount_name);
        editTextSellerCheckAccount_nationalID = (EditText) findViewById(R.id.editText_sellerCheckAccount_nationalID);

        imageButtonSellerCheckAccount_birthday = (ImageButton) findViewById(R.id.imageButton_sellerCheckAccount_birthday);

        checkBoxSellerCheckAccount_agree = (CheckBox) findViewById(R.id.checkBox_sellerCheckAccount_agree);

        buttonSellerCheckAccount_next = (Button) findViewById(R.id.button_sellerCheckAccount_next);
        buttonSellerCheckAccount_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BecomeSellerActivity.this,SellerDetailActivity.class);
                startActivity(intent);
            }
        });
    }


}







