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


}
