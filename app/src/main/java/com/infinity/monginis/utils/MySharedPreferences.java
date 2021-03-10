package com.infinity.monginis.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String PREFERENCES_FILE_NAME = "monginis_preferences_data";

    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setFCMToken(String fcmToken) {
        editor.putString(PreferencesConstants.FCM_TOKEN, fcmToken);
        editor.apply();
    }

    public String getFCMToken() {
        return sharedPreferences.getString(PreferencesConstants.FCM_TOKEN, "");
    }

    public String getAndroidID() {
        return sharedPreferences.getString(PreferencesConstants.ANDROID_ID, "");
    }

    public void setAndroidId(String androidID) {
        editor.putString(PreferencesConstants.ANDROID_ID, androidID);
        editor.apply();
    }


    public void setDeviceId(String deviceId) {
        editor.putString(PreferencesConstants.DEVICE_ID, deviceId);
        editor.apply();
    }

    public String getDeviceID() {
        return sharedPreferences.getString(PreferencesConstants.DEVICE_ID, "0");
    }

    public String getVersionCode() {
        return sharedPreferences.getString(PreferencesConstants.VERSION_CODE, "");
    }

    public void setVersionCode(String versionCode) {
        editor.putString(PreferencesConstants.VERSION_CODE, versionCode);
        editor.apply();
    }

    public String getVersionName() {
        return sharedPreferences.getString(PreferencesConstants.VERSION_NAME, "");
    }

    public void setVersionName(String versionName) {
        editor.putString(PreferencesConstants.VERSION_NAME, versionName);
        editor.apply();
    }


}
