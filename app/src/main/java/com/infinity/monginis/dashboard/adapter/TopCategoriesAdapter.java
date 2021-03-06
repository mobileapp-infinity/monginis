package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;

import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;

public class TopCategoriesAdapter extends RecyclerView.Adapter<TopCategoriesAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private GetCategoryForDashboardPojo getCategoryForDashboardPojo;

    public static boolean isFromTopCategories = false;

    public TopCategoriesAdapter(Context context, GetCategoryForDashboardPojo getCategoryForDashboardPojo) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.getCategoryForDashboardPojo = getCategoryForDashboardPojo;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_top_categories, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context)
                .load("https://connectcourse.files.wordpress.com/2020/07/cake_3.jpg")
                .placeholder(R.drawable.monginis_logo)
                .into(holder.ivTopCategories);

        if (!CommonUtil.checkIsEmptyOrNullCommon(getCategoryForDashboardPojo.getRECORDS().get(position).getCatName())) {
            holder.tvCategoryName.setText(getCategoryForDashboardPojo.getRECORDS().get(position).getCatName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isFromTopCategories = true;
                vpDashboard.setCurrentItem(1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getCategoryForDashboardPojo.getRECORDS().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTopCategories;
        TextViewRegularFont tvCategoryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTopCategories = itemView.findViewById(R.id.ivTopCategories);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }


}
