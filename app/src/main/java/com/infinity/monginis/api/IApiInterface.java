package com.infinity.monginis.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infinity.monginis.dashboard.pojo.GetAllCityPojo;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetFlavoursPojo;
import com.infinity.monginis.dashboard.pojo.GetItemWeightPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsPhotoForDashboardAppPojo;
import com.infinity.monginis.dashboard.pojo.GetOccasionPojo;
import com.infinity.monginis.dashboard.pojo.GetVersionInfoPojo;
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

    @GET("GetLatestVesion")
    Call<GetVersionInfoPojo> getLatestVersion(@Query("android_id") String app_version,
                                              @Query("version_name") String android_id,
                                              @Query("version_code") String device_id,
                                              @Query("update_severity") String user_id,
                                              @Query("key") String key);


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
    Call<GetFlavoursPojo> GetFlavours(@Query("app_version") String app_version,
                                      @Query("android_id") String android_id,
                                      @Query("device_id") String device_id,
                                      @Query("user_id") String user_id,
                                      @Query("key") String key,
                                      @Query("comp_id") String comp_id);

    @GET("Get_occasion")
    Call<GetOccasionPojo> GetOccasion(@Query("app_version") String app_version,
                                      @Query("android_id") String android_id,
                                      @Query("device_id") String device_id,
                                      @Query("user_id") String user_id,
                                      @Query("key") String key,
                                      @Query("comp_id") String comp_id);


    @GET("Get_item_weight")
    Call<GetItemWeightPojo> GetItemWeight(@Query("app_version") String app_version,
                                          @Query("android_id") String android_id,
                                          @Query("device_id") String device_id,
                                          @Query("user_id") String user_id,
                                          @Query("key") String key,
                                          @Query("comp_id") String comp_id,
                                          @Query("item_id") String item_id);

    @GET("Get_All_City")
    Call<GetAllCityPojo> GetAllCity(@Query("app_version") String app_version,
                                    @Query("android_id") String android_id,
                                    @Query("device_id") String device_id,
                                    @Query("user_id") String user_id,
                                    @Query("key") String key,
                                    @Query("comp_id") String comp_id,
                                    @Query("state_id") String state_id
    );


    @GET("GetItemsPhotoForDashboardApp")
    Call<GetItemsPhotoForDashboardAppPojo> GetItemsPhotoForDashboardApp(@Query("app_version") String app_version,
                                                                        @Query("android_id") String android_id,
                                                                        @Query("device_id") String device_id,
                                                                        @Query("user_id") String user_id,
                                                                        @Query("key") String key,
                                                                        @Query("comp_id") String comp_id

    );


}
