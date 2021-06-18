package com.infinity.monginis.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.infinity.monginis.dashboard.model.CartItemModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void setUserMobileNo(String mobileNo) {
        editor.putString(PreferencesConstants.MOBILE_NO, mobileNo);
        editor.apply();
    }

    public String getUserMobileNo() {
        return sharedPreferences.getString(PreferencesConstants.MOBILE_NO, "");
    }


    public void setFavouriteItems(HashMap<String, ArrayList<String>> testHashMap) {

        //  ArrayList arrayList = new ArrayList();
        //  testHashMap = new HashMap<String, ArrayList<String>>();
        // testHashMap.put("key1", arrayList);
        // testHashMap.put("key2", "value2");

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(testHashMap);
        editor.putString(PreferencesConstants.SELECTED_FAVOURITE_ITEMS, hashMapString);
        editor.apply();

    }

    public String getFavouriteItems() {
        return sharedPreferences.getString(PreferencesConstants.SELECTED_FAVOURITE_ITEMS, "");
    }


    public void setUserWiseCartItems(HashMap<String, ArrayList<CartItemModel>> cartHashMap) {

        Gson gson = new Gson();
        String hashMapString = gson.toJson(cartHashMap);
        editor.putString(PreferencesConstants.SELECTED_CART_ITEMS, hashMapString);
        editor.apply();
    }

    public String getUserWiseCartItems() {
        return sharedPreferences.getString(PreferencesConstants.SELECTED_CART_ITEMS, "");
    }

    public void setSelectredAddsonArray(JSONArray jsonArray) {
        editor.putString(PreferencesConstants.ADDS_ON_ARRAY, jsonArray.toString());
        editor.apply();
    }

    public String getSelecteItemJsonArray() {
        return sharedPreferences.getString(PreferencesConstants.ADDS_ON_ARRAY, "");
    }

    public void setSelectedSpecilaItemJson(JSONArray jsonArray){
        editor.putString(PreferencesConstants.SPECIAL_SELECTED_ITEM_JSON, jsonArray.toString());
        editor.apply();

    }

    public String getSelectedJsonObject() {
        return sharedPreferences.getString(PreferencesConstants.SPECIAL_SELECTED_ITEM_JSON, "");
    }

    public void setSelectedItemId(String itemId){
        editor.putString(PreferencesConstants.ITEM_ID, itemId);
        editor.apply();

    }

    public String getSelectedItemId() {
        return sharedPreferences.getString(PreferencesConstants.ITEM_ID, "");
    }




}
