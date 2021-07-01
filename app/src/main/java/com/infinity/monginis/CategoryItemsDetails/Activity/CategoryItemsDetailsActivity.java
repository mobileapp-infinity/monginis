package com.infinity.monginis.CategoryItemsDetails.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infinity.monginis.CategoryItemsDetails.Adapter.ItemDetailsByCategoryAdapter;
import com.infinity.monginis.CategoryItemsDetails.Pojo.ItemDetailsByCategoryPojo;
import com.infinity.monginis.R;
import com.infinity.monginis.ShopForItemActiivty.Activity.ShopForItemActivity;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;

import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.pojo.GetItemLikeDislikePojo;
import com.infinity.monginis.dashboard.pojo.ItemLikeDisLikePojo;
import com.infinity.monginis.favourites.FavouriteAdapter;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryItemsDetailsActivity extends AppCompatActivity implements View.OnClickListener, ItemDetailsByCategoryAdapter.IOnItemClickListener {


    GridView gvItemByCategory;
    MySharedPreferences mySharedPreferences;
    Intent catIdIntent;
    String catId, catName, itemId;
    private boolean isFromPopular = false;
    AppCompatImageView ivBack;
    ConnectionDetector connectionDetector;
    LinearLayout llNoDataFoundItemDetailsByCategory, llItemDetailsByCategoryProgressbar, llItemDetailsByCategory;
    ArrayList saveFavouriteItemList = new ArrayList();
    TextViewRegularFont tvSelectedCategory;

    AppCompatImageView imgSearch;
    AppCompatEditText edtItemSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_items_details);

        if (savedInstanceState != null) {
            saveFavouriteItemList = savedInstanceState.getStringArrayList("myArrayList");
        }
        initView();


        if (isFromPopular) {
            GetSpecialItemByCatId(itemId);
        } else {

            getItemLikeDislike("9898574748","");

        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putStringArrayList("myArrayList", saveFavouriteItemList);

    }

    private void initView() {
        connectionDetector = new ConnectionDetector(this);
        gvItemByCategory = findViewById(R.id.gvItemByCategory);
        mySharedPreferences = new MySharedPreferences(CategoryItemsDetailsActivity.this);
        catIdIntent = getIntent();
       // catId = catIdIntent.getStringExtra("catId");
        catId = catIdIntent.getStringExtra("id");
        catName = catIdIntent.getStringExtra("name");
        itemId = catIdIntent.getStringExtra("itemId");
        isFromPopular = catIdIntent.getBooleanExtra("isFromPopular", false);
        llNoDataFoundItemDetailsByCategory = findViewById(R.id.llNoDataFoundItemDetailsByCategory);
        tvSelectedCategory = findViewById(R.id.tvSelectedCategory);
        tvSelectedCategory.setText(catName);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        llItemDetailsByCategoryProgressbar = findViewById(R.id.llItemDetailsByCategoryProgressbar);
        llItemDetailsByCategory = findViewById(R.id.llItemDetailsByCategory);
        edtItemSearch = findViewById(R.id.edtItemSearch);
        imgSearch = findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelectedCategory.setVisibility(View.GONE);
                edtItemSearch.setVisibility(View.VISIBLE);
                imgSearch.setVisibility(View.GONE);

            }
        });
        edtItemSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemDetailsByCategoryAdapter.getFilter().filter(s.toString());
            }
        });
        edtItemSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (edtItemSearch.getRight() - edtItemSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here

                        edtItemSearch.setVisibility(View.GONE);
                        tvSelectedCategory.setVisibility(View.VISIBLE);
                        imgSearch.setVisibility(View.VISIBLE);
                        edtItemSearch.setText("");

                        return true;
                    }
                }
                return false;
            }
        });

    }

    ItemDetailsByCategoryAdapter itemDetailsByCategoryAdapter;
    private void GetSpecialItemByCatId(String catId) {

        if (connectionDetector.isConnectingToInternet()) {

            llItemDetailsByCategoryProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundItemDetailsByCategory.setVisibility(View.GONE);
            ApiImplementer.GetSpecialItemByCatIdImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, catId, CommonUtil.CUST_ID, new Callback<ItemDetailsByCategoryPojo>() {
                @Override
                public void onResponse(Call<ItemDetailsByCategoryPojo> call, Response<ItemDetailsByCategoryPojo> response) {

                    llNoDataFoundItemDetailsByCategory.setVisibility(View.GONE);
                    llItemDetailsByCategoryProgressbar.setVisibility(View.GONE);


                    try {

                        ArrayList<ItemDetailsByCategoryPojo>itemDetailsByCategoryPojoArrayList = new ArrayList<>();
                        if (response.isSuccessful() && response.body() != null) {

                            ItemDetailsByCategoryPojo itemDetailsByCategoryPojo = response.body();

                            if (itemDetailsByCategoryPojo != null && itemDetailsByCategoryPojo.getRECORDS().size() > 0) {


                                for (int l=0;l<itemDetailsByCategoryPojo.getRECORDS().size();l++){

                                    for (int k=0;k<itemIdList.size();k++){
                                        if (itemDetailsByCategoryPojo.getRECORDS().get(l).getItmId() == itemIdList.get(k).getItemId()){
                                            itemDetailsByCategoryPojo.getRECORDS().get(l).setItemAvailableInLikedList(true);

                                        }
                                        if (itemDetailsByCategoryPojo.getRECORDS().get(l).getItmId() == itemIdList.get(k).getItemId() && itemIdList.get(k).isLikeOrNot()){
                                            itemDetailsByCategoryPojo.getRECORDS().get(l).setItemAlreadyLiked(true);

                                        }

                                    }

                                    itemDetailsByCategoryPojoArrayList.add(itemDetailsByCategoryPojo);


                                }
                                llItemDetailsByCategory.setVisibility(View.VISIBLE);
                                itemDetailsByCategoryAdapter = new ItemDetailsByCategoryAdapter(itemDetailsByCategoryPojoArrayList,CategoryItemsDetailsActivity.this,CategoryItemsDetailsActivity.this, itemDetailsByCategoryPojo, saveFavouriteItemList);
                                gvItemByCategory.setAdapter(itemDetailsByCategoryAdapter);


                            } else {
                                llItemDetailsByCategory.setVisibility(View.GONE);
                                llNoDataFoundItemDetailsByCategory.setVisibility(View.VISIBLE);

                            }


                            System.out.println(itemDetailsByCategoryPojo);


                        } else {
                            Toast.makeText(CategoryItemsDetailsActivity.this, "Error in response", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        Toast.makeText(CategoryItemsDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ItemDetailsByCategoryPojo> call, Throwable t) {
                    llItemDetailsByCategoryProgressbar.setVisibility(View.GONE);
                    Toast.makeText(CategoryItemsDetailsActivity.this, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(CategoryItemsDetailsActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack) {
            onBackPressed();
        }
    }

    @Override
    public void onItemClicked(ItemDetailsByCategoryPojo itemDetailsByCategoryPojo, int position) {





        Intent redirectToShopForItemActivityIntent = new Intent(CategoryItemsDetailsActivity.this, ShopForItemActivity.class);
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_ID, itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_NAME, itemDetailsByCategoryPojo.getRECORDS().get(position).getItmName() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_MRP, itemDetailsByCategoryPojo.getRECORDS().get(position).getMRP() + "");
        startActivity(redirectToShopForItemActivityIntent);

    }

    @Override
    public void onItemClicked(GetItemLikeDislikePojo getItemLikeDislikePojo, int position) {

        Intent redirectToShopForItemActivityIntent = new Intent(CategoryItemsDetailsActivity.this, ShopForItemActivity.class);
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_ID, getItemLikeDislikePojo.getRecords().get(position).getId() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_NAME, getItemLikeDislikePojo.getRecords().get(position).getItm_name() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_MRP, getItemLikeDislikePojo.getRecords().get(position).getItm_mrp() + "");
        startActivity(redirectToShopForItemActivityIntent);

    }

    @Override
    public void onLikeButtonClicked(View view,ItemDetailsByCategoryPojo itemDetailsByCategoryPojo, int position,String isItemAlreadyLiked,String isItemAvailableInLiked) {

        //temp disable
        manageItemLikeDislike("9898574748",itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId()+"",isItemAlreadyLiked,isItemAvailableInLiked);
        /*view.findViewById(R.id.imgLike).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

            }
        });*/

    }

    private void hashmaptest() {
        //create test hashmap
        HashMap<String, String> testHashMap = new HashMap<String, String>();
        testHashMap.put("key1", "value1");
        testHashMap.put("key2", "value2");

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(testHashMap);

        //save in shared prefs
        //  MySharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        //  prefs.edit().putString("hashString", hashMapString).apply();

        //get from shared prefs
        // String storedHashMapString = prefs.getString("hashString", "oopsDintWork");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        // HashMap<String, String> testHashMap2 = gson.fromJson(storedHashMapString, type);

        //use values
        //   String toastString = testHashMap2.get("key1") + " | " + testHashMap2.get("key2");
        //   Toast.makeText(this, toastString, Toast.LENGTH_LONG).show();
    }

    private void manageItemLikeDislike(String mobileNo,String itemId,String likeOrDislike,String isAlredyLike){

        ApiImplementer.insertUpdateItemLike(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.COMP_ID, CommonUtil.USER_ID, ApiUrls.TESTING_KEY, mobileNo,itemId,likeOrDislike,isAlredyLike, new Callback<ItemLikeDisLikePojo>() {
            @Override
            public void onResponse(Call<ItemLikeDisLikePojo> call, Response<ItemLikeDisLikePojo> response) {

                try {
                    if (response.isSuccessful() && response.body() != null){
                        System.out.println("URLL"+call.request().url());

                        ItemLikeDisLikePojo itemLikeDisLikePojo = response.body();
                        if (itemLikeDisLikePojo != null ){

                            if (itemLikeDisLikePojo.getTotal() == 1){
                                Toast.makeText(CategoryItemsDetailsActivity.this,itemLikeDisLikePojo.getMessage(),Toast.LENGTH_LONG).show();

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

    }



    boolean isItemLiked = false;
    public static ArrayList<ItemLikeDislikeModel>itemIdList;
    private void getItemLikeDislike(String mobileNo,String itemId){
        DialogUtil.showProgressDialogCancelable(CategoryItemsDetailsActivity.this,"");
        ApiImplementer.getItemLikeImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, mobileNo,  new Callback<GetItemLikeDislikePojo>() {
            @Override
            public void onResponse(Call<GetItemLikeDislikePojo> call, Response<GetItemLikeDislikePojo> response) {
                try {
                    GetSpecialItemByCatId(catId);

                    DialogUtil.hideProgressDialog();
                    itemIdList = new ArrayList<>();
                    if (response.isSuccessful() && response.body() != null){


                        GetItemLikeDislikePojo getItemLikeDislikePojo = response.body();

                        if (getItemLikeDislikePojo != null && getItemLikeDislikePojo.getRecords().size()> 0 ){

                            for (int i=0 ;i<getItemLikeDislikePojo.getRecords().size();i++){
                                if (getItemLikeDislikePojo.getRecords().get(i).getLike_dislike_flag().equals("0")){
                                    isItemLiked  =false;
                                }else{
                                    isItemLiked  =true;
                                }
                                itemIdList.add(new ItemLikeDislikeModel(isItemLiked,getItemLikeDislikePojo.getRecords().get(i).getRtItemId()));

                            }

                        }

                      //  Set<ItemLikeDislikeModel> set = new HashSet<>(itemIdList);
                      //  itemIdList.clear();
                      //  itemIdList.addAll(set);


                        System.out.println(itemIdList);







                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<GetItemLikeDislikePojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();


            }
        });
    }



    public class ItemLikeDislikeModel{


        private boolean isLikeOrNot;
        private int itemId;


        public ItemLikeDislikeModel(boolean isLikeOrNot, int itemId) {
            this.isLikeOrNot = isLikeOrNot;
            this.itemId = itemId;
        }

        public boolean isLikeOrNot() {
            return isLikeOrNot;
        }

        public void setLikeOrNot(boolean likeOrNot) {
            isLikeOrNot = likeOrNot;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }
    }


}
