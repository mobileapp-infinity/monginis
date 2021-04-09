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
import com.infinity.monginis.dashboard.model.CartItemModel;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import java.util.ArrayList;

public class CustomizeItemAdapter extends RecyclerView.Adapter<CustomizeItemAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CartItemModel> cartItemModelArrayList;
    private MySharedPreferences mySharedPreferences;
    private IOnOrderPlaced iOnOrderPlaced;

    public CustomizeItemAdapter(Context context, ArrayList<CartItemModel> cartItemModelArrayList, IOnOrderPlaced iOnOrderPlaced) {
        this.context = context;
        mySharedPreferences = new MySharedPreferences(context);
        this.cartItemModelArrayList = cartItemModelArrayList;
        this.iOnOrderPlaced = iOnOrderPlaced;
    }

    public interface IOnOrderPlaced {
        void onOrderPlaced(ArrayList<CartItemModel> cartItemModelArrayList, int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View customize_view = LayoutInflater.from(context).inflate(R.layout.inflater_cutomize_item_view, parent, false);
        return new MyViewHolder(customize_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        //  holder.tvCustomizeItemName.setText();
        if (!CommonUtil.checkIsEmptyOrNullCommon(cartItemModelArrayList.get(position).getItemName())) {
            holder.tvCustomizeItemName.setText(cartItemModelArrayList.get(position).getItemName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(cartItemModelArrayList.get(position).getItemMrp())) {
            holder.tvMrp.setText(cartItemModelArrayList.get(position).getItemMrp() + "");
        }

        holder.bindListener(cartItemModelArrayList, position);
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
        return cartItemModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llPlaceOrder;
        TextViewRegularFont tvCustomizeItemName;
        TextViewRegularFont tvBy;
        TextViewRegularFont tvMrp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomizeItemName = itemView.findViewById(R.id.tvCustomizeItemName);
            llPlaceOrder = itemView.findViewById(R.id.llPlaceOrder);
            tvBy = itemView.findViewById(R.id.tvBy);
            tvMrp = itemView.findViewById(R.id.tvMrp);


        }

        private void bindListener(ArrayList<CartItemModel> cartItemModelArrayLists, int position) {

            llPlaceOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnOrderPlaced.onOrderPlaced(cartItemModelArrayList, position);
                }
            });


        }
    }
}
