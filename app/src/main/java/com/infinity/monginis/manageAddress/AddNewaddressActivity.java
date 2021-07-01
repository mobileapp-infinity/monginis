package com.infinity.monginis.manageAddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.dashboard.pojo.AddAddressResponsePojo;
import com.infinity.monginis.dashboard.pojo.GetUserByMobileNoPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewaddressActivity extends AppCompatActivity {
    private MySharedPreferences mySharedPreferences;
    private Button btnProceed;
    private  String position = "0";
    private AppCompatEditText edtAddress;
    private AppCompatImageView ivBack;
    private GetUserByMobileNoPojo getUserByMobileNoPojo;
    private boolean isEdit = false;
    private String orderId = "0";
    private String id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_newaddress);
        initView();

    }


    private void initView(){



        edtAddress = findViewById(R.id.edtAddress);
        ivBack = findViewById(R.id.ivBack);
        mySharedPreferences = new MySharedPreferences(AddNewaddressActivity.this);
        btnProceed = findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit){
                    insertAddress(edtAddress.getText().toString(),orderId,"9898574748",id);
                }else{
                    insertAddress(edtAddress.getText().toString(),orderId,"9898574748",id);
                }

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().getSerializableExtra("details") != null){
            getUserByMobileNoPojo = (GetUserByMobileNoPojo) getIntent().getSerializableExtra("details");
            position =   getIntent().getStringExtra("position");
            id =getUserByMobileNoPojo.getRecords().get(Integer.parseInt(position)).getId()+"";
            isEdit  =true;

            if (!CommonUtil.checkIsEmptyOrNullCommon(getUserByMobileNoPojo.getRecords().get(Integer.parseInt(position)).getUdAddress())){
                edtAddress.setText(getUserByMobileNoPojo.getRecords().get(Integer.parseInt(position)).getUdAddress());
                btnProceed.setText("Update");
            }

        }

    }



    private void insertAddress(String address , String orderId, String mobileNo,String id){

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(AddNewaddressActivity.this, "");
            }
        });


        ApiImplementer.inaerUserDetailsImplmenter(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.COMP_ID, CommonUtil.USER_ID, ApiUrls.TESTING_KEY, address,orderId ,mobileNo,id, new Callback<AddAddressResponsePojo>() {
            @Override
            public void onResponse(Call<AddAddressResponsePojo> call, Response<AddAddressResponsePojo> response) {

                DialogUtil.hideProgressDialog();
                try {

                    System.out.println(call.request().url());

                    if (response.isSuccessful() && response.body() != null){

                        AddAddressResponsePojo addAddressResponsePojo = response.body();

                        if (addAddressResponsePojo != null && addAddressResponsePojo.getTotal() == 1){
                            Toast.makeText(AddNewaddressActivity.this,addAddressResponsePojo.getMessage(),Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<AddAddressResponsePojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
            }
        });

    }
}