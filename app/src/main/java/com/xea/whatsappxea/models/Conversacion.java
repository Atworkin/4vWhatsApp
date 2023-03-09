package com.xea.whatsappxea.models;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Conversacion implements Serializable {
    private List<String> participantes;
    private String nombre;
    private int photo;
    private String telfUser;
    private String id;

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

    public String getTelfUser() {
        return telfUser;
    }

    public void setTelfUser(String telfUser) {
        this.telfUser = telfUser;
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
        if (!(o instanceof Conversacion)) return false;
        Conversacion that = (Conversacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
