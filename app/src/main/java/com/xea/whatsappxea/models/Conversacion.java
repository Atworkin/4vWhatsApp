package com.xea.whatsappxea.models;


import java.util.ArrayList;

public class Conversacion {

    private int[] participantes;
    private String nombreC;
    private ArrayList<Mensaje> mensajes;

    public Conversacion() {
    }

    public Conversacion(int[] participantes) {
        this.participantes = participantes;
        this.nombreC = "";
    }

    public Conversacion(int[] participantes, String nombreC) {
        this(participantes);
        this.nombreC = nombreC;
    }



    public int[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int[] participantes) {
        this.participantes = participantes;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public void addMensajes(Mensaje mensaje) {
        this.mensajes.add(mensaje);
    }

    public Mensaje getLastMensajes() {
        return mensajes.get(mensajes.size()-1);
    }
}
