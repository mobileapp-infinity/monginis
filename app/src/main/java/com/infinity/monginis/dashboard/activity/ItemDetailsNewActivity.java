package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
import com.infinity.monginis.R;
import com.infinity.monginis.utils.ConnectionDetector;

public class ItemDetailsNewActivity extends AppCompatActivity {
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_new);
        initView();
        getItemDetailsActivity();
    }


    private void initView(){
        connectionDetector = new ConnectionDetector(this);
    }

    private void getItemDetailsActivity(){

        if (connectionDetector.isConnectingToInternet()) {

        }else{
            Toast.makeText(ItemDetailsNewActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}