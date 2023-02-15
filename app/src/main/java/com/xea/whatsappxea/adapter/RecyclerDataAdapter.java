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
import com.xea.whatsappxea.models.User;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.RecyclerDataHolder> {

    private ArrayList<Conversacion> listData;
    private OnItemClickListener itemListener;
    private int position;
    private String telEmisor;

    public RecyclerDataAdapter(ArrayList<Conversacion> listData, String telEmisor, OnItemClickListener listener){
        this.listData = listData;
        this.itemListener = listener;
        this.telEmisor = telEmisor;
    }

    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversaciones_lista,null, false);
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {
        holder.assignData(listData.get(position),itemListener);


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView nombre;
        TextView mensaje;

        public RecyclerDataHolder(@NonNull final View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.imgPhoto);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            mensaje = (TextView) itemView.findViewById(R.id.txtMensaje);

        }

        public void assignData(final Conversacion s, final OnItemClickListener onItemClickListener) {
           // s.getParticipantes();

            //photo.setImageResource();


            if(!s.getNombreC().equals("")){
                nombre.setText(s.getNombreC());
                photo.setImageResource(s.getPhoto());
            }else{
               for (User u : s.getParticipantes()){
                   if(!u.equals(new User("","",telEmisor))){
                       nombre.setText(u.getName());
                       photo.setImageResource(u.getPhoto());
                   }
               }


            }
            mensaje.setText(s.getLastMensaje().getContenido());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(s);

                }
            });

        }

    }


    public interface OnItemClickListener{
        void onItemClick(Conversacion name);
    }
}
