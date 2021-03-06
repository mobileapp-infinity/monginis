package com.infinity.monginis.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.infinity.monginis.R;
import com.infinity.monginis.utils.CommonUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetDialogForLoginUser.ILoginUserDialog {

    private MaterialCardView cvLogin;
    private TextInputLayout tilMobileNumber;
    private TextInputEditText edMobileNumber;
    //test
    //change by harsh
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
//twstw
    //commit
    private void initView() {
        cvLogin = findViewById(R.id.cvLogin);
        cvLogin.setOnClickListener(this);
        tilMobileNumber = findViewById(R.id.tilMobileNumber);
        edMobileNumber = findViewById(R.id.edMobileNumber);
        tilMobileNumber.setStartIconDrawable(R.drawable.ic_phone);
        edMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (CommonUtil.checkIsEmptyOrNullCommon(s.toString())) {
                    tilMobileNumber.setError("Enter mobile number");
                } else if (s.toString().length() != 10) {
                    tilMobileNumber.setError("Enter valid mobile number");
                } else {
                    tilMobileNumber.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    BottomSheetDialogForLoginUser bottomSheetDialogForLoginUser = new BottomSheetDialogForLoginUser(LoginActivity.this);

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvLogin) {
            if (!bottomSheetDialogForLoginUser.isAdded()){
                bottomSheetDialogForLoginUser.show(getSupportFragmentManager(), "test");
            }
        }
    }

    @Override
    public void onLoginBtnClick(String mobileNo) {
        bottomSheetDialogForLoginUser.dismiss();
    }
}