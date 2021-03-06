package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.adapter.CartScreenAdapter;
import com.infinity.monginis.login.BottomSheetDialogForLoginUser;
import com.infinity.monginis.login.LoginActivity;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView rvCartList;
    ImageView ivBack;
    Button btnProceed;
    private LinearLayout llDiscountCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);
        initView();

    }

    private void initView() {

        rvCartList = findViewById(R.id.rvCartList);
        btnProceed = findViewById(R.id.btnProceed);
        llDiscountCode = findViewById(R.id.llDiscountCode);
        ivBack = findViewById(R.id.ivBack);

        rvCartList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CartScreenAdapter cartScreenAdapter = new CartScreenAdapter(this);
        rvCartList.setAdapter(cartScreenAdapter);
        BottomSheetDialogForOrderType bottomSheetDialogForOrderType = new BottomSheetDialogForOrderType(CartActivity.this);
        bottomSheetDialogForOrderType.show(CartActivity.this.getSupportFragmentManager(), "");

        ivBack.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        llDiscountCode.setOnClickListener(this);

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

       /* String toastMsg;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                toastMsg = "Normal screen";

                ivBack.setBackgroundResource(R.drawable.ic_baseline_navigate_before_32);
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();*/
    }

    BottomSheetDialogForLoginUser bottomSheetDialogForLoginUser = new BottomSheetDialogForLoginUser(CartActivity.this);

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {
            onBackPressed();
        } else if (view.getId() == R.id.btnProceed) {
           /* if (!bottomSheetDialogForLoginUser.isAdded()) {
                bottomSheetDialogForLoginUser.show(getSupportFragmentManager(), "test");
            }*/

            Intent confirmOrder = new Intent(this, ConfirmOrderActivity.class);
            startActivity(confirmOrder);
        } else if (view.getId() == R.id.llDiscountCode) {
          /*  Intent discountCodeIntent = new Intent(this, DiscountCodeActivity.class);
            startActivity(discountCodeIntent);*/
        }
    }
}