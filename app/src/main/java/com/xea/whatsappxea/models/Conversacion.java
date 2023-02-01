package com.xea.whatsappxea.models;


public class conversacion {

    private int[] participantes;
    private String nombreC;

    public conversacion() {
    }

    public conversacion(int[] participantes) {
        this.participantes = participantes;
        this.nombreC = "";
    }

    public conversacion(int[] participantes, String nombreC) {
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
