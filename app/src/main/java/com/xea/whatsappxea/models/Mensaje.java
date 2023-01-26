package com.xea.whatsappxea.models;


public class Mensaje  {



    private int idConversacion;
    private int idRemitente;
    private String contenido;

    public Mensaje() {
    }

    public Mensaje(int idConversacion, int idRemitente, String contenido) {
        this.idConversacion = idConversacion;
        this.idRemitente = idRemitente;
        this.contenido = contenido;
    }



    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
        this.idConversacion = idConversacion;
    }

    public int getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(int idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
