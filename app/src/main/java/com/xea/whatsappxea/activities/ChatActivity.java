package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.Mensaje;
import com.xea.whatsappxea.models.User;
import java.util.Arrays;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    FirebaseFirestore db;
    String conversacionActualId;
    User userClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = FirebaseDB.getInstance();

        String userTel =(String) getIntent().getStringExtra("user");
        String userLogged = (String) getIntent().getStringExtra("userLogged");
        List<String> participantes = Arrays.asList(userTel, userLogged);
        if (userTel != null) {
            DocumentReference docRef = db.collection("users").document(userTel);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                                userClicked = document.toObject(User.class);
                        }
                    }
                     else {
                        Toast.makeText(ChatActivity.this, "Error de conexión con la base de datos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        CollectionReference tablaRefConversaciones = db.collection("conversaciones");

        Query query = tablaRefConversaciones.whereEqualTo("participantes", participantes);

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
            } else {
                Exception e = task.getException();
                Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        CollectionReference mensajesRef = FirebaseFirestore.getInstance().collection("mensajes");

        // Llamar al método addSnapshotListener para establecer un listener en la referencia de la colección de mensajes
        mensajesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(ChatActivity.this, "Error al obtener los mensajes:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DocumentChange dc : snapshot.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        // Manejar la adición de una nueva fila (row) a la colección de mensajes
                        DocumentSnapshot mensaje = dc.getDocument();
                        Mensaje nuevoMensaje = mensaje.toObject(Mensaje.class);
                        Toast.makeText(ChatActivity.this, "Nuevo mensaje: " + mensaje.getData(), Toast.LENGTH_SHORT).show();
                        // aquí podrías actualizar tu UI para mostrar el nuevo mensaje
                    }
                }
            }
        });

        }
    }
