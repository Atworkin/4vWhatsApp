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
    List<Conversacion> result;


    public ShowFragment() {
        // Required empty public constructor
    }

    //ACTUALIZAR FRAGMENT AL AÑADIR CONVERSACION

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);

        db = FirebaseDB.getInstance();
        //Pasar por parametro el usuario loggeado
        CollectionReference conversacionesRef = db.collection("conversaciones");
        CollectionReference participantesRef = db.collection("users");

        db.collection("conversaciones")
                .whereArrayContains("participantes", "123")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Conversacion> conversaciones = new ArrayList<>();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            List<String> participantes = (List<String>) documentSnapshot.get("participantes");
                            for (String participante : participantes) {
                                if (!participante.equals("123")) {
                                    participantesRef.document(participante)
                                            .get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    String nombre = (String) documentSnapshot.get("name");
                                                    Conversacion newConversacion = documentSnapshot.toObject(Conversacion.class);
                                                    newConversacion.setNombre(nombre);
                                                    conversaciones.add(newConversacion);

                                                    // Aquí puedes actualizar tu RecyclerView con la lista de conversaciones obtenidas
                                                    // por ejemplo, crear un nuevo adapter y establecerlo en tu RecyclerView:
                                                    RecyclerConversaciones adapter = new RecyclerConversaciones(conversaciones, (string, position) -> {
                                                        Intent intent = new Intent(view.getContext(), ChatActivity.class);
                                                        Conversacion conv = conversaciones.get(position);
                                                        intent.putExtra("user", "user.getTelNumber()");
                                                        intent.putExtra("userLogged", "userLogged");
                                                        startActivity(intent);
                                                    });
                                                    recyclerConversaciones = view.findViewById(R.id.recyclerChats);
                                                    recyclerConversaciones.setAdapter(adapter);
                                                    recyclerConversaciones.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Aquí manejas el fallo al obtener el nombre del participante
                                                }
                                            });
                                    break;
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Aquí manejas el fallo al obtener la conversación
                    }
                });

        return  view;
    }
}