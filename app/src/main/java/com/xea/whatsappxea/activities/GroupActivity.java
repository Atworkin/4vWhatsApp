package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.RecyclerContactos;
import com.xea.whatsappxea.models.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ArrayList<String> users;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        String userLogged = (String) getIntent().getStringExtra("userLogged");
        db = FirebaseDB.getInstance();

        Query usersRef = db.collection("users").whereNotEqualTo("telNumber",userLogged);
        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        users = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            User user = documentSnapshot.toObject(User.class);
                            users.add(user.getName()+"\n"+user.getTelNumber().toString());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GroupActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                    }
                });
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(GroupActivity.this,R.layout.activity_group,users));

    }

}