package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;

public class PaymentOptionsActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton rbMasterCard, rbVisaCard;
    TextViewRegularFont tvAddNewCard;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        initView();

        rbMasterCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()) {
                    rbVisaCard.setChecked(false);
                } else {
                    rbVisaCard.setChecked(true);
                }

                Intent rateThisOrderIntent = new Intent(PaymentOptionsActivity.this, RateThisOrderActivity.class);
                startActivity(rateThisOrderIntent);
            }
        });

        rbVisaCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    rbMasterCard.setChecked(false);
                } else {
                    rbMasterCard.setChecked(true);
                }

               // Intent rateThisOrderIntent = new Intent(PaymentOptionsActivity.this, RateThisOrderActivity.class);
               // startActivity(rateThisOrderIntent);
            }
        });
    }

    private void initView() {
        rbMasterCard = findViewById(R.id.rbMasterCard);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        rbVisaCard = findViewById(R.id.rbVisaCard);
        tvAddNewCard = findViewById(R.id.tvAddNewCard);
        tvAddNewCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {

            onBackPressed();

        } else if (view.getId() == R.id.tvAddNewCard) {
            Intent addNewCardIntent = new Intent(PaymentOptionsActivity.this, AddNewCardActivity.class);
            startActivity(addNewCardIntent);
        }

    }
}