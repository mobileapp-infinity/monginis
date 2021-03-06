package com.infinity.monginis.api;

import com.infinity.monginis.CategoryItemsDetails.Pojo.ItemDetailsByCategoryPojo;
import com.infinity.monginis.ShopForItemActiivty.pojo.GetShopListForItemStockPojo;
import com.infinity.monginis.ShopForItemActiivty.pojo.ShopLikeDislikePojo;
import com.infinity.monginis.dashboard.pojo.AddAddressResponsePojo;
import com.infinity.monginis.confrimOrder.pojo.GetPartialOrderDetailReponsePojo;
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
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.dashboard.pojo.SubSectionPojo;
import com.infinity.monginis.login.Pojo.CheckLoginOTPPojo;
import com.infinity.monginis.login.Pojo.CheckOTPVerifyPojo;

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

    //Changing api to category dashboard to Get_Sections on 10-6-20201
    public static void GetCategoryForDashboardImplementer(String app_version, String android_id, String device_id,
                                                          String user_id, String key, String comp_id,
                                                          Callback<GetCategoryForDashboardPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetCategoryForDashboardPojo> call = apiService.GetCategoryForDashboard(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);
    }
    //Changing api to category dashboard to Get_Sections on 10-6-20201
    public static void GetSections(String app_version, String android_id, String device_id,
                                                          String user_id, String key,String cityName, String comp_id,
                                                          Callback<GetSectionPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetSectionPojo> call = apiService.GetSections(app_version, android_id, device_id, user_id, key,cityName ,comp_id);
        call.enqueue(cb);
    }


    public static void GetCategoryBySectionImplmenter(String app_version, String android_id, String device_id,
                                   String user_id, String key,String section_id, String comp_id,
                                   Callback<SubSectionPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<SubSectionPojo> call = apiService.GetCategoryBySection(app_version, android_id, device_id, user_id, key,section_id ,comp_id);
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
                                              String user_id, String key, String comp_id,String item_id,
                                              Callback<GetFlavoursPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetFlavoursPojo> call = apiService.GetFlavours(app_version, android_id, device_id, user_id, key, comp_id,item_id);
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

    public static void getAddonsItemsListImplementer(String app_version, String android_id,
                                                     String device_id, String user_id, String key, String comp_id, String cust_id,
                                                     Callback<Get_Addons_Items_List_Pojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<Get_Addons_Items_List_Pojo> call = apiService.getAddonsItemsList(app_version, android_id, device_id, user_id, key, comp_id, cust_id);
        call.enqueue(cb);
    }


    public static void getAllShopImplementer(String app_version, String android_id,
                                  String device_id, String user_id, String key, String comp_id, Callback<GetAllShopPojo> cb) {

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetAllShopPojo> call = apiService.getAllShop(app_version, android_id, device_id, user_id, key, comp_id);
        call.enqueue(cb);

    }



    public static void getItemMrpByWeightAndFlavourorNewImplementer(String app_version, String android_id,
                                                                    String device_id, String user_id, String key, String comp_id, String itemId, String state_name, String city_name, String hsn_code, String Flavour, Callback<ItemMrpByFlavourAndWeightPojo> cb ){

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ItemMrpByFlavourAndWeightPojo> call = apiService.Get_item_mrp_by_weight_and_flavour_for_new(app_version, android_id, device_id, user_id, key, comp_id,itemId,state_name,city_name,hsn_code,Flavour);
        call.enqueue(cb);

    }



    public static void getPartialOrderDetails(String app_version, String android_id,
                                              String device_id, String user_id, String key, String comp_id, String id, Callback<GetPartialOrderDetailReponsePojo> cb ){
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetPartialOrderDetailReponsePojo> call = apiService.Get_partial_order_detail(app_version, android_id, device_id, user_id, key, comp_id,id);
        call.enqueue(cb);

    }


    public static void inaerUserDetailsImplmenter(String app_version, String android_id,
                                                  String device_id, String comp_id, String user_id, String key, String address, String orderId, String mobileNo , String id, Callback<AddAddressResponsePojo> cb ){
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<AddAddressResponsePojo> call = apiService.Insert_User_address_detail(app_version, android_id, device_id, user_id, key,address,orderId, comp_id,mobileNo,id);
        call.enqueue(cb);

    }

    //Get_user_by_mobile_no


    public static void getUserMobileNoImplmenter(String app_version, String android_id,
                                                 String device_id, String comp_id, String user_id, String key, String mobileNo , Callback<GetUserByMobileNoPojo> cb ){
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetUserByMobileNoPojo> call = apiService.Get_user_by_mobile_no(app_version, android_id, device_id,comp_id, user_id, key,mobileNo);
        call.enqueue(cb);

    }



    //Delete_user_address_detail_by_id

    public static void deleteUserDetailsImplemnter(String app_version, String android_id,
                                                   String device_id, String comp_id, String user_id, String key, String id , Callback<DeleteAddressPojo> cb ){
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<DeleteAddressPojo> call = apiService.Delete_user_address_detail_by_id(app_version, android_id, device_id,comp_id, user_id, key,id);
        call.enqueue(cb);

    }


    public static void insertUpdateItemLike(String app_version, String android_id,
                                            String device_id, String comp_id, String user_id, String key, String mobile_no, String item_id, String likeDislike, String is_item_already_like, Callback<ItemLikeDisLikePojo> cb ){
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ItemLikeDisLikePojo> call = apiService.itmeLikeDislike(app_version, android_id, device_id,comp_id, user_id, key,mobile_no,item_id,likeDislike,is_item_already_like);
        call.enqueue(cb);

    }


//Insert_Update_Shop_like


    public static void insertUpdateShopLikeImplementer(String app_version, String android_id,
                                                       String device_id, String comp_id, String user_id, String key, String mobile_no, String shopId, String liker_dislike_flag, String isShopAlreadyLiked, Callback<ItemLikeDisLikePojo> cb ){
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ItemLikeDisLikePojo> call = apiService.InsertUpdateShopLike(app_version, android_id, device_id,comp_id, user_id, key,mobile_no,shopId,liker_dislike_flag,isShopAlreadyLiked);
        call.enqueue(cb);

    }

    public static void getItemLikeImplementer(String appversion, String androidId, String deviceId, String user_id, String key, String mobile_no, Callback<GetItemLikeDislikePojo> cb ){

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<GetItemLikeDislikePojo> call = apiService.Get_item_like(appversion, androidId, deviceId, user_id, key,mobile_no);
        call.enqueue(cb);



    }


    public static void getShopLikeImplementer(String appversion, String androidId, String deviceId, String user_id, String key, String mobile_no, Callback<ShopLikeDislikePojo> cb ){

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ShopLikeDislikePojo> call = apiService.Get_shop_like(appversion, androidId, deviceId, user_id, key,mobile_no);
        call.enqueue(cb);



    }


}
