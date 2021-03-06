package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.adapter.MyOrdersListAdapter;

public class MyOrdersScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvMyOrdersList;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_screen);
        initView();
    }

    private void initView() {
        rvMyOrdersList = findViewById(R.id.rvMyOrdersList);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        rvMyOrdersList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MyOrdersListAdapter myOrdersListAdapter = new MyOrdersListAdapter(MyOrdersScreenActivity.this);
        rvMyOrdersList.setAdapter(myOrdersListAdapter);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {
            onBackPressed();
        }

    }
}