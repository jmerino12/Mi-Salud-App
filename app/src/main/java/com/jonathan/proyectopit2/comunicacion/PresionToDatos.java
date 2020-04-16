package com.jonathan.proyectopit2.comunicacion;

public class PresionToDatos {

    String Sistolica, Diastolica;

    public PresionToDatos(String sistolica, String diastolica) {
        Sistolica = sistolica;
        Diastolica = diastolica;
    }

    public PresionToDatos() {
    }

    public String getSistolica() {
        return Sistolica;
    }

    public void setSistolica(String sistolica) {
        Sistolica = sistolica;
    }

    public String getDiastolica() {
        return Diastolica;
    }

    public void setDiastolica(String diastolica) {
        Diastolica = diastolica;
    }
}
