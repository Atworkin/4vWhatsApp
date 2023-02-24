package com.xea.whatsappxea.models;


import java.io.Serializable;
import java.util.List;

public class Conversacion implements Serializable {
    private List<String> participantes;
    private String nombre;
    private int photo;
    private boolean isGroup;

    public Conversacion() {
    }

    public Conversacion(List<String> participantes) {
        this.participantes = participantes;
    }
    public Conversacion(List<String> participantes,int photo) {
        this(participantes);
        this.photo = photo;
    }
    public Conversacion(List<String> participantes,String nombre,int photo) {
        this.participantes = participantes;
        this.nombre = nombre;
        this.photo = photo;
    }

    public boolean getIsGroup(){return isGroup;}
    public void setIsGroup(boolean isGroup){
        this.isGroup = isGroup;
    }
    public int getPhoto() {
      return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

}
