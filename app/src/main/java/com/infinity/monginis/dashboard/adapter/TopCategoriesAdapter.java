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
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;

import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetSectionPojo;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;

public class TopCategoriesAdapter extends RecyclerView.Adapter<TopCategoriesAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private GetCategoryForDashboardPojo getCategoryForDashboardPojo;
    private GetSectionPojo  getSectionPojo;
    private IOnCategoryClicked iOnCategoryClicked;

    public static boolean isFromTopCategories = false;

    public TopCategoriesAdapter(Context context,GetSectionPojo getSectionPojo, IOnCategoryClicked iOnCategoryClicked) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.getCategoryForDashboardPojo = getCategoryForDashboardPojo;
        this.getSectionPojo = getSectionPojo;
        this.iOnCategoryClicked = iOnCategoryClicked;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_top_categories, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RequestOptions options = new RequestOptions()

                .placeholder(R.drawable.monginis_logo)
                .error(R.drawable.monginis_logo)

                .priority(Priority.HIGH);

     /*   try {
            Glide.with(context)
                    .load(getCategoryForDashboardPojo.getRECORDS().get(position).getImg_url())
                    .placeholder(R.drawable.monginis_logo).apply(options)
                    .into(holder.ivTopCategories);

        } catch (Exception e) {

            System.out.println("Glide image Error" + e.getMessage());
        }
*/

        if (!CommonUtil.checkIsEmptyOrNullCommon(getSectionPojo.getRecords().get(position).getCsmSectionName())) {
            holder.tvCategoryName.setText(getSectionPojo.getRecords().get(position).getCsmSectionName());
        }

        holder.bindListener(getSectionPojo,position);

        /**Old*/
      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isFromTopCategories = true;
                vpDashboard.setCurrentItem(1);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return getSectionPojo.getRecords().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTopCategories;
        TextViewRegularFont tvCategoryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTopCategories = itemView.findViewById(R.id.ivTopCategories);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }

        private void bindListener(GetSectionPojo getSectionPojo,int position) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnCategoryClicked.getItemDetailsByCatgory(getSectionPojo,position);
                }
            });

        }
    }


    public interface IOnCategoryClicked {

        void getItemDetailsByCatgory(GetSectionPojo getSectionPojo,int position);
    }


}
