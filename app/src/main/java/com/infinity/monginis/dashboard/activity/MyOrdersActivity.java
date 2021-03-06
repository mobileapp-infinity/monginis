package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.infinity.monginis.R;

public class MyOrdersActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivBack;
    Button btnTackOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        initView();
    }


    private void initView() {
        ivBack = findViewById(R.id.ivBack);
        btnTackOrder = findViewById(R.id.btnTackOrder);
        btnTackOrder.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {
            onBackPressed();
        } else if (view.getId() == R.id.btnTackOrder) {
            Intent tackOrderIntent = new Intent(MyOrdersActivity.this, TackOrderActivity.class);
            startActivity(tackOrderIntent);
        }

    }
}