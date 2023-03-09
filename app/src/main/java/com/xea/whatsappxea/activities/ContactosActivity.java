package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.RecyclerContactos;
import com.xea.whatsappxea.adapter.RecyclerDataAdapter;
import com.xea.whatsappxea.models.User;

import java.util.ArrayList;
import java.util.List;

public class ContactosActivity extends AppCompatActivity {

    List<User> result;
    RecyclerView recyclerViewUsers;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        String userLogged = (String) getIntent().getStringExtra("userLogged");
        db = FirebaseDB.getInstance();

        recyclerViewUsers = findViewById(R.id.recyclerViewContactos);
        Query usersRef = db.collection("users").whereNotEqualTo("telNumber", userLogged);
        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        result = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            User user = documentSnapshot.toObject(User.class);
                            result.add(user);
                        }
                        RecyclerContactos recyclerDataAdapter = new RecyclerContactos(result, (string, position) -> {
                            Intent intent = new Intent(ContactosActivity.this, ChatActivity.class);
                            User user = result.get(position);
                            intent.putExtra("userClickedTlf", user.getTelNumber());
                            intent.putExtra("userLogged", userLogged);
                            startActivity(intent);
                            finish();
                        });
                        recyclerViewUsers.setAdapter(recyclerDataAdapter);
                        recyclerViewUsers.setLayoutManager(new GridLayoutManager(ContactosActivity.this, 1));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ContactosActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}