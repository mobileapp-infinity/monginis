package com.infinity.monginis.ShopForItemActiivty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.ItemDetailsActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.IntentConstants;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.MyViewHolder> {

    private Context context;
    private GetShopListForItemStockPojo getShopListForItemStockPojo;


    public ShopListAdapter(Context context, GetShopListForItemStockPojo getShopListForItemStockPojo) {
        this.context = context;
        this.getShopListForItemStockPojo = getShopListForItemStockPojo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.inflater_shop_list, parent, false);
        return new MyViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (!CommonUtil.checkIsEmptyOrNullCommon(getShopListForItemStockPojo.getRECORDS().get(position).getCusName())) {
            holder.tvPopularItemName.setText(getShopListForItemStockPojo.getRECORDS().get(position).getCusName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getShopListForItemStockPojo.getRECORDS().get(position).getCusAdd1())) {
            holder.tvShopAddress.setText(getShopListForItemStockPojo.getRECORDS().get(position).getCusAdd1());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent itemDetailsIntent = new Intent(context, ItemDetailsActivity.class);
                itemDetailsIntent.putExtra(IntentConstants.SELECTED_SHOP_ID, getShopListForItemStockPojo.getRECORDS().get(position).getShopId() + "");
                itemDetailsIntent.putExtra(IntentConstants.SELECTED_SHOP_NAME, getShopListForItemStockPojo.getRECORDS().get(position).getCusName() + "");
                itemDetailsIntent.putExtra(IntentConstants.SELECTED_SHOP_ADDRESS, getShopListForItemStockPojo.getRECORDS().get(position).getCusAdd1() + "");
                context.startActivity(itemDetailsIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return getShopListForItemStockPojo.getRECORDS().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivSearchItem;
        TextViewRegularFont tvPopularItemName, tvShopName, tvShopAddress;
        TextViewRegularFont tvBy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSearchItem = itemView.findViewById(R.id.ivSearchItem);
            tvPopularItemName = itemView.findViewById(R.id.tvPopularItemName);
            // tvShopName = itemView.findViewById(R.id.tvShopName);
            tvShopAddress = itemView.findViewById(R.id.tvShopAddress);
            tvBy = itemView.findViewById(R.id.tvBy);
        }
    }
}
