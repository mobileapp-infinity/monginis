package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;

public class DiscountCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llContent, llGooglePayContent;
    private TextViewRegularFont tvPayTmExpandClose, tvGooglePayExpandClose;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_code);
        initView();
    }

    private void initView() {
        llContent = findViewById(R.id.llContent);
        llGooglePayContent = findViewById(R.id.llGooglePayContent);
        tvPayTmExpandClose = findViewById(R.id.tvPayTmExpandClose);
        tvGooglePayExpandClose = findViewById(R.id.tvGooglePayExpandClose);
        ivBack = findViewById(R.id.ivBack);

        tvGooglePayExpandClose.setOnClickListener(this);
        tvPayTmExpandClose.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvPayTmExpandClose) {

            if (llContent.getVisibility() == View.VISIBLE) {

                llContent.setVisibility(View.GONE);
                tvPayTmExpandClose.setText("Expand");
                tvPayTmExpandClose.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_more_24, 0);
            } else {
                llContent.setVisibility(View.VISIBLE);
                tvPayTmExpandClose.setText("Close");
                tvPayTmExpandClose.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_less_24, 0);
            }

        } else if (view.getId() == R.id.tvGooglePayExpandClose) {

            if (llGooglePayContent.getVisibility() == View.VISIBLE) {
                llGooglePayContent.setVisibility(View.GONE);
                tvGooglePayExpandClose.setText("Expand");
                tvGooglePayExpandClose.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_more_24, 0);
            } else {
                llGooglePayContent.setVisibility(View.VISIBLE);
                tvGooglePayExpandClose.setText("Close");
                tvGooglePayExpandClose.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_less_24, 0);
            }

        } else if (view.getId() == R.id.ivBack) {
            onBackPressed();
        }

    }
}