package com.infinity.monginis.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiInterface {

    @GET("Get_all_items")
    Call<SearchCategoryPojo> getSearchCategory(@Query("app_version") String app_version,
                                               @Query("android_id") String android_id,
                                               @Query("device_id") String device_id,
                                               @Query("user_id") String user_id,
                                               @Query("key") String key,
                                               @Query("comp_id") String comp_id);


    @GET("Get_Category_For_Dashboard")
    Call<GetCategoryForDashboardPojo> GetCategoryForDashboard(@Query("app_version") String app_version,
                                                              @Query("android_id") String android_id,
                                                              @Query("device_id") String device_id,
                                                              @Query("user_id") String user_id,
                                                              @Query("key") String key,
                                                              @Query("comp_id") String comp_id);

    @GET("Get_Items_For_Dashboard")
    Call<GetItemsForDashboardPojo> GetItemsForDashboard(@Query("app_version") String app_version,
                                                        @Query("android_id") String android_id,
                                                        @Query("device_id") String device_id,
                                                        @Query("user_id") String user_id,
                                                        @Query("key") String key,
                                                        @Query("shop_id") String shop_id,
                                                        @Query("city_name") String city_name,
                                                        @Query("comp_id") String comp_id);

    @GET("Get_flavours")
    Call<GetItemsForDashboardPojo> GetFlavours(@Query("app_version") String app_version,
                                               @Query("android_id") String android_id,
                                               @Query("device_id") String device_id,
                                               @Query("user_id") String user_id,
                                               @Query("key") String key,
                                               @Query("comp_id") String comp_id);


}
