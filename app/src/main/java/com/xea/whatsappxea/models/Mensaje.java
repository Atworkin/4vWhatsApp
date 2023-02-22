package com.xea.whatsappxea.models;


import java.io.Serializable;
import java.util.Objects;

public class Mensaje implements Serializable {

    private String id;
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
    public String getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(String idConversacion) {
        this.idConversacion = idConversacion;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(id, mensaje.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
