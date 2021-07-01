package com.infinity.monginis.favourites;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.pojo.GetItemLikeDislikePojo;
import com.infinity.monginis.utils.CommonUtil;

public class FavouriteAdapter extends BaseAdapter {


    private Context context;
    private GetItemLikeDislikePojo getItemLikeDislikePojo;
   private IOnFavouriteItemClicked iOnFavouriteItemClicked;



    public FavouriteAdapter(Context context, GetItemLikeDislikePojo getItemLikeDislikePojo) {
        this.context = context;
        iOnFavouriteItemClicked =(IOnFavouriteItemClicked)context;
        this.getItemLikeDislikePojo = getItemLikeDislikePojo;
    }

    @Override
    public int getCount() {
        return getItemLikeDislikePojo.getRecords().size();
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

        Glide.with(context).load("https://b.zmtcdn.com/data/pictures/chains/4/18543374/27a8d3fe04bfa2470639f6a249400934.jpg").error(R.drawable.ic_launcher_background).into(ivItemByCategory);

        //tvItemNameByCategory.setText(getItemLikeDislikePojo.getRecords().get(position).ge());

        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemLikeDislikePojo.getRecords().get(position).getItm_name())){
            tvItemNameByCategory.setText(getItemLikeDislikePojo.getRecords().get(position).getItm_name());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemLikeDislikePojo.getRecords().get(position).getItm_mrp())){
            tvItemPriceByCategory.setText("Rs"+getItemLikeDislikePojo.getRecords().get(position).getItm_mrp()+"/-");
        }



        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemLikeDislikePojo.getRecords().get(position).getLike_dislike_flag())) {

            if (!getItemLikeDislikePojo.getRecords().get(position).getLike_dislike_flag().equals("0")) {

                imgLike.startAnimation(AnimationUtils.loadAnimation(context, R.anim.favourite_icon_animation));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgLike.clearAnimation();
                    }
                }, 200);

                imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_filled));

            }


        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnFavouriteItemClicked.onFavouriteClicked(getItemLikeDislikePojo,position);
            }
        });
        return view;
    }


    public interface IOnFavouriteItemClicked{
        void onFavouriteClicked(GetItemLikeDislikePojo getItemLikeDislikePojo,int position);
    }
}
