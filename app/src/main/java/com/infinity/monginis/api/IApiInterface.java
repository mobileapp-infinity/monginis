package com.infinity.monginis.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
import com.infinity.monginis.dashboard.pojo.SavePartialOrderDetailPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.login.Pojo.CheckLoginOTPPojo;
import com.infinity.monginis.login.Pojo.CheckOTPVerifyPojo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("Get_special_item_by_cat_id")
    Call<ItemDetailsByCategoryPojo> GetSpecialItemByCatId(@Query("app_version") String app_version,
                                                          @Query("android_id") String android_id,
                                                          @Query("device_id") String device_id,
                                                          @Query("user_id") String user_id,
                                                          @Query("key") String key,
                                                          @Query("comp_id") String comp_id,
                                                          @Query("cat_id") String cat_id,
                                                          @Query("cust_id") String cust_id

    );


    @GET("Get_Shop_List_For_Item_Stock")
    Call<GetShopListForItemStockPojo> getShopListForItemStock(@Query("app_version") String app_version,
                                                              @Query("android_id") String android_id,
                                                              @Query("device_id") String device_id,
                                                              @Query("key") String key,
                                                              @Query("item_id") String item_id);

    @GET("Check_Login_OTP")
    Call<CheckLoginOTPPojo> checkLoginOTP(@Query("app_version") String app_version,
                                          @Query("android_id") String android_id,
                                          @Query("device_id") String device_id,
                                          @Query("user_id") String user_id,
                                          @Query("key") String key,
                                          @Query("mobile_no") String mobile_no);

    @GET("Check_OTP_Verify")
    Call<CheckOTPVerifyPojo> CheckOTPVerify(@Query("app_version") String app_version,
                                            @Query("android_id") String android_id,
                                            @Query("device_id") String device_id,
                                            @Query("user_id") String user_id,
                                            @Query("key") String key,
                                            @Query("otp") String otp,
                                            @Query("mobile_no") String mobile_no);


    @GET("get_schedule")
    Call<GetSchedulePojo> getSchedule(@Query("app_version") String app_version,
                                      @Query("android_id") String android_id,
                                      @Query("device_id") String device_id,
                                      @Query("user_id") String user_id,
                                      @Query("key") String key,
                                      @Query("comp_id") String comp_id,
                                      @Query("cust_id") String cust_id,
                                      @Query("delv_date") String delv_date,
                                      @Query("itm_id") String itm_id);



    @Multipart
    @POST("save_special_order_partial")
    Call<SavePartialOrderDetailPojo>saveSpecialOrderPartial(@Part("app_version") RequestBody app_version, @Part("android_id") RequestBody android_id, @Part("device_id") RequestBody device_id, @Part("user_id") RequestBody user_id, @Part("key") RequestBody key, @Part("comp_id") RequestBody comp_id, @Part("cust_id")RequestBody cust_id, @Part("occasion_id")RequestBody occasion_id,
                                                            @Part("message")RequestBody message,
                                                            @Part("delv_date")RequestBody delv_date,
                                                            @Part("spe_intr")RequestBody spe_intr,
                                                            @Part("occasion_name")RequestBody occasion_name,
                                                            @Part("price")RequestBody price,
                                                            @Part("weight")RequestBody weight,
                                                            @Part("qty")RequestBody qty,
                                                            @Part("cgst_per")RequestBody cgst_per,
                                                            @Part("sgst_per")RequestBody sgst_per,
                                                            @Part("total_addons_price")RequestBody total_addons_price,
                                                            @Part("schedule_id")RequestBody schedule_id,
                                                            @Part MultipartBody.Part file,
                                                            @Part("json_item_detail")RequestBody json_item_detail,
                                                            @Part("json_addonse_item_details")RequestBody json_addonse_item_details);


    @GET("Get_Pos_Items_Stock")
    Call<GetItmePosStockPojo> GetPosItemsStock(@Query("app_version") String app_version,
                                               @Query("android_id") String android_id,
                                               @Query("device_id") String device_id,
                                               @Query("user_id") String user_id,
                                               @Query("key") String key,
                                               @Query("shop_id") String shop_id,
                                               @Query("d_time") String d_time,
                                               @Query("comp_id") String comp_id
    );

    @GET("get_addons_items_list")
    Call<Get_Addons_Items_List_Pojo> getAddonsItemsList(@Query("app_version") String app_version,
                                                        @Query("android_id") String android_id,
                                                        @Query("device_id") String device_id,
                                                        @Query("user_id") String user_id,
                                                        @Query("key") String key,
                                                        @Query("comp_id") String comp_id,
                                                        @Query("cust_id") String cust_id
    );


}
