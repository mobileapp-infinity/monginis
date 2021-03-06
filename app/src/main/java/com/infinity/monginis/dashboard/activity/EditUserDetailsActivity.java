package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.infinity.monginis.R;
import com.infinity.monginis.dashboard.adapter.MyStepperAdapter;
import com.infinity.monginis.dashboard.fragments.FragmentVerticalStepper;
import com.stepstone.stepper.StepperLayout;

public class EditUserDetailsActivity extends AppCompatActivity {

    private FragmentVerticalStepper fragmentVerticalStepper = new FragmentVerticalStepper();

    private StepperLayout stepperUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);
        initView();
        stepperUserDetails = findViewById(R.id.stepperUserDetails);
        stepperUserDetails.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentVerticalStepper).commit();
    }

    private void initView() {
        stepperUserDetails = findViewById(R.id.stepperUserDetails);
    }

}