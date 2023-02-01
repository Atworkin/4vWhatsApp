package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xea.whatsappxea.R;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText number, name, pass;
    Button Registerbtn;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();
        number = findViewById(R.id.txtNumeroRegister);
        name = findViewById(R.id.txtNombreRegister);
        pass = findViewById(R.id.txtPassRegister);
        Registerbtn = findViewById(R.id.btnTryRegister);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strName = name.getText().toString();
                String strNumber = number.getText().toString();
                String strPass = pass.getText().toString();

                Map<String,Object> user = new HashMap<>();
                user.put("Nombre",strName);
                user.put("Numero",strNumber);
                user.put("Password",strPass);



                db.collection("Usuarios")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(RegisterActivity.this,"Te has registrado correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                                Toast.makeText(RegisterActivity.this,"Número de telefono ya registrado",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
}