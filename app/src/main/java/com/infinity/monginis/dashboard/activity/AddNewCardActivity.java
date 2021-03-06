package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

import com.infinity.monginis.R;

public class AddNewCardActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
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