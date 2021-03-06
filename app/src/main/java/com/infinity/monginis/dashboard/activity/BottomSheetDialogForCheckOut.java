package com.infinity.monginis.dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;

public class BottomSheetDialogForCheckOut extends BottomSheetDialogFragment implements View.OnClickListener {

    private TextViewRegularFont tvItemAndPrice;
    private Button btnCheckout;
    private ItemDetailsActivity activity;

    public BottomSheetDialogForCheckOut(ItemDetailsActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_checkout_bottom_sheet_dialog,
                container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tvItemAndPrice = view.findViewById(R.id.tvItemAndPrice);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnCheckout) {

            this.dismiss();
            Intent customizeScreenIntent = new Intent(activity, CustomizeScreenActivity.class);
            startActivity(customizeScreenIntent);

        }
    }
}
