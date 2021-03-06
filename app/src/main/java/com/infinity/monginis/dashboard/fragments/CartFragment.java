package com.infinity.monginis.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.activity.CustomizeScreenActivity;
import com.infinity.monginis.dashboard.activity.PaymentOptionsActivity;
import com.infinity.monginis.dashboard.adapter.CartScreenAdapter;
import com.infinity.monginis.dashboard.adapter.CustomizeItemAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;

import java.util.Timer;
import java.util.TimerTask;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;

public class CartFragment extends Fragment implements View.OnClickListener {

    private static CartFragment cartFragment = null;
    ViewPager vpCustomizeScreen;
    TabLayout tb_layout;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};
    RecyclerView rvCustomizeItemList;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment getCartFragment() {
        if (cartFragment == null) {
            cartFragment = new CartFragment();
        }
        return cartFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView(view);
        return view;
    }


    private void initView(View view) {
        vpCustomizeScreen = view.findViewById(R.id.vpCustomizeScreen);
        rvCustomizeItemList = view.findViewById(R.id.rvCustomizeItemList);
        rvCustomizeItemList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        imageSliderAdapter = new ImageSliderAdapter(getActivity(), images);
        vpCustomizeScreen.setAdapter(imageSliderAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1200, 1800);
        tb_layout = view.findViewById(R.id.tb_layout);
        tb_layout.setupWithViewPager(vpCustomizeScreen);
        CustomizeItemAdapter customizeItemAdapter = new CustomizeItemAdapter(getActivity());
        rvCustomizeItemList.setAdapter(customizeItemAdapter);
    }

    @Override
    public void onClick(View view) {

    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
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