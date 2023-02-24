package com.xea.whatsappxea.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.xea.whatsappxea.R;
import android.os.Bundle;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        String userLogged = (String) getIntent().getStringExtra("userLogged");
    }
}