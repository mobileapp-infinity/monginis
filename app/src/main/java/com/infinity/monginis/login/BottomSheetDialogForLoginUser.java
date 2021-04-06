package com.infinity.monginis.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.dashboard.activity.CartActivity;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.dashboard.activity.ItemDetailsActivity;
import com.infinity.monginis.login.Pojo.CheckLoginOTPPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetDialogForLoginUser extends BottomSheetDialogFragment implements View.OnClickListener {

    private MaterialCardView cvLogin;
    private TextInputLayout tilMobileNumber;
    private TextInputEditText edMobileNumber;
    private LoginActivity activity;
    private DashboardActivity dashboardActivity;
    private CartActivity cartActivity;
    private ItemDetailsActivity itemDetailsActivity;
    private Context context;
    private MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    private ILoginUserDialog iLoginUserDialog;
    private TextViewMediumFont tvSignUp;

    private AppCompatImageView imgCloseLogin;

    public BottomSheetDialogForLoginUser(LoginActivity activity) {
        this.activity = activity;
        this.iLoginUserDialog = (ILoginUserDialog) activity;
    }

    public BottomSheetDialogForLoginUser(CartActivity activity) {
        this.cartActivity = activity;

    }

    public BottomSheetDialogForLoginUser(DashboardActivity activity) {
        this.dashboardActivity = activity;

    }

    public BottomSheetDialogForLoginUser(ItemDetailsActivity activity) {
        this.itemDetailsActivity = activity;

    }

    public BottomSheetDialogForLoginUser(Context context) {
        this.context = context;

    }


    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_login_dialog,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(dashboardActivity);
        connectionDetector = new ConnectionDetector(dashboardActivity);
        cvLogin = view.findViewById(R.id.cvLogin);
        cvLogin.setOnClickListener(this);
        tvSignUp = view.findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);
        tilMobileNumber = view.findViewById(R.id.tilMobileNumber);
        edMobileNumber = view.findViewById(R.id.edMobileNumber);
        imgCloseLogin = view.findViewById(R.id.imgCloseLogin);
        imgCloseLogin.setOnClickListener(this);
        tilMobileNumber.setStartIconDrawable(R.drawable.ic_phone);
        edMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValid(s.toString());
//                if (CommonUtil.checkIsEmptyOrNullCommon(s.toString())) {
//                    tilMobileNumber.setError("Enter mobile number");
//                } else if (s.toString().length() != 10) {
//                    tilMobileNumber.setError("Enter valid mobile number");
//                } else {
//                    tilMobileNumber.setError("");
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isValid(String mobileNo) {
        boolean isValid = true;
        if (CommonUtil.checkIsEmptyOrNullCommon(mobileNo)) {
            tilMobileNumber.setError("Enter mobile number");
            isValid = false;
        } else if (mobileNo.length() != 10) {
            tilMobileNumber.setError("Enter valid mobile number");
            isValid = false;
        } else {
            tilMobileNumber.setError("");
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvLogin) {
            if (isValid(edMobileNumber.getText().toString())) {
                this.dismiss();

                //  checkLoginOTPImplementer(edMobileNumber.getText().toString());

                if (activity != null) {
                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(activity,
                            edMobileNumber.getText().toString());
                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                        bottomSheetDialogForVerifyOTP.show(activity.getSupportFragmentManager(), "verfy_otp");
                    }
                } else if (cartActivity != null) {
                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(cartActivity,
                            edMobileNumber.getText().toString());
                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                        bottomSheetDialogForVerifyOTP.show(cartActivity.getSupportFragmentManager(), "verfy_otp");
                    }
                } else if (itemDetailsActivity != null) {
                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(itemDetailsActivity,
                            edMobileNumber.getText().toString());
                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                        bottomSheetDialogForVerifyOTP.show(itemDetailsActivity.getSupportFragmentManager(), "verfy_otp");
                    }
                } else if (dashboardActivity != null) {
                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(dashboardActivity,
                            edMobileNumber.getText().toString());
                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                        bottomSheetDialogForVerifyOTP.show(dashboardActivity.getSupportFragmentManager(), "verfy_otp");
                    }
                }

