package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.CategoryDetailListAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.pojo.CategoryDetailsPojo;
import com.infinity.monginis.dashboard.pojo.GetItmePosStockPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;
import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;

public class ItemDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager vpCategoryDetails;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};
    TabLayout tb_layout;
    MySharedPreferences mySharedPreferences;
    RecyclerView rvCategoryList;
    AppCompatImageView imgLike, ivBack;
    private boolean isLiked = false;
    private Intent shopIdIntent;
    String shopId, shopName, shopAddress, CurrentDateTime;
    private LinearLayout llShopItemList;
    private LinearLayout llNoDataFoundPosItem;
    private TextViewMediumFont tvSelectedShopName ;
    private TextViewRegularFont tvSelectedShopAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initView();
        GetPosItemsStock(shopId, CurrentDateTime);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        vpDashboard.setCurrentItem(0);
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(this);
        vpCategoryDetails = findViewById(R.id.vpCategoryDetails);
        shopIdIntent = getIntent();
        tvSelectedShopName = findViewById(R.id.tvSelectedShopName);
        tvSelectedShopAddress = findViewById(R.id.tvSelectedShopAddress);
        llShopItemList = findViewById(R.id.llShopItemList);
        llNoDataFoundPosItem = findViewById(R.id.llNoDataFoundPosItem);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        CurrentDateTime = df.format(c.getTime());
        shopId = shopIdIntent.getStringExtra(IntentConstants.SELECTED_SHOP_ID);
        shopName = shopIdIntent.getStringExtra(IntentConstants.SELECTED_SHOP_NAME);
        shopAddress = shopIdIntent.getStringExtra(IntentConstants.SELECTED_SHOP_ADDRESS);
        tvSelectedShopName.setText(shopName);
        tvSelectedShopAddress.setText(shopAddress);
        imageSliderAdapter = new ImageSliderAdapter(ItemDetailsActivity.this, images);
        vpCategoryDetails.setAdapter(imageSliderAdapter);
        tb_layout = findViewById(R.id.tb_layout);
        tb_layout.setupWithViewPager(vpCategoryDetails);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1200, 1800);
        rvCategoryList = findViewById(R.id.rvCategoryList);
        imgLike = findViewById(R.id.imgLike);
        ivBack = findViewById(R.id.ivBack);
        imgLike.setOnClickListener(this);
        ivBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgLike) {
            isLiked = !isLiked;
            if (isLiked) {

                imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_filled));

                imgLike.startAnimation(AnimationUtils.loadAnimation(ItemDetailsActivity.this, R.anim.favourite_icon_animation));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgLike.clearAnimation();
                    }
                }, 200);

                // imgLike.
                //  Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fad);
                //  imageView.startAnimation(fadeInAnimation);
            } else {
                imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_outline));
                imgLike.startAnimation(AnimationUtils.loadAnimation(ItemDetailsActivity.this, R.anim.favourite_icon_animation));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgLike.clearAnimation();
                    }
                }, 200);
            }
        } else if (v.getId() == R.id.ivBack) {
            onBackPressed();

        }
    }


    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            ItemDetailsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if ((images.length - 1) != vpCategoryDetails.getCurrentItem()) {
                        vpCategoryDetails.setCurrentItem(vpCategoryDetails.getCurrentItem() + 1);
                    } else {
                        vpCategoryDetails.setCurrentItem(0);
                    }
                }
            });
        }
    }


    private void GetPosItemsStock(String shop_id, String d_time) {

        ApiImplementer.GetPosItemsStockImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, shop_id, d_time, CommonUtil.COMP_ID, new Callback<GetItmePosStockPojo>() {
            @Override
            public void onResponse(Call<GetItmePosStockPojo> call, Response<GetItmePosStockPojo> response) {

                llShopItemList.setVisibility(View.VISIBLE);
                llNoDataFoundPosItem.setVisibility(View.GONE);
                try {

                    if (response.isSuccessful() && response.body() != null) {

                        GetItmePosStockPojo getItmePosStockPojo = response.body();

                        if (getItmePosStockPojo != null && getItmePosStockPojo.getRecords().size() > 0) {

                            ArrayList<CategoryDetailsPojo> categoryDetailsPojoArrayList = new ArrayList<>();


                            rvCategoryList.setAdapter(new CategoryDetailListAdapter(ItemDetailsActivity.this, categoryDetailsPojoArrayList, isFromSpecialOrderItem, getItmePosStockPojo));
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(ItemDetailsActivity.this, R.anim.slide_from_bottom_layout);
                            rvCategoryList.setLayoutAnimation(controller);


                        } else {
                            llNoDataFoundPosItem.setVisibility(View.VISIBLE);
                            llShopItemList.setVisibility(View.GONE);
                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(ItemDetailsActivity.this, "Error in response", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GetItmePosStockPojo> call, Throwable t) {
                Toast.makeText(ItemDetailsActivity.this, "Request failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}