package com.xea.whatsappxea.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.activities.ChatActivity;
import com.xea.whatsappxea.activities.ContactosActivity;
import com.xea.whatsappxea.adapter.RecyclerContactos;
import com.xea.whatsappxea.adapter.RecyclerDataAdapter;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.User;

import java.util.ArrayList;
import java.util.List;



public class AddFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerDataAdapter adapter;
    private List<Conversacion> listData;
    private FirebaseFirestore db;
    List<Conversacion> result;
    public AddFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
/**
        recyclerView = view.findViewById(R.id.recyclerViewConversaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Conversacion> conversaciones = realm.where(Conversacion.class).findAll();
        List<Conversacion> list = new ArrayList<>(conversaciones);
        listData = list;
     **/
/**
        db = FirebaseDB.getInstance();
        recyclerView = view.findViewById(R.id.recyclerViewConversaciones);

        Query usersRef = db.collection("users").whereNotEqualTo("telNumber",userLogged);
        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        result = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            User user = documentSnapshot.toObject(User.class);
                            result.add(user);
                        }
                        RecyclerContactos recyclerDataAdapter = new RecyclerContactos(result,(string, position)->{
                            Intent intent = new Intent(ContactosActivity.this, ChatActivity.class);
                            User user = result.get(position);
                            intent.putExtra("user",user.getTelNumber());
                            intent.putExtra("userLogged",userLogged);
                            startActivity(intent);
                        });

                        recyclerViewUsers.setAdapter(recyclerDataAdapter);
                        recyclerViewUsers.setLayoutManager(new GridLayoutManager(ContactosActivity.this,1));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ContactosActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                    }
                });
        **/
        return  view;
    }
}