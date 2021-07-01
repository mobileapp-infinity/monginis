package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.SubCategoryAdapter;
import com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetSectionPojo;
import com.infinity.monginis.dashboard.pojo.SubSectionPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SectionsubCategoryActivity extends AppCompatActivity implements SubCategoryAdapter.iOnCategoryClicked{

    private GridView rvSubCategory;
    private ConnectionDetector connectionDetector;
    private MySharedPreferences mySharedPreferences;
    private String secitonId = "",sectionName = "";
    private TextViewRegularFont tvSelectedCategory;
    private ImageView ivBack;

    private LinearLayoutManager linearLayoutManager;
    private AppCompatImageView imgSearch;
    private LinearLayout llNoDataFoundShopList;
    private AppCompatEditText edtItemSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectionsub_category);
        initView();
        setSubCategory();
    }
    
    
    
    private void initView(){
        connectionDetector = new ConnectionDetector(this);
        ivBack = findViewById(R.id.ivBack);
       // edtItemSearch = findViewById(R.id.edtItemSearch);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        secitonId = getIntent().getStringExtra("sectionId");
        sectionName = getIntent().getStringExtra("sectionName");
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvSubCategory = findViewById(R.id.rvSubCategory);
        tvSelectedCategory = findViewById(R.id.tvSelectedCategory);
        tvSelectedCategory.setText(sectionName);
        llNoDataFoundShopList = findViewById(R.id.llNoDataFoundShopList);
     //   rvSubCategory.setLayoutManager(linearLayoutManager);
        mySharedPreferences = new MySharedPreferences(this);


    }


    private void setSubCategory(){

        if (connectionDetector.isConnectingToInternet()) {
            rvSubCategory.setVisibility(View.GONE);
            llNoDataFoundShopList.setVisibility(View.VISIBLE);

            ApiImplementer.GetCategoryBySectionImplmenter(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY,secitonId, CommonUtil.COMP_ID, new Callback<SubSectionPojo>() {
                @Override
                public void onResponse(Call<SubSectionPojo> call, Response<SubSectionPojo> response) {



                    try {
                        if (response.isSuccessful() && response.body() != null
                        ) {

                            llNoDataFoundShopList.setVisibility(View.GONE);
                            rvSubCategory.setVisibility(View.VISIBLE);


                            //GetCategoryForDashboardPojo getCategoryForDashboardPojo = response.body();
                            SubSectionPojo subSectionPojo = response.body();
                            if (subSectionPojo != null && subSectionPojo.getRecords().size() > 0) {

                                SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(SectionsubCategoryActivity.this,subSectionPojo);
                                rvSubCategory.setAdapter(subCategoryAdapter);

                               /* TopCategoriesAdapter topCategoriesAdapter = new TopCategoriesAdapter(getActivity(),  getSectionPojo,new TopCategoriesAdapter.IOnCategoryClicked() {
                                    @Override
                                    public void getItemDetailsByCatgory(GetCategoryForDashboardPojo getCategoryForDashboardPojo1, int position) {
                                        Intent itemDetailsIntent = new Intent(getActivity(), CategoryItemsDetailsActivity.class);
                                        itemDetailsIntent.putExtra("catId", getCategoryForDashboardPojo1.getRECORDS().get(position).getId() + "");
                                        itemDetailsIntent.putExtra("catName", getCategoryForDashboardPojo1.getRECORDS().get(position).getCatName() + "");
                                        getActivity().startActivity(itemDetailsIntent);

                                    }
                                });

                                rvSubCategory.setAdapter(topCategoriesAdapter);*/
                            }


                        } else {


                            llNoDataFoundShopList.setVisibility(View.VISIBLE);
                            rvSubCategory.setVisibility(View.GONE);
                        }

                    } catch (Exception e) {
                        llNoDataFoundShopList.setVisibility(View.GONE);
                        Toast.makeText(SectionsubCategoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<SubSectionPojo> call, Throwable t) {

                    Toast.makeText(SectionsubCategoryActivity.this, "Error in response" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    llNoDataFoundShopList.setVisibility(View.GONE);
                    rvSubCategory.setVisibility(View.VISIBLE);
                }
            });
        } else {
            Toast.makeText(SectionsubCategoryActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

        
    }


    @Override
    public void getItemDetailsBySubCategoryClicked(SubSectionPojo subSectionPojo, int posiiton) {

        System.out.println(subSectionPojo.getRecords().get(posiiton).getIcmCatName());
        Intent intent = new Intent(SectionsubCategoryActivity.this,CategoryItemsDetailsActivity.class);
        intent.putExtra("id",subSectionPojo.getRecords().get(posiiton).getId()+"");
        intent.putExtra("name",subSectionPojo.getRecords().get(posiiton).getIcmCatName()+"");

        startActivity(intent);



    }
}