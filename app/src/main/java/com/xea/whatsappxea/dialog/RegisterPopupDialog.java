package com.xea.whatsappxea.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.xea.whatsappxea.R;

public class RegisterPopupDialog extends Dialog {

        public RegisterPopupDialog(Context context) {
            super(context);
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.popup_register);
            getWindow().setBackgroundDrawableResource(R.drawable.rounded_shape_popup);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

