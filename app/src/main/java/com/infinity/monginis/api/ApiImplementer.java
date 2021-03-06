package com.infinity.monginis.api;

import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
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
                                              Callback<GetItemsForDashboardPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetItemsForDashboardPojo> call = apiService.GetFlavours(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }

}
