package com.infinity.monginis.dashboard.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiClient;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.api.IApiInterface;
import com.infinity.monginis.dashboard.activity.CustomizeScreenActivity;
import com.infinity.monginis.dashboard.activity.PaymentOptionsActivity;
import com.infinity.monginis.dashboard.adapter.CartScreenAdapter;
import com.infinity.monginis.dashboard.adapter.CustomizeItemAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.model.CartItemModel;
import com.infinity.monginis.dashboard.pojo.SavePartialOrderPojo;
import com.infinity.monginis.login.SplashActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;

public class CartFragment extends Fragment implements View.OnClickListener {

    private static CartFragment cartFragment = null;
    ViewPager vpCustomizeScreen;
    private AppCompatImageView ivBack;
    TabLayout tb_layout;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};
    RecyclerView rvCustomizeItemList;
    ConstraintLayout csMain;
    public Timer timer;
    Intent intent;
    CustomizeItemAdapter customizeItemAdapter;
    private IApiInterface apiInterface;
    private LinearLayout llNoDataFoundCartItems;
    private MySharedPreferences mySharedPreferences;
    HashMap<String, ArrayList<CartItemModel>> testHashMap2;
    ArrayList<CartItemModel> cartItemModelArrayList;
    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment getCartFragment() {
        if (cartFragment == null) {
            cartFragment = new CartFragment();
        }
        return cartFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView(view);
        return view;
    }


    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(getActivity());
        apiInterface = ApiClient.getClient().create(IApiInterface.class);
        //if (CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo()))
        vpCustomizeScreen = view.findViewById(R.id.vpCustomizeScreen);
        rvCustomizeItemList = view.findViewById(R.id.rvCustomizeItemList);
        csMain = view.findViewById(R.id.csMain);
        ivBack = view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        intent = getActivity().getIntent();
        llNoDataFoundCartItems = view.findViewById(R.id.llNoDataFoundCartItems);
        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())) {

            Gson gson = new Gson();
            String stringFromsharedPref = mySharedPreferences.getUserWiseCartItems();
            if (!CommonUtil.checkIsEmptyOrNullCommon(stringFromsharedPref)) {
                llNoDataFoundCartItems.setVisibility(View.GONE);
                java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<CartItemModel>>>() {
                }.getType();
                testHashMap2  = gson.fromJson(stringFromsharedPref, type);
                cartItemModelArrayList = testHashMap2.get(mySharedPreferences.getUserMobileNo());
                rvCustomizeItemList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                customizeItemAdapter = new CustomizeItemAdapter(getActivity(), cartItemModelArrayList, new CustomizeItemAdapter.IOnOrderPlaced() {
                    @Override
                    public void onOrderPlaced(ArrayList<CartItemModel> cartItemModelArrayList, int position) {

                        save_special_order_partial(cartItemModelArrayList, position, cartItemModelArrayList.get(position).getMenuId(),
                                cartItemModelArrayList.get(position).getYourMessage(),
                                cartItemModelArrayList.get(position).getDeliverydate(),
                                cartItemModelArrayList.get(position).getYourInstruction(),
                                cartItemModelArrayList.get(position).getMenu(),
                                "20.0",
                                cartItemModelArrayList.get(position).getWeight(),
                                cartItemModelArrayList.get(position).getQty(),
                                "0.0",
                                "0.0",
                                cartItemModelArrayList.get(position).getTotalAddsOnPrice(),
                                cartItemModelArrayList.get(position).getScheduleId(),
                                cartItemModelArrayList.get(position).getSelectedItemArray(),
                                cartItemModelArrayList.get(position).getSelectedAddsOnArray(),
                                cartItemModelArrayList.get(position).getSpecialCakePhoto());

                    }
                });
                rvCustomizeItemList.setAdapter(customizeItemAdapter);
            } else {
                llNoDataFoundCartItems.setVisibility(View.VISIBLE);
            }

        }


        imageSliderAdapter = new ImageSliderAdapter(getActivity(), images);
        vpCustomizeScreen.setAdapter(imageSliderAdapter);
        timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1200, 1800);
        tb_layout = view.findViewById(R.id.tb_layout);
        tb_layout.setupWithViewPager(vpCustomizeScreen);
        Bundle bundle = intent.getExtras();
        ArrayList<CartItemModel> cartItemModel = (ArrayList<CartItemModel>) bundle.getSerializable("cartItemModel");
        //
        // rvCustomizeItemList.setAdapter(customizeItemAdapter);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ivBack) {
            getActivity().onBackPressed();
        }

    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }


    private void save_special_order_partial(ArrayList<CartItemModel> cartItemModelArrayList, int position, String occasion_id, String message, String delv_date, String spe_intr, String occasion_name, String price, String weight, String qty, String cgst_per, String sgst_per, String total_addons_price, String schedule_id, String special_item_array, String addon_array, MultipartBody.Part specialCakePhoto
    ) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(getActivity(), "");
            }
        });

        RequestBody AppVersionCode = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getVersionCode()));
        RequestBody AppAndroidId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getAndroidID()));
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
        RequestBody req_json_addonse_item_details = RequestBody.create(MediaType.parse("text/plain"), addon_array.toString());

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
                null,
                req_json_item_detail,
                req_json_addonse_item_details

        );
        call.enqueue(new Callback<SavePartialOrderPojo>() {
            @Override
            public void onResponse(Call<SavePartialOrderPojo> call, Response<SavePartialOrderPojo> response) {
                DialogUtil.hideProgressDialog();
                try {


                    if (response.isSuccessful() && response.body() != null) {
                        SavePartialOrderPojo savePartialOrderPojo = response.body();

                        if (savePartialOrderPojo != null && savePartialOrderPojo.getFLAG() == 1) {

                            cartItemModelArrayList.remove(position);
                            testHashMap2.put(mySharedPreferences.getUserMobileNo(), cartItemModelArrayList);
                            mySharedPreferences.setUserWiseCartItems(testHashMap2);
                            Toast.makeText(getActivity(), savePartialOrderPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                            customizeItemAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {

                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SavePartialOrderPojo> call, Throwable t) {

            }
        });


    }
}