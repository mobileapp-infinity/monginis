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
import com.infinity.monginis.dashboard.pojo.ConfrimOrderReponsePojo;
import com.infinity.monginis.utils.CommonUtil;

import org.jetbrains.annotations.NotNull;

public class ConfirmOrderAccessoriesAdapter  extends RecyclerView.Adapter<ConfirmOrderAccessoriesAdapter.MyViewholder> {



    private Context context;
    private ConfrimOrderReponsePojo confrimOrderReponsePojo;

    public ConfirmOrderAccessoriesAdapter(Context context, ConfrimOrderReponsePojo confrimOrderReponsePojo) {
        this.context = context;
        this.confrimOrderReponsePojo = confrimOrderReponsePojo;
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

        if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getAccesories().get(position).getLinkItem())){
            holder.tvName.setText(confrimOrderReponsePojo.getRecords().getAccesories().get(position).getLinkItem()+"");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getAccesories().get(position).getQuantity())){
            holder.tvQty.setText(confrimOrderReponsePojo.getRecords().getAccesories().get(position).getQuantity()+"");
        }

    }

    @Override
    public int getItemCount() {
        return confrimOrderReponsePojo.getRecords().getAccesories().size();
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
