package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
        String bankName = sp.getString("bankName","臺灣銀行  > ");
        String bankArea = sp.getString("bankArea","臺北");
        String bankBranch = sp.getString("bankBranch","選擇");

        textViewBankAccount_bankName = (TextView)findViewById(R.id.textView_bankAccount_bankName);
        textViewBankAccount_bankName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankAccountActivity.this,ChooseBankNameActivity.class);
                startActivity(intent);
            }
        });
        textViewBankAccount_bankName.setText(bankName+"  > ");
        //------------------------------------------------------------------------------------------
        textViewBankAccount_bankArea = (TextView)findViewById(R.id.textView_bankAccount_bankArea);
        textViewBankAccount_bankArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankAccountActivity.this,ChooseBankAreaActivity.class);
                startActivity(intent);
            }
        });
        textViewBankAccount_bankArea.setText(bankArea+"  > ");
        //------------------------------------------------------------------------------------------
        textViewBankAccount_branch = (TextView)findViewById(R.id.textView_bankAccount_branch);
        textViewBankAccount_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankAccountActivity.this,ChooseBankBranchActivity.class);
                startActivity(intent);
            }
        });
        textViewBankAccount_branch.setText(bankBranch+"  > ");
        //------------------------------------------------------------------------------------------
        editTextBankAccount_bankAccountNumber = (EditText)findViewById(R.id.editText_bankAccount_bankAccountNumber);
        editTextBankAccount_bankAccountName = (EditText)findViewById(R.id.editText_bankAccount_bankAccountName);
        //------------------------------------------------------------------------------------------
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
                        String bankAccountName = editTextBankAccount_bankAccountName.getText().toString();
                        String bankAccountNumber = editTextBankAccount_bankAccountNumber.getText().toString();
                        SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
                        sp.edit().clear().commit();
                        Intent intent = new Intent(BankAccountActivity.this,MyStoreActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });


    }
}