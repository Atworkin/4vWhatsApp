package com.xea.whatsappxea.models;


import com.xea.whatsappxea.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mensaje extends RealmObject {

    @PrimaryKey
    private int id;

    private String idRemitente;
    private String contenido;

    public Mensaje() {
    }

    public Mensaje(String idRemitente, String contenido) {
        this.id = MyApplication.mensajeID.incrementAndGet() ;
        this.idRemitente = idRemitente;
        this.contenido = contenido;
    }


    public int getId() {
        return id;
    }

    public String getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(String idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
