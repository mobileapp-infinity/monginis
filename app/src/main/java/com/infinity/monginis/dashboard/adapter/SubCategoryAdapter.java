package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.dashboard.pojo.SubSectionPojo;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;

public class SubCategoryAdapter extends BaseAdapter {




    private Context context;
    private SubSectionPojo subSectionPojo;
    private iOnCategoryClicked iOnCategoryClicked;

    public SubCategoryAdapter(Context context, SubSectionPojo subSectionPojo) {
        this.context = context;
        this.subSectionPojo = subSectionPojo;
        this.iOnCategoryClicked = (iOnCategoryClicked)context;
    }

   /* @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_section_item,parent,false);
        return  new MyViewHolder(view);
       // return null;
    }*/

   /* @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(subSectionPojo.getRecords().get(position).getIcmCatName())){

            holder.tvSubCategoryName.setText(subSectionPojo.getRecords().get(position).getIcmCatName());
        }


        holder.bindListner(subSectionPojo,position);
    }*/

  /*  @Override
    public int getItemCount() {
        return subSectionPojo.getRecords().size();
    }*/

    @Override
    public int getCount() {
        return subSectionPojo.getRecords().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View view  = convertView;
        convertView = LayoutInflater.from(context).inflate(R.layout.sub_section_item,parent,false);
        TextViewMediumFont tvSubCategoryName = convertView.findViewById(R.id.tvSubCategoryName);
        tvSubCategoryName.setText(subSectionPojo.getRecords().get(position).getIcmCatName());


       // bindListner(subSectionPojo,position,convertView);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryItemsDetailsActivity.class);
                intent.putExtra("id",subSectionPojo.getRecords().get(position).getId()+"");
                intent.putExtra("name",subSectionPojo.getRecords().get(position).getIcmCatName()+"");
                context.startActivity(intent);

            }
        });

        //convertView.setPadding(0,20,0,20);
        return convertView;
    }





   /* public class MyViewHolder extends RecyclerView.ViewHolder {
        TextViewMediumFont tvSubCategoryName;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tvSubCategoryName =itemView.findViewById(R.id.tvSubCategoryName);
        }


    }*/


    public interface iOnCategoryClicked{
        void getItemDetailsBySubCategoryClicked(SubSectionPojo subSectionPojo,int posiiton);
    }





    private void bindListner(SubSectionPojo subSectionPojo,int positionnnn,View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnCategoryClicked.getItemDetailsBySubCategoryClicked(subSectionPojo,positionnnn);
            }
        });

    }
}
