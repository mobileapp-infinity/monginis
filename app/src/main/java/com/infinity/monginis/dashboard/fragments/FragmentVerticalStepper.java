package com.infinity.monginis.dashboard.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.utils.CommonUtil;

public class FragmentVerticalStepper extends Fragment {

//    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[3];
//    private MaterialCardView cvStepOneUserName, cvStepTwoMobileNumber, cvStepThreeEmailId;
//
//    private int mActivatedColorRes = R.color.material_blue_500;
//    private int mDoneIconRes = R.drawable.ic_done_white_16dp;
//
//    private TextInputEditText edtUserName, edtMobileNumber, edtEmailId;
//    private TextInputLayout tilUserName, tilMobileNumber, tilEmailID;

    public FragmentVerticalStepper() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vertical_stepper, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        mSteppers[0] = view.findViewById(R.id.stepper_0);
//        mSteppers[1] = view.findViewById(R.id.stepper_1);
//        mSteppers[2] = view.findViewById(R.id.stepper_2);
//
//        edtUserName = view.findViewById(R.id.edtUserName);
//        edtMobileNumber = view.findViewById(R.id.edtMobileNumber);
//        edtEmailId = view.findViewById(R.id.edtEmailId);
//        tilUserName = view.findViewById(R.id.tilUserName);
//        tilMobileNumber = view.findViewById(R.id.tilMobileNumber);
//        tilEmailID = view.findViewById(R.id.tilEmailID);
//        tilUserName.setHintEnabled(false);
//        tilMobileNumber.setHintEnabled(false);
//        tilEmailID.setHintEnabled(false);
//        VerticalStepperItemView.bindSteppers(mSteppers);
//
//        cvStepOneUserName = view.findViewById(R.id.cvStepOneUserName);
//        cvStepTwoMobileNumber = view.findViewById(R.id.cvStepTwoMobileNumber);
//        cvStepThreeEmailId = view.findViewById(R.id.cvStepThreeEmailId);
//
//        mSteppers[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSteppers[0].setActivated(true);
//            }
//        });
//        mSteppers[1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSteppers[1].setActivated(true);
//            }
//        });
//        mSteppers[2].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSteppers[2].setActivated(true);
//            }
//        });
//
//        cvStepOneUserName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!CommonUtil.checkIsEmptyOrNullCommon(edtUserName.getText().toString())) {
//                    mSteppers[0].setSummaryFinished(edtUserName.getText().toString());
//                    tilUserName.setError("");
//                    mSteppers[0].nextStep();
//                } else {
//                    mSteppers[0].dispatchSetActivated(true);
//                    tilUserName.setError("Enter Name");
//                    mSteppers[0].setErrorText("Enter Name");
//                }
//            }
//        });
//        cvStepTwoMobileNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CommonUtil.checkIsEmptyOrNullCommon(edtMobileNumber.getText().toString())) {
//                    mSteppers[1].setErrorText("Enter mobile number");
//                    tilMobileNumber.setError("Enter mobile number");
//                } else if (edtMobileNumber.getText().toString().length() != 10) {
//                    mSteppers[1].setErrorText("Enter valid mobile number");
//                    tilMobileNumber.setError("Enter valid mobile number");
//                } else {
//                    tilMobileNumber.setError("");
//                    mSteppers[1].setSummaryFinished(edtMobileNumber.getText().toString());
//                    mSteppers[1].nextStep();
//                }
//            }
//        });
//        cvStepThreeEmailId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CommonUtil.checkIsEmptyOrNullCommon(edtEmailId.getText().toString())) {
//                    mSteppers[2].setErrorText("Enter emailId");
//                    tilEmailID.setError("Enter emailId");
//                } else if (!edtEmailId.getText().toString().contains("@")) {
//                    mSteppers[2].setErrorText("Enter valid emailId");
//                    tilEmailID.setError("Enter valid emailId");
//                } else {
//                    tilEmailID.setError("");
//                    mSteppers[2].setIsLastStep(true);
//                    mSteppers[2].setSummaryFinished(edtEmailId.getText().toString());
//                }
//            }
//        });
//    }

}