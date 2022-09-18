package com.example.iot_project.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iot_project.R;

public class MemberOrdersRecyclerViewAdapter extends RecyclerView.Adapter<MemberOrdersRecyclerViewAdapter.ViewHolder> {

    private String name;

    public MemberOrdersRecyclerViewAdapter(String name) {
        this.name = name;
    };

    @NonNull
    @Override
    public MemberOrdersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_member_orders, parent, false);
        return new MemberOrdersRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberOrdersRecyclerViewAdapter.ViewHolder holder, int position) {
        Context Context = holder.itemView.getContext();
        int a = position;
        holder.textViewCardviewOrders.setText(name);
        holder.button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textViewCardviewOrders.setText("777" + a);
            }
        });

        if (name.equals("未收")) {
            holder.button_1.setVisibility(View.GONE);
            holder.imageViewPic.setImageDrawable(ContextCompat.getDrawable(Context, R.drawable.goods_demo));
            holder.textViewPrice.setText("9999");
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textViewCardviewOrders;
        private final Button button_1;
        private final ImageView imageViewPic;
        private final TextView textViewPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCardviewOrders = (TextView)itemView.findViewById(R.id.TextView_cardview_member_orders);
            button_1 = (Button)itemView.findViewById(R.id.button_member_orders_1);
            imageViewPic = (ImageView)itemView.findViewById(R.id.imageView_member_orders_pic);
            textViewPrice = (TextView)itemView.findViewById(R.id.textView_member_orders_total);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
            ((MemberActivity)itemView.getContext()).showOrdersDetailed("訂單詳情");
        }

    }

}
