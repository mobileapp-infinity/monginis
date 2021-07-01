package com.infinity.monginis.favourites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.infinity.monginis.CategoryItemsDetails.Adapter.ItemDetailsByCategoryAdapter;
import com.infinity.monginis.R;
import com.infinity.monginis.ShopForItemActiivty.pojo.ShopLikeDislikePojo;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.dashboard.activity.CustomizeScreenActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopFavouriteActivity extends AppCompatActivity implements View.OnClickListener {


    private AppCompatImageView ivBack;
    private GridView gvShopLikes;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_favourite);
        initView();
        getShopLike();
    }


    private void initView(){
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

        mySharedPreferences = new MySharedPreferences(this);
        gvShopLikes = findViewById(R.id.gvShopLikes);



    }


    private  void getShopLike(){

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(ShopFavouriteActivity.this, "");
            }
        });
        ApiImplementer.getShopLikeImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, "9898574748", new Callback<ShopLikeDislikePojo>() {
            @Override
            public void onResponse(Call<ShopLikeDislikePojo> call, Response<ShopLikeDislikePojo> response) {
                DialogUtil.hideProgressDialog();

                try {

                    if (response.body() != null && response.isSuccessful()){

                        ShopLikeDislikePojo getItemLikeDislikePojo = response.body();

                        if (getItemLikeDislikePojo != null && getItemLikeDislikePojo.getRecords().size() > 0){
                            ItemDetailsByCategoryAdapter itemDetailsByCategoryAdapter = new ItemDetailsByCategoryAdapter(ShopFavouriteActivity.this,getItemLikeDislikePojo,true);

                            gvShopLikes.setAdapter(itemDetailsByCategoryAdapter);
                        }else{

                        }


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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack){
            onBackPressed();
        }
    }
}