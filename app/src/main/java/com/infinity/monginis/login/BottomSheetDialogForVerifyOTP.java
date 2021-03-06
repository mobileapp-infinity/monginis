package com.infinity.monginis.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.BottomSheetDialogForCheckOut;
import com.infinity.monginis.dashboard.activity.CartActivity;
import com.infinity.monginis.dashboard.activity.ConfirmOrderActivity;
import com.infinity.monginis.dashboard.activity.ItemDetailsActivity;
import com.infinity.monginis.utils.CommonUtil;

import static com.infinity.monginis.dashboard.adapter.PopularItemsAdapter.isFromSpecialOrderItem;
import static com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter.isFromTopCategories;

public class BottomSheetDialogForVerifyOTP extends BottomSheetDialogFragment implements View.OnClickListener {

    private Activity activity;
    private CartActivity cartActivity;
    private ItemDetailsActivity itemDetailsActivity;

    private MaterialCardView cvVerifyOTP;
    private AppCompatImageView imgCloseOTPDialog;
    private String mobileNo;
    private TextViewRegularFont tvMobileNo;

    public BottomSheetDialogForVerifyOTP(Activity activity, String mobileNo) {
        this.activity = activity;
        this.mobileNo = mobileNo;
    }

    public BottomSheetDialogForVerifyOTP(CartActivity activity, String mobileNo) {
        this.cartActivity = activity;
        this.mobileNo = mobileNo;
    }

    public BottomSheetDialogForVerifyOTP(ItemDetailsActivity activity, String mobileNo) {
        this.itemDetailsActivity = activity;
        this.mobileNo = mobileNo;
    }


    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_login_user_otp_dialog,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        cvVerifyOTP = view.findViewById(R.id.cvVerifyOTP);
        cvVerifyOTP.setOnClickListener(this);
        imgCloseOTPDialog = view.findViewById(R.id.imgCloseOTPDialog);
        imgCloseOTPDialog.setOnClickListener(this);
        tvMobileNo = view.findViewById(R.id.tvMobileNo);
        tvMobileNo.setText("+91 " + mobileNo);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvVerifyOTP) {
            this.dismiss();

            //if (item)

            if (isFromTopCategories) {
                BottomSheetDialogForCheckOut bottomSheetDialogForCheckOut = new BottomSheetDialogForCheckOut((ItemDetailsActivity) itemDetailsActivity);
                bottomSheetDialogForCheckOut.show(((ItemDetailsActivity) itemDetailsActivity).getSupportFragmentManager(), "place_order_fragment");
            } else if (isFromSpecialOrderItem) {

                BottomSheetDialogPlaceOrder bottomSheetDialogPlaceOrder = new BottomSheetDialogPlaceOrder((ItemDetailsActivity) itemDetailsActivity);
                bottomSheetDialogPlaceOrder.show(((ItemDetailsActivity) itemDetailsActivity).getSupportFragmentManager(), "place_order_fragment");
            } else {
                BottomSheetDialogForCheckOut bottomSheetDialogForCheckOut = new BottomSheetDialogForCheckOut((ItemDetailsActivity) itemDetailsActivity);
                bottomSheetDialogForCheckOut.show(((ItemDetailsActivity) itemDetailsActivity).getSupportFragmentManager(), "place_order_fragment");

            }


        } else if (v.getId() == R.id.imgCloseOTPDialog) {
            this.dismiss();
        }
    }

}