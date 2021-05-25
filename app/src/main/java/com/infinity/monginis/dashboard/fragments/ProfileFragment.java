package com.infinity.monginis.dashboard.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.AddressActivity;
import com.infinity.monginis.dashboard.activity.MyOrdersActivity;
import com.infinity.monginis.dashboard.activity.MyOrdersScreenActivity;
import com.infinity.monginis.dashboard.activity.PaymentOptionsActivity;
import com.infinity.monginis.login.SplashActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.MySharedPreferences;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static ProfileFragment profileFragment = null;

    LinearLayout llMyOrders;
    LinearLayout llManageAddress;
    LinearLayout llPayments;
    LinearLayout llFavourites;
    LinearLayout llHelp;
    LinearLayout llLogout;
    private Dialog dialog;
    private MySharedPreferences mySharedPreferences;
    private TextViewMediumFont tvCustomerName;

    TextViewRegularFont tvEdit;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment getProfileFragment() {
        if (profileFragment == null) {
            profileFragment = new ProfileFragment();
        }
        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void initView(View view) {
        llMyOrders = view.findViewById(R.id.llMyOrders);
        llManageAddress = view.findViewById(R.id.llManageAddress);
        llPayments = view.findViewById(R.id.llPayments);
        llFavourites = view.findViewById(R.id.llFavourites);
        llHelp = view.findViewById(R.id.llHelp);
        llLogout = view.findViewById(R.id.llLogout);
        tvEdit = view.findViewById(R.id.tvEdit);
        mySharedPreferences = new MySharedPreferences(getActivity());

        tvCustomerName = view.findViewById(R.id.tvCustomerName);
        String loggedInUserName = mySharedPreferences.getUserMobileNo();
        if (loggedInUserName != null && !CommonUtil.checkIsEmptyOrNullCommon(loggedInUserName)) {
            llLogout.setVisibility(View.VISIBLE);
            tvCustomerName.setText(mySharedPreferences.getUserMobileNo());
        }else{
            llLogout.setVisibility(View.GONE);
        }

        llMyOrders.setOnClickListener(this);
        llManageAddress.setOnClickListener(this);
        llPayments.setOnClickListener(this);
        llFavourites.setOnClickListener(this);
        llFavourites.setOnClickListener(this);
        llHelp.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        tvEdit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.llMyOrders) {

            Intent myOrdersIntent = new Intent(getActivity(), MyOrdersScreenActivity.class);
            startActivity(myOrdersIntent);

        } else if (view.getId() == R.id.llManageAddress) {

            Intent manageAddresses = new Intent(getActivity(), AddressActivity.class);
            startActivity(manageAddresses);

        } else if (view.getId() == R.id.llPayments) {

            Intent paymentOptions = new Intent(getActivity(), PaymentOptionsActivity.class);
            startActivity(paymentOptions);

        } else if (view.getId() == R.id.llFavourites) {

        } else if (view.getId() == R.id.llHelp) {

        } else if (view.getId() == R.id.llLogout) {

         exitDialog();


        } else if (view.getId() == R.id.tvEdit) {

        }
    }

    private void exitDialog(){
        dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog

        dialog.setCancelable(false);
        View customProgressDialog = LayoutInflater.from(getActivity()).inflate(R.layout.exit_dialog, null);
        TextViewMediumFont tvNo = customProgressDialog.findViewById(R.id.tvNoThanks);
        Button btnExit = customProgressDialog.findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                mySharedPreferences.setUserMobileNo("");
                getActivity().finish();
            }
        });

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.setContentView(customProgressDialog);

        dialog.show();
    }

}