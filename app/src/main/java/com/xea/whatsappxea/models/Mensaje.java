package com.xea.whatsappxea.models;


import java.io.Serializable;

public class Mensaje implements Serializable {

    private int id;
    private String idRemitente;
    private String contenido;
    private String idConversacion;

    public Mensaje() {
    }

    public Mensaje(String idRemitente, String idConversacion,String contenido) {
        this.idRemitente = idRemitente;
        this.contenido = contenido;
        this.idConversacion = idConversacion;
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
