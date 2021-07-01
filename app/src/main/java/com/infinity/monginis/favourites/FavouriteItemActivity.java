package com.infinity.monginis.favourites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
import com.infinity.monginis.CategoryItemsDetails.Adapter.ItemDetailsByCategoryAdapter;
import com.infinity.monginis.R;
import com.infinity.monginis.ShopForItemActiivty.Activity.ShopForItemActivity;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.dashboard.pojo.GetItemLikeDislikePojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteItemActivity extends AppCompatActivity implements View.OnClickListener,FavouriteAdapter.IOnFavouriteItemClicked {

    private GridView gvFavouriteItems;
    private MySharedPreferences mySharedPreferences;
    private AppCompatImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_item);
        initView();
        getItemLikes();
    }


    private void initView(){
        ivBack =findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        gvFavouriteItems = findViewById(R.id.gvFavouriteItems);
        mySharedPreferences = new MySharedPreferences(this);
    }



    private void getItemLikes(){
        ApiImplementer.getItemLikeImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, "9898574748", new Callback<GetItemLikeDislikePojo>() {
            @Override
            public void onResponse(Call<GetItemLikeDislikePojo> call, Response<GetItemLikeDislikePojo> response) {
                try {
                    if (response.isSuccessful() && response.body() != null){
                        GetItemLikeDislikePojo getItemLikeDislikePojo = response.body();

                        if (getItemLikeDislikePojo != null && getItemLikeDislikePojo.getRecords().size() > 0){

                          /*  ItemDetailsByCategoryAdapter itemDetailsByCategoryAdapter = new ItemDetailsByCategoryAdapter(FavouriteItemActivity.this,getItemLikeDislikePojo,true);
                            gvFavouriteItems.setAdapter(itemDetailsByCategoryAdapter);*/

                            FavouriteAdapter favouriteAdapter  = new FavouriteAdapter(FavouriteItemActivity.this,getItemLikeDislikePojo);
                            gvFavouriteItems.setAdapter(favouriteAdapter);



                        }else{


                        }

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<GetItemLikeDislikePojo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ivBack){
         onBackPressed();
        }

    }

    @Override
    public void onFavouriteClicked(GetItemLikeDislikePojo getItemLikeDislikePojo, int position) {

        Intent redirectToShopForItemActivityIntent = new Intent(FavouriteItemActivity.this, ShopForItemActivity.class);
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_ID, getItemLikeDislikePojo.getRecords().get(position).getId() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_NAME, getItemLikeDislikePojo.getRecords().get(position).getItm_name() + "");
        redirectToShopForItemActivityIntent.putExtra(IntentConstants.SELECTED_ITEM_MRP, getItemLikeDislikePojo.getRecords().get(position).getItm_mrp() + "");
        startActivity(redirectToShopForItemActivityIntent);

    }

}