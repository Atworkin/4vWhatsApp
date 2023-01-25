package com.xea.whatsappxea.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Hobby extends RealmObject {

    @PrimaryKey
    private int id;

    private String titulo;
    private String tipo;
    private int nota;
    private String descripcion;

    public Hobby(){}

    public Hobby(String titulo, String tipo, int nota, String descripcion){
        this.titulo = titulo;
        this.tipo = tipo;
        this.nota = nota;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
