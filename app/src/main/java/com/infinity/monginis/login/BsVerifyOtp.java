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
import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
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
import com.infinity.monginis.dashboard.adapter.DashboardViewPagerAdapter;
import com.infinity.monginis.dashboard.fragments.CartFragment;
import com.infinity.monginis.dashboard.fragments.ExploreFragment;
import com.infinity.monginis.dashboard.fragments.ProfileFragment;
import com.infinity.monginis.dashboard.fragments.SearchFragment;
import com.infinity.monginis.login.Pojo.CheckOTPVerifyPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.MySharedPreferences;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;
import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;
import static com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter.isFromTopCategories;

public class BsVerifyOtp extends BottomSheetDialogFragment implements View.OnClickListener {

    private Activity activity;
    private CartActivity cartActivity;
    private ItemDetailsActivity itemDetailsActivity;
    private DashboardActivity dashboardActivity;
    private CategoryItemsDetailsActivity categoryItemsDetailsActivity;
    private ConnectionDetector connectionDetector;

    private MaterialCardView cvVerifyOTP;
    private AppCompatImageView imgCloseOTPDialog;
    private String mobileNo;
    private OtpView otpView;
    private MySharedPreferences mySharedPreferences;
    private TextViewRegularFont tvMobileNo;
    private boolean isFromProfile = false;
    private boolean isFromCart = false;
    private boolean isFromSpecialOrder = false;
    private boolean isFromFavrt = false;
    private boolean isfromItemDetails = false;

    public BsVerifyOtp(Activity activity, String mobileNo) {
        this.activity = activity;
        this.mobileNo = mobileNo;
    }

    public BsVerifyOtp(CartActivity activity, String mobileNo) {
        this.cartActivity = activity;
        this.mobileNo = mobileNo;
    }

    public BsVerifyOtp(ItemDetailsActivity activity, String mobileNo, boolean isfromItemDetails) {
        this.itemDetailsActivity = activity;
        this.mobileNo = mobileNo;
        this.isfromItemDetails = isfromItemDetails;
    }


    public BsVerifyOtp(CategoryItemsDetailsActivity activity, String mobileNo, boolean isFromFavrt) {
        this.categoryItemsDetailsActivity = activity;
        this.mobileNo = mobileNo;
        this.isFromFavrt = isFromFavrt;
    }

    public BsVerifyOtp(DashboardActivity activity, String mobileNo, boolean isFromProfile, boolean isFromSpecialOrder, boolean isFromCart) {
        this.dashboardActivity = activity;
        this.mobileNo = mobileNo;
       this.isFromProfile = isFromProfile;
       this.isFromSpecialOrder = isFromSpecialOrder;
       this.isFromCart = isFromCart;
      // this.isFromFavrt = isFromFavrt;
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
        if (isFromFavrt){
            mySharedPreferences = new MySharedPreferences(categoryItemsDetailsActivity);
            connectionDetector = new ConnectionDetector(categoryItemsDetailsActivity);
        }else if (isfromItemDetails){
            mySharedPreferences = new MySharedPreferences(itemDetailsActivity);
            connectionDetector = new ConnectionDetector(itemDetailsActivity);
        }else {
            mySharedPreferences = new MySharedPreferences(dashboardActivity);
            connectionDetector = new ConnectionDetector(dashboardActivity);
        }

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
                                    mySharedPreferences.setUserMobileNo(mobileNo);

                                    //Toast.makeText(dashboardActivity, checkOTPVerifyPojo.getRecords().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                    dismiss();
                                    if (isFromProfile){
                                        //setUpViewPager();
                                        vpDashboard.setCurrentItem(3);
                                      //  ProfileFragment.getProfileFragment();
                                        //vpDashboard.

                                    }else if(isFromCart){
                                        vpDashboard.setCurrentItem(2);
                                    }else if(isFromSpecialOrder){

                                    }else if (isFromFavrt){

                                        //startActivity(getIn);
                                    }

                                   // Intent customizeScreenIntent = new Intent(dashboardActivity, CustomizeScreenActivity.class);
                                   // startActivity(customizeScreenIntent);
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