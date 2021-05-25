package com.infinity.monginis.CategoryItemsDetails.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infinity.monginis.CategoryItemsDetails.Activity.CategoryItemsDetailsActivity;
import com.infinity.monginis.CategoryItemsDetails.Pojo.ItemDetailsByCategoryPojo;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.login.BottomSheetDialogForLoginUser;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;


public class ItemDetailsByCategoryAdapter extends BaseAdapter {

    private Context context;
    private MySharedPreferences mySharedPreferences;
    private ItemDetailsByCategoryPojo itemDetailsByCategoryPojo;
    private IOnItemClickListener iOnItemClickListener;
    private ArrayList<String> saveFavouriteItemList;
    private ArrayList<String> selectedFavouriteItems;
    Gson gson;
    private CategoryItemsDetailsActivity activity;
    private HashMap<String, ArrayList<String>> selectedFavouriteItemList;


    public ItemDetailsByCategoryAdapter(CategoryItemsDetailsActivity activity, Context context, ItemDetailsByCategoryPojo itemDetailsByCategoryPojo, ArrayList<String> saveFavouriteItemList) {
        this.activity = activity;
        this.context = context;
        this.itemDetailsByCategoryPojo = itemDetailsByCategoryPojo;
        iOnItemClickListener = (IOnItemClickListener) context;
        this.saveFavouriteItemList = saveFavouriteItemList;
        selectedFavouriteItems = new ArrayList<>();
        selectedFavouriteItemList = new HashMap<>();
        gson = new Gson();
        mySharedPreferences = new MySharedPreferences(context);
    }

    @Override
    public int getCount() {
        return itemDetailsByCategoryPojo.getRECORDS().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.inflater_item_details_by_category_view, null);
        ImageView ivItemByCategory = view.findViewById(R.id.ivItemByCategory);
        AppCompatImageView imgLike = view.findViewById(R.id.imgLike);
        TextViewMediumFont tvItemNameByCategory = view.findViewById(R.id.tvItemNameByCategory);
        TextViewRegularFont tvItemPriceByCategory = view.findViewById(R.id.tvItemPriceByCategory);
        tvItemNameByCategory.setText(itemDetailsByCategoryPojo.getRECORDS().get(position).getItmName());
        tvItemPriceByCategory.setText("MRP : " + itemDetailsByCategoryPojo.getRECORDS().get(position).getPrice() + "/-Rs");


        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getFavouriteItems())) {
            String stringFromsharedPref = mySharedPreferences.getFavouriteItems();
            java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<String>>>() {
            }.getType();
            HashMap<String, ArrayList<String>> testHashMap2 = gson.fromJson(stringFromsharedPref, type);
            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())) {
                saveFavouriteItemList = testHashMap2.get(mySharedPreferences.getUserMobileNo());
            }


            if (saveFavouriteItemList.contains(itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "")) {

                imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_filled));
                itemDetailsByCategoryPojo.getRECORDS().get(position).setLiked(true);


            } else {

                ;
                imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_outline));
                itemDetailsByCategoryPojo.getRECORDS().get(position).setLiked(false);
            }
        }

        //   String storedHashMapString = mySharedPreferences.getString("hashString", "oopsDintWork");
        // java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        // HashMap<String, String> testHashMap2 = gson.fromJson(storedHashMapString, type);

        //use values
        // String toastString = testHashMap2.get("key1") + " | " + testHashMap2.get("key2");
        // Toast.makeText(this, toastString, Toast.LENGTH_LONG).show();


        Glide.with(context).load("https://b.zmtcdn.com/data/pictures/chains/4/18543374/27a8d3fe04bfa2470639f6a249400934.jpg").error(R.drawable.ic_launcher_background).into(ivItemByCategory);
        imgLike.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())){

                    if (!itemDetailsByCategoryPojo.getRECORDS().get(position).isLiked()) {
                        itemDetailsByCategoryPojo.getRECORDS().get(position).setLiked(true);


                        if (!saveFavouriteItemList.contains(itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "")) {
                            saveFavouriteItemList.add(itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "");
                        }

                        imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_filled));

                        selectedFavouriteItemList.put(mySharedPreferences.getUserMobileNo() + "", saveFavouriteItemList);
                        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())) {
                            mySharedPreferences.setFavouriteItems(selectedFavouriteItemList);
                        }

                        imgLike.startAnimation(AnimationUtils.loadAnimation(context, R.anim.favourite_icon_animation));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imgLike.clearAnimation();
                            }
                        }, 200);
                    } else {
                        itemDetailsByCategoryPojo.getRECORDS().get(position).setLiked(false);
                        imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_outline));


                        if (saveFavouriteItemList.contains(itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "")) {
                            saveFavouriteItemList.remove(itemDetailsByCategoryPojo.getRECORDS().get(position).getItmId() + "");
                        }

                        selectedFavouriteItemList.put(mySharedPreferences.getUserMobileNo() + "", saveFavouriteItemList);
                        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())) {
                            mySharedPreferences.setFavouriteItems(selectedFavouriteItemList);
                        }
                        imgLike.startAnimation(AnimationUtils.loadAnimation(context, R.anim.favourite_icon_animation));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imgLike.clearAnimation();
                            }
                        }, 200);
                    }




                }else{
                    BottomSheetDialogForLoginUser bottomSheetDialogForLoginUser = new BottomSheetDialogForLoginUser(activity,true);
                    if (!bottomSheetDialogForLoginUser.isAdded()) {
                        bottomSheetDialogForLoginUser.show(activity.getSupportFragmentManager(), "test");
                    }
                }




            }
        });

        if (CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getFavouriteItems())) {
            if (!itemDetailsByCategoryPojo.getRECORDS().get(position).isLiked() ) {
                imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_outline));

            } else {
                imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_filled));
            }
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnItemClickListener.onItemClicked(itemDetailsByCategoryPojo, position);
            }
        });

        return view;


    }


    public interface IOnItemClickListener {
        void onItemClicked(ItemDetailsByCategoryPojo itemDetailsByCategoryPojo, int position);
    }
}
