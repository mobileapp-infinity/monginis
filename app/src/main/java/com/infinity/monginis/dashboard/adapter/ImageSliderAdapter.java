package com.infinity.monginis.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.card.MaterialCardView;
import com.infinity.monginis.R;

import java.util.Objects;

public class ImageSliderAdapter extends PagerAdapter {
    Context context;

    // Array of images
    int[] images;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ImageSliderAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.layout_image_slider, container, false);
       // View itemView = mLayoutInflater.inflate(R.layout.dashboard_banner_item_view, container, false);

        // referencing the image view from the item.xml file
        AppCompatImageView imageView = itemView.findViewById(R.id.imgCategorySlider);

        // setting the image in the imageView
        imageView.setImageResource(images[position]);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
