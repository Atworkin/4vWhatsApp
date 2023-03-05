package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.RecyclerChat;
import com.xea.whatsappxea.adapter.RecyclerContactos;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.Mensaje;
import com.xea.whatsappxea.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    FirebaseFirestore db;
    String conversacionActualId;
    User userClicked;
    RecyclerView recyclerChat;
    RecyclerChat recyclerDataAdapter;
    List<Mensaje> result;
    Button btnSend;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = FirebaseDB.getInstance();
        result = new ArrayList<>();

        String userTel = (String) getIntent().getStringExtra("userClickedTlf");
        String userLogged = (String) getIntent().getStringExtra("userLogged");
        String nombreConversacion = (String) getIntent().getStringExtra("nombreConversacion");
        String idGrupo = (String) getIntent().getStringExtra("idGrupo");
        List<String> participantes = Arrays.asList(userTel, userLogged);

        if (idGrupo != null) {
            conversacionActualId = idGrupo;
            recyclerChat = findViewById(R.id.recyclerChat);
            Query getMensajesRef = db.collection("mensajes")
                    .whereEqualTo("idConversacion", conversacionActualId).orderBy("fecha");
            getMensajesRef.get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            result = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Mensaje mensaje = documentSnapshot.toObject(Mensaje.class);
                                mensaje.setId(documentSnapshot.getId());


                                result.add(mensaje);
                            }

                            recyclerDataAdapter = new RecyclerChat(result, userLogged, true);

                            recyclerChat.setAdapter(recyclerDataAdapter);
                            recyclerChat.setLayoutManager(new GridLayoutManager(ChatActivity.this, 1));


                            CollectionReference mensajesRef = db.collection("mensajes");

                            mensajesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                                    if (error != null) {
                                        Toast.makeText(ChatActivity.this, "Error al obtener los mensajes:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                        if (dc.getType() == DocumentChange.Type.ADDED) {

                                            DocumentSnapshot mensaje = dc.getDocument();
                                            Mensaje nuevoMensaje = mensaje.toObject(Mensaje.class);
                                            nuevoMensaje.setId(mensaje.getId());

                                            if (nuevoMensaje.getIdConversacion().equals(conversacionActualId) && !result.contains(nuevoMensaje)) {
                                                result.add(nuevoMensaje);
                                                recyclerDataAdapter.notifyItemInserted(result.size() - 1);

                                            }
                                        }
                                    }
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        } else {

            CollectionReference tablaRefConversaciones = db.collection("conversaciones");
            List<String> participantesRev = new ArrayList<>(participantes);
            Collections.reverse(participantesRev);

            Query query = tablaRefConversaciones.whereIn("participantes", Arrays.asList(participantes, participantesRev));

            query.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (!querySnapshot.isEmpty()) {
                        DocumentSnapshot docSnapshot = querySnapshot.getDocuments().get(0);
                        conversacionActualId = docSnapshot.getId();
                    } else {
                        Conversacion conversacionNueva = new Conversacion(participantes);
                        tablaRefConversaciones.add(conversacionNueva).addOnSuccessListener(documentReference -> {
                            conversacionActualId = documentReference.getId();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }
                    recyclerChat = findViewById(R.id.recyclerChat);
                    Query getMensajesRef = db.collection("mensajes")
                            .whereEqualTo("idConversacion", conversacionActualId).orderBy("fecha");
                    getMensajesRef.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    result = new ArrayList<>();
                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        Mensaje mensaje = documentSnapshot.toObject(Mensaje.class);
                                        mensaje.setId(documentSnapshot.getId());
                                        result.add(mensaje);
                                    }

                                    recyclerDataAdapter = new RecyclerChat(result, userLogged, false);

                                    recyclerChat.setAdapter(recyclerDataAdapter);
                                    recyclerChat.setLayoutManager(new GridLayoutManager(ChatActivity.this, 1));


                                    CollectionReference mensajesRef = db.collection("mensajes");

                                    mensajesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                                            if (error != null) {
                                                Toast.makeText(ChatActivity.this, "Error al obtener los mensajes:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                                return;
                                            }

                                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                                if (dc.getType() == DocumentChange.Type.ADDED) {

                                                    DocumentSnapshot mensaje = dc.getDocument();
                                                    Mensaje nuevoMensaje = mensaje.toObject(Mensaje.class);
                                                    nuevoMensaje.setId(mensaje.getId());

                                                    if (nuevoMensaje.getIdConversacion().equals(conversacionActualId) && !result.contains(nuevoMensaje)) {
                                                        result.add(nuevoMensaje);
                                                        recyclerDataAdapter.notifyItemInserted(result.size() - 1);

                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Exception e = task.getException();
                    Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
        btnSend = (Button) findViewById(R.id.btnSendMsg);
        txtMessage = (EditText) findViewById(R.id.txtMsg);
        CollectionReference tablaRefMensajes = db.collection("mensajes");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contentMsg = String.valueOf(txtMessage.getText());

                DocumentReference docRef = db.collection("users").document(userLogged);
                docRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String username = document.getString("name");
                            Mensaje newMensaje = new Mensaje(userLogged, conversacionActualId, contentMsg);
                            newMensaje.setNombre(username);
                            tablaRefMensajes.add(newMensaje).addOnSuccessListener(documentReference -> {
                                txtMessage.setText("");
                            }).addOnFailureListener(e -> {
                                Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                        }
                    }
                });
            }
        });
    }
}
