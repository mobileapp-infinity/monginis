package com.infinity.monginis.dashboard.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.monginis.R;

public class BottomSheetDialogForOrderType extends BottomSheetDialogFragment implements View.OnClickListener {

    private CartActivity cartActivity;

    public BottomSheetDialogForOrderType(CartActivity cartActivity) {
        this.cartActivity = cartActivity;
    }


    AppCompatButton btnPickUp, btnDelivery;

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View orderTypeView = inflater.inflate(R.layout.layout_for_order_type_bottom_sheet_dialog, container, false);

        initView(orderTypeView);
        return orderTypeView;
    }

    private void initView(View view) {
        btnPickUp = view.findViewById(R.id.btnPickUp);
        btnDelivery = view.findViewById(R.id.btnDelivery);
        btnPickUp.setOnClickListener(this);
        btnDelivery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPickUp) {
            this.dismiss();


        } else if (view.getId() == R.id.btnDelivery) {
            this.dismiss();

        }
    }
}
