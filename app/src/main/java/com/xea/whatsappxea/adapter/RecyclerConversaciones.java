package com.xea.whatsappxea.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.models.Conversacion;

import java.util.List;

public class RecyclerConversaciones extends RecyclerView.Adapter<RecyclerConversaciones.RecyclerDataHolder>{
    private List<Conversacion> contactosList;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void OnItemClick(String string, int position);
    }


    public RecyclerConversaciones(List<Conversacion> conversacionList ,OnItemClickListener listener){
        this.contactosList = conversacionList;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {
        Conversacion asign = contactosList.get(position);

        /**
        FirebaseFirestore db = FirebaseDB.getInstance();
        CollectionReference conversacionesRef = db.collection("conversaciones");
        CollectionReference participantesRef = db.collection("users");
        conversacionesRef.whereArrayContains("participantes", userLogged)
                .limit(1)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            List<String> participantes = (List<String>) documentSnapshot.get("participantes");
                            for (String participante : participantes) {
                                if (!participante.equals(userLogged)) {
                                    participantesRef.document(participante)
                                            .get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    String nombre = (String) documentSnapshot.get("nombre");

                                                }
                                            });
                                    break;
                                }
                            }
                        }
                    }
                });**/
        holder.assignData(asign.getNombre(),asign.getPhoto(),listener);
    }

    @Override
    public int getItemCount() {
        return contactosList.size();
    }

    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversaciones_lista,parent,false);
        return new RecyclerDataHolder(view);

    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder {
        private ImageView fotoUsuario;
        private TextView nombre;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            fotoUsuario = (ImageView)itemView.findViewById(R.id.imgPhoto);
            nombre = (TextView)itemView.findViewById(R.id.txtNombre);

        }

        public void assignData(String nombre,int img, OnItemClickListener listener) {
            this.nombre.setText(nombre);
            this.fotoUsuario.setImageResource(img);
            itemView.setOnClickListener(view -> listener.OnItemClick(nombre,getAdapterPosition()));

        }
    }

}
