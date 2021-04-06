package com.infinity.monginis.api;

import com.infinity.monginis.CategoryItemsDetails.Pojo.ItemDetailsByCategoryPojo;
import com.infinity.monginis.ShopForItemActiivty.GetShopListForItemStockPojo;
import com.infinity.monginis.dashboard.pojo.GetAllCityPojo;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetFlavoursPojo;
import com.infinity.monginis.dashboard.pojo.GetItemWeightPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsPhotoForDashboardAppPojo;
import com.infinity.monginis.dashboard.pojo.GetItmePosStockPojo;
import com.infinity.monginis.dashboard.pojo.GetOccasionPojo;
import com.infinity.monginis.dashboard.pojo.GetSchedulePojo;
import com.infinity.monginis.dashboard.pojo.GetVersionInfoPojo;
import com.infinity.monginis.dashboard.pojo.Get_Addons_Items_List_Pojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.login.Pojo.CheckLoginOTPPojo;
import com.infinity.monginis.login.Pojo.CheckOTPVerifyPojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiImplementer {

    public static void getSearchCategoryApiImplementer(String app_version, String android_id, String device_id,
                                                       String user_id, String key, String comp_id,
                                                       Callback<SearchCategoryPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<SearchCategoryPojo> call = apiService.getSearchCategory(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }

    public static void GetCategoryForDashboardImplementer(String app_version, String android_id, String device_id,
                                                          String user_id, String key, String comp_id,
                                                          Callback<GetCategoryForDashboardPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetCategoryForDashboardPojo> call = apiService.GetCategoryForDashboard(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }

    public static void GetItemsForDashboardImplementer(String app_version, String android_id, String device_id,
                                                       String user_id, String key, String shop_id, String city_name, String comp_id,
                                                       Callback<GetItemsForDashboardPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetItemsForDashboardPojo> call = apiService.GetItemsForDashboard(app_version, android_id, device_id, user_id, key, shop_id, city_name, comp_id);
        call.enqueue(cb);
    }

    public static void getFlavoursImplementer(String app_version, String android_id, String device_id,
                                              String user_id, String key, String comp_id,
                                              Callback<GetFlavoursPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetFlavoursPojo> call = apiService.GetFlavours(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }

    public static void getOccasionImplementer(String app_version, String android_id, String device_id,
                                              String user_id, String key, String comp_id,
                                              Callback<GetOccasionPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetOccasionPojo> call = apiService.GetOccasion(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }


    public static void GetItemWeightImplementer(String app_version, String android_id, String device_id,
                                                String user_id, String key, String comp_id, String item_id,
                                                Callback<GetItemWeightPojo> cb) {

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetItemWeightPojo> call = apiService.GetItemWeight(app_version, android_id, device_id, user_id, key, comp_id, item_id);
        call.enqueue(cb);

    }


    public static void getVersionInfoApiImplementer(String android_id, String version_name,
                                                    String version_code, String update_severity, String key,
                                                    Callback<GetVersionInfoPojo> cb) {

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetVersionInfoPojo> call = apiService.getLatestVersion(android_id, version_name, version_code, update_severity, key);
        call.enqueue(cb);

    }


    public static void GetAllCityImplementer(String app_version, String android_id,
                                             String device_id, String user_id, String key, String comp_id, String state_id,
                                             Callback<GetAllCityPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetAllCityPojo> call = apiService.GetAllCity(app_version, android_id, device_id, user_id, key, comp_id, state_id);
        call.enqueue(cb);
    }

    public static void getItemsForDashboardAppImplementer(String app_version, String android_id,
                                                          String device_id, String user_id, String key, String comp_id,
                                                          Callback<GetItemsPhotoForDashboardAppPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetItemsPhotoForDashboardAppPojo> call = apiService.GetItemsPhotoForDashboardApp(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }

    public static void GetSpecialItemByCatIdImplementer(String app_version, String android_id,
                                                        String device_id, String user_id, String key, String comp_id, String cat_id, String cust_id,
                                                        Callback<ItemDetailsByCategoryPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ItemDetailsByCategoryPojo> call = apiService.GetSpecialItemByCatId(app_version, android_id, device_id, user_id, key, comp_id, cat_id, cust_id);
        call.enqueue(cb);
    }

    public static void getShopListForItemStockImplementer(String app_version, String android_id,
                                                          String device_id, String key, String item_id,
                                                          Callback<GetShopListForItemStockPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetShopListForItemStockPojo> call = apiService.getShopListForItemStock(app_version, android_id, device_id, key, item_id);
        call.enqueue(cb);
    }

    public static void checkLoginOTPImplementer(String app_version, String android_id,
                                                String device_id, String key, String item_id, String mobile_no,
                                                Callback<CheckLoginOTPPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckLoginOTPPojo> call = apiService.checkLoginOTP(app_version, android_id, device_id, key, item_id, mobile_no);
        call.enqueue(cb);
    }

    public static void checkOTPVerifyImplementer(String app_version, String android_id,
                                                 String device_id, String user_id, String key, String otp, String mobile_no,
                                                 Callback<CheckOTPVerifyPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckOTPVerifyPojo> call = apiService.CheckOTPVerify(app_version, android_id, device_id, user_id, key, otp, mobile_no);
        call.enqueue(cb);
    }

    public static void getScheduleImplementer(String app_version, String android_id,
                                              String device_id, String user_id, String key, String comp_id, String cust_id, String delv_date, String itm_id,
                                              Callback<GetSchedulePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetSchedulePojo> call = apiService.getSchedule(app_version, android_id, device_id, user_id, key, comp_id, cust_id, delv_date, itm_id);
        call.enqueue(cb);
    }


    public static void GetPosItemsStockImplementer(String app_version, String android_id,
                                                   String device_id, String user_id, String key, String shop_id, String d_time, String comp_id,
                                                   Callback<GetItmePosStockPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetItmePosStockPojo> call = apiService.GetPosItemsStock(app_version, android_id, device_id, user_id, key, shop_id, d_time, comp_id);
        call.enqueue(cb);
    }

    public static void getAddonsItemsList(String app_version, String android_id,
                                          String device_id, String user_id, String key, String comp_id, String cust_id,
                                          Callback<Get_Addons_Items_List_Pojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<Get_Addons_Items_List_Pojo> call = apiService.getAddonsItemsList(app_version, android_id, device_id, user_id, key, comp_id, cust_id);
        call.enqueue(cb);
    }


}
