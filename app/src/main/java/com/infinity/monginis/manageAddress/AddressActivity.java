package com.infinity.monginis.manageAddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.manageAddress.adapter.AddressAdapter;
import com.infinity.monginis.dashboard.pojo.DeleteAddressPojo;
import com.infinity.monginis.dashboard.pojo.GetUserByMobileNoPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout llNoDataFound;
    private AppCompatImageView imgAddAddress;
    private RecyclerView rvAddressesList;
    private Button btnAddNewAddress;
    private AppCompatImageView ivBack;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_acttivity);
        initView();
        //getUserByMobileNo();
    }


    private void initView() {

        rvAddressesList = findViewById(R.id.rvAddressesList);
        imgAddAddress = findViewById(R.id.imgAddAddress);
        imgAddAddress.setOnClickListener(this);
        ivBack = findViewById(R.id.ivBack);
        llNoDataFound = findViewById(R.id.llNoDataFound);
        mySharedPreferences = new MySharedPreferences(this);

       // btnAddNewAddress.setOnClickListener(this);
        ivBack.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgAddAddress){
            Intent intent = new Intent(this,AddNewaddressActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.ivBack){

            onBackPressed();

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        getUserByMobileNo();
    }
    AddressAdapter addressAdapter;
    private void getUserByMobileNo(){

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(AddressActivity.this, "");
            }
        });

        ApiImplementer.getUserMobileNoImplmenter(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.COMP_ID, CommonUtil.USER_ID, ApiUrls.TESTING_KEY, "9898574748", new Callback<GetUserByMobileNoPojo>() {
            @Override
            public void onResponse(Call<GetUserByMobileNoPojo> call, Response<GetUserByMobileNoPojo> response) {
                try {
                    System.out.println(call.request().url());
                    DialogUtil.hideProgressDialog();


                    if (response.isSuccessful() && response.body() != null){

                        GetUserByMobileNoPojo getUserByMobileNoPojo =response.body();

                        if (getUserByMobileNoPojo != null && getUserByMobileNoPojo.getRecords().size() > 0){
                            rvAddressesList.setVisibility(View.VISIBLE);
                            llNoDataFound.setVisibility(View.GONE);

                            rvAddressesList.setLayoutManager(new LinearLayoutManager(AddressActivity.this, LinearLayoutManager.VERTICAL, false));
                            addressAdapter = new AddressAdapter(AddressActivity.this, getUserByMobileNoPojo, new AddressAdapter.IOnItemClickes() {
                                @Override
                                public void onViewClicked(AddressAdapter.MyViewHolder holder,GetUserByMobileNoPojo getUserByMobileNoPojo,int pos) {

                                    holder.itemView.findViewById(R.id.ivEdit).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Toast.makeText(AddressActivity.this,"Edit Clicked",Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(AddressActivity.this,AddNewaddressActivity.class);
                                            intent.putExtra("details",getUserByMobileNoPojo);
                                            intent.putExtra("position",pos+"");

                                            startActivity(intent);
                                        }
                                    });

                                    holder.itemView.findViewById(R.id.ivDelete).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(AddressActivity.this,"Delete Clicked",Toast.LENGTH_LONG).show();
                                            deleteAddressById(getUserByMobileNoPojo.getRecords().get(pos).getId()+"");

                                        }
                                    });

                                }
                            });
                            rvAddressesList.setAdapter(addressAdapter);


                        }else{
                            rvAddressesList.setVisibility(View.GONE);
                            llNoDataFound.setVisibility(View.VISIBLE);
                        }

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<GetUserByMobileNoPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                llNoDataFound.setVisibility(View.VISIBLE);
                rvAddressesList.setVisibility(View.GONE);
            }
        });

    }

    private void deleteAddressById(String id){
        ApiImplementer.deleteUserDetailsImplemnter(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.COMP_ID, CommonUtil.USER_ID, ApiUrls.TESTING_KEY, id, new Callback<DeleteAddressPojo>() {
            @Override
            public void onResponse(Call<DeleteAddressPojo> call, Response<DeleteAddressPojo> response) {
                try {
                    if (response.isSuccessful() && response.body() != null){
                        DeleteAddressPojo deleteAddressPojo  =response.body();

                        if (deleteAddressPojo != null && deleteAddressPojo.getTotal() == 1){

                            Toast.makeText(AddressActivity.this,deleteAddressPojo.getMessage(),Toast.LENGTH_LONG).show();
                            getUserByMobileNo();

                        }

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<DeleteAddressPojo> call, Throwable t) {

            }
        });
    }
}