package com.xea.whatsappxea.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.Mensaje;

import java.util.ArrayList;

public class RecyclerChat extends RecyclerView.Adapter<RecyclerChat.RecyclerDataHolder> {

private ArrayList<Mensaje> listData;
private OnItemClickListener itemListener;
private int position;


public RecyclerChat(ArrayList<Mensaje> listData, OnItemClickListener listener){
        this.listData = listData;
        this.itemListener = listener;

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

    TextView nombre;
    TextView mensaje;

    public RecyclerDataHolder(@NonNull final View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.txtNombre);
        mensaje = (TextView) itemView.findViewById(R.id.txtMensaje);

    }

    public void assignData(final Mensaje s, final RecyclerChat.OnItemClickListener onItemClickListener) {
        // s.getParticipantes();

        //photo.setImageResource();


    }

}


public interface OnItemClickListener{
    void onItemClick(Conversacion name);

}}
