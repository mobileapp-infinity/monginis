package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.adapter.AddressAdapter;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView rvAddressesList;
    private Button btnAddNewAddress;
    private AppCompatImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_acttivity);
        initView();
    }


    private void initView() {

        rvAddressesList = findViewById(R.id.rvAddressesList);
        ivBack = findViewById(R.id.ivBack);

        btnAddNewAddress = findViewById(R.id.btnAddNewAddress);
        btnAddNewAddress.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        rvAddressesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AddressAdapter addressAdapter = new AddressAdapter(AddressActivity.this);
        rvAddressesList.setAdapter(addressAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddNewAddress) {

        } else if (view.getId() == R.id.ivBack) {
            onBackPressed();
        }
    }
}