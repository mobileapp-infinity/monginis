package com.infinity.monginis.ShopForItemActiivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopForItemActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;

    private Intent intent;
    private AppCompatImageView ivBack;
    private String itemID, itemName, itemMrp;
    private RecyclerView rvShopList;
    private LinearLayout llNoDataFoundShopList, llShopListProgressbar, llShopList;
    private TextViewRegularFont tvItemName, tvItemMrp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_for_item);
        initView();
        getShopListForItemStock(itemID);
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(ShopForItemActivity.this);
        intent = getIntent();
        itemID = intent.getStringExtra(IntentConstants.SELECTED_ITEM_ID);
        itemName = intent.getStringExtra(IntentConstants.SELECTED_ITEM_NAME);
        itemMrp = intent.getStringExtra(IntentConstants.SELECTED_ITEM_MRP);

        tvItemName = findViewById(R.id.tvItemName);
        tvItemMrp = findViewById(R.id.tvItemMrp);
        tvItemName.setText(itemName);
        tvItemMrp.setText(itemMrp);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        llNoDataFoundShopList = findViewById(R.id.llNoDataFoundShopList);
        rvShopList = findViewById(R.id.rvShopList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvShopList.setLayoutManager(linearLayoutManager);
        llShopListProgressbar = findViewById(R.id.llShopListProgressbar);
        llShopList = findViewById(R.id.llShopList);
    }


    private void getShopListForItemStock(String item_id) {


        llShopListProgressbar.setVisibility(View.VISIBLE);
        llNoDataFoundShopList.setVisibility(View.GONE);

        ApiImplementer.getShopListForItemStockImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), ApiUrls.TESTING_KEY, item_id, new Callback<GetShopListForItemStockPojo>() {
            @Override
            public void onResponse(Call<GetShopListForItemStockPojo> call, Response<GetShopListForItemStockPojo> response) {

                llShopListProgressbar.setVisibility(View.GONE);
                llShopList.setVisibility(View.VISIBLE);

                try {

                    if (response.isSuccessful() && response.body() != null) {

                        GetShopListForItemStockPojo getShopListForItemStockPojo = response.body();

                        if (getShopListForItemStockPojo != null && getShopListForItemStockPojo.getRECORDS().size() > 0) {

                            ShopListAdapter shopListAdapter = new ShopListAdapter(ShopForItemActivity.this, getShopListForItemStockPojo);
                            rvShopList.setAdapter(shopListAdapter);

                        } else {
                            llShopList.setVisibility(View.GONE);
                            llNoDataFoundShopList.setVisibility(View.VISIBLE);
                        }


                    }


                } catch (Exception e) {

                    Toast.makeText(ShopForItemActivity.this, "Error in response", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<GetShopListForItemStockPojo> call, Throwable t) {
                llShopListProgressbar.setVisibility(View.GONE);
                Toast.makeText(ShopForItemActivity.this, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ivBack) {
            onBackPressed();

        }
    }
}
