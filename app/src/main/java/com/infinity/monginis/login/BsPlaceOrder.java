package com.infinity.monginis.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.activity.BsFroSpecialOrder;
import com.infinity.monginis.itemDetails.ItemDetailsActivity;

public class BsPlaceOrder extends BottomSheetDialogFragment implements View.OnClickListener {

    private ItemDetailsActivity activity;
    private LinearLayout llRepeatLast;
    private LinearLayout llIWillChoose;

    public BsPlaceOrder(ItemDetailsActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_place_order_bottom_sheet_dialog,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        llRepeatLast = view.findViewById(R.id.llRepeatLast);
        llRepeatLast.setOnClickListener(this);
        llIWillChoose = view.findViewById(R.id.llIWillChoose);
        llIWillChoose.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llIWillChoose) {

            this.dismiss();
            BsFroSpecialOrder bottomSheetForSpecialOrder = new BsFroSpecialOrder((ItemDetailsActivity) activity);
            bottomSheetForSpecialOrder.show(((ItemDetailsActivity) activity).getSupportFragmentManager(), "");


        } else if (v.getId() == R.id.llRepeatLast) {


        }
    }





}
