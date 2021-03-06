package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.infinity.monginis.R;

public class RateThisOrderActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_this_order);
        initView();
    }

    private void initView() {
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {
            onBackPressed();
        }
    }
}