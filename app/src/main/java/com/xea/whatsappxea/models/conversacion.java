package com.xea.whatsappxea.models;


public class Conversacion {

    private int[] participantes;
    private String nombreC;

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
}
