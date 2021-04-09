package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiClient;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.api.IApiInterface;
import com.infinity.monginis.dashboard.adapter.CustomizeItemAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.model.CartItemModel;
import com.infinity.monginis.dashboard.pojo.SavePartialOrderPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class CustomizeScreenActivity extends AppCompatActivity {


    ViewPager vpCustomizeScreen;
    TabLayout tb_layout;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};
    private MySharedPreferences mySharedPreferences;
    private IApiInterface apiInterface;
    private Intent cartItemIntent;
    private RecyclerView rvCustomizeItemList;
    JSONArray specialItemArray;
    public MultipartBody.Part specialCakePhoto = null;
    JSONArray specialItemDetailsAddsOnArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_screen);
        initView();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(CustomizeScreenActivity.this);
        apiInterface = ApiClient.getClient().create(IApiInterface.class);
        vpCustomizeScreen = findViewById(R.id.vpCustomizeScreen);
        rvCustomizeItemList = findViewById(R.id.rvCustomizeItemList);
        rvCustomizeItemList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageSliderAdapter = new ImageSliderAdapter(CustomizeScreenActivity.this, images);
        vpCustomizeScreen.setAdapter(imageSliderAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CustomizeScreenActivity.MyTimerTask(), 1200, 1800);
        tb_layout = findViewById(R.id.tb_layout);
        cartItemIntent = getIntent();
        Bundle bundle = cartItemIntent.getExtras();
        ArrayList<CartItemModel> cartItemModel = (ArrayList<CartItemModel>) bundle.getSerializable("cartItemModel");
        tb_layout.setupWithViewPager(vpCustomizeScreen);
        CustomizeItemAdapter customizeItemAdapter = new CustomizeItemAdapter(this, cartItemModel, new CustomizeItemAdapter.IOnOrderPlaced() {
            @Override
            public void onOrderPlaced(ArrayList<CartItemModel> cartItemModelArrayList, int position) {

                saveSpecialOrderPartial(cartItemModelArrayList.get(position).getMenuId()+"",cartItemModelArrayList.get(position).getYourMessage()+"",cartItemModelArrayList.get(position).getDeliverydate()+"",cartItemModelArrayList.get(position).getYourInstruction()+"",cartItemModelArrayList.get(position).getMenu()+"","0",cartItemModelArrayList.get(position).getWeight()+"",cartItemModelArrayList.get(position).getQty()+"","0","0","0",cartItemModelArrayList.get(position).getScheduleId()+"",specialItemDetailsAddsOnArray);

            }
        });
        rvCustomizeItemList.setAdapter(customizeItemAdapter);


        System.out.println(cartItemModel);
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            CustomizeScreenActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if ((images.length - 1) != vpCustomizeScreen.getCurrentItem()) {
                        vpCustomizeScreen.setCurrentItem(vpCustomizeScreen.getCurrentItem() + 1);
                    } else {
                        vpCustomizeScreen.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void saveSpecialOrderPartial(String occasion_id, String message, String delv_date, String spe_intr, String occasion_name, String price, String weight, String qty, String cgst_per, String sgst_per, String total_addons_price, String schedule_id, JSONArray special_item_array) {

        String addon_array = "";
        if (specialItemDetailsAddsOnArray == null) {
            addon_array = specialItemDetailsAddsOnArray + "";

        } else {
            addon_array = specialItemDetailsAddsOnArray.toString();

        }

        CustomizeScreenActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(CustomizeScreenActivity.this, "");
            }
        });


        RequestBody AppVersionCode = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getVersionCode()));
        RequestBody AppAndroidId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getAndroidID()));

//        RequestBody Type_api = RequestBody.create(MediaType.parse("text/plain"), Type);
        RequestBody reg_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getDeviceID()));
        RequestBody reg_user_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(CommonUtil.USER_ID));
        RequestBody req_key = RequestBody.create(MediaType.parse("text/plain"), ApiUrls.TESTING_KEY);
        RequestBody req_company_id = RequestBody.create(MediaType.parse("text/plain"), CommonUtil.COMP_ID);
        RequestBody req_cust_id = RequestBody.create(MediaType.parse("text/plain"), CommonUtil.CUST_ID);
        RequestBody req_occasion_id = RequestBody.create(MediaType.parse("text/plain"), occasion_id);
        RequestBody req_message = RequestBody.create(MediaType.parse("text/plain"), message);
        RequestBody req_delv_date = RequestBody.create(MediaType.parse("text/plain"), delv_date);
        RequestBody req_spe_intr = RequestBody.create(MediaType.parse("text/plain"), spe_intr);

        RequestBody req_occasion_name = RequestBody.create(MediaType.parse("text/plain"), occasion_name);
        RequestBody req_price = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody req_weight = RequestBody.create(MediaType.parse("text/plain"), weight);
        RequestBody req_qty = RequestBody.create(MediaType.parse("text/plain"), qty);
        RequestBody req_cgst_per = RequestBody.create(MediaType.parse("text/plain"), cgst_per);
        RequestBody req_sgst_per = RequestBody.create(MediaType.parse("text/plain"), sgst_per);
        RequestBody req_total_addons_price = RequestBody.create(MediaType.parse("text/plain"), total_addons_price);
        RequestBody req_schedule_id = RequestBody.create(MediaType.parse("text/plain"), schedule_id);
        RequestBody req_json_item_detail = RequestBody.create(MediaType.parse("text/plain"), special_item_array.toString());
        RequestBody req_json_addonse_item_details = RequestBody.create(MediaType.parse("text/plain"), addon_array);


        Call<SavePartialOrderPojo> call = apiInterface.saveSpecialOrderPartial(
                AppVersionCode,
                AppAndroidId,
                reg_id,
                reg_user_id,
                req_key,
                req_company_id,
                req_cust_id,
                req_occasion_id,
                req_message,
                req_delv_date,
                req_spe_intr,
                req_occasion_name,
                req_price,
                req_weight,
                req_qty,
                req_cgst_per,
                req_sgst_per,
                req_total_addons_price,
                req_schedule_id,
                specialCakePhoto,
                req_json_item_detail,
                req_json_addonse_item_details

        );


        call.enqueue(new Callback<SavePartialOrderPojo>() {
            @Override
            public void onResponse(Call<SavePartialOrderPojo> call, retrofit2.Response<SavePartialOrderPojo> response) {
                DialogUtil.hideProgressDialog();


                try {
                    if (response.isSuccessful() && response.body() != null) {


                        SavePartialOrderPojo savePartialOrderPojo = response.body();

                        if (savePartialOrderPojo != null && savePartialOrderPojo.getFLAG() == 1) {
                            // Intent special_order_confirmation_receipt_intent = new Intent(CustomizeScreenActivity.this, Special_Order_Confirmation_Receipt.class);
                            //  special_order_confirmation_receipt_intent.putExtra("id", savePartialOrderPojo.getID() + "");
                            //  special_order_confirmation_receipt_intent.putExtra("total_addons_price", total_addons_price);
                            // startActivity(special_order_confirmation_receipt_intent);
                            //  finish();
                        } else {
                            Toast.makeText(CustomizeScreenActivity.this, savePartialOrderPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                        }


                    }
                } catch (Exception e) {

                    Toast.makeText(CustomizeScreenActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SavePartialOrderPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(CustomizeScreenActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}