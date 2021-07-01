package com.infinity.monginis.api;

import com.infinity.monginis.CategoryItemsDetails.Pojo.ItemDetailsByCategoryPojo;
import com.infinity.monginis.ShopForItemActiivty.pojo.GetShopListForItemStockPojo;
import com.infinity.monginis.ShopForItemActiivty.pojo.ShopLikeDislikePojo;
import com.infinity.monginis.dashboard.pojo.AddAddressResponsePojo;
import com.infinity.monginis.dashboard.pojo.ConfrimOrderReponsePojo;
import com.infinity.monginis.dashboard.pojo.DeleteAddressPojo;
import com.infinity.monginis.dashboard.pojo.GetAllCityPojo;
import com.infinity.monginis.dashboard.pojo.GetAllShopPojo;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetFlavoursPojo;
import com.infinity.monginis.dashboard.pojo.GetItemLikeDislikePojo;
import com.infinity.monginis.dashboard.pojo.GetItemWeightPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsPhotoForDashboardAppPojo;
import com.infinity.monginis.dashboard.pojo.GetItmePosStockPojo;
import com.infinity.monginis.dashboard.pojo.GetOccasionPojo;
import com.infinity.monginis.dashboard.pojo.GetSchedulePojo;
import com.infinity.monginis.dashboard.pojo.GetSectionPojo;
import com.infinity.monginis.dashboard.pojo.GetUserByMobileNoPojo;
import com.infinity.monginis.dashboard.pojo.GetVersionInfoPojo;
import com.infinity.monginis.dashboard.pojo.Get_Addons_Items_List_Pojo;
import com.infinity.monginis.dashboard.pojo.ItemLikeDisLikePojo;
import com.infinity.monginis.dashboard.pojo.ItemMrpByFlavourAndWeightPojo;
import com.infinity.monginis.dashboard.pojo.SavePartialOrderPojo;
import com.infinity.monginis.dashboard.pojo.SaveSpecialOrderconfirmPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.dashboard.pojo.SubSectionPojo;
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

    @GET("Get_Sections")
    Call<GetSectionPojo> GetSections(@Query("app_version") String app_version,
                                     @Query("android_id") String android_id,
                                     @Query("device_id") String device_id,
                                     @Query("user_id") String user_id,
                                     @Query("key") String key,
                                     @Query("city_name") String city_name,
                                     @Query("comp_id") String comp_id);

    @GET("Get_category_by_section")
    Call<SubSectionPojo> GetCategoryBySection(@Query("app_version") String app_version,
                                              @Query("android_id") String android_id,
                                              @Query("device_id") String device_id,
                                              @Query("user_id") String user_id,
                                              @Query("key") String key,
                                              @Query("section_id") String section_id,
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
                                      @Query("comp_id") String comp_id,
                                      @Query("item_id") String item_id);

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

    @GET("Get_Items_Detail_By_Item_Id")
    Call<ItemDetailsByCategoryPojo> GetItemsDetailByItemIdImplementer(@Query("app_version") String app_version,
                                                                      @Query("android_id") String android_id,
                                                                      @Query("device_id") String device_id,
                                                                      @Query("user_id") String user_id,
                                                                      @Query("key") String key,
                                                                      @Query("comp_id") String comp_id,
                                                                      @Query("item_id") String item_id

    );

    //Get_Items_Detail_By_Item_Id


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
    Call<SavePartialOrderPojo> saveSpecialOrderPartial(@Part("app_version") RequestBody app_version, @Part("android_id") RequestBody android_id, @Part("device_id") RequestBody device_id, @Part("user_id") RequestBody user_id, @Part("key") RequestBody key, @Part("comp_id") RequestBody comp_id, @Part("cust_id") RequestBody cust_id, @Part("occasion_id") RequestBody occasion_id,
                                                       @Part("message") RequestBody message,
                                                       @Part("delv_date") RequestBody delv_date,
                                                       @Part("spe_intr") RequestBody spe_intr,
                                                       @Part("occasion_name") RequestBody occasion_name,
                                                       @Part("price") RequestBody price,
                                                       @Part("weight") RequestBody weight,
                                                       @Part("qty") RequestBody qty,
                                                       @Part("cgst_per") RequestBody cgst_per,
                                                       @Part("sgst_per") RequestBody sgst_per,
                                                       @Part("total_addons_price") RequestBody total_addons_price,
                                                       @Part("schedule_id") RequestBody schedule_id,
                                                       @Part MultipartBody.Part file,
                                                       @Part("json_item_detail") RequestBody json_item_detail,
                                                       @Part("json_addonse_item_details") RequestBody json_addonse_item_details);


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

    @GET("Get_All_Shop")
    Call<GetAllShopPojo> getAllShop(@Query("app_version") String app_version,
                                    @Query("android_id") String android_id,
                                    @Query("device_id") String device_id,
                                    @Query("user_id") String user_id,
                                    @Query("key") String key,
                                    @Query("comp_id") String comp_id

    );

    @GET("Get_item_mrp_by_weight_and_flavour_for_new")
    Call<ItemMrpByFlavourAndWeightPojo> Get_item_mrp_by_weight_and_flavour_for_new(@Query("app_version") String app_version,
                                                                                   @Query("android_id") String android_id,
                                                                                   @Query("device_id") String device_id,
                                                                                   @Query("user_id") String user_id,
                                                                                   @Query("key") String key,
                                                                                   @Query("comp_id") String comp_id,
                                                                                   @Query("item_id") String item_id,
                                                                                   @Query("state_name") String state_name,
                                                                                   @Query("city_name") String city_name,
                                                                                   @Query("hsn_code") String hsn_code,
                                                                                   @Query("Flavour") String Flavour


    );

    @GET("Get_partial_order_detail")
    Call<ConfrimOrderReponsePojo> Get_partial_order_detail(@Query("app_version") String app_version,
                                                           @Query("android_id") String android_id,
                                                           @Query("device_id") String device_id,
                                                           @Query("user_id") String user_id,
                                                           @Query("key") String key,
                                                           @Query("comp_id") String comp_id,
                                                           @Query("id") String item_id


    );


    @Multipart
    @POST("Save_special_order_confirm")
    Call<SaveSpecialOrderconfirmPojo> Save_special_order_confirm(@Part("app_version") RequestBody app_version, @Part("android_id") RequestBody android_id, @Part("device_id") RequestBody device_id, @Part("user_id") RequestBody user_id, @Part("key") RequestBody key, @Part("comp_id") RequestBody comp_id, @Part("cust_id") RequestBody cust_id, @Part("id") RequestBody id,
                                                                 @Part("order_for") RequestBody order_for,
                                                                 @Part("order_by") RequestBody order_by,
                                                                 @Part("mobile_no") RequestBody mobile_no,
                                                                 @Part("adv_amt") RequestBody adv_amt,
                                                                 @Part("remaining_amt") RequestBody remaining_amt,
                                                                 @Part("mrp") RequestBody mrp,
                                                                 @Part("cgst_per") RequestBody cgst_per,
                                                                 @Part("sgst_per") RequestBody sgst_per,
                                                                 @Part("total_addons_price") RequestBody total_addons_price,
                                                                 @Part("schedule_id") RequestBody schedule_id,
                                                                 @Part MultipartBody.Part file,
                                                                 @Part("order_id") RequestBody order_id,
                                                                 @Part("anniversery_date") RequestBody anniversery_date,
                                                                 @Part("date_of_birth") RequestBody date_of_birth,
                                                                 @Part("pick_up_date") RequestBody pick_up_date,
                                                                 @Part("pick_up_time") RequestBody pick_up_time


    );

    @POST("Insert_User_address_detail")
    Call<AddAddressResponsePojo> Insert_User_address_detail(@Query("app_version") String app_version,
                                                            @Query("android_id") String android_id,
                                                            @Query("device_id") String device_id,
                                                            @Query("user_id") String user_id,
                                                            @Query("key") String key,
                                                            @Query("address") String address,
                                                            @Query("order_id") String order_id,
                                                            @Query("comp_id") String comp_id,
                                                            @Query("mobile_no") String mobile_no,
                                                            @Query("id") String item_id


    );

    //Get_user_by_mobile_no
    @GET("Get_user_by_mobile_no")
    Call<GetUserByMobileNoPojo> Get_user_by_mobile_no(@Query("app_version") String app_version,
                                                      @Query("android_id") String android_id,
                                                      @Query("device_id") String device_id,
                                                      @Query("comp_id") String comp_id,
                                                      @Query("user_id") String user_id,
                                                      @Query("key") String key,
                                                      @Query("mobile_no") String mobile_no


    );
    //Delete_user_address_detail_by_id
    @GET("Delete_user_address_detail_by_id")
    Call<DeleteAddressPojo> Delete_user_address_detail_by_id(@Query("app_version") String app_version,
                                                             @Query("android_id") String android_id,
                                                             @Query("device_id") String device_id,
                                                             @Query("comp_id") String comp_id,
                                                             @Query("user_id") String user_id,
                                                             @Query("key") String key,
                                                             @Query("id") String id


    );

    @GET("Insert_Update_item_like")
    Call<ItemLikeDisLikePojo> itmeLikeDislike(@Query("app_version") String app_version,
                                              @Query("android_id") String android_id,
                                              @Query("device_id") String device_id,
                                              @Query("comp_id") String comp_id,
                                              @Query("user_id") String user_id,
                                              @Query("key") String key,
                                              @Query("mobile_no") String mobile_no,
                                              @Query("item_id") String item_id,
                                              @Query("like_dislike_flag") String like_dislike_flag,
                                              @Query("is_item_already_like") String is_item_already_like


    );




    @GET("Insert_Update_Shop_like")
    Call<ItemLikeDisLikePojo> InsertUpdateShopLike(@Query("app_version") String app_version,
                                              @Query("android_id") String android_id,
                                              @Query("device_id") String device_id,
                                              @Query("comp_id") String comp_id,
                                              @Query("user_id") String user_id,
                                              @Query("key") String key,
                                              @Query("mobile_no") String mobile_no,
                                              @Query("shop_id") String shop_id,
                                              @Query("liker_dislike_flag") String liker_dislike_flag,
                                              @Query("is_shop_already_like") String is_shop_already_like


    );

    //Get_item_like

    @GET("Get_item_like")
    Call<GetItemLikeDislikePojo> Get_item_like(@Query("app_version") String app_version,
                                               @Query("android_id") String android_id,
                                               @Query("device_id") String device_id,
                                               @Query("user_id") String user_id,
                                               @Query("key") String key,
                                               @Query("mobile_no") String mobile_no



    );


//Get_shop_like

    @GET("Get_shop_like")
    Call<ShopLikeDislikePojo> Get_shop_like(@Query("app_version") String app_version,
                                            @Query("android_id") String android_id,
                                            @Query("device_id") String device_id,
                                            @Query("user_id") String user_id,
                                            @Query("key") String key,
                                            @Query("mobile_no") String mobile_no




    );



}
