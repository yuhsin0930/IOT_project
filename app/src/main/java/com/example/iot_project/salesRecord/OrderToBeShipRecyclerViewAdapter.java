package com.example.iot_project.salesRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_project.R;
import com.example.iot_project.member.MemberActivity;
import com.example.iot_project.member.MemberOrdersRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderToBeShipRecyclerViewAdapter extends RecyclerView.Adapter<OrderToBeShipRecyclerViewAdapter.ViewHolder>{

    private final Context mainContext;
    private final List<Map<String, Object>> myOrderList;
    private final LayoutInflater myLayoutInflater;

    //  1. 資料送進來
    public OrderToBeShipRecyclerViewAdapter(Context context, List<Map<String, Object>> orderList){
        mainContext = context;
        myOrderList = orderList;
        myLayoutInflater = LayoutInflater.from(context);
    }

    //    2. 取得RecyclerView上的View (R.layout.item_layout上面設定的View)
    @NonNull
    @Override
    public OrderToBeShipRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = myLayoutInflater.inflate(R.layout.order_to_be_ship_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    //  3. 設定 RecyclerView 的 item數量
    @Override
    public int getItemCount() {
        return myOrderList.size();
    }

    //  4. 取得 RecyclerView 上 Item 的 View
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTobeship_orderNum;
        private final TextView textViewTobeship_productName;
        private final TextView textViewTobeship_productNum;
        private final TextView textViewTobeship_productPrice;
        private final TextView textViewTobeship_totalProductNum;
        private final TextView textViewTobeship_totalPrice;
        private final Button buttonTobeship_shipped;
        private final Button buttonTobeship_cancelOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //    6. 監聽哪個item被按
            textViewTobeship_orderNum = (TextView) itemView.findViewById(R.id.textView_tobeship_orderNum);
            textViewTobeship_productName = (TextView)itemView.findViewById(R.id.textView_tobeship_productName);
            textViewTobeship_productNum = (TextView)itemView.findViewById(R.id.textView_tobeship_productNum);
            textViewTobeship_productPrice = (TextView)itemView.findViewById(R.id.textView_tobeship_productPrice);
            textViewTobeship_totalProductNum = (TextView)itemView.findViewById(R.id.textView_tobeship_totalProductNum);
            textViewTobeship_totalPrice = (TextView)itemView.findViewById(R.id.textView_tobeship_totalPrice);

            buttonTobeship_shipped = (Button)itemView.findViewById(R.id.button_tobeship_shipped);
            buttonTobeship_cancelOrder  = (Button)itemView.findViewById(R.id.button_tobeship_cancelOrder);


            buttonTobeship_shipped.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            buttonTobeship_cancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ((SalesRecordActivity)itemView.getContext()).orderToBeShipDetail(myOrderList.get(position));
                }
            });
            
        }
    }

    //  5.取出對應position應該要顯示的資料
    @Override
    public void onBindViewHolder(@NonNull OrderToBeShipRecyclerViewAdapter.ViewHolder holder, int position) {
        Map<String, Object> data = myOrderList.get(position);
        String orderNum = data.get("orderNum").toString();
        String productName = data.get("productName").toString();
        int productNum = (int)data.get("productNum");
        int productPrice = (int)data.get("productPrice");
        int allProductNum = (int)data.get("allProductNum");
        int totalPrice = (int)data.get("totalPrice");

        holder.textViewTobeship_orderNum.setText(orderNum);
        holder.textViewTobeship_productName.setText(productName);
        holder.textViewTobeship_productNum.setText(String.valueOf(productNum));
        holder.textViewTobeship_productPrice.setText(String.valueOf(productPrice));
        holder.textViewTobeship_totalProductNum.setText(String.valueOf(allProductNum));
        holder.textViewTobeship_totalPrice.setText(String.valueOf(totalPrice));

    }

    //    7. return 最新的FoodList，因為會隨時更新數量所以每次的FoodList都會不一樣
    public List<Map<String, Object>> getData(){
        return myOrderList;
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


}

