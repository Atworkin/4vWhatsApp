package com.xea.whatsappxea.models;


import com.xea.whatsappxea.app.MyApplication;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Conversacion extends RealmObject {

    @PrimaryKey
    private int id;

    private String[] participantes;
    private String nombreC;
    private ArrayList<Mensaje> mensajes;

    public Conversacion() {
    }

    public Conversacion(String[] participantes) {
        this.id = MyApplication.conversacionID.incrementAndGet() ;
        this.mensajes = new ArrayList<Mensaje>();
        this.participantes = participantes;
        this.nombreC = "";
    }

    public Conversacion(String[] participantes, String nombreC) {
        this(participantes);
        this.nombreC = nombreC;
    }



    public String[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String[] participantes) {
        this.participantes = participantes;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public void addMensajes(Mensaje mensaje) {
        this.mensajes.add(mensaje);
    }

    public Mensaje getLastMensajes() {
        if (this.mensajes.isEmpty()){
            return new Mensaje("","");
        }else {
            return mensajes.get(mensajes.size() - 1);
        }
    }
}
