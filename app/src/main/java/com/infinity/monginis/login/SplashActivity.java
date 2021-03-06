package com.infinity.monginis.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

public class SplashActivity extends AppCompatActivity {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivSplashLogo;

    private final String[] RunTimePerMissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA};

    private final String[] BACKGROUND_LOCATION_RUN_TIME_PERMISSION = {Manifest.permission.ACCESS_BACKGROUND_LOCATION};
    private static final int MY_PERMISSIONS_REQUEST_READ_WRITE_STATE = 100;
    private static final int REQUEST_CODE_BACKGROUND_LOCATION_PERMISSION_FOR_ANDROID_Q = 1002;

    LocationTrackerNew locationTrackerNew;
    String userCurrentStreetName;
    String userCurrentAddress;
    String userCurrentCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        setFcmToken();
        loadSplashScreenAnimationAndAskForPermission();
    }

    private void redirectToDashboard() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        intent.putExtra(IntentConstants.USER_CURRENT_STREET_NAME, userCurrentStreetName);
        intent.putExtra(IntentConstants.USER_CURRENT_ADDRESS, userCurrentAddress);
        intent.putExtra(IntentConstants.USER_CURRENT_CITY_NAME, userCurrentCityName);
        startActivity(intent);
        finish();
    }

    private void setFcmToken() {
        try {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SplashActivity.this, new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String fcmToken = instanceIdResult.getToken();
                    if (fcmToken != null &&
                            !fcmToken.isEmpty()) {
                        mySharedPreferences.setFCMToken(fcmToken);
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(SplashActivity.this);
        ivSplashLogo = findViewById(R.id.ivSplashLogo);
    }

    private void loadSplashScreenAnimationAndAskForPermission() {
        Animation slide_up = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.slide_up);
        ivSplashLogo.startAnimation(slide_up);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!hasPermissions(SplashActivity.this, RunTimePerMissions)) {
                            ActivityCompat.requestPermissions(SplashActivity.this, RunTimePerMissions, MY_PERMISSIONS_REQUEST_READ_WRITE_STATE);
                        } else {
                            permissionForBackgroundLocationAndroidQ();
                        }
                    } else {
                        checkVersionInfoApiCall();
                    }
                }
            }
        };
        timer.start();
    }


    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasPermissionsForBackgroundLocation(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                    return false;
                }
            }
        }
        return true;
    }

    private void permissionForBackgroundLocationAndroidQ() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!hasPermissionsForBackgroundLocation(SplashActivity.this, BACKGROUND_LOCATION_RUN_TIME_PERMISSION)) {
                ActivityCompat.requestPermissions(SplashActivity.this, BACKGROUND_LOCATION_RUN_TIME_PERMISSION,
                        REQUEST_CODE_BACKGROUND_LOCATION_PERMISSION_FOR_ANDROID_Q);
            } else {
                checkVersionInfoApiCall();
            }
        } else {
            checkVersionInfoApiCall();
        }
    }

    private void alertAlert(String msg) {
        new MaterialAlertDialogBuilder(SplashActivity.this)
                .setTitle(Html.fromHtml("<b>" + getResources().getString(R.string.permission_request) + " </b>"))
                .setMessage(msg + " So Reopen the app and grant permission for well uses of app")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_WRITE_STATE) {
            if (grantResults.length == 5 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                permissionForBackgroundLocationAndroidQ();
            } else {
                alertAlert(getResources().getString(R.string.permissions_has_not_grant));
            }
        } else if (requestCode == REQUEST_CODE_BACKGROUND_LOCATION_PERMISSION_FOR_ANDROID_Q) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkVersionInfoApiCall();
            } else {
                alertAlert(getResources().getString(R.string.permissions_has_not_grant));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_ENABLE_GPS) {
            if (CommonUtil.checkIsGPSEnabled(SplashActivity.this)) {
                checkVersionInfoApiCall();
            } else {
                DialogUtil.showGPSNotEnabledDialog(SplashActivity.this);
            }
        }
    }

    private void checkVersionInfoApiCall() {
        if (CommonUtil.checkIsGPSEnabled(SplashActivity.this)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtil.showProgressDialogNotCancelable(SplashActivity.this, "Fetching Location...");

                    locationTrackerNew = new LocationTrackerNew(SplashActivity.this, new LocationTrackerNew.IGetUserCurrentLocation() {
                        @Override
                        public void fetchCurrentLocation(String cityName, Address address) {
                            DialogUtil.hideProgressDialog();
                            if (!CommonUtil.checkIsEmptyOrNullCommon(cityName)) {
                                userCurrentCityName = cityName;
                                if (!CommonUtil.checkIsEmptyOrNullCommon(address.getAddressLine(0))) {
                                    userCurrentAddress = address.getAddressLine(0);
                                }
                                if (!CommonUtil.checkIsEmptyOrNullCommon(address.getThoroughfare())) {
                                    userCurrentStreetName = address.getThoroughfare();
                                } else if (!CommonUtil.checkIsEmptyOrNullCommon(address.getSubThoroughfare())) {
                                    userCurrentStreetName = address.getSubThoroughfare();
                                }
                                redirectToDashboard();
                            } else {
                                DialogUtil.showCityNotFoundDialog(SplashActivity.this);
                            }
                        }
                    });
                }
            });
        } else {
            DialogUtil.showGPSNotEnabledDialog(SplashActivity.this);
        }
    }
}