package com.xea.whatsappxea.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.activities.ChatActivity;
import com.xea.whatsappxea.adapter.RecyclerConversaciones;
import com.xea.whatsappxea.models.Conversacion;

import java.util.ArrayList;
import java.util.List;


public class ShowFragment extends Fragment {

    private RecyclerView recyclerConversaciones;
    private FirebaseFirestore db;
    private List<Conversacion> conversaciones;
    private String userLogged;
    private RecyclerConversaciones adapter;

    public ShowFragment() {
        // Required empty public constructor
    }

    public ShowFragment(String userLogged) {
        this.userLogged = userLogged;
    }

    //ACTUALIZAR FRAGMENT AL AÑADIR CONVERSACION

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);

        db = FirebaseDB.getInstance();
        conversaciones = new ArrayList<>();

        adapter = new RecyclerConversaciones(conversaciones, (string, position) -> {
            Intent intent = new Intent(view.getContext(), ChatActivity.class);
            Conversacion conv = conversaciones.get(position);
            intent.putExtra("userLogged", userLogged);
            intent.putExtra("nombreConversacion", conv.getNombre());
            intent.putExtra("userClickedTlf", conv.getTelfUser());
            intent.putExtra("idGrupo", conv.getId());
            startActivity(intent);
        });
        recyclerConversaciones = view.findViewById(R.id.recyclerChats);
        recyclerConversaciones.setAdapter(adapter);
        recyclerConversaciones.setLayoutManager(new GridLayoutManager(view.getContext(), 1));

        CollectionReference conversacionesRef = db.collection("conversaciones");


        conversacionesRef
                .whereArrayContains("participantes", userLogged)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            List<String> participantes = (List<String>) documentSnapshot.get("participantes");
                            Conversacion newConversacion = documentSnapshot.toObject(Conversacion.class);
                            CollectionReference participantesRef = db.collection("users");
                            if (newConversacion.getIsGroup()) {
                                newConversacion.setId(documentSnapshot.getId());
                                conversaciones.add(newConversacion);
                                adapter.notifyItemInserted(conversaciones.size() - 1);

                            } else {
                                for (String participante : participantes) {
                                    if (!participante.equals(userLogged)) {
                                        participantesRef.document(participante)
                                                .get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        String nombre = (String) documentSnapshot.get("name");
                                                        String tlf = (String) documentSnapshot.get("telNumber");
                                                        Number photoNumber = (Number) documentSnapshot.get("photo");
                                                        int photo = photoNumber.intValue();
                                                        newConversacion.setNombre(nombre);
                                                        newConversacion.setPhoto(photo);
                                                        newConversacion.setTelfUser(tlf);
                                                        conversaciones.add(newConversacion);

                                                        adapter.notifyItemInserted(conversaciones.size() - 1);

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                    }
                                                });
                                        break;
                                    }
                                }
                            }
                        }
                        }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


        return view;
    }



}
