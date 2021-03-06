package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

public class CartScreenAdapter extends RecyclerView.Adapter<CartScreenAdapter.MyViewHolder> {

    private Context context;
    ArrayList<String>customizeList;

    public CartScreenAdapter(Context context) {
        this.context = context;
        customizeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_cart_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SearchableSpinner spCustomize;
        TextViewMediumFont tvCartItemName;
        TextViewRegularFont tvSize;
        AppCompatEditText edItemCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customizeList.add("Customize 1");
            customizeList.add("Customize 2");
            customizeList.add("Customize 3");
            spCustomize = itemView.findViewById(R.id.spCustomize);
            edItemCount = itemView.findViewById(R.id.edItemCount);
            ArrayAdapter<String> customizeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_common_layout, customizeList);
            customizeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
            spCustomize.setTitle("Customize");
            spCustomize.setAdapter(customizeAdapter);
            tvCartItemName = itemView.findViewById(R.id.tvCartItemName);
            tvSize = itemView.findViewById(R.id.tvSize);
        }
    }
}
