package com.infinity.monginis.api;

import com.infinity.monginis.dashboard.pojo.GetAllCityPojo;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetFlavoursPojo;
import com.infinity.monginis.dashboard.pojo.GetItemWeightPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsPhotoForDashboardAppPojo;
import com.infinity.monginis.dashboard.pojo.GetOccasionPojo;
import com.infinity.monginis.dashboard.pojo.GetVersionInfoPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;

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

}
