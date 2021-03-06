package com.infinity.monginis.dashboard.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

public class MyStepperAdapter extends AbstractFragmentStepAdapter {

    public MyStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
