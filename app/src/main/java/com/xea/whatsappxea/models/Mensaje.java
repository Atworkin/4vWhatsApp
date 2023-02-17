package com.xea.whatsappxea.models;


public class Mensaje{

    private int id;
    private String idRemitente;
    private String contenido;

    public Mensaje() {
    }

    public Mensaje(String idRemitente, String contenido) {
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
