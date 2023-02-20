package com.xea.whatsappxea.models;


import java.io.Serializable;
import java.util.List;

public class Conversacion implements Serializable {
    private List<String> participantes;
    //private int photo;

    public Conversacion() {
    }

    public Conversacion(List<String> participantes) {
        this.participantes = participantes;
    }
    public Conversacion(List<String> participantes,int photo) {
        this(participantes);
        //this.photo = photo;
    }


    //public int getPhoto() {
    //    return photo;
    //}

    // public void setPhoto(int photo) {
    //    this.photo = photo;
    //}

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

}
