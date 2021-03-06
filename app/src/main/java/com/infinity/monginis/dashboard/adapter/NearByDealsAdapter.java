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
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;


public class NearByDealsAdapter extends RecyclerView.Adapter<NearByDealsAdapter.MyViewHolder> {

    private Context context;

    public NearByDealsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_near_by_item, parent, false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int devicewidth = (int) (displayMetrics.widthPixels * 0.6);
        view.getLayoutParams().width = devicewidth;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load("https://b.zmtcdn.com/data/pictures/chains/4/18543374/27a8d3fe04bfa2470639f6a249400934.jpg").error(R.drawable.ic_launcher_background).into(holder.ivNearByItem);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivNearByItem;
        private TextViewRegularFont tvNearByItemName;
        private TextViewRegularFont tvNearByItemDesc;
        private TextViewRegularFont tvNearByItemNewPrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNearByItem = itemView.findViewById(R.id.ivNearByItem);

            tvNearByItemDesc = itemView.findViewById(R.id.tvNearByItemDesc);
            tvNearByItemNewPrice = itemView.findViewById(R.id.tvNearByItemNewPrice);
        }
    }
}
