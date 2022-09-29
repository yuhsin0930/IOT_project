package com.example.iot_project.salesRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_project.R;

import java.util.List;
import java.util.Map;

public class OrderInvalidRecyclerAdapter extends RecyclerView.Adapter<OrderInvalidRecyclerAdapter.ViewHolder> {
    private final Context mainContext;
    private final List<Map<String, Object>> myOrderList;
    private final LayoutInflater myLayoutInflater;

    //  1. 資料送進來
    public OrderInvalidRecyclerAdapter(Context context, List<Map<String, Object>> orderList){
        mainContext = context;
        myOrderList = orderList;
        myLayoutInflater = LayoutInflater.from(context);
    }

    //    2. 取得RecyclerView上的View (R.layout.item_layout上面設定的View)
    @NonNull
    @Override
    public OrderInvalidRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = myLayoutInflater.inflate(R.layout.order_invalid_items, parent, false);
        OrderInvalidRecyclerAdapter.ViewHolder viewHolder = new OrderInvalidRecyclerAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    //  5.取出對應position應該要顯示的資料
    @Override
    public void onBindViewHolder(@NonNull OrderInvalidRecyclerAdapter.ViewHolder holder, int position) {

    }

    //  3. 設定 RecyclerView 的 item數量
    @Override
    public int getItemCount() {
        return myOrderList.size();
    }

    //  4. 取得 RecyclerView 上 Item 的 View
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView_invalid_orderNum;
        private final TextView textView_invalid_productName;
        private final TextView textView_invalid_productNum;
        private final TextView textView_invalid_productPrice;
        private final TextView textView_invalid_totalPrice;
        private final TextView textView_invalid_totalProductNum;
        private final ImageView imageView_invalidPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_invalid_orderNum = (TextView)itemView.findViewById(R.id.textView_invalid_orderNum);
            textView_invalid_productName = (TextView)itemView.findViewById(R.id.textView_invalid_productName);
            textView_invalid_productNum = (TextView)itemView.findViewById(R.id.textView_invalid_productNum);
            textView_invalid_productPrice = (TextView)itemView.findViewById(R.id.textView_invalid_productPrice);
            textView_invalid_totalProductNum = (TextView)itemView.findViewById(R.id.textView_invalid_totalProductNum);
            textView_invalid_totalPrice = (TextView)itemView.findViewById(R.id.textView_invalid_totalPrice);
            imageView_invalidPic = (ImageView)itemView.findViewById(R.id.imageView_invalid_Pic);
        }
    }
}