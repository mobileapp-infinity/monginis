package com.infinity.monginis.dashboard.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.SectionsubCategoryActivity;
import com.infinity.monginis.dashboard.activity.SelectCityActivity;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.adapter.NearByDealsAdapter;
import com.infinity.monginis.dashboard.adapter.PopularItemsAdapter;
import com.infinity.monginis.dashboard.adapter.SearchCategoryAdapter;
import com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter;
import com.infinity.monginis.dashboard.pojo.GetAllCityPojo;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsPhotoForDashboardAppPojo;
import com.infinity.monginis.dashboard.pojo.GetSectionPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.dashboard.pojo.TestPojo;
import com.infinity.monginis.login.SplashActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import net.seifhadjhassen.recyclerviewpager.PagerModel;
import net.seifhadjhassen.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private MySharedPreferences mySharedPreferences;

    public static final int SELECT_CITY_REQUEST_CODE = 1234;
    private LinearLayout llTopCategoryFilter;
    private TextViewMediumFont tvStreetName;
    private TextViewRegularFont tvUserAddress;
    private String userCityName = "";
    private ConnectionDetector connectionDetector;
    private LinearLayout llCategoryProgressbar, llPopularProgressbar, llRecyclerBannerProgressbar;
    private LinearLayout llExploreContent;
    private Dialog cityListDialog;
    private ArrayList<String> cityList;
    private ImageSliderAdapter imageSliderAdapter;
    RecyclerViewPager recyclerViewPagerStudentSideBanner;
    private ArrayList<String> dashboardImagesList;

    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};


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
        connectionDetector = new ConnectionDetector(getActivity());
        mySharedPreferences = new MySharedPreferences(getActivity());
        llTopCategories = view.findViewById(R.id.llTopCategories);
        rvTopCategories = view.findViewById(R.id.rvTopCategories);
        llTopCategoryFilter = view.findViewById(R.id.llTopCategoryFilter);
        llTopCategoryFilter.setOnClickListener(this);
        rvTopCategories.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPagerStudentSideBanner = view.findViewById(R.id.pager);

        llPopularItems = view.findViewById(R.id.llPopularItems);
        rvPopularItems = view.findViewById(R.id.rvPopularItems);


        rvPopularItems.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        imageSliderAdapter = new ImageSliderAdapter(getActivity(), images);

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

        if (userCityName == null || userCityName.equals("")) {

            Intent selectCityIntent = new Intent(getActivity(), SelectCityActivity.class);

            startActivityForResult(selectCityIntent, SELECT_CITY_REQUEST_CODE);

        }


        llCategoryProgressbar = view.findViewById(R.id.llCategoryProgressbar);
        llRecyclerBannerProgressbar = view.findViewById(R.id.llRecyclerBannerProgressbar);
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

            ApiImplementer.GetSections(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY,userCityName, CommonUtil.COMP_ID, new Callback<GetSectionPojo>() {
                @Override
                public void onResponse(Call<GetSectionPojo> call, Response<GetSectionPojo> response) {

                    if (userCityName != null && !userCityName.equals("")) {
                        GetItemsForDashboard(userCityName);
                    }


                    try {
                        if (response.isSuccessful() && response.body() != null
                        ) {

                            llCategoryProgressbar.setVisibility(View.GONE);
                            rvTopCategories.setVisibility(View.VISIBLE);


                            //GetCategoryForDashboardPojo getCategoryForDashboardPojo = response.body();
                            GetSectionPojo getSectionPojo = response.body();
                            if (getSectionPojo != null && getSectionPojo.getRecords().size() > 0) {

                                TopCategoriesAdapter topCategoriesAdapter = new TopCategoriesAdapter(getActivity(),  getSectionPojo,new TopCategoriesAdapter.IOnCategoryClicked() {
                                    @Override
                                    public void getItemDetailsByCatgory(GetSectionPojo getSectionPojo1, int position) {
                                        Intent itemDetailsIntent = new Intent(getActivity(), SectionsubCategoryActivity.class);
                                        itemDetailsIntent.putExtra("sectionId", getSectionPojo.getRecords().get(position).getId() + "");
                                        itemDetailsIntent.putExtra("sectionName", getSectionPojo.getRecords().get(position).getCsmSectionName() + "");
                                       // itemDetailsIntent.putExtra("catName", getCategoryForDashboardPojo1.getRECORDS().get(position).getCatName() + "");
                                        getActivity().startActivity(itemDetailsIntent);

                                    }
                                });

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
                public void onFailure(Call<GetSectionPojo> call, Throwable t) {

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

            ApiImplementer.GetItemsForDashboardImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.SHOP_ID, userCityName, CommonUtil.COMP_ID, new Callback<GetItemsForDashboardPojo>() {
                @Override
                public void onResponse(Call<GetItemsForDashboardPojo> call, Response<GetItemsForDashboardPojo> response) {

                    String url = response.raw().request().url() + "";
                    GetItemsPhotoForDashboardApp();

                    try {
                        if (response.isSuccessful() && response.body() != null


                        ) {

                            llPopularProgressbar.setVisibility(View.GONE);
                            rvPopularItems.setVisibility(View.VISIBLE);


                            GetItemsForDashboardPojo getItemsForDashboardPojo = response.body();
                            if (getItemsForDashboardPojo != null && getItemsForDashboardPojo.getRecords().size() > 0) {

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


    private void getAllCityApiCall() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(getActivity(), "");
            }
        });

        if (connectionDetector.isConnectingToInternet()) {
            ApiImplementer.GetAllCityImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, "0", new Callback<GetAllCityPojo>() {
                @Override
                public void onResponse(Call<GetAllCityPojo> call, Response<GetAllCityPojo> response) {
                    DialogUtil.hideProgressDialog();

                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            GetAllCityPojo getAllCityPojo = response.body();
                            cityList = new ArrayList<>();
                            cityList.add("City");
                            if (getAllCityPojo != null && getAllCityPojo.getRECORDS().size() > 0) {

                                for (int i = 0; i < getAllCityPojo.getRECORDS().size(); i++) {

                                    cityList.add(getAllCityPojo.getRECORDS().get(i).getNAME());
                                }

                                cityListDialog(cityList);


                            } else {
                                Toast.makeText(getActivity(), getAllCityPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Error in response" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<GetAllCityPojo> call, Throwable t) {

                    DialogUtil.hideProgressDialog();
                    Toast.makeText(getActivity(), "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


    public void cityListDialog(ArrayList<String> cityList) {

        cityListDialog = new Dialog(getActivity());
        cityListDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog

        cityListDialog.setCancelable(false);
        View customProgressDialog = LayoutInflater.from(getActivity()).inflate(R.layout.custom_layout_for_city_list_dialog, null);

        MaterialButton btnOk = customProgressDialog.findViewById(R.id.btnOk);
        SearchableSpinner spCity = customProgressDialog.findViewById(R.id.spCity);


        ArrayAdapter<String> cityListAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_common_layout, cityList);
        cityListAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spCity.setTitle("Select City");
        spCity.setAdapter(cityListAdapter);

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userCityName = cityList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityListDialog.dismiss();
                GetItemsForDashboard(userCityName);
            }
        });


        cityListDialog.setContentView(customProgressDialog);
        cityListDialog.show();

    }


    private void GetItemsPhotoForDashboardApp() {
        if (connectionDetector.isConnectingToInternet()) {
            ApiImplementer.getItemsForDashboardAppImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, new Callback<GetItemsPhotoForDashboardAppPojo>() {
                @Override
                public void onResponse(Call<GetItemsPhotoForDashboardAppPojo> call, Response<GetItemsPhotoForDashboardAppPojo> response) {

                    dashboardImagesList = new ArrayList<>();
                    llRecyclerBannerProgressbar.setVisibility(View.GONE);
                    recyclerViewPagerStudentSideBanner.setVisibility(View.VISIBLE);

                    try {
                        if (response.isSuccessful() && response.body() != null) {

                            GetItemsPhotoForDashboardAppPojo getItemsPhotoForDashboardAppPojo = response.body();

                            for (int i = 0; i < getItemsPhotoForDashboardAppPojo.getRECORDS().size(); i++) {
                                dashboardImagesList.add(getItemsPhotoForDashboardAppPojo.getRECORDS().get(i).getItmUrl());
                            }

                            System.out.println(dashboardImagesList);


                            if (getItemsPhotoForDashboardAppPojo != null && getItemsPhotoForDashboardAppPojo.getRECORDS().size() > 0) {
                                recyclerViewPagerStudentSideBanner.addItem(new PagerModel(R.drawable.dummy_img_1, "", activity));
                                recyclerViewPagerStudentSideBanner.addItem(new PagerModel(R.drawable.dummy_img_2, "", activity));
                                recyclerViewPagerStudentSideBanner.addItem(new PagerModel(R.drawable.dummy_img_1, "", activity));
                                recyclerViewPagerStudentSideBanner.addItem(new PagerModel(R.drawable.dummy_img_4, "", activity));

                                recyclerViewPagerStudentSideBanner.start();


                            } else {
                                Toast.makeText(getActivity(), getItemsPhotoForDashboardAppPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                            }


                        }


                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetItemsPhotoForDashboardAppPojo> call, Throwable t) {
                    llRecyclerBannerProgressbar.setVisibility(View.GONE);
                    Toast.makeText(activity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        } else {

            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_CITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    userCityName = data.getStringExtra("selectedCityName");
                    GetCategoryForDashboardApiCall();
                   // GetItemsForDashboard(userCityName);
                } catch (Exception e) {

                    Toast.makeText(getActivity(), "Error in getting city Name" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

        }
    }
}