package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.model.AddsOnItemModel;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class AddsOnAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, ArrayList<AddsOnItemModel>> AddsOnItemHashMap;
    private ArrayList<String> categoryName;


    public AddsOnAdapter(Context context, HashMap<String, ArrayList<AddsOnItemModel>> AddsOnItemHashMap, ArrayList<String> categoryName) {
        this.context = context;
        this.AddsOnItemHashMap = AddsOnItemHashMap;
        this.categoryName = categoryName;
    }

    @Override
    public int getGroupCount() {
        return AddsOnItemHashMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.AddsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.categoryName.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.AddsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .get(childPosition).getItem_name();
    }


    public Object getItemQty(int groupPosition, int childPosition) {
        return this.AddsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .get(childPosition).getQty();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.addson_header_view, null);
        TextViewMediumFont tvAddsOnCategoryName = convertView.findViewById(R.id.tvAddsOnCategoryName);
        tvAddsOnCategoryName.setText(getGroup(groupPosition) + "");

        convertView.setPadding(0, 0, 0, 20);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.addson_child_view, null);
        TextViewRegularFont tvAddOnItemName = convertView.findViewById(R.id.tvAddOnItemName);
        AppCompatEditText edItemCount = convertView.findViewById(R.id.edItemCount);

        AppCompatImageView imgAdd = convertView.findViewById(R.id.imgAdd);
        AppCompatImageView imgMinus = convertView.findViewById(R.id.imgMinus);

        tvAddOnItemName.setText(getChild(groupPosition, childPosition) + "");
        edItemCount.setText(getItemQty(groupPosition, childPosition) + "");
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuantity(1, edItemCount, groupPosition, childPosition, AddsOnItemHashMap, categoryName);
            }
        });


        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusQuantity(1,edItemCount,groupPosition,childPosition, AddsOnItemHashMap,categoryName);
            }
        });

        convertView.setPadding(0, 12, 0, 12);

        return convertView;
    }


    public void addQuantity(int addBy, AppCompatEditText edtQty, int groupPosition, int childPosition, HashMap<String, ArrayList<AddsOnItemModel>> categoryItemHashMap, ArrayList<String> categoryNameHeader) {

        int avlQty = 0;

        if (!CommonUtil.checkIsEmptyOrNullCommon(edtQty.getText().toString())) {
            avlQty = Integer.valueOf(String.valueOf(edtQty.getText().toString()));
        }


        avlQty = avlQty + addBy;


        edtQty.setText(String.valueOf(avlQty));


        categoryItemHashMap.get(categoryName.get(groupPosition))
                .get(childPosition).setQty(avlQty + "");


        int total = Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty()) * Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getMrp());

        categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).setTotal_price(total + "");


        Double total_amt = Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty()) * Double.parseDouble(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getSh_price());


        categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).setTotal_amount(total_amt + "");

        categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).setTotal_amount(total_amt + "");


          totalAddsOnPrice(categoryItemHashMap,categoryNameHeader,groupPosition,childPosition);

    }

    private void totalAddsOnPrice(HashMap<String, ArrayList<AddsOnItemModel>> categoryItemHashMap, ArrayList<String> categoryNameHeader, int groupPosition, int childPosition) {


        int total = 0;
        for (int i = 0; i < categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).size(); i++) {
            total += Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getTotal_price());
        }


    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void minusQuantity(int minusBy, AppCompatEditText tvQty, int groupPosition, int childPosition, HashMap<String, ArrayList<AddsOnItemModel>> categoryItemHashMap, ArrayList<String> categoryNameHeader) {
        int avlQty = Integer.valueOf(String.valueOf(tvQty.getText()));

        if (avlQty > 0) {
            avlQty = avlQty - minusBy;
            tvQty.setText(String.valueOf(avlQty));
            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setQty(avlQty + "");

            int total = Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty()) * Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getMrp());

            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setTotal_price(total + "");


            Double total_amt = Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty()) * Double.parseDouble(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getSh_price());


            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setTotal_amount(total_amt + "");

            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setTotal_amount(total_amt + "");


        }

          totalAddsOnPrice(categoryItemHashMap,categoryNameHeader,groupPosition,childPosition);

    }
}
