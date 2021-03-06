package com.infinity.monginis.custom_class;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.infinity.monginis.R;

public class TextViewBoldFont extends AppCompatTextView {
    public TextViewBoldFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewBoldFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewBoldFont(Context context) {
        super(context);
        init();
    }


    private void init() {
        setTypeface(ResourcesCompat.getFont(getContext(),  R.font.poppins_semibold), 1);
    }

}
