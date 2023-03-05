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
import com.xea.whatsappxea.models.Mensaje;

import java.util.List;

public class RecyclerChat extends RecyclerView.Adapter<RecyclerChat.RecyclerDataHolder> {

    private List<Mensaje> listData;
    private String userLogged;
    private boolean isGroup;
    private String nombre;
    private String idRemitente;
    public RecyclerChat(List<Mensaje> listData,String userLogged,boolean isGroup) {
        this.listData = listData;
        this.userLogged = userLogged;
        this.isGroup = isGroup;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (isGroup){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mensaje_grp,parent,false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mensaje,parent,false);
        }
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerChat.RecyclerDataHolder holder, int position) {
        Mensaje asign = listData.get(position);
        holder.assignData(asign.getContenido(), asign.getIdRemitente(),asign.getNombre());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class RecyclerDataHolder extends RecyclerView.ViewHolder {

        TextView mensaje;
        TextView nombre;
        LinearLayout linearMsg;
        LinearLayout linearBg;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            if (isGroup){
                mensaje = (TextView) itemView.findViewById(R.id.msg_grp);
                linearMsg = (LinearLayout)itemView.findViewById(R.id.linear_grp);
                linearBg = (LinearLayout)itemView.findViewById(R.id.linearbg_grp);
                nombre = (TextView)itemView.findViewById(R.id.msg_nombre);
            }else{
                mensaje = (TextView) itemView.findViewById(R.id.msg);
                linearMsg = (LinearLayout)itemView.findViewById(R.id.linearMsg);
            }
        }

        public void assignData(String content,String idRemitente,String nombremsg) {
            this.mensaje.setText(content);
            if(this.nombre != null){
                this.nombre.setText(nombremsg);
            }
            if(!userLogged.equals(idRemitente)){
                if (isGroup){
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearBg.getLayoutParams();
                    params.gravity = Gravity.START;
                    linearBg.setBackgroundResource(R.drawable.msg_left);
                    nombre.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    nombre.setText(nombremsg);
                }else{
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mensaje.getLayoutParams();
                    params.gravity = Gravity.START;
                    mensaje.setBackgroundResource(R.drawable.msg_left);

                }
            }
        }
    }
}
