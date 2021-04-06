package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<String> cityList;
    private ArrayList<String> cityFilteredList;

    private IOnItemClickListener iOnItemClickListener;

    public SelectCityAdapter(Context context, ArrayList<String> cityList, IOnItemClickListener iOnItemClickListener) {
        this.context = context;
        this.cityList = cityList;
        this.cityFilteredList = cityList;
        this.iOnItemClickListener = iOnItemClickListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    cityFilteredList = cityList;

                } else {
                    ArrayList<String> filteredList = new ArrayList<>();

                    for (String cityName : cityList) {

                        if (cityName.toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(cityName);
                        }

                    }


                    cityFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cityFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                cityFilteredList = (ArrayList<String>) results.values;


                notifyDataSetChanged();
            }
        };
    }

    public interface IOnItemClickListener {
        void onItemClicked(String cityName);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_select_city_item, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (!CommonUtil.checkIsEmptyOrNullCommon(cityFilteredList.get(position))) {
            holder.tvCityName.setText(cityFilteredList.get(position));
        }

        holder.bindListener(position, cityFilteredList);


    }

    @Override
    public int getItemCount() {
        if (cityFilteredList != null && cityFilteredList.size() > 0) {
            return cityFilteredList.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextViewRegularFont tvCityName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCityName = itemView.findViewById(R.id.tvCityName);
        }

        public void bindListener(final int position, ArrayList<String> cityList) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iOnItemClickListener.onItemClicked(cityList.get(position));
                }
            });
        }
    }
}
