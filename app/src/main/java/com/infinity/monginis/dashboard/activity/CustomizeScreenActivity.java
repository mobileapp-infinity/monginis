package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.adapter.CustomizeItemAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.model.CartItemModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CustomizeScreenActivity extends AppCompatActivity {


    ViewPager vpCustomizeScreen;
    TabLayout tb_layout;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};

    private Intent cartItemIntent;
    private RecyclerView rvCustomizeItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_screen);
        initView();
    }

    private void initView() {
        vpCustomizeScreen = findViewById(R.id.vpCustomizeScreen);
        rvCustomizeItemList = findViewById(R.id.rvCustomizeItemList);
        rvCustomizeItemList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageSliderAdapter = new ImageSliderAdapter(CustomizeScreenActivity.this, images);
        vpCustomizeScreen.setAdapter(imageSliderAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CustomizeScreenActivity.MyTimerTask(), 1200, 1800);
        tb_layout = findViewById(R.id.tb_layout);
        Bundle bundle = cartItemIntent.getExtras();
        ArrayList<CartItemModel> cartItemModel = (ArrayList<CartItemModel>) bundle.getSerializable("cartItemModel");
        tb_layout.setupWithViewPager(vpCustomizeScreen);
        CustomizeItemAdapter customizeItemAdapter = new CustomizeItemAdapter(this,cartItemModel);
        rvCustomizeItemList.setAdapter(customizeItemAdapter);
        cartItemIntent = getIntent();

        System.out.println(cartItemModel);
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            CustomizeScreenActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if ((images.length - 1) != vpCustomizeScreen.getCurrentItem()) {
                        vpCustomizeScreen.setCurrentItem(vpCustomizeScreen.getCurrentItem() + 1);
                    } else {
                        vpCustomizeScreen.setCurrentItem(0);
                    }
                }
            });
        }
    }
}