package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;

public class MongininsWalletActivity extends AppCompatActivity {

    TextViewRegularFont tvCurrentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monginins_wallet);
        initView();
    }


    private void initView(){
        tvCurrentBalance = findViewById(R.id.tvCurrentBalance);
    }
}