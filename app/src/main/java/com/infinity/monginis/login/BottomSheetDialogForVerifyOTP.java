package com.infinity.monginis.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.BottomSheetDialogForCheckOut;
import com.infinity.monginis.dashboard.activity.CartActivity;
import com.infinity.monginis.dashboard.activity.ConfirmOrderActivity;
import com.infinity.monginis.dashboard.activity.CustomizeScreenActivity;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.dashboard.activity.ItemDetailsActivity;
import com.infinity.monginis.login.Pojo.CheckOTPVerifyPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.MySharedPreferences;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;
import static com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter.isFromTopCategories;

public class BottomSheetDialogForVerifyOTP extends BottomSheetDialogFragment implements View.OnClickListener {

    private Activity activity;
    private CartActivity cartActivity;
    private ItemDetailsActivity itemDetailsActivity;
    private DashboardActivity dashboardActivity;
    private ConnectionDetector connectionDetector;

    private MaterialCardView cvVerifyOTP;
    private AppCompatImageView imgCloseOTPDialog;
    private String mobileNo;
    private OtpView otpView;
    private MySharedPreferences mySharedPreferences;
    private TextViewRegularFont tvMobileNo;

    public BottomSheetDialogForVerifyOTP(Activity activity, String mobileNo) {
        this.activity = activity;
        this.mobileNo = mobileNo;
    }

    public BottomSheetDialogForVerifyOTP(CartActivity activity, String mobileNo) {
        this.cartActivity = activity;
        this.mobileNo = mobileNo;
    }

    public BottomSheetDialogForVerifyOTP(ItemDetailsActivity activity, String mobileNo) {
        this.itemDetailsActivity = activity;
        this.mobileNo = mobileNo;
    }

    public BottomSheetDialogForVerifyOTP(DashboardActivity activity, String mobileNo) {
        this.dashboardActivity = activity;
        this.mobileNo = mobileNo;
    }


    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_login_user_otp_dialog,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(dashboardActivity);
        connectionDetector = new ConnectionDetector(dashboardActivity);
        cvVerifyOTP = view.findViewById(R.id.cvVerifyOTP);
        otpView = view.findViewById(R.id.otp_view);

        cvVerifyOTP.setOnClickListener(this);
        imgCloseOTPDialog = view.findViewById(R.id.imgCloseOTPDialog);
        imgCloseOTPDialog.setOnClickListener(this);
        tvMobileNo = view.findViewById(R.id.tvMobileNo);
        tvMobileNo.setText("+91 " + mobileNo);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvVerifyOTP) {

            String otp = otpView.getOTP();
            //if (item)

            checkOTPVerify(otp);
            // BottomSheetDialogForCheckOut bottomSheetDialogForCheckOut = new BottomSheetDialogForCheckOut((DashboardActivity) dashboardActivity);
            // bottomSheetDialogForCheckOut.show(((DashboardActivity) dashboardActivity).getSupportFragmentManager(), "place_order_fragment");



          /*  if (isFromTopCategories) {
                BottomSheetDialogForCheckOut bottomSheetDialogForCheckOut = new BottomSheetDialogForCheckOut((ItemDetailsActivity) itemDetailsActivity);
                bottomSheetDialogForCheckOut.show(((ItemDetailsActivity) itemDetailsActivity).getSupportFragmentManager(), "place_order_fragment");
            } else if (isFromSpecialOrderItem) {

                BottomSheetDialogPlaceOrder bottomSheetDialogPlaceOrder = new BottomSheetDialogPlaceOrder((ItemDetailsActivity) itemDetailsActivity);
                bottomSheetDialogPlaceOrder.show(((ItemDetailsActivity) itemDetailsActivity).getSupportFragmentManager(), "place_order_fragment");
            } else {
                BottomSheetDialogForCheckOut bottomSheetDialogForCheckOut = new BottomSheetDialogForCheckOut((ItemDetailsActivity) itemDetailsActivity);
                bottomSheetDialogForCheckOut.show(((ItemDetailsActivity) itemDetailsActivity).getSupportFragmentManager(), "place_order_fragment");

            }*/


        } else if (v.getId() == R.id.imgCloseOTPDialog) {
            this.dismiss();
        }
    }


    private void checkOTPVerify(String otp) {

        if (connectionDetector.isConnectingToInternet()) {

            ApiImplementer.checkOTPVerifyImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, otp, mobileNo, new Callback<CheckOTPVerifyPojo>() {
                @Override
                public void onResponse(Call<CheckOTPVerifyPojo> call, Response<CheckOTPVerifyPojo> response) {

                    try {

                        if (response.isSuccessful() && response.body() != null) {

                            CheckOTPVerifyPojo checkOTPVerifyPojo = response.body();

                            if (checkOTPVerifyPojo != null && checkOTPVerifyPojo.getRecords().size() > 0) {

                                if (checkOTPVerifyPojo.getRecords().get(0).getFlag() == 1) {
                                    Toast.makeText(dashboardActivity, checkOTPVerifyPojo.getRecords().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                    dismiss();
                                    mySharedPreferences.setUserMobileNo(mobileNo);
                                    Intent customizeScreenIntent = new Intent(dashboardActivity, CustomizeScreenActivity.class);
                                    startActivity(customizeScreenIntent);
                                } else {
                                    Toast.makeText(dashboardActivity, checkOTPVerifyPojo.getRecords().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(dashboardActivity, checkOTPVerifyPojo.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }

                    } catch (Exception e) {
                        Toast.makeText(dashboardActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CheckOTPVerifyPojo> call, Throwable t) {
                    Toast.makeText(dashboardActivity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(dashboardActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

}