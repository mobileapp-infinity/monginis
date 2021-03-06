package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.adapter.CategoryDetailListAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.pojo.CategoryDetailsPojo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;
import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;

public class ItemDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager vpCategoryDetails;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};
    TabLayout tb_layout;
    RecyclerView rvCategoryList;
    AppCompatImageView imgLike;
    private boolean isLiked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initView();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        vpDashboard.setCurrentItem(0);
    }

    private void initView() {
        vpCategoryDetails = findViewById(R.id.vpCategoryDetails);
        imageSliderAdapter = new ImageSliderAdapter(ItemDetailsActivity.this, images);
        vpCategoryDetails.setAdapter(imageSliderAdapter);
        tb_layout = findViewById(R.id.tb_layout);
        tb_layout.setupWithViewPager(vpCategoryDetails);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1200, 1800);
        rvCategoryList = findViewById(R.id.rvCategoryList);
        imgLike = findViewById(R.id.imgLike);
        imgLike.setOnClickListener(this);

        ArrayList<CategoryDetailsPojo> categoryDetailsPojoArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            CategoryDetailsPojo categoryDetailsPojo = new CategoryDetailsPojo();
            categoryDetailsPojoArrayList.add(categoryDetailsPojo);
            categoryDetailsPojoArrayList.add(categoryDetailsPojo);
        }

        rvCategoryList.setAdapter(new CategoryDetailListAdapter(ItemDetailsActivity.this, categoryDetailsPojoArrayList,isFromSpecialOrderItem));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgLike) {
            isLiked = !isLiked;
            if (isLiked) {
                imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_filled));
            } else {
                imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_outline));
            }
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

}