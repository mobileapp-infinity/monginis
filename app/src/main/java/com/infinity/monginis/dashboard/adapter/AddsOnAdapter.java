package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.model.AddsOnItemModel;

import java.util.ArrayList;
import java.util.HashMap;

public class AddsOnAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, ArrayList<AddsOnItemModel>> filteredHashMap;
    private ArrayList<String> categoryName;


    public AddsOnAdapter(Context context, HashMap<String, ArrayList<AddsOnItemModel>> filteredHashMap, ArrayList<String> categoryName) {
        this.context = context;
        this.filteredHashMap = filteredHashMap;
        this.categoryName = categoryName;
    }

    @Override
    public int getGroupCount() {
        return filteredHashMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.filteredHashMap.get(this.categoryName.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.categoryName.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.filteredHashMap.get(this.categoryName.get(groupPosition))
                .get(childPosition).getItem_name();
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
        tvAddOnItemName.setText(getChild(groupPosition, childPosition) + "");


        convertView.setPadding(0, 12, 0, 12);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