//                iLoginUserDialog.onLoginBtnClick(edMobileNumber.getText().toString());
            }
        } else if (v.getId() == R.id.tvSignUp) {
            this.dismiss();
            if (dashboardActivity != null) {
                BottomSheetDialogForRegisterUser bottomSheetDialogForRegisterUser = new BottomSheetDialogForRegisterUser(dashboardActivity);
                if (!bottomSheetDialogForRegisterUser.isAdded()) {
                    bottomSheetDialogForRegisterUser.show(dashboardActivity.getSupportFragmentManager(), "registeruser");
                }
            } else if (cartActivity != null) {
                BottomSheetDialogForRegisterUser bottomSheetDialogForRegisterUser = new BottomSheetDialogForRegisterUser(cartActivity);
                if (!bottomSheetDialogForRegisterUser.isAdded()) {
                    bottomSheetDialogForRegisterUser.show(cartActivity.getSupportFragmentManager(), "registeruser");
                }
            } else if (itemDetailsActivity != null) {
                BottomSheetDialogForRegisterUser bottomSheetDialogForRegisterUser = new BottomSheetDialogForRegisterUser(itemDetailsActivity);
                if (!bottomSheetDialogForRegisterUser.isAdded()) {
                    bottomSheetDialogForRegisterUser.show(itemDetailsActivity.getSupportFragmentManager(), "registeruser");
                }
            }

        } else if (v.getId() == R.id.imgCloseLogin) {
            this.dismiss();
        }
    }


    public interface ILoginUserDialog {
        void onLoginBtnClick(String mobileNo);
    }


    private void checkLoginOTPImplementer(String mobileNo) {
        if (connectionDetector.isConnectingToInternet()) {

            dashboardActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtil.showProgressDialogCancelable(dashboardActivity, "");
                }
            });
            ApiImplementer.checkLoginOTPImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, mobileNo, new Callback<CheckLoginOTPPojo>() {
                @Override
                public void onResponse(Call<CheckLoginOTPPojo> call, Response<CheckLoginOTPPojo> response) {

                    DialogUtil.hideProgressDialog();
                    try {

                        if (response.isSuccessful() && response.body() != null) {

                            CheckLoginOTPPojo checkLoginOTPPojo = response.body();

                            if (checkLoginOTPPojo != null && checkLoginOTPPojo.getRecords().size() > 0) {


                                if (activity != null) {
                                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(activity,
                                            edMobileNumber.getText().toString());
                                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                                        bottomSheetDialogForVerifyOTP.show(activity.getSupportFragmentManager(), "verfy_otp");
                                    }
                                } else if (cartActivity != null) {
                                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(cartActivity,
                                            edMobileNumber.getText().toString());
                                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                                        bottomSheetDialogForVerifyOTP.show(cartActivity.getSupportFragmentManager(), "verfy_otp");
                                    }
                                } else if (itemDetailsActivity != null) {
                                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(itemDetailsActivity,
                                            edMobileNumber.getText().toString());
                                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                                        bottomSheetDialogForVerifyOTP.show(itemDetailsActivity.getSupportFragmentManager(), "verfy_otp");
                                    }
                                } else if (dashboardActivity != null) {
                                    BottomSheetDialogForVerifyOTP bottomSheetDialogForVerifyOTP = new BottomSheetDialogForVerifyOTP(dashboardActivity,
                                            edMobileNumber.getText().toString());
                                    if (!bottomSheetDialogForVerifyOTP.isAdded()) {
                                        bottomSheetDialogForVerifyOTP.setCancelable(false);
                                        bottomSheetDialogForVerifyOTP.show(dashboardActivity.getSupportFragmentManager(), "verfy_otp");
                                    }
                                }


                            } else {
                                Toast.makeText(dashboardActivity, checkLoginOTPPojo.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                    } catch (Exception e) {
                        Toast.makeText(dashboardActivity, "error in response" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CheckLoginOTPPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                }
            });

        } else {
            Toast.makeText(dashboardActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }

}
