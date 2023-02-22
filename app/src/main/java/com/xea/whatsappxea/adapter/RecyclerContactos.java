package com.xea.whatsappxea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.models.User;
import java.util.List;

public class RecyclerContactos extends RecyclerView.Adapter<RecyclerContactos.RecyclerDataHolder>{
    private List<User> contactosList;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void OnItemClick(String string, int position);
    }


    public RecyclerContactos(List<User> contactosList, OnItemClickListener listener){
        this.contactosList = contactosList;

        this.listener = listener;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {
        User asign = contactosList.get(position);
        holder.assignData(asign.getName(),asign.getPhoto(),listener);
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

        public void assignData(String nombre, int img,OnItemClickListener listener) {
            this.nombre.setText(nombre);
            this.fotoUsuario.setImageResource(img);
            itemView.setOnClickListener(view -> listener.OnItemClick(nombre,getAdapterPosition()));

        }
    }

}
