package com.infinity.monginis.dashboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.BottomSheetDialogForCheckOut;
import com.infinity.monginis.dashboard.activity.BottomSheetDialogForSpecialOrder;
import com.infinity.monginis.dashboard.activity.CartActivity;
import com.infinity.monginis.dashboard.activity.ItemDetailsActivity;
import com.infinity.monginis.dashboard.pojo.CategoryDetailsPojo;
import com.infinity.monginis.login.BottomSheetDialogForLoginUser;
import com.infinity.monginis.login.BottomSheetDialogPlaceOrder;
import com.infinity.monginis.login.LoginActivity;

import java.util.ArrayList;

import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromRegularOrderItem;
import static com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter.isFromTopCategories;

public class CategoryDetailListAdapter extends RecyclerView.Adapter<CategoryDetailListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<CategoryDetailsPojo> categoryDetailsPojoArrayList;
    private static int MAX_ORDER_QUANTITY = 10;
    private boolean isFromSpecialOrderItem;
    ItemDetailsActivity activity;


    public CategoryDetailListAdapter(Context context, ArrayList<CategoryDetailsPojo> categoryDetailsPojoArrayList, boolean isFromSpecialOrderItem) {
        this.context = context;
        this.categoryDetailsPojoArrayList = categoryDetailsPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        this.isFromSpecialOrderItem = isFromSpecialOrderItem;
        activity = (ItemDetailsActivity) context;
        bottomSheetDialogForLoginUser = new BottomSheetDialogForLoginUser(activity);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_inflater_for_category_detail_screen, parent, false);
        return new MyViewHolder(view);
    }


    BottomSheetDialogForLoginUser bottomSheetDialogForLoginUser;

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (!categoryDetailsPojoArrayList.get(position).isAdded()) {
            holder.btnAddItemCount.setVisibility(View.VISIBLE);
            holder.llAddMoreItem.setVisibility(View.GONE);
        } else {
            holder.btnAddItemCount.setVisibility(View.GONE);
            holder.llAddMoreItem.setVisibility(View.VISIBLE);
        }

        if (isFromTopCategories) {

            isFromSpecialOrderItem = false;
            isFromRegularOrderItem = false;
            holder.btnAddItemCount.setVisibility(View.VISIBLE);
            holder.llAddMoreItem.setVisibility(View.GONE);

        }
        if (isFromSpecialOrderItem) {
            holder.llAddMoreItem.setVisibility(View.GONE);
            holder.btnAddItemCount.setVisibility(View.VISIBLE);
        }
        if (isFromRegularOrderItem){
            holder.llAddMoreItem.setVisibility(View.VISIBLE);
            holder.btnAddItemCount.setVisibility(View.GONE);
        }

        holder.btnAddItemCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  BottomSheetDialogPlaceOrder bottomSheetDialogPlaceOrder = new BottomSheetDialogPlaceOrder((ItemDetailsActivity) context);
                //bottomSheetDialogPlaceOrder.show(((ItemDetailsActivity) context).getSupportFragmentManager(), "place_order_fragment");
//                holder.btnAddItemCount.setVisibility(View.GONE);
//                holder.btnAddItemCount.setVisibility(View.VISIBLE);
//                categoryDetailsPojoArrayList.get(position).setAdded(true);
//                notifyItemChanged(position);

                if (!bottomSheetDialogForLoginUser.isAdded()) {
                    bottomSheetDialogForLoginUser.show(activity.getSupportFragmentManager(), "test");
                }
                // BottomSheetDialogForCheckOut bottomSheetDialogForCheckOut = new BottomSheetDialogForCheckOut((ItemDetailsActivity) context);
                // bottomSheetDialogForCheckOut.show(((ItemDetailsActivity) context).getSupportFragmentManager(), "place_order_fragment");
            }
        });


        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.valueOf(holder.edItemCount.getText().toString()) <= MAX_ORDER_QUANTITY) {
                        addQuantity(1, holder.edItemCount);

                        if (!bottomSheetDialogForLoginUser.isAdded()) {
                            bottomSheetDialogForLoginUser.show(activity.getSupportFragmentManager(), "test");
                        }


                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!TextUtils.isEmpty(holder.edItemCount.getText().toString())) {
                        subtractQuantity(1, holder.edItemCount);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    void addQuantity(int addBy, AppCompatEditText etQty) {

        int avlQty = 0;

        if (!TextUtils.isEmpty(etQty.getText().toString())) {
            avlQty = Integer.valueOf(String.valueOf(etQty.getText()));
        }

        avlQty = avlQty + addBy;

        etQty.setText(String.valueOf(avlQty));
    }

    void subtractQuantity(int subtractBy, AppCompatEditText etQty) {
        int avlQty = Integer.valueOf(String.valueOf(etQty.getText()));

        if (avlQty > 1) {
            avlQty = avlQty - subtractBy;
            etQty.setText(String.valueOf(avlQty));
        }
    }


    @Override
    public int getItemCount() {
        return categoryDetailsPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont btnAddItemCount;
        LinearLayout llAddMoreItem;
        AppCompatImageView imgAdd, imgMinus;
        AppCompatEditText edItemCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnAddItemCount = itemView.findViewById(R.id.btnAddItemCount);
            llAddMoreItem = itemView.findViewById(R.id.llAddMoreItem);
            imgAdd = itemView.findViewById(R.id.imgAdd);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            edItemCount = itemView.findViewById(R.id.edItemCount);
        }
    }

}
