package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    private Context context;

    public AddressAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_manage_address_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position==(getItemCount()-1)){
            holder.vFooter.setVisibility(View.GONE);
        }else{
            holder.vFooter.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextViewRegularFont  tvAddressLine2;
        TextViewMediumFont tvAddressLine1, tvDelete, tvEdit;
        View vFooter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddressLine1 = itemView.findViewById(R.id.tvAddressLine1);
            tvAddressLine2 = itemView.findViewById(R.id.tvAddressLine2);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tvEdit = itemView.findViewById(R.id.tvEdit);
            vFooter = itemView.findViewById(R.id.vFooter);
        }
    }
}
