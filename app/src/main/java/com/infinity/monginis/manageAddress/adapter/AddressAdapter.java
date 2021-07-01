package com.infinity.monginis.manageAddress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.pojo.GetUserByMobileNoPojo;
import com.infinity.monginis.utils.CommonUtil;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    private Context context;
    private GetUserByMobileNoPojo getUserByMobileNoPojo;
    IOnItemClickes iOnItemClickes;
    private int lastSelectedPosition = -1;

    public AddressAdapter(Context context, GetUserByMobileNoPojo getUserByMobileNoPojo, IOnItemClickes iOnItemClickes) {
        this.context = context;
        this.getUserByMobileNoPojo = getUserByMobileNoPojo;
        this.iOnItemClickes = iOnItemClickes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_manage_address_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        if (!CommonUtil.checkIsEmptyOrNullCommon(getUserByMobileNoPojo.getRecords().get(position).getUdAddress())) {

            holder.edtAddress.setText(getUserByMobileNoPojo.getRecords().get(position).getUdAddress());
        }
        if (lastSelectedPosition == position) {
            holder.llSelected.setVisibility(View.VISIBLE);

        } else {
            holder.llSelected.setVisibility(View.INVISIBLE);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastSelectedPosition = position;
                notifyDataSetChanged();
            }
        });


        iOnItemClickes.onViewClicked(holder, getUserByMobileNoPojo, position);

    }

    @Override
    public int getItemCount() {
        return getUserByMobileNoPojo.getRecords().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatEditText edtAddress;
        View vFooter;
        AppCompatImageView ivEdit, ivDelete;
        private LinearLayout llSelected;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            edtAddress = itemView.findViewById(R.id.edtAddress);

            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.tvEdit);
            vFooter = itemView.findViewById(R.id.vFooter);
            llSelected = itemView.findViewById(R.id.llSelected);
        }

    }

    public interface IOnItemClickes {
        void onViewClicked(MyViewHolder holder, GetUserByMobileNoPojo getUserByMobileNoPojo, int pos);
    }


}
