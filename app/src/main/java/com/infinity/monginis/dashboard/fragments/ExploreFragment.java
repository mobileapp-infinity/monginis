package com.infinity.monginis.dashboard.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.NearByDealsAdapter;
import com.infinity.monginis.dashboard.adapter.PopularItemsAdapter;
import com.infinity.monginis.dashboard.adapter.SearchCategoryAdapter;
import com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.dashboard.pojo.TestPojo;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.IntentConstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends Fragment implements View.OnClickListener {

    private static ExploreFragment exploreFragment = null;
    private Activity activity;

    private LinearLayout llTopCategories;
    private RecyclerView rvTopCategories;

    private LinearLayout llPopularItems;
    private RecyclerView rvPopularItems;
    private TextViewRegularFont tvViewAllPopularItems;

    private LinearLayout llNearByDeals;
    private RecyclerView rvNearbyDeals;
    private TextViewRegularFont tvViewAllNearByDeals;

    private LinearLayout llTopCategoryFilter;
    private TextViewMediumFont tvStreetName;
    private TextViewRegularFont tvUserAddress;
    private String userCityName = "";
    private ConnectionDetector connectionDetector;
    private LinearLayout llCategoryProgressbar, llPopularProgressbar;
    private LinearLayout llExploreContent;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public ExploreFragment() {
        // Required empty public constructor
    }

    public static ExploreFragment getExploreFragment() {
        if (exploreFragment == null) {
            exploreFragment = new ExploreFragment();
        }
        return exploreFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        initView(view);
        GetCategoryForDashboardApiCall();
        return view;
    }

    private void initView(View view) {
        llTopCategories = view.findViewById(R.id.llTopCategories);
        rvTopCategories = view.findViewById(R.id.rvTopCategories);
        llTopCategoryFilter = view.findViewById(R.id.llTopCategoryFilter);
        llTopCategoryFilter.setOnClickListener(this);
        rvTopCategories.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));


        llPopularItems = view.findViewById(R.id.llPopularItems);
        rvPopularItems = view.findViewById(R.id.rvPopularItems);
        rvPopularItems.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));

        tvViewAllPopularItems = view.findViewById(R.id.tvViewAllPopularItems);
        tvViewAllPopularItems.setOnClickListener(this);

        llNearByDeals = view.findViewById(R.id.llNearByDeals);
        rvNearbyDeals = view.findViewById(R.id.rvNearbyDeals);
        rvNearbyDeals.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rvNearbyDeals.setAdapter(new NearByDealsAdapter(activity));

        tvViewAllNearByDeals = view.findViewById(R.id.tvViewAllNearByDeals);
        tvViewAllNearByDeals.setOnClickListener(this);
        tvStreetName = view.findViewById(R.id.tvStreetName);
        tvUserAddress = view.findViewById(R.id.tvUserAddress);

        tvStreetName.setText(activity.getIntent().getStringExtra(IntentConstants.USER_CURRENT_STREET_NAME));
        tvUserAddress.setText(activity.getIntent().getStringExtra(IntentConstants.USER_CURRENT_ADDRESS));
        userCityName = activity.getIntent().getStringExtra(IntentConstants.USER_CURRENT_CITY_NAME);
        connectionDetector = new ConnectionDetector(getActivity());
        llCategoryProgressbar = view.findViewById(R.id.llCategoryProgressbar);
        llPopularProgressbar = view.findViewById(R.id.llPopularProgressbar);
        llExploreContent = view.findViewById(R.id.llExploreContent);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvViewAllPopularItems) {

        } else if (v.getId() == R.id.tvViewAllNearByDeals) {

        } else if (v.getId() == R.id.llTopCategoryFilter) {

        }
    }

    private void GetCategoryForDashboardApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            rvTopCategories.setVisibility(View.GONE);
            llCategoryProgressbar.setVisibility(View.VISIBLE);

            ApiImplementer.GetCategoryForDashboardImplementer("1", "1", "1", "0", ApiUrls.TESTING_KEY, "1", new Callback<GetCategoryForDashboardPojo>() {
                @Override
                public void onResponse(Call<GetCategoryForDashboardPojo> call, Response<GetCategoryForDashboardPojo> response) {

                    GetItemsForDashboard(userCityName);

                    try {
                        if (response.isSuccessful() && response.body() != null
                        ) {

                            llCategoryProgressbar.setVisibility(View.GONE);
                            rvTopCategories.setVisibility(View.VISIBLE);


                            GetCategoryForDashboardPojo getCategoryForDashboardPojo = response.body();
                            if (getCategoryForDashboardPojo != null && getCategoryForDashboardPojo.getRECORDS().size() > 0) {

                                TopCategoriesAdapter topCategoriesAdapter = new TopCategoriesAdapter(getActivity(), getCategoryForDashboardPojo);

                                rvTopCategories.setAdapter(topCategoriesAdapter);
                            }


                        } else {


                            llCategoryProgressbar.setVisibility(View.GONE);
                            rvTopCategories.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        llCategoryProgressbar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<GetCategoryForDashboardPojo> call, Throwable t) {

                    Toast.makeText(activity, "Error in response" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    llCategoryProgressbar.setVisibility(View.GONE);
                    rvTopCategories.setVisibility(View.VISIBLE);
                }
            });
        } else {
            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void GetItemsForDashboard(String userCityName) {
        if (connectionDetector.isConnectingToInternet()) {
            rvPopularItems.setVisibility(View.GONE);
            llPopularProgressbar.setVisibility(View.VISIBLE);

            ApiImplementer.GetItemsForDashboardImplementer("1", "1", "1", "0", ApiUrls.TESTING_KEY, "1", userCityName, "1", new Callback<GetItemsForDashboardPojo>() {
                @Override
                public void onResponse(Call<GetItemsForDashboardPojo> call, Response<GetItemsForDashboardPojo> response) {

                    try {
                        if (response.isSuccessful() && response.body() != null
                        ) {

                            llPopularProgressbar.setVisibility(View.GONE);
                            rvPopularItems.setVisibility(View.VISIBLE);


                            GetItemsForDashboardPojo getItemsForDashboardPojo = response.body();
                            if (getItemsForDashboardPojo != null && getItemsForDashboardPojo.getRECORDS().size() > 0) {

                                PopularItemsAdapter popularItemsAdapter = new PopularItemsAdapter(getActivity(), getItemsForDashboardPojo);

                                rvPopularItems.setAdapter(popularItemsAdapter);
                            }


                        } else {


                            llPopularProgressbar.setVisibility(View.GONE);
                            rvPopularItems.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        llPopularProgressbar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<GetItemsForDashboardPojo> call, Throwable t) {

                    Toast.makeText(activity, "Error in response" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    llPopularProgressbar.setVisibility(View.GONE);
                    rvPopularItems.setVisibility(View.VISIBLE);
                }
            });
        } else {
            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}