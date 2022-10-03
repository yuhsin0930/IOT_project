package com.example.iot_project.salesRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.iot_project.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setWindow();

    }
    private void setWindow() {
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(0xFFFFFF);
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    //---------------------------------------------------------------------------------------------
//    取出此賣家(seller_id) 訂單狀態(orderStatus)="已完成"的所有訂單編號(orders_id)
//    以及訂單中的買家帳號(member_id)
//    以及訂單中所有商品的
//      商品名稱(goods.goods_name)
//      商品規格(goodsNorm.norm)
//      商品售價(goodsNorm.price)
//      購買數量(sum資料表中的sum欄位)
//    付款方式(payway)
//    付款時間(payTime)
//    出貨時間(shippingTime)
//    訂單成立時間(createTime)


}