package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.MyOrdersActivity;

public class MyOrdersListAdapter extends RecyclerView.Adapter<MyOrdersListAdapter.MyViewHolder> {
    private Context context;

    public MyOrdersListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_my_orders_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyOrdersActivity.class);
                context.startActivity(intent);
            }
        });

        if (position % 2 == 0) {
            holder.tvCancelOrder.setVisibility(View.GONE);
        } else {
            holder.tvCancelOrder.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextViewRegularFont tvOrderItemPrice;
        TextViewRegularFont tvCityName;
        TextViewRegularFont tvItemName;
        TextViewRegularFont tvOrderDate;
        TextViewMediumFont tvViewOrder;
        TextViewMediumFont tvCancelOrder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderItemPrice = itemView.findViewById(R.id.tvOrderItemPrice);
            tvCityName = itemView.findViewById(R.id.tvCityName);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvViewOrder = itemView.findViewById(R.id.tvViewOrder);
            tvCancelOrder = itemView.findViewById(R.id.tvCancelOrder);
        }
    }
}
