package com.infinity.monginis.itemDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.ShopForItemActiivty.pojo.ShopLikeDislikePojo;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.CategoryDetailListAdapter;
import com.infinity.monginis.dashboard.adapter.ImageSliderAdapter;
import com.infinity.monginis.dashboard.fragments.SearchFragment;
import com.infinity.monginis.dashboard.pojo.CategoryDetailsPojo;
import com.infinity.monginis.dashboard.pojo.GetItmePosStockPojo;
import com.infinity.monginis.dashboard.pojo.ItemLikeDisLikePojo;
import com.infinity.monginis.login.BsLogin;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;
import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;
import static com.infinity.monginis.dashboard.fragments.SearchFragment.customerPojoArrayList;
import static com.infinity.monginis.dashboard.fragments.SearchFragment.shopList;

public class ItemDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager vpCategoryDetails;
    ImageSliderAdapter imageSliderAdapter;
    int[] images = {R.drawable.dummy_img_5, R.drawable.dummy_img_2, R.drawable.dummy_img_3, R.drawable.dummy_img_4};
    TabLayout tb_layout;
    MySharedPreferences mySharedPreferences;
    RecyclerView rvCategoryList;
    AppCompatImageView imgLike, ivBack;
    private boolean isLiked = false;
    private Intent shopIdIntent;
    String shopId, shopName, shopAddress, CurrentDateTime;
    private boolean isAlreadyLiked = false;
    private boolean isAvailableInLikedList = false;
    private LinearLayout llShopItemList;
    private LinearLayout llNoDataFoundPosItem;
  // private MySharedPreferences mySharedPreferences;
    private TextViewMediumFont tvSelectedShopName ;
    private TextViewRegularFont tvSelectedShopAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initView();
        GetPosItemsStock(shopId, CurrentDateTime);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        vpDashboard.setCurrentItem(1);
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(this);
        vpCategoryDetails = findViewById(R.id.vpCategoryDetails);
        shopIdIntent = getIntent();
        tvSelectedShopName = findViewById(R.id.tvSelectedShopName);
        tvSelectedShopAddress = findViewById(R.id.tvSelectedShopAddress);
        llShopItemList = findViewById(R.id.llShopItemList);
        llNoDataFoundPosItem = findViewById(R.id.llNoDataFoundPosItem);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        CurrentDateTime = df.format(c.getTime());
        shopId = shopIdIntent.getStringExtra(IntentConstants.SELECTED_SHOP_ID);
        isAlreadyLiked = shopIdIntent.getBooleanExtra("isItemLikedTrue",false);
        isAvailableInLikedList = shopIdIntent.getBooleanExtra("isItemAvailableInLikedList",false);
        shopName = shopIdIntent.getStringExtra(IntentConstants.SELECTED_SHOP_NAME);
        shopAddress = shopIdIntent.getStringExtra(IntentConstants.SELECTED_SHOP_ADDRESS);
        tvSelectedShopName.setText(shopName);
        tvSelectedShopAddress.setText(shopAddress);
        imageSliderAdapter = new ImageSliderAdapter(ItemDetailsActivity.this, images);
        vpCategoryDetails.setAdapter(imageSliderAdapter);
        tb_layout = findViewById(R.id.tb_layout);
        tb_layout.setupWithViewPager(vpCategoryDetails);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1200, 1800);
        rvCategoryList = findViewById(R.id.rvCategoryList);
        imgLike = findViewById(R.id.imgLike);
        ivBack = findViewById(R.id.ivBack);
        imgLike.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        if (isAlreadyLiked){
            isLiked = true;
            imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_filled));

            imgLike.startAnimation(AnimationUtils.loadAnimation(ItemDetailsActivity.this, R.anim.favourite_icon_animation));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgLike.clearAnimation();
                }
            }, 200);
        }

        String str = "Hello this is (monginis)";
        str = str.split("[\\(\\)]")[1];
        System.out.println(str);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgLike) {






            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())){
                isLiked = !isLiked;
                if (isLiked) {

                    imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_filled));

                    imgLike.startAnimation(AnimationUtils.loadAnimation(ItemDetailsActivity.this, R.anim.favourite_icon_animation));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgLike.clearAnimation();
                        }
                    }, 200);
                    if (!isAlreadyLiked && isAvailableInLikedList){
                        shopLikeDislike("9898574748",shopId,"1","1");
                    }else if (!isAlreadyLiked && !isAvailableInLikedList){
                        shopLikeDislike("9898574748",shopId,"1","0");
                    }

                    // imgLike.
                    //  Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fad);
                    //  imageView.startAnimation(fadeInAnimation);
                } else {
                    imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_outline));
                    imgLike.startAnimation(AnimationUtils.loadAnimation(ItemDetailsActivity.this, R.anim.favourite_icon_animation));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgLike.clearAnimation();

                        }
                    }, 200);

                    if (isAlreadyLiked && isAvailableInLikedList){
                        shopLikeDislike("9898574748",shopId,"0","1");
                    }/*else if (!isAlreadyLiked && isAvailableInLikedList){
                        shopLikeDislike("9898574748",shopId,"0","1");
                    }*/

                }
            }else{

                BsLogin bsLogin = new BsLogin(ItemDetailsActivity.this,true);
                if (!bsLogin.isAdded()) {
                    bsLogin.show(this.getSupportFragmentManager(), "test");
                }

            }

        } else if (v.getId() == R.id.ivBack) {
            onBackPressed();

        }
    }


    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            ItemDetailsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if ((images.length - 1) != vpCategoryDetails.getCurrentItem()) {
                        vpCategoryDetails.setCurrentItem(vpCategoryDetails.getCurrentItem() + 1);
                    } else {
                        vpCategoryDetails.setCurrentItem(0);
                    }
                }
            });
        }
    }


    private void GetPosItemsStock(String shop_id, String d_time) {

        ApiImplementer.GetPosItemsStockImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, shop_id, d_time, CommonUtil.COMP_ID, new Callback<GetItmePosStockPojo>() {
            @Override
            public void onResponse(Call<GetItmePosStockPojo> call, Response<GetItmePosStockPojo> response) {

                llShopItemList.setVisibility(View.VISIBLE);
                llNoDataFoundPosItem.setVisibility(View.GONE);
                try {

                    if (response.isSuccessful() && response.body() != null) {

                        GetItmePosStockPojo getItmePosStockPojo = response.body();

                        if (getItmePosStockPojo != null && getItmePosStockPojo.getRecords().size() > 0) {

                            ArrayList<CategoryDetailsPojo> categoryDetailsPojoArrayList = new ArrayList<>();


                            rvCategoryList.setAdapter(new CategoryDetailListAdapter(ItemDetailsActivity.this, categoryDetailsPojoArrayList, isFromSpecialOrderItem, getItmePosStockPojo));
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(ItemDetailsActivity.this, R.anim.slide_from_bottom_layout);
                            rvCategoryList.setLayoutAnimation(controller);


                        } else {
                            llNoDataFoundPosItem.setVisibility(View.VISIBLE);
                            llShopItemList.setVisibility(View.GONE);
                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(ItemDetailsActivity.this, "Error in response", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GetItmePosStockPojo> call, Throwable t) {
                Toast.makeText(ItemDetailsActivity.this, "Request failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



   /* private void manageItemLikeDislike(String mobileNo,String itemId,String likeOrDislike){

        ApiImplementer.Insert_Update_Item_like(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.COMP_ID, "", "", "","","", new Callback<ItemLikeDisLikePojo>() {
            @Override
            public void onResponse(Call<ItemLikeDisLikePojo> call, Response<ItemLikeDisLikePojo> response) {

                try {
                    if (response.isSuccessful() && response.body() != null){

                        ItemLikeDisLikePojo itemLikeDisLikePojo = response.body();
                        if (itemLikeDisLikePojo != null ){

                            if (itemLikeDisLikePojo.getTotal() == 1){
                                Toast.makeText(ItemDetailsActivity.this,itemLikeDisLikePojo.getMessage(),Toast.LENGTH_LONG).show();

                            }


                        }





                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<ItemLikeDisLikePojo> call, Throwable t) {

            }
        });

    }*/



    private void shopLikeDislike(String mobileNo, String shopId, String shopLikeDislike,String isShopAlreadyLiked){

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(ItemDetailsActivity.this, "");
            }
        });

        ApiImplementer.insertUpdateShopLikeImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.COMP_ID, CommonUtil.USER_ID, ApiUrls.TESTING_KEY, mobileNo, shopId, shopLikeDislike, isShopAlreadyLiked,new Callback<ItemLikeDisLikePojo>() {
            @Override
            public void onResponse(Call<ItemLikeDisLikePojo> call, Response<ItemLikeDisLikePojo> response) {
                DialogUtil.hideProgressDialog();

                try {
                    if (response.isSuccessful() && response.body() != null){

                        ItemLikeDisLikePojo itemLikeDisLikePojo = response.body();

                        if (itemLikeDisLikePojo != null ){
                            if (isAlreadyLiked){
                                isAlreadyLiked = false;
                            }else{
                                isAlreadyLiked = true;
                            }



                            isAvailableInLikedList = true;

                            Toast.makeText(ItemDetailsActivity.this,itemLikeDisLikePojo.getMessage(),Toast.LENGTH_LONG).show();

                            getAllShopLike("9898574748");


                        }



                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<ItemLikeDisLikePojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();

            }
        });
    }



    private void getAllShopLike(String mobileNo){
        ApiImplementer.getShopLikeImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, mobileNo, new Callback<ShopLikeDislikePojo>() {
            @Override
            public void onResponse(Call<ShopLikeDislikePojo> call, Response<ShopLikeDislikePojo> response) {

                DialogUtil.hideProgressDialog();

                try {

                    if (response.isSuccessful() && response.body() != null){

                        ShopLikeDislikePojo shopLikeDislikePojo = response.body();
                        shopList = new ArrayList<>();
                        if (shopLikeDislikePojo != null){

                            // for (int i=0;i<)
                            for(int i=0;i<shopLikeDislikePojo.getRecords().size();i++){

                                shopList.add(new SearchFragment.ShopLikesModel(shopLikeDislikePojo.getRecords().get(i).getRtShopId()+"",shopLikeDislikePojo.getRecords().get(i).getRtLikeDisplikeFlag()+""));
                            }

                            for (int k = 0; k<customerPojoArrayList.size(); k++){


                                for(int j=0;j<shopList.size();j++){
                                    if (customerPojoArrayList.get(k).getCustomerId() == Integer.parseInt(shopList.get(j).getShopId())){
                                        customerPojoArrayList.get(k).setAvailableInLikedList(true);
                                    }
                                    if (
                                            customerPojoArrayList.get(k).getCustomerId() == Integer.parseInt(shopList.get(j).getShopId()) && shopList.get(j).shopLikeFlag.equals("1")){
                                        customerPojoArrayList.get(k).setAlreadyLikedOrNot(true);


                                    }
                                    if (
                                            customerPojoArrayList.get(k).getCustomerId() == Integer.parseInt(shopList.get(j).getShopId()) && shopList.get(j).shopLikeFlag.equals("0")){
                                        customerPojoArrayList.get(k).setAlreadyLikedOrNot(false);


                                    }
                                }



                            }

                        }



                       /* for (int l=0;l<shopList.size();l++){

                            if (shopList.get(l).getShopId().equals(shopId) && shopList.get(l).getShopLikeFlag().equals("1")){

                                imgLike.setImageDrawable(ContextCompat.getDrawable(ItemDetailsActivity.this, R.drawable.ic_favorite_filled));

                                imgLike.startAnimation(AnimationUtils.loadAnimation(ItemDetailsActivity.this, R.anim.favourite_icon_animation));
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgLike.clearAnimation();
                                    }
                                }, 200);
                                break;

                            }
                        }*/

                        System.out.println(shopList);

                        // if (shopList.indexOf())

                    }


                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<ShopLikeDislikePojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();

            }
        });

    }


    //private class shopa;

}