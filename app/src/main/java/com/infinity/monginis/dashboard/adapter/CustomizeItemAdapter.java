package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.CartActivity;

public class CustomizeItemAdapter extends RecyclerView.Adapter<CustomizeItemAdapter.MyViewHolder> {

    private Context context;

    public CustomizeItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View customize_view = LayoutInflater.from(context).inflate(R.layout.inflater_cutomize_item_view, parent, false);
        return new MyViewHolder(customize_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // holder.tvCustomizeItemName.setText();


        holder.llPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartScreenIntent = new Intent(context, CartActivity.class);
                context.startActivity(cartScreenIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llPlaceOrder;
        TextViewRegularFont tvCustomizeItemName;
        TextViewRegularFont tvBy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomizeItemName = itemView.findViewById(R.id.tvCustomizeItemName);
            llPlaceOrder = itemView.findViewById(R.id.llPlaceOrder);
            tvBy = itemView.findViewById(R.id.tvBy);
        }
    }
}
