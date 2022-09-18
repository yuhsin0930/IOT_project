package com.example.iot_project.SellerRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class BecomeSellerActivity extends AppCompatActivity {
    private TextView textViewSellerCheckAccount_citizenShip;
    private EditText editTextSellerCheckAccount_name;
    private TextView textViewSellerCheckAccount_birthday;
    private ImageButton imageButtonSellerCheckAccount_birthday;
    private EditText editTextSellerCheckAccount_nationalID;
    private CheckBox checkBoxSellerCheckAccount_agree;
    private Button buttonSellerCheckAccount_next;
    private FragmentManager fragManager;
    private FragmentTransaction fragTransit;
    private String sellername_checkAccount;
    private String sellerId_checkAccount;

    DatePickerDialog.OnDateSetListener datePicker; //日歷的監聽，獲得選擇的日期
    Calendar calendar = Calendar.getInstance(); //日期的格式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_seller);

        //----------------------------------------------------------------------------------------------------------
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
        //-----------------------------------------------------------------------------------------------------------

        //-----------------------------------------------------------------------------------------------------------

        editTextSellerCheckAccount_name = (EditText) findViewById(R.id.editText_sellerCheckAccount_name);
        editTextSellerCheckAccount_nationalID = (EditText) findViewById(R.id.editText_sellerCheckAccount_nationalID);
        if(editTextSellerCheckAccount_name.length()>0){
            sellername_checkAccount = editTextSellerCheckAccount_name.getText().toString();
        }
        if(editTextSellerCheckAccount_nationalID.length()>0){
            sellerId_checkAccount = editTextSellerCheckAccount_nationalID.getText().toString();
        }


        //------------------------------------------------------------------------------------------------------------
        imageButtonSellerCheckAccount_birthday = (ImageButton) findViewById(R.id.imageButton_sellerCheckAccount_birthday);
        textViewSellerCheckAccount_birthday = (TextView) findViewById(R.id.textView_sellerCheckAccount_birthday);
        textViewSellerCheckAccount_birthday.setText("");
        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myformat = "yyyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.TAIWAN);
                textViewSellerCheckAccount_birthday.setText(sdf.format(calendar.getTime()));

            }
        };

        imageButtonSellerCheckAccount_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(BecomeSellerActivity.this,
                        datePicker,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                dialog.show();
            }
        });
        //-----------------------------------------------------------------------------------------------------------


        textViewSellerCheckAccount_citizenShip = (TextView) findViewById(R.id.textView_sellerCheckAccount_citizenShip);
        textViewSellerCheckAccount_citizenShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BecomeSellerActivity.this,ChooseCitizenActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sp  = getSharedPreferences("sellerDetail",MODE_PRIVATE);
        String citizen = sp.getString("citizenship","臺灣");
        textViewSellerCheckAccount_citizenShip.setText(citizen+"  > ");
        //---------------------------------------------------------------------------------------------------------------

        buttonSellerCheckAccount_next = (Button) findViewById(R.id.button_sellerCheckAccount_next);
        buttonSellerCheckAccount_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSellerCheckAccount_agree = (CheckBox) findViewById(R.id.checkBox_sellerCheckAccount_agree);
                if(textViewSellerCheckAccount_birthday.getText().toString()=="" || editTextSellerCheckAccount_name.length()==0 || editTextSellerCheckAccount_nationalID.length()==0){
                    Toast.makeText(BecomeSellerActivity.this, "請完整輸入資料", Toast.LENGTH_SHORT).show();
                }
                else if(checkBoxSellerCheckAccount_agree.isChecked()==false){
                    Toast.makeText(BecomeSellerActivity.this, "請勾選同意條款", Toast.LENGTH_SHORT).show();
                }
                else {
                    String sellerBirthday = textViewSellerCheckAccount_birthday.getText().toString();
                    String sellerName = editTextSellerCheckAccount_name.getText().toString();
                    String sellerId = editTextSellerCheckAccount_nationalID.getText().toString();

                    SharedPreferences sp = getSharedPreferences("sellerDetail",MODE_PRIVATE);
                    sp.edit().putString("sellerName",sellerName)
                            .putString("sellerBirthday",sellerBirthday)
                            .putString("sellerId",sellerId)
                            .commit(); //呼叫commit()方法寫入
                    Intent intent = new Intent(BecomeSellerActivity.this, SellerDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}






