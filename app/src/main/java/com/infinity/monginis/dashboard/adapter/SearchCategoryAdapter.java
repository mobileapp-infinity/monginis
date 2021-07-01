package com.infinity.monginis.dashboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.BottomSheetDialogForSpecialOrder;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.dashboard.activity.EditUserDetailsActivity;
import com.infinity.monginis.dashboard.activity.ItemDetailsActivity;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.dashboard.pojo.TestPojo;
import com.infinity.monginis.utils.IntentConstants;

import java.util.ArrayList;

import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.MyViewHolder> {

    private Activity context;
    private LayoutInflater layoutInflater;
    private ArrayList<TestPojo> testPojoArrayList;
    IOnShopClicked iOnShopClicked;


    public SearchCategoryAdapter(Activity context, ArrayList<TestPojo> testPojoArrayList,IOnShopClicked iOnShopClicked) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.testPojoArrayList = testPojoArrayList;
        this.iOnShopClicked = iOnShopClicked;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_search_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.tvPopularItemName.setText(testPojoArrayList.get(position).getShopName());
        holder.tvBy.setText(testPojoArrayList.get(position).getShopAddress());

        //if (testPojoArrayList.get(position).getIs_special_flag() ==1){}

      //  Glide.with(context).f


        holder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (testPojoArrayList.get(position).getIs_special_flag() ==1){
                  //  BottomSheetDialogForSpecialOrder bottomSheetForSpecialOrder = new BottomSheetDialogForSpecialOrder((DashboardActivity) context),testPojoArrayList , position);
                   // bottomSheetForSpecialOrder.show(((DashboardActivity) context).getSupportFragmentManager(), "");


                    BottomSheetDialogForSpecialOrder bottomSheetForSpecialOrder = new BottomSheetDialogForSpecialOrder((DashboardActivity) context,testPojoArrayList,position,true);
                    bottomSheetForSpecialOrder.show(((DashboardActivity) context).getSupportFragmentManager(), "");
                }else{
                    Intent itemDetailsIntent = new Intent(context, ItemDetailsActivity.class);
                    itemDetailsIntent.putExtra("isItemLikedTrue",testPojoArrayList.get(position).isAlreadyLikedOrNot());
                    itemDetailsIntent.putExtra("isItemAvailableInLikedList",testPojoArrayList.get(position).isAvailableInLikedList());
                    itemDetailsIntent.putExtra(IntentConstants.SELECTED_SHOP_ID,testPojoArrayList.get(position).getCustomerId()+"");
                    itemDetailsIntent.putExtra(IntentConstants.SELECTED_SHOP_NAME,testPojoArrayList.get(position).getShopName()+"");


                    context.startActivity(itemDetailsIntent);
                }


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iOnShopClicked.onShopClicked(testPojoArrayList.get(position).getCustomerId());

            }
        });

    }

    @Override
    public int getItemCount() {

        return testPojoArrayList.size();
    }

    public void updateList(ArrayList<TestPojo> testPojoArrayList) {


        this.testPojoArrayList = testPojoArrayList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llView;
        TextViewRegularFont tvPopularItemName;
        TextViewRegularFont tvBy;
        ImageView ivSearchItem;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            llView = itemView.findViewById(R.id.llView);
            tvPopularItemName = itemView.findViewById(R.id.tvPopularItemName);
            ivSearchItem = itemView.findViewById(R.id.ivSearchItem);
            tvBy = itemView.findViewById(R.id.tvBy);
        }
    }


    public  interface IOnShopClicked{

        void onShopClicked(int custId);
    }
}
