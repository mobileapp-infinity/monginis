package com.infinity.monginis.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.infinity.monginis.BuildConfig;
import com.infinity.monginis.Map.MapActivity;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.dashboard.pojo.GetVersionInfoPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivSplashLogo;
    private Dialog updateDialog;
    private ProgressDialog pDialog;
    private PackageInfo pInfo = null;
    String android_id, versionname;
    int versioncode;

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

    private void redirectToMap(Location latlng) {
        Intent intent = new Intent(SplashActivity.this, MapActivity.class);
        intent.putExtra(IntentConstants.USER_CURRENT_STREET_NAME, userCurrentStreetName);
        intent.putExtra(IntentConstants.USER_CURRENT_ADDRESS, userCurrentAddress);
        intent.putExtra(IntentConstants.USER_CURRENT_CITY_NAME, userCurrentCityName);
        intent.putExtra(IntentConstants.USER_CURRENT_LATITUDE, latlng.getLatitude());
        intent.putExtra(IntentConstants.USER_CURRENT_LONGITUDE, latlng.getLongitude());
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
        try {
            android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionname = pInfo.versionName;
            versioncode = pInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        mySharedPreferences.setAndroidId(android_id);
        mySharedPreferences.setDeviceId("0");
        mySharedPreferences.setVersionCode(versioncode + "");
        mySharedPreferences.setVersionName(versionname + "");


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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(SplashActivity.this, "");
            }
        });

        ApiImplementer.getVersionInfoApiImplementer(mySharedPreferences.getAndroidID(), mySharedPreferences.getVersionName(), mySharedPreferences.getVersionCode(), "0", ApiUrls.TESTING_KEY, new Callback<GetVersionInfoPojo>() {
            @Override
            public void onResponse(Call<GetVersionInfoPojo> call, Response<GetVersionInfoPojo> response) {

                DialogUtil.hideProgressDialog();
                try {
                    if (response.isSuccessful() && response.body() != null) {

                        GetVersionInfoPojo getVersionInfoPojo = response.body();

                        if (getVersionInfoPojo != null && versioncode < getVersionInfoPojo.getRECORDS().get(0).getVersionCode() && !CommonUtil.checkIsEmptyOrNullCommon(getVersionInfoPojo.getRECORDS().get(0).getApkUrl())) {

                            updateDialog(getVersionInfoPojo);
                        } else {
                            fetchLocation();
                        }

                    }

                } catch (Exception e) {


                    Toast.makeText(SplashActivity.this, "Error in response" + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<GetVersionInfoPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(SplashActivity.this, "Request Failed" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fetchLocation() {
        if (CommonUtil.checkIsGPSEnabled(SplashActivity.this)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtil.showProgressDialogNotCancelable(SplashActivity.this, "Fetching Location...");

                    locationTrackerNew = new LocationTrackerNew(SplashActivity.this, new LocationTrackerNew.IGetUserCurrentLocation() {
                        @Override
                        public void fetchCurrentLocation(String cityName, Address address, Location lastKnowLocation) {
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

                                redirectToMap(lastKnowLocation);
                               // redirectToDashboard();
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

    private void updateDialog(GetVersionInfoPojo getVersionInfoPojo) {
        updateDialog = new Dialog(SplashActivity.this);
        updateDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog

        updateDialog.setCancelable(false);
        View customProgressDialog = LayoutInflater.from(SplashActivity.this).inflate(R.layout.custom_layout_for_update_app_dialog, null);
        TextViewMediumFont tvNoThanks = customProgressDialog.findViewById(R.id.tvNoThanks);
        Button btnUpdate = customProgressDialog.findViewById(R.id.btnUpdate);
        if (getVersionInfoPojo.getRECORDS().get(0).getUpdateSeverity().equals("2")) {
            tvNoThanks.setVisibility(View.GONE);
        } else {
            tvNoThanks.setVisibility(View.VISIBLE);
        }
        tvNoThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateDialog.dismiss();
                fetchLocation();

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
                UpdateApp atualizaApp = new UpdateApp();
                atualizaApp.setContext(SplashActivity.this);
                atualizaApp.execute(getVersionInfoPojo.getRECORDS().get(0).getApkUrl());
            }
        });


        updateDialog.setContentView(customProgressDialog);
        updateDialog.show();

    }


    private class UpdateApp extends AsyncTask<String, String, String> {
        private Context context;

        void setContext(Context contextf) {
            this.context = contextf;
        }

        @Override
        protected void onPreExecute() {

            try {
                pDialog = new ProgressDialog(context);
                pDialog.setMessage("Downloading Update");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception ignored) {
            }

        }

        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            try {
                pDialog.setProgress(Integer.parseInt(progress[0]));
            } catch (Exception ex) {
            }

        }

        @Override
        protected String doInBackground(String... arg0) {
            int count;
            try {
                URL url = new URL(arg0[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 64000);

                // Output stream to write file

                String root = Environment.getExternalStorageDirectory().toString();

                File myDir = new File(root + "/infinity/Monginis/latest/app/");

                myDir.mkdirs();


                String filename = "Monginis.apk";
                File file = new File(myDir, filename);

                OutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Toast.makeText(SplashActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            try {
                pDialog.dismiss();
            } catch (Exception ignored) {
            }

//            String filepath = Environment.getExternalStorageDirectory().toString() + "/infinity/etrack/latest/app/etrack.apk";
//            String filepath = Environment.getExternalStorageDirectory().toString() + "/infinity/davat/latest/app/etrack.apk";
            String filepath = Environment.getExternalStorageDirectory().toString() + "/infinity/Monginis/latest/app/Monginis.apk";

            File toInstall = new File(filepath);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    Uri apkUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", toInstall);
                    Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                    intent.setData(apkUri);
                    List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        context.grantUriPermission(packageName, apkUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception ex) {
                    //Log.e("error",ex.getMessage());
                }
            } else {
                try {
                    Uri apkUri = Uri.fromFile(toInstall);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception ex) {
                }
            }
        }
    }


}