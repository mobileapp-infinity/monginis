package com.infinity.monginis.confrimOrder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.confrimOrder.pojo.GetPartialOrderDetailReponsePojo;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;


public class ConfirmOrderAddsonAdapter extends RecyclerView.Adapter<ConfirmOrderAddsonAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String>addsonList;
    private GetPartialOrderDetailReponsePojo getPartialOrderDetailReponsePojo;


    public ConfirmOrderAddsonAdapter(Context context, GetPartialOrderDetailReponsePojo getPartialOrderDetailReponsePojo) {
        this.context = context;
        this.getPartialOrderDetailReponsePojo = getPartialOrderDetailReponsePojo;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ConfirmOrderAddsonAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_adds_on_item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ConfirmOrderAddsonAdapter.MyViewHolder holder, int position) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(getPartialOrderDetailReponsePojo.getRecords().getAddons().get(position).getItmName())){

            holder.tvName.setText(getPartialOrderDetailReponsePojo.getRecords().getAddons().get(position).getItmName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getPartialOrderDetailReponsePojo.getRecords().getAddons().get(position).getSroidItmQty())){

            holder.tvQty.setText(getPartialOrderDetailReponsePojo.getRecords().getAddons().get(position).getSroidItmQty()+"");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getPartialOrderDetailReponsePojo.getRecords().getAddons().get(position).getItmPrice())){

            holder.tvPrice.setText(getPartialOrderDetailReponsePojo.getRecords().getAddons().get(position).getItmPrice()+"");
        }
    }

    @Override
    public int getItemCount() {
        return getPartialOrderDetailReponsePojo.getRecords().getAddons().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextViewRegularFont tvName,tvQty,tvPrice;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
