package com.xea.whatsappxea.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xea.whatsappxea.R;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }
}