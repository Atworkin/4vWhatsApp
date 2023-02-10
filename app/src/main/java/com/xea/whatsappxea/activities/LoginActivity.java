package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.dialog.RegisterPopupDialog;
import com.xea.whatsappxea.models.User;


public class LoginActivity extends AppCompatActivity {

    Button btnRegister, btnAcceder, btnVolverPopup, btnRegisterPopup;
    RegisterPopupDialog dialogRegister;
    FirebaseFirestore db;
    EditText txtNompreRegister, txtTelefonoRegister, txtPasswordRegister, txtTelefono, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        db = FirebaseFirestore.getInstance();

        txtTelefono = (EditText) findViewById(R.id.txtTelefonoLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);

        btnRegister = (Button) findViewById(R.id.btnRegistrarse);
        btnAcceder = (Button) findViewById(R.id.btnAcceder);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogRegister = new RegisterPopupDialog(LoginActivity.this);
                dialogRegister.show();

                txtNompreRegister = (EditText) dialogRegister.getWindow().findViewById(R.id.txtNombrePopup);
                txtTelefonoRegister = (EditText) dialogRegister.getWindow().findViewById(R.id.txtTelfPopup);
                txtPasswordRegister = (EditText) dialogRegister.getWindow().findViewById(R.id.txtPassPopup);
                btnRegisterPopup = (Button) dialogRegister.getWindow().findViewById(R.id.btnRegisterPopup);
                btnVolverPopup = (Button) dialogRegister.getWindow().findViewById(R.id.btnVolverPopup);

                btnRegisterPopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String nombre = txtNompreRegister.getText().toString();
                        String telefono = txtTelefonoRegister.getText().toString();
                        String password = txtPasswordRegister.getText().toString();

                        User newUser = new User(nombre, password, telefono);
                        if (nombre.isEmpty() || telefono.isEmpty() || password.isEmpty()) {
                            Toast.makeText(LoginActivity.this, "Por favor ingrese su número de teléfono y contraseña", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        CollectionReference usersTable = db.collection("users");
                        DocumentReference row = usersTable.document(newUser.getTelNumber());

                        row.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (!document.exists()) {
                                        row.set(newUser)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(LoginActivity.this, "Te has registrado correctamente", Toast.LENGTH_SHORT).show();
                                                        dialogRegister.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(LoginActivity.this, "Error al intentar registrarte", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(LoginActivity.this, "El número de teléfono ya esta en uso", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                });
                btnVolverPopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogRegister.dismiss();
                    }
                });
            }
        });

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = txtTelefono.getText().toString();
                String pass = txtPassword.getText().toString();
                if (tel.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor ingrese su número de teléfono y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                DocumentReference row = db.collection("users").document(tel);
                row.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String storedPassword = document.getString("password");
                                if (storedPassword.equals(pass)) {
                                    User userLogged = document.toObject(User.class);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("userLogged", userLogged);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(LoginActivity.this, "Numero de teléfono sin registrar", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Error de conexión con la base de datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
}