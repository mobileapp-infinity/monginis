package com.infinity.monginis.CategoryItemsDetails.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infinity.monginis.CategoryItemsDetails.Adapter.ItemDetailsByCategoryAdapter;
import com.infinity.monginis.CategoryItemsDetails.Pojo.ItemDetailsByCategoryPojo;
import com.infinity.monginis.R;
import com.infinity.monginis.ShopForItemActiivty.ShopForItemActivity;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;

import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.BottomSheetDialogForSpecialOrder;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryItemsDetailsActivity extends AppCompatActivity implements View.OnClickListener, ItemDetailsByCategoryAdapter.IOnItemClickListener {


    GridView gvItemByCategory;
    MySharedPreferences mySharedPreferences;
    Intent catIdIntent;
    String catId, catName, itemId;
    private boolean isFromPopular = false;
    AppCompatImageView ivBack;
    ConnectionDetector connectionDetector;
    LinearLayout llNoDataFoundItemDetailsByCategory, llItemDetailsByCategoryProgressbar, llItemDetailsByCategory;
    ArrayList saveFavouriteItemList = new ArrayList();
    TextViewRegularFont tvSelectedCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_items_details);

        if (savedInstanceState != null) {
            saveFavouriteItemList = savedInstanceState.getStringArrayList("myArrayList");
        }
        initView();


        if (isFromPopular) {
            GetSpecialItemByCatId(itemId);
        } else {
            GetSpecialItemByCatId(catId);
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putStringArrayList("myArrayList", saveFavouriteItemList);

    }

    private void initView() {
        connectionDetector = new ConnectionDetector(this);
        gvItemByCategory = findViewById(R.id.gvItemByCategory);
        mySharedPreferences = new MySharedPreferences(CategoryItemsDetailsActivity.this);
        catIdIntent = getIntent();
       // catId = catIdIntent.getStringExtra("catId");
        catId = catIdIntent.getStringExtra("id");
        catName = catIdIntent.getStringExtra("name");
        itemId = catIdIntent.getStringExtra("itemId");
        isFromPopular = catIdIntent.getBooleanExtra("isFromPopular", false);
        llNoDataFoundItemDetailsByCategory = findViewById(R.id.llNoDataFoundItemDetailsByCategory);
        tvSelectedCategory = findViewById(R.id.tvSelectedCategory);
        tvSelectedCategory.setText(catName);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        llItemDetailsByCategoryProgressbar = findViewById(R.id.llItemDetailsByCategoryProgressbar);
        llItemDetailsByCategory = findViewById(R.id.llItemDetailsByCategory);

    }


    private void GetSpecialItemByCatId(String catId) {

        if (connectionDetector.isConnectingToInternet()) {

            llItemDetailsByCategoryProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundItemDetailsByCategory.setVisibility(View.GONE);
            ApiImplementer.GetSpecialItemByCatIdImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, catId, CommonUtil.CUST_ID, new Callback<ItemDetailsByCategoryPojo>() {
                @Override
                public void onResponse(Call<ItemDetailsByCategoryPojo> call, Response<ItemDetailsByCategoryPojo> response) {

                    llNoDataFoundItemDetailsByCategory.setVisibility(View.GONE);
                    llItemDetailsByCategoryProgressbar.setVisibility(View.GONE);

                    try {

                        if (response.isSuccessful() && response.body() != null) {

                            ItemDetailsByCategoryPojo itemDetailsByCategoryPojo = response.body();

                            if (itemDetailsByCategoryPojo != null && itemDetailsByCategoryPojo.getRECORDS().size() > 0) {
                                llItemDetailsByCategory.setVisibility(View.VISIBLE);
                                ItemDetailsByCategoryAdapter itemDetailsByCategoryAdapter = new ItemDetailsByCategoryAdapter(CategoryItemsDetailsActivity.this,CategoryItemsDetailsActivity.this, itemDetailsByCategoryPojo, saveFavouriteItemList);
                                gvItemByCategory.setAdapter(itemDetailsByCategoryAdapter);

                            } else {
                                llItemDetailsByCategory.setVisibility(View.GONE);
                                llNoDataFoundItemDetailsByCategory.setVisibility(View.VISIBLE);

                            }


                        } else {
                            Toast.makeText(CategoryItemsDetailsActivity.this, "Error in response", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        Toast.makeText(CategoryItemsDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ItemDetailsByCategoryPojo> call, Throwable t) {
                    llItemDetailsByCategoryProgressbar.setVisibility(View.GONE);
                    Toast.makeText(CategoryItemsDetailsActivity.this, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(CategoryItemsDetailsActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack) {
            onBackPressed();
        }
    }

    @Override
    public void onItemClicked(ItemDetailsByCategoryPojo itemDetailsByCategoryPojo, int position) {





        Intent redirectToShopForItemActivityIntent = new Intent(CategoryItemsDetailsActivity.this, ShopForItemActivity.class);
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_ID, itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_NAME, itemDetailsByCategoryPojo.getRECORDS().get(position).getItmName() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_MRP, itemDetailsByCategoryPojo.getRECORDS().get(position).getMRP() + "");
        startActivity(redirectToShopForItemActivityIntent);

    }

    private void hashmaptest() {
        //create test hashmap
        HashMap<String, String> testHashMap = new HashMap<String, String>();
        testHashMap.put("key1", "value1");
        testHashMap.put("key2", "value2");

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(testHashMap);

        //save in shared prefs
        //  MySharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        //  prefs.edit().putString("hashString", hashMapString).apply();

        //get from shared prefs
        // String storedHashMapString = prefs.getString("hashString", "oopsDintWork");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        // HashMap<String, String> testHashMap2 = gson.fromJson(storedHashMapString, type);

        //use values
        //   String toastString = testHashMap2.get("key1") + " | " + testHashMap2.get("key2");
        //   Toast.makeText(this, toastString, Toast.LENGTH_LONG).show();
    }
}
