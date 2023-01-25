package com.xea.whatsappxea.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asier_echauri.toolbarasier.R;
import com.asier_echauri.toolbarasier.models.Hobby;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.RecyclerDataHolder> {

    private ArrayList<Hobby> listData;
    private OnItemClickListener itemListener;
    private int position;

    public RecyclerDataAdapter(ArrayList<Hobby> listData, OnItemClickListener listener){
        this.listData = listData;
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hobbys_lista,null, false);
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

        TextView tipo;
        TextView nombre;
        TextView nota;

        public RecyclerDataHolder(@NonNull final View itemView) {
            super(itemView);
            tipo = (TextView) itemView.findViewById(R.id.tipo);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            nota = (TextView) itemView.findViewById(R.id.nota);

        }

        public void assignData(final Hobby s, final OnItemClickListener onItemClickListener) {
            tipo.setText(s.getTipo());
            nombre.setText(s.getTitulo());
            nota.setText(s.getNota());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(s);

                }
            });

        }

    }


    public interface OnItemClickListener{
        void onItemClick(Hobby name);
    }
}
