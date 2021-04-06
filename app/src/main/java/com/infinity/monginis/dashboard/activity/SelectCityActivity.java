package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.dashboard.adapter.SelectCityAdapter;
import com.infinity.monginis.dashboard.pojo.GetAllCityPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCityActivity extends AppCompatActivity implements View.OnClickListener {

    ConnectionDetector connectionDetector;
    MySharedPreferences mySharedPreferences;
    ArrayList<String> cityList;
    private RecyclerView rvSelectCity;
    private LinearLayout llMain, llSelectCityProgressbar, llSelectCity, llNoDataFoundSelectCity;
    AppCompatEditText edtSearchCity;
    private AppCompatImageView imgClearSearch;
    private SelectCityAdapter selectCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        initView();
        getAllCityApiCall();
        edtSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    imgClearSearch.setVisibility(View.VISIBLE);
                } else {
                    imgClearSearch.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                selectCityAdapter.getFilter().filter(s.toString());
            }
        });
    }


    private void initView() {
        connectionDetector = new ConnectionDetector(this);
        mySharedPreferences = new MySharedPreferences(SelectCityActivity.this);
        rvSelectCity = findViewById(R.id.rvSelectCity);
        edtSearchCity = findViewById(R.id.edtSearchCity);
        llMain = findViewById(R.id.llMain);
        llSelectCityProgressbar = findViewById(R.id.llSelectCityProgressbar);
        llNoDataFoundSelectCity = findViewById(R.id.llNoDataFoundSelectCity);
        llSelectCity = findViewById(R.id.llSelectCity);
        rvSelectCity = findViewById(R.id.rvSelectCity);
        imgClearSearch = findViewById(R.id.imgClearSearch);
        imgClearSearch.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectCityActivity.this, LinearLayoutManager.VERTICAL, false);
        rvSelectCity.setLayoutManager(linearLayoutManager);

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.slide_from_bottom_layout);
        rvSelectCity.setLayoutAnimation(controller);


    }

    @Override
    public void onBackPressed() {


        Toast.makeText(SelectCityActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();
    }

    private void getAllCityApiCall() {


        if (connectionDetector.isConnectingToInternet()) {
            llSelectCity.setVisibility(View.GONE);
            llSelectCityProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundSelectCity.setVisibility(View.GONE);
            ApiImplementer.GetAllCityImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, "0", new Callback<GetAllCityPojo>() {
                @Override
                public void onResponse(Call<GetAllCityPojo> call, Response<GetAllCityPojo> response) {

                    llMain.setVisibility(View.VISIBLE);

                    llSelectCity.setVisibility(View.VISIBLE);
                    llSelectCityProgressbar.setVisibility(View.GONE);


                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            GetAllCityPojo getAllCityPojo = response.body();
                            cityList = new ArrayList<>();

                            if (getAllCityPojo != null && getAllCityPojo.getRECORDS().size() > 0) {

                                for (int i = 0; i < getAllCityPojo.getRECORDS().size(); i++) {

                                    cityList.add(getAllCityPojo.getRECORDS().get(i).getNAME());
                                }


                                selectCityAdapter = new SelectCityAdapter(SelectCityActivity.this, cityList, new SelectCityAdapter.IOnItemClickListener() {
                                    @Override
                                    public void onItemClicked(String cityName) {

                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("selectedCityName", cityName);
                                        setResult(Activity.RESULT_OK, returnIntent);
                                        finish();


                                    }
                                });


                                rvSelectCity.setAdapter(selectCityAdapter);


                            } else {
                                llSelectCityProgressbar.setVisibility(View.GONE);
                                llNoDataFoundSelectCity.setVisibility(View.VISIBLE);
                                llSelectCity.setVisibility(View.GONE);
                                llMain.setVisibility(View.GONE);
                                Toast.makeText(SelectCityActivity.this, getAllCityPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    } catch (Exception e) {
                        llMain.setVisibility(View.GONE);
                        llSelectCity.setVisibility(View.GONE);
                        llSelectCityProgressbar.setVisibility(View.GONE);

                        Toast.makeText(SelectCityActivity.this, "Error in response" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<GetAllCityPojo> call, Throwable t) {

                    llSelectCity.setVisibility(View.GONE);
                    llSelectCityProgressbar.setVisibility(View.GONE);
                    llNoDataFoundSelectCity.setVisibility(View.VISIBLE);
                    llMain.setVisibility(View.GONE);
                    Toast.makeText(SelectCityActivity.this, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(SelectCityActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgClearSearch) {
            imgClearSearch.setVisibility(View.GONE);
            edtSearchCity.setText("");
        }
    }
}