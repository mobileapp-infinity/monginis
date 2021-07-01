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

import org.jetbrains.annotations.NotNull;

public class ConfirmOrderAccessoriesAdapter  extends RecyclerView.Adapter<ConfirmOrderAccessoriesAdapter.MyViewholder> {



    private Context context;
    private GetPartialOrderDetailReponsePojo getPartialOrderDetailReponsePojo;

    public ConfirmOrderAccessoriesAdapter(Context context, GetPartialOrderDetailReponsePojo getPartialOrderDetailReponsePojo) {
        this.context = context;
        this.getPartialOrderDetailReponsePojo = getPartialOrderDetailReponsePojo;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_accessories_item_view,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ConfirmOrderAccessoriesAdapter.MyViewholder holder, int position) {

        if (!CommonUtil.checkIsEmptyOrNullCommon(getPartialOrderDetailReponsePojo.getRecords().getAccesories().get(position).getLinkItem())){
            holder.tvName.setText(getPartialOrderDetailReponsePojo.getRecords().getAccesories().get(position).getLinkItem()+"");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getPartialOrderDetailReponsePojo.getRecords().getAccesories().get(position).getQuantity())){
            holder.tvQty.setText(getPartialOrderDetailReponsePojo.getRecords().getAccesories().get(position).getQuantity()+"");
        }

    }

    @Override
    public int getItemCount() {
        return getPartialOrderDetailReponsePojo.getRecords().getAccesories().size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextViewRegularFont tvName;
        TextViewRegularFont tvQty;
        public MyViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvQty = itemView.findViewById(R.id.tvQty);
        }
    }
}
