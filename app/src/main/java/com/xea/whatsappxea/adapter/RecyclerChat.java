package com.xea.whatsappxea.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.Mensaje;
import com.xea.whatsappxea.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecyclerChat extends RecyclerView.Adapter<RecyclerChat.RecyclerDataHolder> {

    private List<Mensaje> listData;
    private String userLogged;


    public RecyclerChat(List<Mensaje> listData,String userLogged) {
        this.listData = listData;
        this.userLogged = userLogged;
    }

    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mensaje,parent,false);
        return new RecyclerDataHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerChat.RecyclerDataHolder holder, int position) {
        Mensaje asign = listData.get(position);
        holder.assignData(asign.getContenido(), asign.getIdRemitente());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class RecyclerDataHolder extends RecyclerView.ViewHolder {

        TextView mensaje;
        LinearLayout linearMsg;
        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            mensaje = (TextView) itemView.findViewById(R.id.msg);
            linearMsg = (LinearLayout)itemView.findViewById(R.id.linearMsg);
        }

        public void assignData(String content,String idRemitente) {
            this.mensaje.setText(content);
            if(!userLogged.equals(idRemitente)){
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mensaje.getLayoutParams();
                params.gravity = Gravity.START;
                mensaje.setBackgroundResource(R.drawable.msg_left);
            }
        }

    }
}
