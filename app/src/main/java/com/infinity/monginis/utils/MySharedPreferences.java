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
    public String getDelvDate() {
        return sharedPreferences.getString(PreferencesConstants.delv_date, "");
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

    public void setSelectedItemDetails(String delv_date,String selectedFile,String itemName,String  hsn_code,String uom_id,String weight,String price,String cgst_per,String sgst_per,String qty,String mrp,String flavour,String shape,String addsonPrice,String schedule_id,String occassion_id,String occassion_name ,String message,String specialMessage ){
        editor.putString(PreferencesConstants.ITEM_NAME, itemName);
        editor.putString(PreferencesConstants.HSN_CODE, hsn_code);
        editor.putString(PreferencesConstants.uom_id, uom_id);
        editor.putString(PreferencesConstants.price, price);
        editor.putString(PreferencesConstants.weight, weight);
        editor.putString(PreferencesConstants.cgst_per, cgst_per);
        editor.putString(PreferencesConstants.sgst_per, sgst_per);
        editor.putString(PreferencesConstants.qty, qty);
        editor.putString(PreferencesConstants.mrp, mrp);
        editor.putString(PreferencesConstants.flavour, flavour);
        editor.putString(PreferencesConstants.shape, shape);
        editor.putString(PreferencesConstants.occassion_id, occassion_id);
        editor.putString(PreferencesConstants.occassion_name, occassion_name);
        editor.putString(PreferencesConstants.delv_date, delv_date);
        editor.putString(PreferencesConstants.addsonPrice, addsonPrice);
        editor.putString(PreferencesConstants.addsonPrice, addsonPrice);
        editor.putString(PreferencesConstants.message, message);
        editor.putString(PreferencesConstants.specialMessage, specialMessage);
        editor.putString(PreferencesConstants.schedule_id, schedule_id);
        editor.putString(PreferencesConstants.selectedFile, selectedFile);
        editor.apply();

    }

    public String getSelecteItemName() {
        return sharedPreferences.getString(PreferencesConstants.ITEM_NAME, "");
    }

    public String getOccassionName() {
        return sharedPreferences.getString(PreferencesConstants.occassion_name, "");
    }


    public String getSelectedFile() {
        return sharedPreferences.getString(PreferencesConstants.selectedFile, "");
    }


    public String getSelectedItemHsnCode() {
        return sharedPreferences.getString(PreferencesConstants.HSN_CODE, "");
    }
    public String getSelectedItemUomId() {
        return sharedPreferences.getString(PreferencesConstants.uom_id, "");
    }
    public String getSelectedItemPrice() {
        return sharedPreferences.getString(PreferencesConstants.price, "");
    }
    public String getSelectedItemWeight() {
        return sharedPreferences.getString(PreferencesConstants.weight, "");
    }




    public String getSelectedItemCsgstPer() {
        return sharedPreferences.getString(PreferencesConstants.cgst_per, "");
    }

    public String getSelectedItemSgstPrt() {
        return sharedPreferences.getString(PreferencesConstants.sgst_per, "");
    }

    public String getSelectedItemMrp() {
        return sharedPreferences.getString(PreferencesConstants.mrp, "");
    }

    public String getSelectedItemFlavours() {
        return sharedPreferences.getString(PreferencesConstants.flavour, "");
    }

    public String getSelectedItemShape() {
        return sharedPreferences.getString(PreferencesConstants.shape, "");
    }
    public String getSelectedItemOccassionId() {
        return sharedPreferences.getString(PreferencesConstants.occassion_id, "");
    }

    public String getSelectedItemMessage() {
        return sharedPreferences.getString(PreferencesConstants.message, "");
    }
    public String getSelectedItemSpecialMessage() {
        return sharedPreferences.getString(PreferencesConstants.specialMessage, "");
    }
    public String getSelectedScheduleId() {
        return sharedPreferences.getString(PreferencesConstants.schedule_id, "");
    }
    public String getAddsOnPrice() {
        return sharedPreferences.getString(PreferencesConstants.addsonPrice, "");
    }


   /* public void setFavouriteShops(ArrayList<>) {

        //  ArrayList arrayList = new ArrayList();
        //  testHashMap = new HashMap<String, ArrayList<String>>();
        // testHashMap.put("key1", arrayList);
        // testHashMap.put("key2", "value2");

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(testHashMap);
        editor.putString(PreferencesConstants.SELECTED_FAVOURITE_ITEMS, hashMapString);
        editor.apply();

    }*/


    public void setLatLong(Double lat,Double longitude){
        editor.putString(PreferencesConstants.lat, lat.toString());
        editor.putString(PreferencesConstants.longitude, longitude.toString());
        editor.apply();

    }

    public String getLat() {
        return sharedPreferences.getString(PreferencesConstants.lat, "");
    }

    public String getLongitutde() {
        return sharedPreferences.getString(PreferencesConstants.longitude, "");
    }




}
