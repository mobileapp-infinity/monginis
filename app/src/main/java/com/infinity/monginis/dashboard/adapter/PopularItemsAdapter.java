package com.infinity.monginis.dashboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.utils.CommonUtil;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;
import static com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter.isFromTopCategories;

public class PopularItemsAdapter extends RecyclerView.Adapter<PopularItemsAdapter.MyViewHolder> {

    private Context context;
    private GetItemsForDashboardPojo getItemsForDashboardPojo;

    public PopularItemsAdapter(Context context, GetItemsForDashboardPojo getItemsForDashboardPojo) {
        this.context = context;
        this.getItemsForDashboardPojo = getItemsForDashboardPojo;
    }

    public static boolean isFromSpecialOrderItem = false;
    public static boolean isFromRegularOrderItem = false;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View popular_item_view = LayoutInflater.from(context).inflate(R.layout.inflater_home_popular_item, parent, false);

        // popular_item_view.setLayoutParams(Integer.parseInt(parent.getWidth() * 0.7)); = ViewGroup.LayoutParams(,ViewGroup.LayoutParams.MATCH_PARENT)
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int devicewidth = (int) (displayMetrics.widthPixels * 0.7);
        popular_item_view.getLayoutParams().width = devicewidth;
        return new MyViewHolder(popular_item_view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RequestOptions options = new RequestOptions()

                .placeholder(R.drawable.monginis_logo)
                .error(R.drawable.monginis_logo)

                .priority(Priority.HIGH);

        try {
            Glide.with(context).load(getItemsForDashboardPojo.getRECORDS().get(position).getItmUrl()).apply(options).into(holder.ivPopularItem);
        } catch (Exception e) {

            System.out.println("Item Image Setting Error:::::" + e.getMessage());
        }


        if (position % 2 == 0) {

            holder.tvPopularCategory.setText("(Special)");
        } else {
            holder.tvPopularCategory.setText("(Regular)");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRECORDS().get(position).getItmName())) {
            holder.tvPopularItemName.setText(getItemsForDashboardPojo.getRECORDS().get(position).getItmName())
            ;
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRECORDS().get(position).getPrice().toString())) {
            holder.tvPrice.setText("Rs." + getItemsForDashboardPojo.getRECORDS().get(position).getPrice().toString())
            ;
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRECORDS().get(position).getItmFlv()) && !CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRECORDS().get(position).getItmShape())) {
            holder.tvFlavourAndShape.setText(getItemsForDashboardPojo.getRECORDS().get(position).getItmFlv() + "(" + getItemsForDashboardPojo.getRECORDS().get(position).getItmShape() + ")");
            ;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position % 2 == 0) {

                    isFromSpecialOrderItem = true;
                    isFromRegularOrderItem = false;
                    isFromTopCategories = false;
                    vpDashboard.setCurrentItem(1);

                } else {
                    isFromSpecialOrderItem = false;
                    isFromRegularOrderItem = true;
                    isFromTopCategories = false;
                    vpDashboard.setCurrentItem(1);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return getItemsForDashboardPojo.getRECORDS().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPopularItem;
        TextViewRegularFont tvPopularItemName, tvFlavourAndShape, tvPrice, tvPopularCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPopularItem = itemView.findViewById(R.id.ivPopularItem);
            tvPopularItemName = itemView.findViewById(R.id.tvPopularItemName);
            tvFlavourAndShape = itemView.findViewById(R.id.tvFlavourAndShape);
            tvPrice = itemView.findViewById(R.id.tvNewPrice);
            tvPopularCategory = itemView.findViewById(R.id.tvPopularCategory);
        }
    }
}
