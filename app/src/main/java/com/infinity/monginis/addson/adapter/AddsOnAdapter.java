package com.infinity.monginis.addson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.model.AddsOnItemModel;
import com.infinity.monginis.dashboard.pojo.Get_Addons_Items_List_Pojo;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddsOnAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, ArrayList<AddsOnItemModel>> AddsOnItemHashMap;
    private ArrayList<String> categoryName;
    private HashMap<String, List<Get_Addons_Items_List_Pojo.Item>>addsOnItemHashMap;
    private ExpandableListView expandableListView;



    public AddsOnAdapter(Context context,HashMap<String, List<Get_Addons_Items_List_Pojo.Item>>addsOnItemHashMap, ArrayList<String> categoryName,ExpandableListView expandableListView) {
        this.context = context;
 //       this.AddsOnItemHashMap = AddsOnItemHashMap;
        this.addsOnItemHashMap  =addsOnItemHashMap;
        this.categoryName = categoryName;
        this.expandableListView = expandableListView;
    }

    @Override
    public int getGroupCount() {
        return addsOnItemHashMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.addsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.categoryName.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.addsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .get(childPosition).getItemName();
    }


    public double getItemQty(int groupPosition, int childPosition) {
        return this.addsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .get(childPosition).getQty();
    }

    public Object getItemMrp(int groupPosition, int childPosition) {
        return this.addsOnItemHashMap.get(this.categoryName.get(groupPosition))
                .get(childPosition).getMrp();
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

    public static  TextViewMediumFont tvAddsOnCategoryName;
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.addson_header_view, null);
        tvAddsOnCategoryName   = convertView.findViewById(R.id.tvAddsOnCategoryName);
        tvAddsOnCategoryName.setText(getGroup(groupPosition) + "");
        /*if (isExpanded){
            tvAddsOnCategoryName.setCompoundDrawables(null,null, ContextCompat.getDrawable(context,R.drawable.ic_baseline_expand_less_24),null);
        }else{
            tvAddsOnCategoryName.setCompoundDrawables(null,null, ContextCompat.getDrawable(context,R.drawable.ic_baseline_expand_more_24),null);
        }*/

        convertView.setPadding(0, 0, 0, 20);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.addson_child_view, null);
        TextViewRegularFont tvAddOnItemName = convertView.findViewById(R.id.tvAddOnItemName);
        AppCompatEditText edItemCount = convertView.findViewById(R.id.edItemCount);
        TextViewRegularFont tvAddsOnItemPrice = convertView.findViewById(R.id.tvAddsOnItemPrice);

        AppCompatImageView imgAdd = convertView.findViewById(R.id.imgAdd);
        AppCompatImageView imgMinus = convertView.findViewById(R.id.imgMinus);

        tvAddOnItemName.setText(getChild(groupPosition, childPosition) + "");
        int value = (int)Math.round(getItemQty(groupPosition,childPosition));
        edItemCount.setText(value+"");
        tvAddsOnItemPrice.setText(getItemMrp(groupPosition, childPosition) + "");
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuantity(1, edItemCount, groupPosition, childPosition, addsOnItemHashMap, categoryName);
            }
        });


        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusQuantity(1,edItemCount,groupPosition,childPosition, addsOnItemHashMap,categoryName);
            }
        });


       /* ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.set(left, top, right, bottom);*/
       // ViewGroup.LayoutParams params=(ViewGroup.LayoutParams) convertView.getLayoutParams();
        //params.setPadding(0, 0, 0, 5);
       // convertView.setLayoutParams(params);
        convertView.setPadding(0, 40, 0, 40);

        //expandableListView
        return convertView;
    }


    public void addQuantity(int addBy, AppCompatEditText edtQty, int groupPosition, int childPosition, HashMap<String,List<Get_Addons_Items_List_Pojo.Item>> categoryItemHashMap, ArrayList<String> categoryNameHeader) {

        int avlQty = 0;

        if (!CommonUtil.checkIsEmptyOrNullCommon(edtQty.getText().toString())) {
            avlQty = Integer.valueOf(String.valueOf(edtQty.getText().toString()));
        }


        avlQty = avlQty + addBy;



        edtQty.setText(String.valueOf(avlQty));

        double todouble = avlQty;
        categoryItemHashMap.get(categoryName.get(groupPosition))
                .get(childPosition).setQty(todouble);


        double total = categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty() * categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getMrp();

        categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).setTotalAmt(total);


        Double total_amt = categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty() *categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getMrp();


        categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).setTotal_net_amt(total_amt);
        System.out.println(categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).getTotalAmt());

        System.out.println(categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).getTotal_net_amt());

        /*categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                .get(childPosition).setTotal_amount(total_amt + "");
*/

        //  totalAddsOnPrice(categoryItemHashMap,categoryNameHeader,groupPosition,childPosition);

    }

    private void totalAddsOnPrice(HashMap<String,Get_Addons_Items_List_Pojo.Item> categoryItemHashMap, ArrayList<String> categoryNameHeader, int groupPosition, int childPosition) {


       // int total = 0;
       // for (int i = 0; i < categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).size(); i++) {
       //     total += Integer.parseInt(categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getTotal_price());
       // }


    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void minusQuantity(int minusBy, AppCompatEditText tvQty, int groupPosition, int childPosition, HashMap<String,List<Get_Addons_Items_List_Pojo.Item>> categoryItemHashMap, ArrayList<String> categoryNameHeader) {
        int avlQty = Integer.valueOf(String.valueOf(tvQty.getText()));

        if (avlQty > 0) {
            avlQty = avlQty - minusBy;
            tvQty.setText(String.valueOf(avlQty));
            double todouble = avlQty;
            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setQty( todouble);

          double total = categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty() * 5.0;

            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setTotalAmt(total);


            Double total_amt = categoryItemHashMap.get(categoryNameHeader.get(groupPosition)).get(childPosition).getQty() * 3.5;


            categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setTotal_net_amt(total_amt );


            System.out.println(categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).getTotalAmt());

            System.out.println(categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).getTotal_net_amt());

          /*  categoryItemHashMap.get(categoryNameHeader.get(groupPosition))
                    .get(childPosition).setTotal_amount(total_amt + "");*/


        }

         // totalAddsOnPrice(categoryItemHashMap,categoryNameHeader,groupPosition,childPosition);

    }
}
