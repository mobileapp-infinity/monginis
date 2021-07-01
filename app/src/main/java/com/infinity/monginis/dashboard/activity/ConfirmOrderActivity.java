package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.manageAddress.AddressActivity;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton rbNow, rbSchedule;
    TextViewRegularFont tvApplyCoupon;
    TextViewRegularFont tvSelectAddress;
    TextViewRegularFont tvPaymentMethod;
    ImageView ivBack;
    LinearLayout llSelectPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();

        rbNow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    rbSchedule.setChecked(false);
                } else {
                    rbSchedule.setChecked(true);
                }
            }
        });

        rbSchedule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    rbNow.setChecked(false);
                } else {
                    rbNow.setChecked(true);
                }

            }
        });

    }


    private void initView() {

        rbNow = (RadioButton) findViewById(R.id.rbNow);
        rbSchedule = findViewById(R.id.rbSchedule);
        //  tvApplyCoupon = findViewById(R.id.tvApplyCoupon);
        tvSelectAddress = findViewById(R.id.tvSelectAddress);
        llSelectPayment = findViewById(R.id.llSelectPayment);
        tvSelectAddress.setOnClickListener(this);
        llSelectPayment.setOnClickListener(this);
        //  tvPaymentMethod = findViewById(R.id.tvPaymentMethod);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {
            onBackPressed();
        } else if (view.getId() == R.id.tvSelectAddress) {
            Intent addressIntent = new Intent(this, AddressActivity.class);
            startActivity(addressIntent);
        } else if (view.getId() == R.id.llSelectPayment) {
            Intent paymentIntent = new Intent(this, PaymentOptionsActivity.class);
            startActivity(paymentIntent);
        }

    }
}