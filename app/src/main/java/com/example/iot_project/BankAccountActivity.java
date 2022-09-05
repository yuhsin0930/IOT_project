package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BankAccountActivity extends AppCompatActivity {

    private View butttonSuccessBecomeSeller_checkMyStore;
    private TextView textViewBankAccount_bankName,textViewBankAccount_bankArea,textViewBankAccount_branch;
    private EditText editTextBankAccount_bankAccountNumber,editTextBankAccount_bankAccountName;
    private Button buttonBankAccount_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);

        textViewBankAccount_bankName = (TextView)findViewById(R.id.textView_bankAccount_bankName);
        textViewBankAccount_bankArea = (TextView)findViewById(R.id.textView_bankAccount_bankArea);
        textViewBankAccount_branch = (TextView)findViewById(R.id.textView_bankAccount_branch);

        editTextBankAccount_bankAccountNumber = (EditText)findViewById(R.id.editText_bankAccount_bankAccountNumber);
        editTextBankAccount_bankAccountName = (EditText)findViewById(R.id.editText_bankAccount_bankAccountName);



        buttonBankAccount_finish = (Button)findViewById(R.id.button_bankAccount_finish);

        buttonBankAccount_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog successbecomeSellerNowDlg = new Dialog(BankAccountActivity.this);
                successbecomeSellerNowDlg.setContentView(R.layout.dialog_success_become_seller);
                successbecomeSellerNowDlg.show();
                successbecomeSellerNowDlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                butttonSuccessBecomeSeller_checkMyStore = (Button)successbecomeSellerNowDlg.findViewById(R.id.button_successBecomeSellerDialog_checkMyStore);
                butttonSuccessBecomeSeller_checkMyStore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BankAccountActivity.this,MyStoreActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });


    }
}