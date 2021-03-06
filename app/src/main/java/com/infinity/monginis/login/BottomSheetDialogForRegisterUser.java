package com.infinity.monginis.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.utils.CommonUtil;

public class BottomSheetDialogForRegisterUser extends BottomSheetDialogFragment implements View.OnClickListener {

    private MaterialCardView cvRegister;
    private TextInputLayout tilMobileNumber;
    private TextInputEditText edMobileNumber;
    private Activity activity;
    private TextInputLayout tilUserName;
    private TextInputEditText edUserName;

    public BottomSheetDialogForRegisterUser(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_register_user_dialog,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        cvRegister = view.findViewById(R.id.cvRegister);
        cvRegister.setOnClickListener(this);
        tilUserName = view.findViewById(R.id.tilUserName);
        edUserName = view.findViewById(R.id.edUserName);
        tilMobileNumber = view.findViewById(R.id.tilMobileNumber);
        edMobileNumber = view.findViewById(R.id.edMobileNumber);
        tilUserName.setStartIconDrawable(R.drawable.ic_pserson);
        tilMobileNumber.setStartIconDrawable(R.drawable.ic_phone);
        edUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidMobileNo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isValidMobileNo(String mobileNo) {
        boolean isValid = true;
        if (CommonUtil.checkIsEmptyOrNullCommon(mobileNo)) {
            tilMobileNumber.setError("Enter mobile number");
            isValid = false;
        } else if (mobileNo.length() != 10) {
            tilMobileNumber.setError("Enter valid mobile number");
            isValid = false;
        } else {
            tilMobileNumber.setError("");
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidUsername(String userName) {
        boolean isValid = true;
        if (CommonUtil.checkIsEmptyOrNullCommon(userName)) {
            tilUserName.setError("Enter Username");
            isValid = false;
        } else {
            tilUserName.setError("");
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvRegister) {
            if (isValidUsername(edUserName.getText().toString()) && isValidMobileNo(edMobileNumber.getText().toString())) {
                this.dismiss();
//                iRegisterUser.onRegisterButtonClick(edUserName.getText().toString(),
//                        edMobileNumber.getText().toString());
            }
        }
    }

    public interface IRegisterUser {
        void onRegisterButtonClick(String userName, String mobileNo);
    }

}