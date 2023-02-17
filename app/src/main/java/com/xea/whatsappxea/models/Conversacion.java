package com.xea.whatsappxea.models;


import com.xea.whatsappxea.app.MyApplication;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Conversacion {

    private int id;
    private List<User> participantes;
    private String nombreC;
    private List<Mensaje> mensajes;
    private int photo;

    public Conversacion() {
    }

    public Conversacion(List<User> participantes) {
        this.participantes = participantes;
        this.nombreC = "";
        this.photo = 0;
    }

    public Conversacion(List<User> participantes, String nombreC,int photo) {
        this(participantes);
        this.nombreC = nombreC;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }


    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public List<User> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<User> participantes) {
        this.participantes = participantes;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public void addMensajes(Mensaje mensaje) {
        this.mensajes.add(mensaje);
    }

    public Mensaje getLastMensaje() {
        if (this.mensajes.isEmpty()){
            return new Mensaje("","");
        }else {
            return mensajes.get(mensajes.size() - 1);
        }
    }


}
